/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.test;

import etud.entities.Categorie;
import etud.entities.Marque;
import etud.entities.Produit;
import etud.entities.SousCategorie;
import etud.services.CategorieCRUD;
import etud.services.MarqueCRUD;
import etud.services.ProduitCRUD;
import etud.services.SousCategorieCRUD;
import etud.utils.Myconnection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hassen
 */
public class MainClass {
    public static void main(String[] args) {
        
        
        /*   ******************************** Produit *************************************  */
     
       
        
         Myconnection mc =new Myconnection();
                ProduitCRUD prod1 = new ProduitCRUD();

        System.out.println(  prod1.GetmarqueId("m2")) ; 
        System.out.println(  prod1.GetmarqueName(16) ) ; 
        Produit p2 = new Produit (2000,"path23",1,"XL","produit2",17,23,"this is our second new product");
        prod1.AjoutProduit(p2);
      /*  ************ Ajout ************* */
       
      
       /*
        
         
      Produit p = new Produit (1500,"path",1,"XL","produit1","16","1","this is our new product");
      Produit p2 = new Produit (2000,"path2",1,"XL","produit2","16","1","this is our second new product");
       
       ProduitCRUD prod1 = new ProduitCRUD();
       ProduitCRUD prod2 = new ProduitCRUD();
       prod1.AjoutProduit(p); 
       prod2.AjoutProduit(p2); */
       
       
      
      /*  ************ Affiche ************* */
       
        
       /*
       List<Produit> myList = new ArrayList<>(prod1.AfficheProduit()); 
       for (Produit x : myList ) {
           System.out.println(x.getNom());
       } 
       */
       
        
      /*  ************ Delete ************* */
      
        
      /*
      
      
        ProduitCRUD prod3 = new ProduitCRUD();
        Produit p2 = new Produit (1500,"path",1,"XL","produit1","5","1","this is our new product");
        p2.setId(79);
        prod3.DeleteProduit(p2);
        
      */
        
      /*  ************ Update ************* */
        
        
       /*
        
        ProduitCRUD prod4 = new ProduitCRUD();
        Produit p2 = new Produit (5000,"path2000",3,"XL","produit1","5","1","this is our new product");
        p2.setId(80);
        prod4.UpdateProduit(p2);
        
        */
     
        
        
        
        
      
      /*   ******************************** Marque *************************************  */
    
      
      
      
     /* ************ Ajout ************* */
      
     
   /*
     
    Marque m = new Marque("marque2");
    MarqueCRUD m2 = new MarqueCRUD();
    m2.AjoutMarque(m);
    
     */
   
     /* ************ Affiche ************* */
     
   /*
   
    List<Marque> myList = new ArrayList<>(m2.AfficheMarque()); 
       for (Marque x : myList ) {
           System.out.println(x.getLibelle());
       }
   */ 
   
   
    /*  ************ Delete ************* */
      
        
      
      /*
      
        MarqueCRUD marque = new MarqueCRUD();
        Marque m4 = new Marque ("new marque");
        m4.setId(13);
        marque.DeleteMarque(m4);
        
      */
   
   
   /*  ************ Update ************* */
        
        
       
        /*
   
  
        MarqueCRUD marque = new MarqueCRUD();
        Marque m4 = new Marque ("m1");
        m4.setId(14);
        marque.UpdateMarque(m4);
        
         */
   
   
   /*   ******************************** Categorie *************************************  */
   
   
   
   /* ************ Ajout ************* */
   
   /*
   
    Categorie c = new Categorie("categorie2");
    CategorieCRUD c1 = new CategorieCRUD();
    c1.AjoutCategorie(c);
  
   */
   
   
   /* ************ Affiche ************* */
   
   
   /*
     List<Categorie> myList = new ArrayList<>(c1.AfficheCategorie()); 
       for (Categorie x : myList ) {
           System.out.println(x.getLibelle());
       } 
    */
   
   
   
   /*  ************ Delete ************* */
      
        
      
      
      /*
        CategorieCRUD categorie = new CategorieCRUD();
        Categorie c3 = new Categorie ("new categorie");
        c3.setId(21);
        categorie.DeleteCategorie(c3);
       
      */ 
      
   
   
   /*  ************ Update ************* */
        
        
       
        
   /*
  
        CategorieCRUD categorie = new CategorieCRUD();
        Categorie c3 = new Categorie ("new categorie");
        c3.setId(23);
        categorie.UpdateCategorie(c3);
        
    */    
   
   
       
       
       
       
    /*   ******************************** Sous Categorie *************************************  */
  
    
    /* ************ Ajout ************* */
    
   /*
    
    SousCategorie sc = new SousCategorie(3,"Souscategorie12");
    SousCategorieCRUD sc1 = new SousCategorieCRUD();
    sc1.AjoutSousCategorie(sc);
    
    */
    
    /* ************ Affiche ************* */
   
    /*
    
    List<SousCategorie> myList = new ArrayList<>(sc1.AfficheSousCategorie()); 
       for (SousCategorie x : myList ) {
           System.out.println(x.getLibelle());
       } 
    */

    
    /*  ************ Delete ************* */
      
        
      
      
      /*
    
    
        SousCategorieCRUD Souscategorie = new SousCategorieCRUD();
        SousCategorie sc3 = new SousCategorie (3,"new Souscategorie");
        sc3.setId(22);
        Souscategorie.DeleteSousCategorie(sc3);
       
       */
       
      
   
   
   /*  ************ Update ************* */
        
        
       
        
  
    /*
        SousCategorieCRUD souscategorie = new SousCategorieCRUD();
        SousCategorie sc3 = new SousCategorie (3,"new Souscategorie");
        sc3.setId(1);
        souscategorie.UpdateSousCategorie(sc3);
        
    */  
    
    
    }   
}
