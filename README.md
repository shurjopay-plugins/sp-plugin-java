![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# shurjoPay plugin (JAVA)
[_**ShurjoMukhi Limited**_](https://shurjomukhi.com.bd/) developed plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with java application. The information contained in this document is proprietary and confidential to [_**ShurjoMukhi Limited**_](https://shurjomukhi.com.bd/), for the product [**_shurjoPay_**](https://shurjopay.com.bd/). _shurjoPay plugin_ helps merchants and service providers to integrate easily. Plugin provides 3 functions:
- **Make Payment**
- **Verify payment order**
- **Check verified order status**
## Documentation
You can download source from our [github source](https://github.com/shurjopay-plugins/sp-plugin-java).
You can pull binary/jar from the central Maven repositories:

```xml
<dependency>
    <groupId>bd.com.shurjopay.plugin</groupId>
    <artifactId>sp-plugin-java</artifactId>
    <version>1.0.0</version>
</dependency>
```
> **_NOTE:_ [_ShurjoMukhi Limited_](https://shurjomukhi.com.bd/) offers another plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with <u>_Spring Application_. Visit our [Spring plugin](https://github.com/shurjopay-plugins/sp-plugin-spring)** 
### Implementation
First of all developer need to configure shurjopay.properties & logback.xml file which should be located on merchant app's class path. Properties file contains four fields ``` SP_USER, SP_PASS, SHURJOPAY_API, SP_CALLBACK, SPLOG_PATH, SPLOG_FILE ```
- **shurjopay.properties example**
```properties
#merchant username and password
SP_USER = sp_sandbox
SP_PASS = pyyk97hu&6u6
#shurjoPay API's base path for sandbox
SHURJOPAY_API = https://sandbox.shurjopayment.com/api/
#callback URL is used for cancel payment or success payment
SP_CALLBACK = https://sandbox.shurjopayment.com/response
#shurjoPay plug-in log file location
SPLOG_PATH= /home/alamin/git/sp-plugin-java/tmp
SPLOG_FILE = shurjopay-plugin.log 
```
- **logback.xml example**
```xml 
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE xml>
<configuration>
	<property resource="shurjopay.properties"/>
	<appender name="FILE" class="ch.qos.logback.core.FileAppender">
		<file>${SPLOG_PATH}/${SPLOG_FILE}</file>
		<append>true</append>
		<encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
			<pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
		</encoder>
	</appender>
	<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <logger name="bd.com.shurjopay.plugin" level="INFO" additivity="false">
        <appender-ref ref="FILE"/>
    </logger>
	<root level="INFO">
		<appender-ref ref="FILE" />
		<appender-ref ref="CONSOLE" />
	</root>
</configuration> 
```
### Make Payment: 
Merchants and service providers can make payment by calling this service. Developer should call _makePayment()_ method with payment request parameter. _shurjoPay_ needs some information to perform creating payment request. So that, this service method requires request payment object param. After performing with this, service returns response object contains payment URL and customer information.
- **Example**
	- Request payment
	 ```java 
		request.setAmount("10");
		request.setOrderId("sp215689");
		request.setCurrency("BDT");
		request.setCustomerName("John");
		request.setCustomerAddr("Holding no-N/A, Road-16, Gulshan-1, Dhaka");
		request.setCustomerPhn("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
	 ```
	- Response payment
	 ```java
	 	paymentUrl= <generated payment url by shurjoPay gateway>
		amount=10
		currency=BDT
		spOrderId=sp32aad7c6dad7a
		customerOrderId=sp215689
		customerName=John
		customerAddr=Holding no-N/A, Road-16, Gulshan-1, Dhaka
		customerPhn=01766666666
		customerCity=Dhaka
		customerEmail=null
		clintIp=102.101.1.1
		intent=sale
		transactionStatus=Initiated
	 ```
### Verify payment: 
After a succussful payment merchants or service providers get verify payment order by redirecting callback url. Developer must call _verifyPayment()_ method with shurjopay order id (shurjopay transaction id) parameter. sp order id (shurjopay transaction id) is provided by payment response named spOrderId. A successful successful payment returns payment verification object.
- **Example**
	- Request payment verification of a order
	 ```java
	 	Parameter: spOrderId
		Parameter type: String
	 ```
	- Response order
	 ```java
	 	orderId=sp32aad7c6dad7a
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
After a succussful payment (sp_code=1000) and verify payment merchants or service providers can check payment status. Developer should call _checkPaymentStatus()_ method with order id (shurjopay transaction id) parameter. sp order id (shurjopay transaction id) is provided by order response named orderId. A successfully verified payment with orderId returns a payment object.
- **Example**
	- Request verification of a order
	 ```java
	 	Parameter: spOrderId
		Parameter type: String
	 ```
	- Response order
	 ```java
	 	orderId=sp32aad7c6dad7a
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
## License
This code is under the [MIT open source License](http://www.opensource.org/licenses/mit-license.php).
#### Please [contact](https://shurjopay.com.bd/#contacts) for more detail!
<hr>
Copyright ©️2022 Shurjomukhi Limited.
