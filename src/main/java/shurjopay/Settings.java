package shurjopay;

import org.json.JSONObject;

//here sandbox credentials are set for test purpose.
public class Settings {
    public static String username = "sp_sandbox"; //<Live server credential will be provided by shurjoPay team>
    public static String password = "pyyk97hu&6u6"; //<Live server credential will be provided by shurjoPay team>
    public static String getApiUrl = "https://sandbox.shurjopayment.com/api/get_token"; //"https://engine.shurjopayment.com/api/get_token";
    public static String checkoutUrl = "https://sandbox.shurjopayment.com/api/secret-pay"; //"https://engine.shurjopayment.com/api/secret-pay";
    public static String verificationUrl = "https://www.sandbox.shurjopayment.com/api/verification"; //"https://www.engine.shurjopayment.com/api/verification";
    public static String prefix = "sp"; //<Live server credential will be provided by shurjoPay team>
    public static String merchantReturnUrl = "https://www.sandbox.shurjopayment.com/response"; //<YourDomainName.com/Custom_return_url>
    public static String merchantCancelUrl = "https://www.sandbox.shurjopayment.com/response"; //<YourDomainName.com/Custom_cancel_url>

    public static void configure(Boolean sandbox, JSONObject configCredentials){
        if (sandbox==false) {
            Settings.username = configCredentials.getString("username");
            Settings.password = configCredentials.getString("password");
            Settings.getApiUrl = configCredentials.getString("getApiUrl");
            Settings.checkoutUrl = configCredentials.getString("checkoutUrl");
            Settings.verificationUrl = configCredentials.getString("verificationUrl");
            Settings.prefix = configCredentials.getString("prefix");
            Settings.merchantReturnUrl = configCredentials.getString("merchantReturnUrl");
            Settings.merchantCancelUrl = configCredentials.getString("merchantCancelUrl");
        }
        else System.out.println("Using sandbox");
    }
}
//change the credentials to live credentials after getting on-board with shurjoPay!
