package com.shurjopay.plugin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	@DisplayName("For making shurjoPay payment (Success payment test): ")
	void testMakePayment() throws InterruptedException, ShurjopayException {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		String paymentUrl = paymentRes.getPaymentUrl();

		fillupAndSubmitPaymentForm(paymentUrl, false);
		
		assertNotNull(paymentUrl, () -> "Making Payment returns null");
	}

	@Test
	@Order(2)
	@DisplayName("For verifying order (Success payment test): ")
	void testVerifyOrder() throws ShurjopayException {
		VerifiedPayment order = shurjopay.verifyPayment(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}

	@Test
	@Order(3)
	@DisplayName("For checking order status (Success payment test): ")
	void testCheckPaymentStatus() throws ShurjopayException {
		VerifiedPayment order = shurjopay.checkPaymentStatus(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
	}
	
	@Test
	@Order(4)
	@DisplayName("For making shurjoPay payment (Failed payment test): ")
	void testMakePaymentFailed() throws ShurjopayException, InterruptedException {
		PaymentReq req = getPaymentReq();
		paymentRes = shurjopay.makePayment(req);
		String paymentUrl = paymentRes.getPaymentUrl();

		fillupAndSubmitPaymentForm(paymentUrl, true);
		assertNotNull(paymentUrl, () -> "Making Payment returns null");
	}

	@Test
	@Order(5)
	@DisplayName("For verifying order (Failed payment test): ")
	void testVerifyOrderFailed() {
		Throwable exception = assertThrows(ShurjopayException.class, () -> shurjopay.verifyPayment(paymentRes.getSpOrderId()));
		assertEquals("Code: 1005 Message: Bank transaction failed.", exception.getMessage());
	}

	@Test
	@Order(6)
	@DisplayName("For checking order status (Failed payment test): ")
	void testCheckPaymentStatusFailed() throws ShurjopayException {
		VerifiedPayment order = shurjopay.checkPaymentStatus(paymentRes.getSpOrderId());
		assertNotNull(order.getOrderId(), () -> "Order is not found.");
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
	
	
	private void fillupAndSubmitPaymentForm(String url, boolean shouldFail) throws InterruptedException {
		WebDriver driver = getChrome();
		driver.get(url);
		Thread.sleep(3 * 1000);
		
		/** Fills up card number */
		WebElement webEl = driver.findElement(By.id("input-38"));
		webEl.sendKeys("444444444444");
		
		/** Fills up expire month */
		webEl = driver.findElement(By.id("mm"));
		webEl.sendKeys("12");
		
		/** Fills up expire year */
		webEl = driver.findElement(By.id("yyyy"));
		webEl.sendKeys("2024");
		
		/** Fills up cvv */
		webEl = driver.findElement(By.id("cvc_cvv"));
		webEl.sendKeys("123");
		
		/** Fills up card holder name */
		webEl = driver.findElement(By.id("card_holder_name"));
		webEl.sendKeys("Robot");
		
		/** click index 1 for SUCCESS or index 0 for FAILED button */
		webEl = shouldFail ? driver.findElements(By.className("paynow_btn")).get(0) : driver.findElements(By.className("paynow_btn")).get(1);;
		webEl.click();
		
		Thread.sleep(2 * 1000);
		driver.quit();
	}
	
	private WebDriver getChrome() {
		System.setProperty("webdriver.chrome.driver", "/home/alamin/git/sp-plugin-java/src/test/resources/drivers/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(false);
		
		WebDriver driver = new ChromeDriver(options);
		return driver;
	}

}
