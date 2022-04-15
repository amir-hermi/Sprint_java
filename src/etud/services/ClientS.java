/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entitiy.Commande;
import etud.entitiy.Produit;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class ClientS {
    //Socket clientSocket = null;
    
   
  
    
    public ClientS() {
        ArrayList<Commande> data = new ArrayList<>();
        try {
           Socket clientSocket = new Socket("localhost", 1400);
           ObjectInputStream br =new ObjectInputStream(clientSocket.getInputStream());
            data = (ArrayList<Commande>) br.readObject();
            br.close();
            int i =0;
            for (Commande c : data) {
                System.out.println(c.getId());
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
