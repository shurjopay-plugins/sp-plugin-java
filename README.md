![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# ShurjoPay Online Payment API Integration:
This document has been prepared by shurjoMukhi Limited to enable the online merchants to integrate shurjoPay payment gateway. The information contained in this document is proprietary and confidential to shurjoMukhi Limited, for the product **_shurjoPay_**.
### Audience
This document is intended for the technical personnel of merchants and service providers that want to integrate a new online payment gateway using java plugin provided by **_shurjoPay_**.
### Prerequisite
	◼️ shurjoPay Version 2.0
## Integration
**_shurjoPay_** Online payment gateway has several APIs which need to be integrated by merchants for accessing different services.
The available services are:
- Authenticate users
- Making payment
- Verifying payment order
- Checking verified payment order status
## shurjoPay plugin (JAVA)
_ShurjoMukhi Limited_ developed plugin for integration with java based. _shurjoPay plugin_ helps merchants and service providers to integrate easity by using this plugin. Plugin provides 3 services mainly such as
- **Make Payment**
- **Verify payment order**
- **Check verified order status**
## How to implement
### Before All:
First of all developer have to configure shurjopay.properties. Properties file contains four fields ``` username, password, shurjopay-api, callback-url ```
- **Example**
``` username = sp_sandbox
password = pyyk97hu&6u6
shurjopay-api = https://sandbox.shurjopayment.com/api/
callback-url = https://sandbox.shurjopayment.com/response 
```
### Make Payment: 
Merchants and service providers can make payment by calling this service. Developer should call makePayment() method with payment request parameter. _shurjoPay_ needs some information to perform creating payment request. So that, this service requires request payment object. After performing with this, service returns response object contains payment URL and customer information.
- **Example**
	- Request payment
	 ``` 
	 	request.setPrefix("sp");
		request.setStoreId("1");
		request.setAmount("10");
		request.setOrderId("sp215689");
		request.setCurrency("BDT");
		request.setCustomerName("John");
		request.setCustomerAddr("Holding no-N/A, Road-16, Gulshan-1, Dhaka");
		request.setCustomerPhn("01766666666");
		request.setCustomerCity("Dhaka");
		request.setCustomerPostCode("1212");
		request.setClintIp("123.456.1.1");
	 ```
	- Response payment
	 ``` 
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
		spCode=null
		message=null
	 ```
### Verify payment order: 
After a succussful payment merchants or service providers have to verify payment order. Developer must call verifyOrder() method with shurjopay order id parameter. sp order id is provided by payment response named spOrderId. A successful verification returns order object.
- **Example**
	- Request verification of a order
	 ``` 
	 	Parameter: spOrderId
		Parameter type: String
	 ```
	- Response order
	 ``` 
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
### Check verified order status: 
After a succussful payment and verify order merchants or service providers can check payment order status. Developer must call checkPaymentStatus() method with shurjopay order id parameter. sp order id is provided by order response named orderId. A successfully verified orderId returns a order object.
- **Example**
	- Request verification of a order
	 ``` 
	 	Parameter: spOrderId
		Parameter type: String
	 ```
	- Response order
	 ``` 
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
# More...
https://shurjopay.com.bd/#contacts
<hr>
Copyright ©️2022 Shurjomukhi Limited.
