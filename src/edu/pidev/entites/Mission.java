/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.entites;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Mission {
    private int id;
    private String status ="en attente";
    private String livreur,adresse;
    private String date;
    private int idUser;

    public Mission() {
    }

    public Mission(int id, String livreur, String adresse, String date, int idUser) {
        this.id = id;
        this.livreur = livreur;
        this.adresse = adresse;
        this.date = date;
        this.idUser = idUser;
    }

    public Mission(String adresse, String livreur,String date,int idUser) {
        this.adresse = adresse;
        this.livreur = livreur;
      
        this.idUser = idUser;
         this.date = date;
    }

    public Mission(int id, String adresse, String date) {
        this.id = id;
        this.adresse = adresse;
        this.date = date;
    }

  

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLivreur() {
        return livreur;
    }

    public void setLivreur(String livreur) {
        this.livreur = livreur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Mission{" + "id=" + id + ", status=" + status + ", livreur=" + livreur + ", adresse=" + adresse + ", date=" + date + ", idUser=" + idUser + '}';
    }
    
    
}
