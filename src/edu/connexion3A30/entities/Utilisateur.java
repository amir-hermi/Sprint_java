/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.entities;

import java.sql.Date;

/**
 *
 * @author bilel
 */
public class Utilisateur implements Comparable<Utilisateur>{
    private int id ;
    private String username ;
    private String lastname ;
     private String role ;
     private String email ; 
     private String password ;
     private String tel ;
     private String etat ; 
     private String adresse ; 
     private String Activation_token ; 
     private Date date_naissance;
     
     

    public Utilisateur() {
    }

    public Utilisateur(String username, String lastname, String role, String email, String password, String tel, String etat, String adresse, String Activation_token, Date date_naissance) {
        this.username = username;
        this.lastname = lastname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.etat = etat;
        this.adresse = adresse;
        this.Activation_token = Activation_token;
        this.date_naissance = date_naissance;
    }
    
     
             
    public Utilisateur(String username, String lastname, String email, String password, String tel, String adresse) {
        this.username = username;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.adresse = adresse;
    }
public Utilisateur(int id, String username, String lastname, String role, String email, String password, String tel, String etat, String adresse, Date date_naissance) {
        this.id = id;
        this.username = username;
        this.lastname = lastname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.etat = etat;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
    }

    public Utilisateur(String username, String lastname, String role, String email, String password, String tel, String etat, String adresse, Date date_naissance) {
        this.username = username;
        this.lastname = lastname;
        this.role = role;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.etat = etat;
        this.adresse = adresse;
        this.date_naissance = date_naissance;
    }

    


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Utilisateur(String nom, String prenom) {
        this.username = username;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
       public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

  

  

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
   public Utilisateur(String aUsername){
       username = aUsername ;
   }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

   

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", username=" + username + ", lastname=" + lastname + ", role=" + role + ", email=" + email + ", password=" + password + ", tel=" + tel + ", etat=" + etat + ", date_naissance=" + date_naissance + '}';
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getActivation_token() {
        return Activation_token;
    }

    public void setActivation_token(String Activation_token) {
        this.Activation_token = Activation_token;
    }
    
    
    
    
    
   

    

    @Override
    public int compareTo(Utilisateur t) {
      int compareInt = this.username.compareTo(t.username);
      if (compareInt < 0) return -1 ; //this.name is bigger
      if (compareInt > 0) return 1 ; //this.name is bigger
      return 0;
      
    }
 
    
    
}
