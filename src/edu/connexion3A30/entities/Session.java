/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.entities;

/**
 *
 * @author bilel
 */
public class Session {
     private static int id = 0;
    private static String username;
    private static String lastname;
    private static String email;
    private static String roles;
    private static int id_Lo;
    private static String etat;

   
    
    public static int getId_Lo() {
        return id_Lo;
    }

    public static void setId_Lo(int id_Lo) {
        Session.id_Lo = id_Lo;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }
    
    public static String getEtat() {
        return etat;
    }

    public static void setEtat(String etat) {
        Session.etat = etat;
    }
    
 public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        Session.lastname = lastname;
    }
    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        Session.roles = roles;
    }
}
