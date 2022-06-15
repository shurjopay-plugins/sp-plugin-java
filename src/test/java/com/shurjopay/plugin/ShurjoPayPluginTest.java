package com.shurjopay.plugin;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import com.shurjopay.plugin.model.PaymentReq;
import com.shurjopay.plugin.model.PaymentRes;
import com.shurjopay.plugin.model.VerifiedOrder;

/**
 * @author Al - Amin
 * @since 2022-06-14
 */
@DisplayName("Testing plugin =>")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ShurjoPayPluginTest {

	private ShurjoPayPlugin shurjopay;
	private PaymentRes paymentRes;

	@BeforeAll
	void setup() {
		shurjopay = new ShurjoPayPlugin();
	}

	@Test
	@Order(1)
	@DisplayName("For making shurjoPay payment: ")
	void testCheckoutPayment() {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		assertNotNull(paymentRes.getCheckoutUrl(), () -> "Making Payment returns null");
	}

	@Test
	@Order(2)
	@DisplayName("For verifying order: ")
	void testVerifyOrder() {
		VerifiedOrder order = shurjopay.getPaymentStatus(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	@Test
	@Order(3)
	@DisplayName("For checking order status: ")
	void testGetPaymentStatus() {
		VerifiedOrder order = shurjopay.getPaymentStatus(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	private PaymentReq getPaymentReq() {
		PaymentReq req = new PaymentReq();

		req.setPrefix("sp");
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
