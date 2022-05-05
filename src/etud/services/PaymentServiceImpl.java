/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import com.stripe.Stripe;
import com.stripe.model.Customer;
import etud.entitiy.Commande;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;

import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.PaymentMethod;
import etud.entitiy.Utilisateur;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class PaymentServiceImpl implements PaymentService {

    public static final String stripeKey = "rk_test_51KUvLNIc0oubFheRqSoj1IUDvQ2Lhj8lyTXj6KE533bJlUAFlTVZZYr18kDBDKm76cnuPJbMZftUZgZ07DGNOyvD00ZRxbv4zd";

    public PaymentServiceImpl() {
        Stripe.apiKey = stripeKey;
    }

    @Override
    public String createCustomer(Utilisateur user) {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("description",
                user.getUsername() + " " + "bilel ghazouani");
        customerParams.put("email", "bilelGH@gmail.com");

        String id = null;

        try {
            // Create customer
            Customer stripeCustomer = Customer.create(customerParams);
            id = stripeCustomer.getId();
            user.setStripeCustomerId(id);
           
           // System.out.println(stripeCustomer);
        } catch (CardException e) {
            System.err.println(e.getMessage());
        } catch (RateLimitException e) {
            System.err.println(e.getMessage());
        } catch (InvalidRequestException e) {
            System.err.println(e.getMessage());
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());
        } catch (ApiConnectionException e) {
            System.err.println(e.getMessage());
        } catch (StripeException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return id;
    }

    @Override
    public void chargeCreditCard(Commande commande) {

        // Stripe requires the charge amount to be in cents
        BigDecimal chargeAmountCents = new BigDecimal(commande.getMontant()*100);

        Utilisateur user = commande.getUser();

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", chargeAmountCents);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Monthly Charges");
        chargeParams.put("customer", user.getStripeCustomerId());

        try {
            // Submit charge to credit card
            Charge charge = Charge.create(chargeParams);
            System.out.println(charge);
        } catch (CardException e) {
            // Transaction was declined
            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
        } catch (RateLimitException e) {
            System.err.println(e.getMessage());// Too many requests made to the API too quickly
        } catch (InvalidRequestException e) {
            System.err.println(e.getMessage());// Invalid parameters were supplied to Stripe's API
        } catch (AuthenticationException e) {
            System.err.println(e.getMessage());// Authentication with Stripe's API failed (wrong API key?)
        } catch (ApiConnectionException e) {
            System.err.println(e.getMessage());// Network communication with Stripe failed
        } catch (StripeException e) {
            System.err.println(e.getMessage());// Generic error
        } catch (Exception e) {
            System.err.println(e.getMessage());// Something else happened unrelated to Stripe
        }

    }

    public boolean creationCard(Utilisateur u) {
        boolean res = true;
        try {
            Map<String, Object> retrieveParams
                    = new HashMap<>();
            List<String> expandList = new ArrayList<>();
            expandList.add("sources");
            retrieveParams.put("expand", expandList);
            Customer customer
                    = Customer.retrieve(
                            u.getStripeCustomerId(),
                            retrieveParams,
                            null
                    );

            Map<String, Object> params = new HashMap<>();
            params.put("source", "tok_visa");

            Card card
                    = (Card) customer.getSources().create(params);
            return true;
        } catch (StripeException ex) {
            System.err.println(ex.getMessage());
            res=!res;
        }
        return res;
    }

    public boolean checkVisa(String u , String num , String expm , String expy , String cvc) {
        boolean res=true;
        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", num);
            card.put("exp_month", expm);
            card.put("exp_year", expy);
            card.put("cvc", cvc);
            Map<String, Object> params = new HashMap<>();
            params.put("type", "card");
            params.put("card", card);
            PaymentMethod paymentMethod
                    = PaymentMethod.create(params);
            Map<String, Object> params1 = new HashMap<>();
params1.put("customer", u);
            paymentMethod.attach(params1);
        } catch (StripeException ex) {
            System.out.println(ex.getMessage());
            res=false;
        }
        return res;
    }
 
}
