![image](https://user-images.githubusercontent.com/57352037/155895117-523cfb9e-d895-47bf-a962-2bcdda49ad66.png)

# ShurjoPay-Java Integration Steps:

## Prerequisite
To integrate ShurjoPay you need few credentials to access shurjopay:
```
:param prefix: Any string not more than 5 characters. It distinguishes the stores of a merchant.
:param currency: ISO format,(only BDT and USD are allowed).
:param return_url: Merchant should provide a GET Method return url to verify users initiated transaction status. 
:param cancel_url: Merchant should provide a cancel url to redirect the user if he/she cancels the transaction in midway. 
:param client_ip: User's ip
:param username: Merchant's Username provided by shurjopay.
:param password: Merchant's Password provided by shurjopay.
:param post_address: Live shurjopay version 2 URL.
```


> 📝 **NOTE** For ShurjoPay version 2 live engine integration all necessary credential will be given to merchant after subscription completed on shurjoPay gateway.

---
```
Step 1:
Install the "ShurjoPay-Java.jar" into your project library. 

Step 2:
import shurjopay.Settings;
import shurjopay.ShurjoPay;
import org.json.JSONObject;
import java.io.IOException;

Step 3:
You have to set your live credentials by creating a JSONObject and put the necessary values as shown below and execute the Settings.configure(Boolean sandbox, JSONObject configCredential) method.
Put sandbox to false and set the configCredentials after getting your live API credentials from ShurjoPay.

Example (For Sandbox)::
        Boolean sandbox = true;
        JSONObject configCredentials = new JSONObject();
        configCredentials.put("username", "");
        configCredentials.put("password", "");
        configCredentials.put("getApiUrl", "");
        configCredentials.put("checkoutUrl", "");
        configCredentials.put("verificationUrl", "");
        configCredentials.put("prefix", "");
        configCredentials.put("merchantReturnUrl", "");
        configCredentials.put("merchantCancelUrl", "");

        Settings.configure(sandbox, configCredentials); //<--- EXECUTING
        
Step 4:
Now put all of your checkout information into a JSONObject and execute the ShurjoPay.executeChecout(JSONObject checkoutInfo) method.
You have to fill up the fields as shown below.

Example (For Sandbox):
        JSONObject getTokenAndStoreId = ShurjoPay.getToken(); 
        JSONObject checkoutInfo = new JSONObject(); //make a json object and put the necessary checkout data parameters inside it.
        checkoutInfo.put("prefix", Settings.prefix );
        checkoutInfo.put("token",getTokenAndStoreId.getString("token")); 
        checkoutInfo.put("store_id", getTokenAndStoreId.getInt("store_id")); 
        checkoutInfo.put("return_url", Settings.merchantReturnUrl);
        checkoutInfo.put("cancel_url", Settings.merchantCancelUrl);
        checkoutInfo.put("order_id", "sp315689");
        checkoutInfo.put("amount", "420" );
        checkoutInfo.put("currency", "BDT");
        checkoutInfo.put("client_ip", "102.101.1.1");
        checkoutInfo.put("customer_name", "Maharab Kibria");
        checkoutInfo.put("customer_phone", "01777777777");
        checkoutInfo.put("customer_email", "maharab.kibria@gmail.com");
        checkoutInfo.put("customer_address", "221 Baker Street");
        checkoutInfo.put("customer_city", "Dhaka");
        checkoutInfo.put("customer_state", "Dhaka");
        checkoutInfo.put("customer_postcode", "1217");
        checkoutInfo.put("customer_country", "Bangladesh");
        JSONObject checkoutResponse = ShurjoPay.executeCheckout(checkoutInfo);//<--- EXECUTING
        
After executing the executeCheckout(String checkoutInfo) method, the customers will be redirected to the shurjoPay payment gateway UI.

Step 5:
Once the customer is done with their payment, ShurjoPay will redirect the merchants to the return url that you have provided into the Settings.configure() method.
To show the necessary order information on the url, you need to execute the Shurjopay.verifyOrder(String orderId) method.

Example (For Sandbox):
        String orderId = checkoutResponse.getString("sp_order_id");
        JSONObject getVerificationResponse = ShurjoPay.verifyOrder(orderId); //<--- EXECUTING 



```


### Postman Documentations

    This document will illustrate the overall request and response flow.
    URL : https://documenter.getpostman.com/view/6335853/U16dS8ig	
