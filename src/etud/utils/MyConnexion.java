/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author hp
 */
public class MyConnexion {

    private String url = "jdbc:mysql://localhost:3306/sporttech";
    private String login = "root";
    private String pwd = "";
    Connection cnx;
    public static MyConnexion instance;

    private MyConnexion() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablir");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyConnexion getInstance() {
        if(instance == null){
            instance = new MyConnexion();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }

}
