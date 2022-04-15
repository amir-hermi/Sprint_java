/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.services;

import etud.entitiy.Commande;
import etud.entitiy.Utilisateur;
import etud.utils.MyConnexion;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author hp
 */
public class ServerS extends Thread {

    ServerSocket server;
    Socket client;
    ObjectOutputStream out;
    ArrayList<Commande> data ;

    public ServerS() {
        try {
            server = new ServerSocket(1400);
            System.out.println("ServerSocket awaiting connections...");
            start();
        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
        }
    }

    public void run() {
        try {
            while (true) {
                try {
                    client = server.accept();
                    System.out.println("Connection from " + client + "!");
                    out = new ObjectOutputStream(client.getOutputStream());
                    System.out.println("Stream received");
                    try {
                        CommandeService cs = new CommandeService();
                        data = ((ArrayList<Commande>) cs.listCommande());
                        System.out.println("data added "+data.toString());           
                        out.writeObject(data);
                        out.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception e) {
        }
    }

}
