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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shurjopay.plugin.model.PaymentReq;
import com.shurjopay.plugin.model.PaymentRes;
import com.shurjopay.plugin.model.VerifiedPaymentRes;

/**
 * @author Al - Amin
 * @since 2022-06-14
 */
@DisplayName("Testing plugin =>")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ShurjoPayTest {

	private ShurjoPay shurjopay;
	private PaymentRes paymentRes;
	private static final Logger log = LoggerFactory.getLogger(ShurjoPayTest.class);

	@BeforeAll
	void setup() {
		shurjopay = new ShurjoPay();
	}

	@Test
	@Order(1)
	@DisplayName("For making shurjoPay payment: ")
	void testMakePayment() {
		log.info("Testing make payment");
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		assertNotNull(paymentRes.getPaymentUrl(), () -> "Making Payment returns null");
	}

	@Test
	@Order(2)
	@DisplayName("For verifying order: ")
	void testVerifyOrder() {
		VerifiedPaymentRes order = shurjopay.verifyPayment(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	@Test
	@Order(3)
	@DisplayName("For checking order status: ")
	void testGetPaymentStatus() {
		VerifiedPaymentRes order = shurjopay.checkPaymentStatus(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	private PaymentReq getPaymentReq() {
		PaymentReq request = new PaymentReq();

		request.setPrefix("sp");
		request.setAmount(10.00);
		request.setOrderId("sp315689");
		request.setCurrency("BDT");
		request.setCustomerName("Maharab kibria");
		request.setCustomerAddress("Dhaka");
		request.setCustomerPhone("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
		request.setCustomerEmail("al@gmail.com");
		request.setClientIp("102.101.1.1");
		return request;
	}
}
