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
> **_NOTICE:_ [_ShurjoMukhi Limited_](https://shurjomukhi.com.bd/) offers another plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with <u>_Spring Application_. Visit our [Spring plugin](https://github.com/shurjopay-plugins/sp-plugin-spring)** 

Our sample projects with implementation of **java plugin** are available. Please visit [Java Project](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/java-app-java-plugin), [Spring Project](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/java-app-java-plugin). <br/>
First of all developer needs to configure shurjopay.properties & logback.xml file to use _shurjoPay_. Properties file contains four fields ``` SP_USER, SP_PASS, SHURJOPAY_API, SP_CALLBACK ``` and 2 other fields to configure _shurjoPay_ logging are ```  SPLOG_PATH, SPLOG_FILE. ```
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
	 ```
	- You will get java POJO corresponding this [_JSON_](https://github.com/shurjopay-plugins/sp-plugin-java/blob/develop/src/test/resources/sample-msg/payment-res.json)

### Verify payment: 
After a succussful payment merchants or service providers get shurjoPay transaction id by redirect callback url. Developer must call _verifyPayment()_ feature with shurjopay transaction id as a param. shurjopay transaction id is provided by payment response named spTxnId. A successful payment returns payment verification object.
- **Example**
	- Request payment verification of a order
	 ```java
	 	Parameter: spTxnId
		Parameter type: String
	 ```
	- Response
	 ```java
	 	spTxnId=sp32aad7c6dad00
		currency=BDT
		amount=10
		payableAmount=10
		discountAmount=null
		discpercent=0
		usdAmt=0
		usdRate=0
		method=null
		spMsg=initiated
		spCode=1068
		name=John
		email=john@example.com
		address=Holding no-N/A, Road-16, Gulshan-1, Dhaka
		city=Dhaka
		value1=value1
		value2=value2
		value3=value3
		value4=value4
	 ```
### Check payment status: 
After a succussfully verified payment (sp_code=1000), merchants or service providers can check payment status. Developer need to call _checkPaymentStatus()_ feature with shurjoPay transaction id param. shurjoPay transaction id is provided by verified payment response named spTxnId. A successfully verified payment with spTxnId returns a payment object.
- **Example**
	- Request verification of a order
	 ```java
	 	Parameter: spTxnId
		Parameter type: String
	 ```
	- Response order
	 ```java
	 	spTxnId=sp32aad7c6dad00
		currency=BDT
		amount=10
		payableAmount=10
		discountAmount=null
		discpercent=0
		usdAmt=0
		usdRate=0
		method=null
		spMsg=initiated
		spCode=1068
		customerName=John
		customerEmail=john@example.com
		customerAddress=Holding no-N/A, Road-16, Gulshan-1, Dhaka
		city=Dhaka
		value1=value1
		value2=value2
		value3=value3
		value4=value4
	 ```
## License
This code is under the [MIT open source License](http://www.opensource.org/licenses/mit-license.php).
#### Please [contact](https://shurjopay.com.bd/#contacts) with shurjoPay team for more detail!
<hr>
Copyright ©️2022 Shurjomukhi Limited.
