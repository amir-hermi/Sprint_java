/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.tests;

import edu.connexion3A30.entities.Utilisateur;
import edu.connexion3A30.services.CommandeService;
import edu.connexion3A30.services.PersonneCRUD;
import edu.connexion3A30.util.MyConnection;
import java.sql.Date;

import java.util.Scanner;

/**
 *
 * @author bilel
 */
public class MainClass {
    public static void main(String[] args) throws Exception { 
       // MyConnexion cn = new MyConnexion();
       // Utilisateur p =new Utilisateur("Mejbri", "Hannibaal");
       
       
        PersonneCRUD pcd = new PersonneCRUD();
        CommandeService aa = new CommandeService();
      
//        Utilisateur p2 =new Utilisateur();
        
        //Personne p3 = new Utilisateur("ahmed", "habeib");
        
        Date d = new Date(03,03,1999);
         //Utilisateur p6 = new Utilisateur("dsfds", "sdfds" , "ROLE_ADMIN" , "dsf@gmail.com" ,"chiheb" ,"9393399","ariana e soghra nahej il bacha" ,d);
          //p6.setId(62);
           Utilisateur p6 = new Utilisateur("ds", "hbaxwcieb", "ROLE_ADMIN", "sd@gmail.com","hfhfh","829434292", "jjdjjd", "jdjdjd","codecode", d) ;
         
         
         //Smsapi.sendSMS("amir hermi");
         
        //pcd.login("amir" , "hosyam");
        //p6.setId(35);
        
        //pcd.UpdatePersonne(p6);
        
           //pcd.Delete(p6);
           
       pcd.ajouter(p6);
        
       // pcd.verifierEmailBd("bilel.;jnkjjkj@esprit.tn");
       // pcd.ajoutPersonne2(p6);
        
        //pcd.ajoutPersonne2(p6);
        //pcd.ajouter(p6);
       //pcd.trendingoffre();
          
         // pcd.Search(10001);
         
           // p3.setId(3);
            
            //pcd.UpdatePersonne(p6);
            
                 //pcd.listPersonnes() ;
       
        //pcd.ajoutPersonne(p);
        //pcd.ajoutPersonne2(p2);
        //System.out.println(pcd.listPersonnes());
        
        //System.out.println(aa.listcommande());
        
        
        ///////////////////////
    }
    
}
