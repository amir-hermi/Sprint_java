/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class MyConnection {
    public String url="jdbc:mysql://localhost:3306/sporttech";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static MyConnection instance;
    private static Connection connection ;
    
      private static String HOST = "127.0.0.1";
        private static int PORT = 3306;
        private static String DB_NAME = "sporttech";
        private static String USERNAME = "root";
        private static String PASSWORD = "";
    private MyConnection() {
        
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());  
        }
          
        
    }
    public Connection getCnx()
    {
        return cnx;
    }
    
    public static MyConnection getInstance()
    {
        if(instance == null)
        {
            instance = new MyConnection();
        }
        return instance;
    }
    
    public static Connection getConnect (){
        try {
            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return  connection;
        } 
}
