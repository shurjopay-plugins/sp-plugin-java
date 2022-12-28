# ![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png) Java plugin

Official shurjoPay Java plugin for merchants or service providers to connect with [**_shurjoPay_**](https://shurjopay.com.bd) Payment Gateway ``` v2.1 ``` developed and maintained by [_**ShurjoMukhi Limited**_](https://shurjomukhi.com.bd).

This plugin can be used with any java application or framework (e.g. Spring).
Also it makes easy for developers to integrate with shurjoPay ``` v2.1 ``` with calling three methods only:

1. **makePayment**: create and send payment request
1. **verifyPayment**: verify payment status at shurjoPay
1. **paymentStatus**: Check payment details and status

Also reduces many of the things that you had to do manually:

- Handles http request and errors.
- JSON serialization and deserialization.
- Authentication during initiating and verifying of payments.
## Audience
This document is intended for the technical personnel of merchants and service providers who wants to integrate our online payment gateway using java plugin provided by _**shurjoMukhi Limited**_.
## How to use this shurjoPay plugin
To integrate the shurjoPay Payment Gateway in your Java project do the following tasks sequentially.
### Step 1: Add dependency to your project corresponding Maven or Gradle:<br>
**Maven**
```xml
<dependency>
    <groupId>bd.com.shurjopay.plugin</groupId>
    <artifactId>sp-plugin-java</artifactId>
    <version>1.0.0</version>
</dependency>
```
**Gradle**
```gradle
implementation 'bd.com.shurjopay.plugin:sp-plugin-java:1.0.0'
```
**Download Jar**<br>
Go to [Java Plugin repositories](https://search.maven.org/search?q=shurjopay%20java%20plugin) then download jar file.<br>

> **_Attention:_ [_shurjoMukhi Limited_](https://shurjomukhi.com.bd/) offers another plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with <u>_Spring Application_. Visit our [Spring plugin](https://github.com/shurjopay-plugins/sp-plugin-spring)**<hr>

### Step 2: Create shurjopay.properties inside project resource path.
Properties file contains four fields ``` SP_USER, SP_PASS, SHURJOPAY_API, SP_CALLBACK ``` to configure shurjoPay and 2 other fields to configure _shurjoPay_ logging are ```  SPLOG_PATH, SPLOG_FILE. ```
```properties
#merchant username and password provided by shurjoPay author
SP_USER = <merchant_username>
SP_PASS = <merchant_password>

#shurjoPay API's base path for sandbox
SHURJOPAY_API = https://sandbox.shurjopayment.com/api/

#callback URL is used for cancel payment or success payment
SP_CALLBACK = https://sandbox.shurjopayment.com/response

#shurjoPay plug-in log file location
SPLOG_PATH= /var/log
SPLOG_FILE = sp-java-plugin.log
```
- Visit [_shurjopay.properties_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/test/resources/shurjopay.properties.sample) for example.
- If you add ```logback-classic``` dependency in your application then shurjoPay will maintain separate log file to track shurjoPay errors only.

### Step 3: After that, you can initiate payment request to shurjoPay using below code example.
- Request example
 ```java 
	// Initialize shurjopay
	Shurjopay shurjopay = new Shurjopay();

	// Prepare payment request to initiate payment
	PaymentReq request = new PaymentReq();
	request.setPrefix("sp");
	request.setAmount(10.00);
	request.setCustomerOrderId("sp315689");
	request.setCurrency("BDT");
	request.setCustomerName("Dummy");
	request.setCustomerAddress("Dhaka");
	request.setCustomerPhone("01766666666");
	request.setCustomerCity("Dhaka");
	request.setCustomerPostCode("1212");
	request.setCustomerEmail("dummy@gmail.com");

	// Calls first method to initiate a payment
	shurjopay.makePayment(request);
 ```
- Returns [_POJO_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/main/java/com/shurjomukhi/model/PaymentRes.java) corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/test/resources/sample-msg/payment-res.json)

### Step 4: Payment verification can be done after each transaction with shurjopay transaction id. 
- Call verify method
 ```java
	shurjopay.verifyPayment(:=spTxnId)
 ```
- Returns [_POJO_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/main/java/com/shurjomukhi/model/VerifiedPayment.java) corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/test/resources/sample-msg/verification-res.json)
### Congrats! Now you are ready to use the java plugin to seamlessly integrate with shurjoPay to make your payment system easy and smooth.

## Want to see shurjoPay visually?
Run the JUnit test to see shurjopay plugin in action. These tests will run on selenium browser and will provide the complete experience. Just download source and run the command ```mvnw test``` in Windows and ```./mvnw test``` in Linux from plugin root path.
## References
1. [Spring example application](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/spring-app-java-plugin) and [Java example application](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/java-app-java-plugin) showing usage of the java plugin.
2. [Sample applications and projects](https://github.com/shurjopay-plugins/sp-plugin-usage-examples) in many different languages and frameworks showing shurjopay integration.
3. [shurjoPay Postman site](https://documenter.getpostman.com/view/6335853/U16dS8ig) illustrating the request and response flow using the sandbox system.
4. [shurjopay Plugins](https://github.com/shurjopay-plugins) home page on github
## License
This code is under the [MIT open source License](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/LICENSE).
#### Please [contact](https://shurjopay.com.bd/#contacts) with shurjoPay team for more detail!
<hr>
Copyright ©️2022 Shurjomukhi Limited.
