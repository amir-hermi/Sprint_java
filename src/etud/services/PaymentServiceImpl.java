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
import com.stripe.model.Charge;
import etud.entitiy.Utilisateur;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hp
 */
public class PaymentServiceImpl implements PaymentService{
    public static final String stripeKey="rk_test_51KUvLNIc0oubFheRqSoj1IUDvQ2Lhj8lyTXj6KE533bJlUAFlTVZZYr18kDBDKm76cnuPJbMZftUZgZ07DGNOyvD00ZRxbv4zd";

    public PaymentServiceImpl() {
        Stripe.apiKey = stripeKey;
    }
    

    @Override
    public String createCustomer(Utilisateur user) {
        Map<String, Object> customerParams = new HashMap<String, Object>();
    customerParams.put("description", 
      user.getUsername()+ " " + user.getLastname());
	customerParams.put("email", user.getEmail());
		
	String id = null;
		
	try { 
      // Create customer
	  Customer stripeCustomer = Customer.create(customerParams);
	  id = stripeCustomer.getId();
          user.setStripeCustomerId(id);
	  System.out.println(stripeCustomer);
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
    int chargeAmountCents = Integer.parseInt(commande.getMontant().toString()) * 100;

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
    }
    

