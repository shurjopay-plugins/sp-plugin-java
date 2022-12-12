![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# shurjoPay plugin (JAVA)
[_**ShurjoMukhi Limited**_](https://shurjomukhi.com.bd/) developed plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with java application. The information contained in this document is proprietary and confidential to [_**ShurjoMukhi Limited**_](https://shurjomukhi.com.bd/), for the product [**_shurjoPay_**](https://shurjopay.com.bd/). This document is intended for the technical personnel of merchants and service providers who wants to integrate our online payment gateway using java plugin provided by [_**ShurjoMukhi Limited**_](https://shurjomukhi.com.bd/). <br/>
_shurjoPay plugin_ helps merchants and service providers to integrate easily. Plugin provides 3 features:
- **Make Payment**
- **Verify payment**
- **Check verified payment status**
## Documentation
You can download source from our [github source](https://github.com/shurjopay-plugins/sp-plugin-java).
You can pull binary/jar from central Maven repositories:
- Maven
```xml
<dependency>
    <groupId>bd.com.shurjopay.plugin</groupId>
    <artifactId>sp-plugin-java</artifactId>
    <version>1.0.0</version>
</dependency>
```
- Gradle
```gradle
implementation 'bd.com.shurjopay.plugin:sp-plugin-java:1.0.0'
```
> **_NOTE:_ [_ShurjoMukhi Limited_](https://shurjomukhi.com.bd/) offers another plugin for integrating most popular [**_shurjoPay_**](https://shurjopay.com.bd/) payment gateway with <u>_Spring Application_. Visit our [Spring plugin](https://github.com/shurjopay-plugins/sp-plugin-spring)** 
## Implementation
Our sample projects with implementation of **java plugin** are available. Please visit [Java Project](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/java-app-java-plugin), [Spring Project](https://github.com/shurjopay-plugins/sp-plugin-usage-examples/tree/dev/java-app-java-plugin). <br/>
First of all developer needs to configure shurjopay.properties & logback.xml file which should be located on merchant app's class path. Properties file contains four fields ``` SP_USER, SP_PASS, SHURJOPAY_API, SP_CALLBACK, SPLOG_PATH, SPLOG_FILE ```
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
### Initialize shurjoPay:
Now time to initialize shurjoPay to perform with features. For that
```java
Shurjopay shurjopay = new Shurjopay();
```
Above code will be initialezed a _shurjoPay_ instance.
### Make Payment: 
Merchants and service providers can make payment by calling this service. Developer should call _makePayment()_ feature with payment request parameter. _shurjoPay_ needs some information to perform creating payment request. So that, this service feature requires request payment object param. After performing with this, service returns response object contains payment URL and customer information.
- **Example**
	- Request payment
	 ```java 
		request.setAmount("10");
		request.setCustomerOrderId("sp3156xx");
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
		spTxnId=sp32aad7c6dad00
		customerOrderId=sp2156xx
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
