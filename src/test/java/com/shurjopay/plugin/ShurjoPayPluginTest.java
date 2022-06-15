package com.shurjopay.plugin;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.shurjopay.plugin.dto.CheckoutReq;
import com.shurjopay.plugin.dto.CheckoutRes;
import com.shurjopay.plugin.dto.ShurjoPayToken;
import com.shurjopay.plugin.dto.VerifiedOrder;

/**
 * @author Al - Amin
 * @since 2022-06-14
 */
@DisplayName("Testing plugin =>")
@TestInstance(Lifecycle.PER_CLASS)
class ShurjoPayPluginTest {

	private ShurjoPayPlugin shurjopay;
	private ShurjoPayToken token;

	@BeforeAll
	void setup() {
		shurjopay = new ShurjoPayPlugin();
		token = shurjopay.authenticate();
	}

	@Test
	@DisplayName("Result for getToken(): ")
	void testGetToken() {
		assertNotNull(token.getToken(), () -> "Token couldn't generated");
	}

	@Test
	@DisplayName("Result for checkoutPayment(): ")
	void testCheckoutPayment() {
		CheckoutReq req = getCheckoutReq();
		CheckoutRes res = shurjopay.makePayment(req);

		assertNotNull(res.getCheckoutUrl(), () -> "Checkout Payment returns null");
	}

	@Test
	@DisplayName("Result for verifyOrder(): ")
	void testVerifyOrder() {
		List<VerifiedOrder> orders = shurjopay.getPaymentStatus("sp62a5dff32855a", token.getToken(), token.getTokenType());
		
		assertTrue(orders.size() > 0,
				() -> "Order is not found.");
	}

	@Test
	@DisplayName("Result for getPaymentStatus(): ")
	void testGetPaymentStatus() {
		List<VerifiedOrder> orders = shurjopay.getPaymentStatus("sp62a5dff32855a", token.getToken(), token.getTokenType());

		assertTrue(orders.size()> 0, 
				() -> "Place valid params");
	}

	private CheckoutReq getCheckoutReq() {
		CheckoutReq req = new CheckoutReq();

		req.setPrefix("sp");
		req.setAuthToken(token.getToken());
		req.setReturnUrl("https://www.sandbox.shurjopayment.com/response");
		req.setCancelUrl(req.getReturnUrl());
		req.setStoreId("1");
		req.setAmount("10");
		req.setOrderId("sp315689");
		req.setCurrency("BDT");
		req.setCustomerName("Maharab kibria");
		req.setCustomerAddr("Dhaka");
		req.setCustomerPhn("01766666666");
		req.setCustomerCity("Dhaka");
		req.setCustomerPostCode("1212");
		req.setClintIp("102.101.1.1");
		return req;
	}

}
