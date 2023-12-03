package Membership;

import com.stripe.param.CustomerUpdateParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerRetrieveParams;
import com.stripe.param.CustomerUpdateParams;
import java.util.HashMap;
import java.util.Map;

public class Payment {
    public static String verifyPayment(String cc, String mon, String yr, String cvv)  {
        String result = "success";
        if (cc.isBlank())
            return result = "Invalid Card Number";
        if (mon.isBlank())
            return result = "Invalid Expiry Month";
        if (yr.isBlank())
            return result = "Invalid Expiry Year";
        if (cvv.isBlank())
            return result = "Invalid CVV Code";

        Stripe.apiKey = "sk_test_51OGMSYFJmNIDWVZ21LNRKCtuvkgykBi7ujUSDqT83CUoADaHNE0I7mtVAdPPONQbVUYKSZrneJjxahobBAR45iuK00viDGMvfn";
        System.out.println("cc: " + cc + "mon: " + mon + "yr: " + yr + "cvv: " + cvv);
        try{
            Customer customer = null; // Declare the customer variable


            // Step 1: Create a customer
            Map<String, Object> customerParam = new HashMap<>();
            customerParam.put("email", "ars@hehe.com");
            Customer newCustomer = Customer.create(customerParam);

            // Assign the new customer to the variable
            customer = newCustomer;

            // Step 2: Create a PaymentMethod and associate it with the customer
            Map<String, Object> cardparam = new HashMap<>();
            cardparam.put("number", "5590490221847724");
            cardparam.put("exp_month", 11);
            cardparam.put("exp_year", 2028);
            cardparam.put("cvc", "308");
            Map<String, Object> paymentMethodParams = new HashMap<>();
            paymentMethodParams.put("type", "card");
            paymentMethodParams.put("card", cardparam);


            PaymentMethod paymentMethod = PaymentMethod.create(paymentMethodParams);

            CustomerUpdateParams updateParams = CustomerUpdateParams.builder()
                    .setDefaultSource(paymentMethod.getId())
                    .build();
            customer.update(updateParams);

            // Step 3: Charge the customer
            Map<String, Object> chargeparam = new HashMap<>();
            chargeparam.put("amount", 50); // in cents
            chargeparam.put("currency", "usd");
            chargeparam.put("customer", customer.getId());

            Charge charge = Charge.create(chargeparam);

            if ("succeeded".equals(charge.getStatus())) {
                // The charge was successful
                System.out.println("Charge succeeded!");
            } else {
                // Handle the case where the charge failed
                System.out.println("Charge failed. Status: " + charge.getStatus());
            }
        } catch (StripeException e) {
            e.printStackTrace();
            // Log or handle the exception
        }

        return result;

    }
}
