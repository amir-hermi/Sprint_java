/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entitiy.Commande;
import etud.entitiy.Utilisateur;

/**
 *
 * @author hp
 */
public interface PaymentService {
    public String createCustomer(Utilisateur user);
  public void chargeCreditCard(Commande commande);
}
