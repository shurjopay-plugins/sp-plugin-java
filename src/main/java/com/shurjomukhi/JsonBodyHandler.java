package com.shurjomukhi;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * HTTP request, response body handler to handle JSON to POJO and vice versa
 * @author Al-Amin
 * @since 2022-09-15
 */
public class JsonBodyHandler<W> implements HttpResponse.BodyHandler<Supplier<W>> {
	private Class<W> wClass;
	
	/**
     * Instantiates JsonBodyHandler
     * @param wClass
     */
	public JsonBodyHandler(Class<W> wClass) {
		this.wClass = wClass;
	}

	@Override
	public HttpResponse.BodySubscriber<Supplier<W>> apply(HttpResponse.ResponseInfo responseInfo) {
		return asJSON(wClass);
	}

	/**
	 * @param targetType
	 * @return mapped JSON object
	 */
	public static <W> HttpResponse.BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();
        return HttpResponse.BodySubscribers.mapping(upstream, inputStream -> toSupplierOfType(inputStream, targetType));
    }

	/**
	 * @param inputStream
	 * @param targetType
	 * @return mapped JSON object
	 */
    public static <W> Supplier<W> toSupplierOfType(InputStream inputStream, Class<W> targetType) {
        return () -> {
            try (InputStream stream = inputStream) {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(stream, targetType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
}