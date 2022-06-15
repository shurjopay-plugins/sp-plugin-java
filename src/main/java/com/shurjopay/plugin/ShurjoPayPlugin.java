package com.shurjopay.plugin;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shurjopay.plugin.dto.CheckoutReq;
import com.shurjopay.plugin.dto.CheckoutRes;
import com.shurjopay.plugin.dto.TokenReq;
import com.shurjopay.plugin.dto.ShurjoPayToken;
import com.shurjopay.plugin.dto.VerifiedOrder;
import com.shurjopay.plugin.enums.EndPoints;

/**
 * 
 * @author Al - Amin
 * @since 2022-06-13
 */
public class ShurjoPayPlugin {
	Properties spProps = PropertiesReader.instance().getProperties();
	String authString;
	ShurjoPayToken authToken;

	/**
	 * Return authorization token for shurjoPay payment gateway system.
	 * Setup shurjopay.properties file .
	 * 
	 * @return
	 */
	public ShurjoPayToken authenticate() {
		var tokenReq = new TokenReq();
		tokenReq.setUsername(getProperty("username"));
		tokenReq.setPassword(getProperty("password"));
		var basePath = getPath(getProperty("env"));

		var client = getClient();

		try {
			var mapper = new ObjectMapper();
			var httpBody = mapper.writeValueAsString(tokenReq);
			var request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.GET_TOKEN.getValue())))
					.POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json")
					.build();

			var response = client.send(request, new JsonBodyHandler<>(ShurjoPayToken.class));
			return response.body().get();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public CheckoutRes makePayment(CheckoutReq req) {
		// check for existence of token. if not found authenticate first.
		var basePath = getPath(getProperty("env"));

		var client = getClient();

		try {
			var mapper = new ObjectMapper();
			var httpBody = mapper.writeValueAsString(req);

			var request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.SECRET_PAY.getValue())))
					.POST(HttpRequest.BodyPublishers.ofString(httpBody)).header("Content-Type", "application/json")
					.build();

			var response = client.send(request, new JsonBodyHandler<>(CheckoutRes.class));
			return response.body().get();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<VerifiedOrder> verifyOrder(String orderId, String token, String tokenType) {
		// check for existence of token. if not found authenticate first.

		var basePath = getPath(getProperty("env"));
		var client = getClient();

		try {
			var orderMap = new HashMap<String, String>();
			orderMap.put("order_id", orderId);
			var mapper = new ObjectMapper();
			var httpBody = mapper.writeValueAsString(orderMap);

			var authToken = getFormattedToken(token, tokenType);

			var request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.VERIFICATION.getValue())))
					.header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(httpBody))
					.header("Content-Type", "application/json").build();

			var response = client.send(request, new JsonBodyHandler<>(VerifiedOrder[].class));

			return Arrays.asList(response.body().get());

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<VerifiedOrder> getPaymentStatus(String orderId, String token, String tokenType) {
		// check for existence of token. if not found authenticate first.

		var basePath = getPath(getProperty("env"));

		var client = getClient();

		try {
			var orderMap = new HashMap<String, String>();
			orderMap.put("order_id", orderId);
			var mapper = new ObjectMapper();
			var httpBody = mapper.writeValueAsString(orderMap);
			var authToken = getFormattedToken(token, tokenType);

			var request = HttpRequest.newBuilder(URI.create(basePath.concat(EndPoints.PMNT_STAT.getValue())))
					.header("Authorization", authToken).POST(HttpRequest.BodyPublishers.ofString(httpBody))
					.header("Content-Type", "application/json").build();

			var response = client.send(request, new JsonBodyHandler<>(VerifiedOrder[].class));
			return Arrays.asList(response.body().get());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		}

	}

	private HttpClient getClient() {
		return HttpClient.newHttpClient();
	}

	private String getPath(String env) {

		return env.equals("sandbox") ? getProperty("sandbox-path") : getProperty("live-path");
	}

	private String getProperty(String key) {
		return spProps.getProperty(key);
//		return PropertiesReader.instance().getProperties().getProperty(key);
	}

	private String getFormattedToken(String token, String tokenType) {
		return tokenType.concat(" ").concat(token);
	}
}
