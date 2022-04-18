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
public class Myconnection {

    String jdbcurl = "jdbc:mysql://localhost:3306/sporttech";
    String username = "root";
    String password = "";

    Connection conn;


    public void connect() {
        try {
            conn = DriverManager.getConnection(jdbcurl, username, password);

            System.out.println("Connected to db ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*
    public static Myconnection getInstance() {
        if(instance == null){
            instance = new Myconnection();
        }
        return instance;
    }
    */
    
    public Connection getConn() {
        return conn;
    }
}