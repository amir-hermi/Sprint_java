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
public class Reclamation {
    private int id;
    private String status ="en cours";
    private String description,categorie_reclamation;
    private String date;
    private int idUser;
   

    public Reclamation() {
    }

    public Reclamation(int id, String status, String description) {
        this.id = id;
        this.status = status;
        this.description = description;
    }

    public Reclamation(String categorie_reclamation, String description, String date, int idUser) {
        this.categorie_reclamation = categorie_reclamation;
        this.description = description;
        this.date = date;
        this.idUser = idUser;
    }

    

   


    public Reclamation(int id, String status, String description, String categorie_reclamation, String date, int idUser) {
        this.id = id;
        this.status = status;
        this.description = description;
        this.categorie_reclamation = categorie_reclamation;
        this.date = date;
        this.idUser = idUser;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCategorie_reclamation() {
        return categorie_reclamation;
    }

    public void setCategorie_reclamation(String categorie_reclamation) {
        this.categorie_reclamation = categorie_reclamation;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", status=" + status + ", description=" + description + ", categorie_reclamation=" + categorie_reclamation + ", date=" + date + ", idUser=" + idUser + '}';
    }



    

   
}
