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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.shurjopay.plugin.model.PaymentReq;
import com.shurjopay.plugin.model.PaymentRes;
import com.shurjopay.plugin.model.VerifiedPayment;

/**
 * @author Al - Amin
 * @since 2022-06-14
 */
@DisplayName("Testing plugin =>")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class ShurjopayTest {

	private Shurjopay shurjopay;
	private PaymentRes paymentRes;
	
	@BeforeAll
	void setup() {
		shurjopay = new Shurjopay();
	}

	@Test
	@Order(1)
	@DisplayName("For making shurjoPay payment: ")
	void testMakePayment() throws InterruptedException {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		WebDriver driver = getChrome();
		driver.get(paymentRes.getPaymentUrl());
		
		Thread.sleep(30 * 1000);
		assertNotNull(paymentRes.getPaymentUrl(), () -> "Making Payment returns null");
	}

	@Test
	@Order(2)
	@DisplayName("For verifying order: ")
	void testVerifyOrder() {
		VerifiedPayment order = shurjopay.verifyPayment(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	@Test
	@Order(3)
	@DisplayName("For checking order status: ")
	void testCheckPaymentStatus() {
		VerifiedPayment order = shurjopay.checkPaymentStatus(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	private WebDriver getChrome() {
		System.setProperty("webdriver.chrome.driver", "D:/Office/git/sp-plugin-java/src/test/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}
	
	private PaymentReq getPaymentReq() {
		PaymentReq request = new PaymentReq();

		request.setPrefix("dummy");
		request.setAmount(10.00);
		request.setOrderId("sp315689");
		request.setCurrency("BDT");
		request.setCustomerName("Dummy");
		request.setCustomerAddress("Dhaka");
		request.setCustomerPhone("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
		request.setCustomerEmail("al@gmail.com");
		return request;
	}
}
