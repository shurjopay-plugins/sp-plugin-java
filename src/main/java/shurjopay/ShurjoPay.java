package shurjopay;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ShurjoPay {

    public static JSONObject getToken() throws IOException{
        JSONObject credential = new JSONObject();
        credential.put("username", Settings.username );
        credential.put("password", Settings.password);
        String credentialToString = credential.toString();

        URL obj = new URL(Settings.getApiUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json;");
        connection.setDoOutput(true);
        connection.setConnectTimeout(30000);
        //send request
        OutputStream os = connection.getOutputStream();
        os.write(credentialToString.getBytes());
        os.flush();
        os.close();

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code of Get Token API: " + responseCode);

        //get response
        String responseDataOfGetToken = "";
        if (responseCode==200) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responseDataOfGetToken = response.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("Something went wrong!");
        }
        JSONObject JSONResponseDataOfGetToken = new JSONObject(responseDataOfGetToken);
        return  JSONResponseDataOfGetToken;
    }

    public static JSONObject executeCheckout(JSONObject checkoutInfo) throws IOException{
        int flag = 0;
        if(checkoutInfo.keys()==null){
        flag = 1;
            System.out.println("Checkout Payload Fields can not be Null and Must Contain a Value.");
            //checkout parameters logic needs to be fixed ***************************************
        }
        ///NOW FOR THE ACTUAL PART///
        String responseDataOfCheckOut = "";
        if (flag == 0){
            URL obj = new URL(Settings.checkoutUrl);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;");
            connection.setDoOutput(true);
            connection.setConnectTimeout(30000);
            //send request
            OutputStream os = connection.getOutputStream();
            String checkoutInfoString = checkoutInfo.toString();
            os.write(checkoutInfoString.getBytes());
            os.flush();
            os.close();
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code of Checkout API : " + responseCode);

            //get response
            if (responseCode==200) {
                try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    responseDataOfCheckOut = response.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("Something went wrong!");
            }
        }
        JSONObject JSONResponseData = new JSONObject(responseDataOfCheckOut);
        String executeCheckoutUrl = JSONResponseData.getString("checkout_url");
        if (Settings.checkoutUrl!=null){
            try {
                URI uri = new URI(executeCheckoutUrl);
                java.awt.Desktop.getDesktop().browse(uri);
                System.out.println("Payment Portal Opened in The Browser.");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return JSONResponseData;
    }

    public static JSONObject verifyOrder(String orderId) throws IOException{
        JSONObject jsonOrderId = new JSONObject(); //creating json object of order id
        jsonOrderId.put("order_id", orderId);
        String jsonOrderIdToString = jsonOrderId.toString(); // turning the json object into string to pass it on the os.write()
        URL obj = new URL(Settings.verificationUrl);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + getToken().getString("token"));
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(30000);
        connection.setDoOutput(true);
        //send request
        OutputStream os = connection.getOutputStream();
        os.write(jsonOrderIdToString.getBytes());
        os.flush();
        os.close();
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code of Verifying Order API: " + responseCode);

        //get response
        String responseDataOfVerification = "";
        if (responseCode==200) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                responseDataOfVerification = response.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else { System.out.println("Something went wrong!");
        }
        //next 3 lines for removing the first "[" and last "]" characters from responseDataOfVerification.
        //so that the string can be put in as a JSONObject() and then returned.
        StringBuffer responseRefining = new StringBuffer(responseDataOfVerification);
        responseRefining.delete(responseDataOfVerification.length() - 1, responseDataOfVerification.length());
        responseRefining.delete(0, 1);

        JSONObject JsonResponseDataOfVerifyOrder = new JSONObject(responseRefining.toString());
        return JsonResponseDataOfVerifyOrder;
    }
}
