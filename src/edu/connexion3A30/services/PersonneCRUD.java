/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.services;



//import com.mysql.cj.Session;
import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import edu.connexion3A30.entities.Utilisateur;
import edu.connexion3A30.entities.Commande;
import edu.connexion3A30.entities.Role;
import edu.connexion3A30.entities.Session;
import edu.connexion3A30.util.Mailapi;

import edu.connexion3A30.util.MyConnection;
import static java.lang.System.console;
import java.security.MessageDigest;
import java.sql.Date;
import javax.crypto.spec.SecretKeySpec;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javafx.scene.control.Alert;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author bilel
 */

public class PersonneCRUD {
    public  void ajoutePersonne(Utilisateur p){
        
        
        
        try {
            String requete = "INSERT INTO utilisateur (nom,prenom) VALUES "  +" ('"+p.getUsername()+"','"+p.getLastname()+"')";
            
            Statement st = new MyConnection().getCnx().createStatement();
            st.executeUpdate(requete); // changer dans base update delete add , // executequery select
            System.err.println("Personne ajoutée");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
        
        
    }
    /*
    
    
      public boolean checklogin(String username,String password){
        try {
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            String query="SELECT * FROM `utilisateur` WHERE `username`='"+username+"' AND `password`='"+password+"'";
            ResultSet rs=st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
     }
    */
    
     public Utilisateur findByUsername(String username){
         Utilisateur u=new Utilisateur();
         try {
            Statement st= MyConnection.getInstance().getCnx().createStatement();
            String query="select * from utilisateur where username='"+username+"'";
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
               
               
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString("username"));
                u.setTel(rs.getString("tel"));
                u.setPassword(rs.getString("password"));
                u.setLastname(rs.getString("lastname"));
             
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
     }
     
     public String Hash() throws Exception {

        String mdp_user = "";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp_user.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }
     
     
   



    
     
     
     
     
     
     
    
    public void ajoutPersonne2(Utilisateur p) {
        
        try {
            String mdp = Hash();
        } catch (Exception ex) {
            Logger.getLogger(PersonneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        try {
              if ( p.getEmail().indexOf("@gmail.com")!=-1 ) {
                  
                   if ( p.getUsername().length() >= 5 ) {
                       
                        
                  
            String requette = "INSERT INTO utilisateur (username,lastname,roles,email ,password ,tel) VALUES"+"(?,?,?,?,?,?)";  //prepared statement asra3 et securise mn statment khter mara bark tet3ada b 4 phase w lbe3i phase execution bark
    //drop matekhdemch fl prepared , prepred statment drequette dynamique
           PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requette);
           pst.setString(1, p.getUsername());
      
           pst.setString(2, p.getLastname());
           
           pst.setString(3, p.getRole());
           
         
           
           pst.setString(4, p.getEmail());
          
             pst.setString(5, p.getPassword());
             pst.setString(6, p.getTel());
         
              
           
        
           
           
           
         
           
           
       
           int id = 0 ;
            //pst.executeUpdate(requette); //select nestaamlou executequery
            
            pst.execute();
            
            int n ;
             Utilisateur u = new Utilisateur();
            pst.execute();
           
              
            
            String query_rechreche = " SELECT *  from utilisateur where username = ? ; ";
            PreparedStatement pst_recherche  = MyConnection.getInstance().getCnx().prepareStatement(query_rechreche);
            pst_recherche.setString(1, p.getUsername() );
            ResultSet rs_user = pst_recherche.executeQuery();
          while(rs_user.next()){
               id = rs_user.getInt(1);
          }
          
              
            
            String requette_panier = "INSERT INTO panier (utilisateur_id) VALUES"+"(?)";
            PreparedStatement pst_panier = MyConnection.getInstance().getCnx().prepareStatement(requette_panier);
            pst_panier.setInt(1, id );
            pst_panier.execute();
            
            
                   }else{
                       System.out.println("size");
                   }
            
              }else {
                  System.out.println("error");
              }

           // System.out.println("personne2 ajout" +pst.executeUpdate(requette) );
        }
      
        
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void ajouter(Utilisateur u) {
int id = 0 ;
        String req = "INSERT INTO `utilisateur`(`username`, `lastname`,  `tel`,`email`, `password`, `roles` , `etat`, `adresse`, `activation_token` ,`date_naissance`)VALUES (?,?,?,?,?,?,?,?,?,?) ";
        try {
            String mdp = Hash();

            PreparedStatement ps = MyConnection.getInstance().getCnx().prepareStatement(req);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getLastname());
            ps.setString(3, u.getTel());
            ps.setString(4, u.getEmail());
            ps.setString(5, mdp + u.getPassword());
            
          
                ps.setString(6, "[\"ROLE_USER\"]");
              
           
            ps.setString(7,"Debloquer");
            ps.setString(8,u.getAdresse());
            ps.setString(9,u.getActivation_token());
            ps.setDate(10, (Date)u.getDate_naissance());
            
            ps.executeUpdate();
            System.out.println("user added !");
            
             String query_rechreche = " SELECT *  from utilisateur where username = ? ; ";
            PreparedStatement pst_recherche  = MyConnection.getInstance().getCnx().prepareStatement(query_rechreche);
            pst_recherche.setString(1, u.getUsername() );
            ResultSet rs_user = pst_recherche.executeQuery();
          while(rs_user.next()){
               id = rs_user.getInt(1);
          }
          
              
            
            String requette_panier = "INSERT INTO panier (utilisateur_id) VALUES"+"(?)";
            PreparedStatement pst_panier = MyConnection.getInstance().getCnx().prepareStatement(requette_panier);
            pst_panier.setInt(1, id );
            pst_panier.execute();
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    
    
    // 
    public void UpdatePersonne(Utilisateur p) {
        try {
            
            String requette ="update utilisateur set username = ? , lastname = ? , roles = ? , email = ? , password = ? , etat = ? , tel = ?  where id = ?";  //prepared statement asra3 et securise mn statment khter mara bark tet3ada b 4 phase w lbe3i phase execution bark
    //drop matekhdemch fl prepared , prepred statment drequette dynamique
           PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requette);
           
           pst.setString(1, p.getUsername());
           pst.setString(2, p.getLastname());
           pst.setString(3, p.getRole());
           pst.setString(4, p.getEmail());
           pst.setString(5, p.getPassword());
           pst.setString(6, p.getEtat());
          
           pst.setString(7, p.getTel());
           pst.setInt(8, p.getId());
            //pst.executeUpdate(requette); //select nestaamlou executequery
            pst.execute();
           // System.out.println("personne2 ajout" +pst.executeUpdate(requette) );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
       public void UpdatePersonne1(Utilisateur p) {
        try {
            System.out.println(p);
            String requette ="update utilisateur set username = ? , lastname = ?  , email = ? , password = ? , adresse = ? , tel = ?  where id = ?";  //prepared statement asra3 et securise mn statment khter mara bark tet3ada b 4 phase w lbe3i phase execution bark
    //drop matekhdemch fl prepared , prepred statment drequette dynamique
           PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requette);
           
           pst.setString(1, p.getUsername());
           pst.setString(2, p.getLastname());
         
           pst.setString(3, p.getEmail());
           pst.setString(4, p.getPassword());
 
          
           pst.setString(5, p.getTel());
           pst.setString(6, p.getAdresse());
           pst.setInt(7, p.getId());
            //pst.executeUpdate(requette); //select nestaamlou executequery
            pst.execute();
           // System.out.println("personne2 ajout" +pst.executeUpdate(requette) );
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    //
    public void modifierUtlisateur(Utilisateur p){
        String requete = "UPDATE utilisateur SET username`=?,lastname`=?,`email`=?,`date_naissance`=?, adresse`=? , tel`=?, roles`=?, password`=? WHERE id=" ;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, p.getUsername());
           pst.setString(2, p.getLastname());
           pst.setString(3, p.getRole());
           pst.setString(4, p.getEmail());
           pst.setString(5, p.getPassword());
           
          
           pst.setString(6, p.getTel());
           pst.setInt(7, p.getId());
            pst.executeUpdate();
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de l'utilisateur : " + p.getUsername()+ " a été éffectuée avec succès ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
       public boolean modifier(Utilisateur p) {
        String req = "update utilisateur set username = ? , lastname = ? , roles = ? , email = ? , password = ? , etat = ? , tel = ?  where id = ?"; 
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, p.getUsername());
           pst.setString(2, p.getLastname());
           pst.setString(3, "Role");
           pst.setString(4, p.getEmail());
           pst.setString(5, p.getPassword());
           pst.setString(6, p.getEtat());
          
           pst.setString(7, p.getTel());
           pst.setInt(8, p.getId());
            pst.executeUpdate();
            System.out.println("user Modifyed !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
    
      public boolean verifierEmailBd(String email) { //Controle De Saisie si mail existe
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String sql = "SELECT * FROM utilisateur WHERE email=?";
            stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setString(1, email);
            rst = stmt.executeQuery();
            if (rst.next()) {
                System.out.println("c bon");
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          System.out.println("non");
        return false;
    }
      
       public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
       
       
  
    public void sendMail(String receveursList, String object, String corps) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "25");

        String MonEmail = "sporttech007@gmail.com";
        String password = "Zayani321";

        javax.mail.Session session = javax.mail.Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(MonEmail, password);
            }

        });

        javax.mail.Message message = prepareMessage(session, MonEmail, receveursList, object, corps);

        try {
            javax.mail.Transport.send(message);
        } catch (javax.mail.MessagingException ex) {
            System.out.println();
        }

        System.err.println("Message envoye avec succes");
    }
    
     public void information_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.INFORMATION);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }
     
     
  private static javax.mail.Message prepareMessage(javax.mail.Session session, String email, String receveursList, String object, String corps) {
        javax.mail.Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(email));

            message.setSubject(object);
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(receveursList));
            message.setText(corps);

            return message;
        } catch (javax.mail.MessagingException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

        
     public void modifierPassword(String mail, String password) {
        PreparedStatement stmt;
        try {

            String sql = "UPDATE utilisateur SET password=?     WHERE email=?";
            stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, mail);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     
    public void modifier(long id_modif, Utilisateur t) {
        try {
            PreparedStatement st;
            st=MyConnection.getInstance().getCnx().prepareStatement("UPDATE `Utilisateur` SET `adresse`=?,`date_naissance`=?,"
                    + "`email`=?,`tel`=?,"
                    + "`password`=?,`lastname`=?,"
                    + "`roles`=?,`username`=? WHERE id=?");
            st.setString(1, t.getAdresse());
            st.setDate(2,new java.sql.Date(t.getDate_naissance().getTime()));
            st.setString(3,t.getEmail());
            
            st.setString(4, t.getTel());
            st.setString(5,( t.getPassword()));
            st.setString(6, t.getLastname());
            
            st.setString(8, t.getUsername());
            st.setLong(9, id_modif);
            if (st.executeUpdate()==1){
            System.out.println("user modifier avec success");
            }else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
       public void supprimer(long id) {
        try {
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            String query="delete from user where id="+id;
            if(st.executeUpdate(query)==1){
            System.out.println("suppression avec success");
            }else {
                System.out.println("user n'existe pas");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
      public void Delete( int id) {
        try {
            
               
                Utilisateur p = new Utilisateur();
            String query_rechreche = " SELECT *  from utilisateur where username = ? ; ";
            PreparedStatement pst_recherche  = MyConnection.getInstance().getCnx().prepareStatement(query_rechreche);
            pst_recherche.setString(1, p.getUsername() );
            ResultSet rs_user = pst_recherche.executeQuery();
          while(rs_user.next()){
               id = rs_user.getInt(1);
          }
          
              
            
            String requette_panier = "delete from panier where utilisateur_id = ?;";
            PreparedStatement pst_panier = MyConnection.getInstance().getCnx().prepareStatement(requette_panier);
            pst_panier.setInt(1, id );
            pst_panier.execute();
           ///
           
            String requette ="delete from utilisateur where id = ?;";  //prepared statement asra3 et securise mn statment khter mara bark tet3ada b 4 phase w lbe3i phase execution bark
    //drop matekhdemch fl prepared , prepred statment drequette dynamique
    
       
           PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requette);
           pst.setInt(1,id);
            //pst.executeUpdate(requette); //select nestaamlou executequery
            pst.execute();
           // System.out.println("personne2 ajout" +pst.executeUpdate(requette) );
           
            
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      
     //list maha il tree 
     
     public List<Utilisateur> listPersonnes(){
         List<Utilisateur> myList = new ArrayList<>();
         String requette ="SELECT * from utilisateur";
          Statement st;
        try {
            st = MyConnection.getInstance().getCnx().createStatement();
             ResultSet rs=st.executeQuery(requette);
             
             
            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setId(rs.getInt(1));
                p.setUsername(rs.getString("username"));
                p.setLastname(rs.getString("lastname"));
                p.setRole(rs.getString("roles"));
                p.setEmail(rs.getString("email"));
                myList.add(p);
            }
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       Collections.sort(myList);
     return myList;
     }
     
     //
     
     
     public Utilisateur Search(int id ) {
        Utilisateur off_final = new Utilisateur(); 
        
        MyConnection conn = new MyConnection();
        conn.getCnx();
        try {
            String query = " SELECT *  from utilisateur where id = ? ; ";
            PreparedStatement preparedStmt = conn.getCnx().prepareStatement(query);
            preparedStmt.setInt(1, id );
            ResultSet rs = preparedStmt.executeQuery();
            if (rs.next() == false)
            {
                System.out.println("utilisateur not found "); 
                
            }
            else {
                
                    System.out.println("utilisateur found   ") ; 
                    off_final.setId(rs.getInt(1));
                    off_final.setUsername(rs.getString("username"));
                           

            }
            
        } catch (SQLException ex) {
        }
        return off_final;
    }


     
     
     /// connecxion
     
     public Utilisateur login(String username,String password ) throws Exception {
        List<Utilisateur> myListUtilisateur = new ArrayList<>();
       
        String requete = "SELECT * FROM utilisateur WHERE username=? AND password = ? ";
        Utilisateur u = null;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                
                
                u = new Utilisateur();
               
                u.setId(rs.getInt(1));
              
                 u.setPassword(rs.getString(2));
                
                Session.setId(rs.getInt("id"));
                Session.setUsername(rs.getString("username"));
                Session.setEmail(rs.getString("email"));
                Session.setRoles(rs.getString("roles"));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //System.out.println(myListUtilisateur);
        System.out.println("welcome : " + Session.getUsername()+ ":votre login a été éffectuée avec succès ");
          System.out.println(   Session.getEmail());
            Mailapi.send("sporttech007@gmail.com", "Zayani321",     Session.getEmail(), "Forgot password", "This is your code for updating your password: ");
          
          //Mailapi.send(username, password, requete, requete, requete);
        
        
          
          
      
        
        
        return u;
    }
     
     
     public void trendingoffre()
    {
        CommandeService commandeS = new CommandeService();
        List<Commande> MyUser = new ArrayList<>(commandeS.listcommande() ) ;
        
        Utilisateur p = new Utilisateur();
        PersonneCRUD pcdd = new PersonneCRUD();
        
        Map<Integer, Integer> count = MyUser.stream().collect(Collectors.groupingBy(e -> e.getUser().getId(),Collectors.summingInt(s ->s.getMontant().intValue()      ) ) );

        System.out.println(count) ; 
        System.out.println(count.size()) ;
        int max = 0 ;
        int id_offre_max = 0 ;  
        for (Map.Entry<Integer, Integer> entry : count.entrySet() ) {
            
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
            if (entry.getValue()>max ) {
                max = entry.getValue();
                id_offre_max = entry.getKey();
            }
        }
        p = pcdd.Search(id_offre_max);
        System.out.println("trending offer with the most reservations : " + p.getUsername()) ; 

    }
     
     
      public List<Utilisateur> sortByNom(){
         List<Utilisateur> users=afficher();
         List<Utilisateur> resultat=users.stream().sorted(Comparator.comparing(Utilisateur::getUsername)).collect(Collectors.toList());
         return resultat;
     }
     
     
 public List<Utilisateur> sortById(){
         List<Utilisateur> users=afficher();
         List<Utilisateur> resultat=users.stream().sorted(Comparator.comparing(Utilisateur::getId)).collect(Collectors.toList());
         return resultat;
     }
     
     
     /*public void trendingoffre()
    {
        
        
       
     
     
     /*
     public void login1(String username, String password)  {
       
        try {
        String req = "SELECT * FROM  WHERE username=?";
        PreparedStatement pre = MyConnection.getInstance().getCnx().prepareStatement(req);
        pre.setString(1, username);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            if (BCrypt.checkpw(password, rs.getString("password"))) {
                
                
               
                
                System.out.println("login ");
            }
            else{ 
                System.out.println("Verifier votre mot de passe et votre adresse mail");
            }
       System.out.println( Session.getUsername() + Session.getEmail());
          
        }
       
    
         } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
}
*/
     
     
    public List<Utilisateur> afficher() {
        List<Utilisateur> lu=new ArrayList<>();
        try {
            Statement st=MyConnection.getInstance().getCnx().createStatement();
            String query="select * from utilisateur";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                Utilisateur u =new Utilisateur();
           
                u.setEmail(rs.getString("email"));
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setTel(rs.getString("tel"));
                u.setPassword(rs.getString("password"));
                u.setLastname(rs.getString("lastname"));
                u.setAdresse(rs.getString("adresse"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setRole(rs.getString("roles"));
               u.setEtat(rs.getString("etat"));
                lu.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonneCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lu;
    }
    // age
    
    public int calculateAge(Date birthDate) {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - birthDate.getYear();
        
        int month1 = currentDate.getMonthValue();
        int month2 = birthDate.getMonth();
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = currentDate.getDayOfMonth();
            int day2 = birthDate.getDay();
            if (day2 > day1) {
                age--;
            }
        }
        return age-1900;
    }
    
    
    } 
  
    

    
    
    

