/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entitiy.Commande;
import etud.entitiy.Panier;
import etud.entitiy.Produit;
import etud.entitiy.Utilisateur;
import etud.utils.MyConnexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class PanierService {
     public List<Panier> listPanier(int id){
                     List<Panier> myList = new ArrayList<>();

        try {
            String requette ="SELECT * FROM panier where utilisateur_id ="+id;
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 ArrayList<Produit> produits = new ArrayList<>();
                 String req ="SELECT * FROM panierproduit INNER JOIN produit ON produit.id=panierproduit.produit_id where panier_id="+rs.getInt("id")+";";
                 Statement stp = MyConnexion.getInstance().getCnx().createStatement();
                 ResultSet prod =stp.executeQuery(req);
             while (prod.next()){
                 Produit p = new Produit();
                 p.setId(prod.getInt("id"));
                 p.setNom(prod.getString("nom"));
                 p.setPrix(Float.valueOf(prod.getString("prix")) );
                 p.setTaille(prod.getString("taille"));
                 p.setQantite(prod.getInt("quantite"));
                 p.setImage(prod.getString("image"));
                 produits.add(p);
             } 
                Panier panier = new Panier();
                panier.setListPorduits(produits);
                myList.add(panier);
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     return myList;
     }
     public void remplirPanier(int idUtilisateur , Produit p){
         
         try {
            String requette ="SELECT * FROM panier where utilisateur_id ="+idUtilisateur+";";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 ArrayList<Produit> produits = new ArrayList<>();
                 String req ="INSERT INTO panierproduit (produit_id , panier_id) VALUES ('"+p.getId()+"','"+rs.getInt("id")+"')";
                  Statement stp = MyConnexion.getInstance().getCnx().createStatement();
                  st.executeUpdate(req); //select nestaamlou executequery
                  System.out.println("Produit ajout");
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     }
     
     public void UpdateProduit(int id , int newQte , String newTaille) {
    String requette = "UPDATE produit SET quantite="+newQte+",taille = '"+newTaille+"' WHERE id="+id+";";
        try {
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            st.executeUpdate(requette); //select nestaamlou executequery
            System.out.println("Produit updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
     
     public void deleteProduitFromPanier(int idUtilisateur , Produit p){
         
         try {
            String requette ="SELECT * FROM panier where utilisateur_id ="+idUtilisateur+";";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 ArrayList<Produit> produits = new ArrayList<>();
                 String req ="DELETE FROM panierproduit WHERE panier_id ="+rs.getInt("id")+" AND produit_id = "+p.getId()+";";
                  Statement stp = MyConnexion.getInstance().getCnx().createStatement();
                  st.executeUpdate(req); //select nestaamlou executequery
                  System.out.println("Produit deleted");
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     }
     
     public void viderPanier(int idUtilisateur ){
         
         try {
            String requette ="SELECT * FROM panier where utilisateur_id ="+idUtilisateur+";";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 
                 String req ="DELETE FROM panierproduit WHERE panier_id ="+rs.getInt("id")+" ;";
                  Statement stp = MyConnexion.getInstance().getCnx().createStatement();
                  st.executeUpdate(req); //select nestaamlou executequery
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     }
     
     
     
     public List<Produit> Recommandation(Utilisateur u){
         List<Produit> rec = new ArrayList<>();
         List<Produit> L = new ArrayList<>();
         try {
            String requette ="SELECT * FROM commande where utilisateur_id ="+u.getId();
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rs =st.executeQuery(requette);
             
            while (rs.next()) {  
                 String req ="SELECT DISTINCT produit_id FROM commande_produit INNER JOIN produit ON produit.id=commande_produit.produit_id where commande_id="+rs.getInt("id")+";";
                 Statement stp = MyConnexion.getInstance().getCnx().createStatement();
                 ResultSet prod =stp.executeQuery(req);
             while (prod.next()){
               
                 Produit p = new Produit();
                 p.setId(prod.getInt("produit_id"));
                 if(!L.contains(p)){
                      L.add(p);
                 }
                
             } 
               
            }
             System.out.println(L);
            for(Produit p : L){
                 String req1 ="SELECT * from produit where id="+p.getId();
             Statement st1 = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rs1 =st1.executeQuery(req1);
              while (rs1.next()){
                  String req2 ="SELECT * FROM `produit` WHERE marque_id ="+rs1.getInt("marque_id")+" OR sous_categire_id="+rs1.getInt("sous_categire_id")+";";
                  //System.out.println("SELECT * FROM `produit` WHERE marque_id ="+rs1.getInt("marque_id")+" OR sous_categire_id="+rs1.getInt("sous_categire_id")+";");
             Statement st2 = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rs2 =st2.executeQuery(req2);
                  while (rs2.next()){
                 Produit prod = new Produit();
                  prod.setId(rs2.getInt("id"));
                 prod.setNom(rs2.getString("nom"));
                 prod.setPrix(Float.valueOf(rs2.getString("prix")) );
                 prod.setTaille(rs2.getString("taille"));
                 prod.setQantite(rs2.getInt("quantite"));
                 if(!rec.contains(prod)){
                 rec.add(prod);    
                 }
                                 
             }
                 
             } 
            }
            String reqf ="SELECT * from produit";
             Statement stf = MyConnexion.getInstance().getCnx().createStatement();
             ResultSet rsf =stf.executeQuery(reqf);
             while (rsf.next()){
                 Produit prodf = new Produit();
                  prodf.setId(rsf.getInt("id"));
                 prodf.setNom(rsf.getString("nom"));
                 prodf.setPrix(Float.valueOf(rsf.getString("prix")) );
                 prodf.setTaille(rsf.getString("taille"));
                 prodf.setQantite(rsf.getInt("quantite"));
                 if(!rec.contains(prodf)){
                 rec.add(prodf);    
                 }
             }
            
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
     return rec;
     }
     
      public ArrayList<Produit> afficheProduit(){
         ArrayList<Produit> prod =new ArrayList<>();
         try {
            String reqf ="SELECT * from produit";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs =st.executeQuery(reqf);
             
            while (rs.next()) {  
                 Produit p =new Produit();
                 p.setId(rs.getInt(1));
                 p.setNom(rs.getString("nom"));
                 p.setPrix(rs.getFloat("prix"));
                 p.setQantite(rs.getInt("quantite"));
                 p.setTaille(rs.getString("taille"));
                 p.setImage(rs.getString("image"));
                 System.out.println(p.toString());
                 prod.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("ereur : "+ex.getMessage());
        }
          
         return prod;
     }
}
