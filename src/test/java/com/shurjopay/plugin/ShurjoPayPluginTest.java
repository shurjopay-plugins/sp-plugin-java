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
	void testMakePayment() {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		System.out.println(paymentRes);
		assertNotNull(paymentRes.getPaymentUrl(), () -> "Making Payment returns null");
	}

	@Test
	@Order(2)
	@DisplayName("For verifying order: ")
	void testVerifyOrder() {
		VerifiedOrder order = shurjopay.verifyOrder(paymentRes.getSpOrderId());
		System.out.println(order);
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	@Test
	@Order(3)
	@DisplayName("For checking order status: ")
	void testGetPaymentStatus() {
		VerifiedOrder order = shurjopay.checkPaymentStatus(paymentRes.getSpOrderId());
		System.out.println(order);
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	private PaymentReq getPaymentReq() {
		PaymentReq request = new PaymentReq();

		request.setPrefix("sp");
		request.setStoreId("1");
		request.setAmount("10");
		request.setOrderId("sp315689");
		request.setCurrency("BDT");
		request.setCustomerName("Maharab kibria");
		request.setCustomerAddr("Dhaka");
		request.setCustomerPhn("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
		request.setClintIp("102.101.1.1");
		return request;
	}
}
