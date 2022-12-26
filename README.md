![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# shurjoPay plugin (JAVA)
[_**shurjoMukhi Limited**_](https://shurjomukhi.com.bd/) developed plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with java application. _shurjoPay plugin_ helps merchants and service providers to integrate easily.<br/>
Plugin provides 3 features:
- **Make Payment**
- **Verify payment**
## Audience
This document is intended for the technical personnel of merchants and service providers who wants to integrate our online payment gateway using java plugin provided by _**shurjoMukhi Limited**_.
## How to use shurjoPay java plugin
You can download source from our [github source](https://github.com/shurjopay-plugins/sp-plugin-java).
You can pull binary/jar from central Maven repositories:<br>
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
> **_Attention:_ [_shurjoMukhi Limited_](https://shurjomukhi.com.bd/) offers another plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with <u>_Spring Application_. Visit our [Spring plugin](https://github.com/shurjopay-plugins/sp-plugin-spring)**<hr>

Our sample projects with implementation of **java plugin** are available. Please visit [Java Project](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/java-app-java-plugin), [Spring Project](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/java-app-java-plugin). <br/>
Developer needs to configure shurjopay.properties & logback.xml file to use _shurjoPay_. Properties file contains four fields ``` SP_USER, SP_PASS, SHURJOPAY_API, SP_CALLBACK ``` to configure shurjoPay and 2 other fields to configure _shurjoPay_ logging are ```  SPLOG_PATH, SPLOG_FILE. ```
- Visit [_shurjopay.properties_](https://github.com/shurjopay-plugins/sp-plugin-java/tree/develop/src/main/resources/sample-properties) for **shurjopay.properties example.**
- Visit [_logback.xml_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/main/resources/logback.xml.sample) for **logback.xml example.**

### Initialize shurjoPay:
Now time to initialize shurjoPay to perform with features. For that
```java
Shurjopay shurjopay = new Shurjopay();
```
Above code will be initialezed a _shurjoPay_ instance.
### Make Payment: 
After initializing developer should call _makePayment()_ method with payment request object parameter.
- **Example**
	- Request example
	 ```java 
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
	- Returns POJO corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/test/resources/sample-msg/payment-res.json)

### Verify payment: 
Developer must call _verifyPayment()_ method with shurjopay transaction id as a string param.
- **Example**
	- Call verify method
	 ```java
	 	shurjopay.verifyPayment(:=spTxnId)
	 ```
	- Returns POJO corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/test/resources/sample-msg/verification-res.json)
## Want to see shurjoPay visually?
Run the java unit test to see shurjopay plugin in action. These tests will run on selenium browser and will provide the complete experience. Just download [source](https://github.com/shurjopay-plugins/sp-plugin-java) and run ```ShurjopayTest``` class.
Have a look to our other [shurjoPay plugins](https://github.com/shurjopay-plugins)
## License
This code is under the [MIT open source License](http://www.opensource.org/licenses/mit-license.php).
#### Please [contact](https://shurjopay.com.bd/#contacts) with shurjoPay team for more detail!
<hr>
Copyright ©️2022 Shurjomukhi Limited.
