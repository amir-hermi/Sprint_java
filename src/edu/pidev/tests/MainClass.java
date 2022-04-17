/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.tests;

import edu.pidev.entites.Mission;
import edu.pidev.entites.Reclamation;
import edu.pidev.services.MissionCRUD;
import edu.pidev.services.ReclamationCRUD;
import edu.pidev.utils.MyConnection;
import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author ASUS
 */
public class MainClass {
    public static void main(String[] args) {
        ///////////////////////////////////////reclamation/////////////////////
        //MyConnection mc = new MyConnection();
        //ReclamationCRUD rcd = new ReclamationCRUD();
        //LocalDate date = LocalDate.now();
        //Reclamation r2 = new Reclamation("taille incorrectee","Changement Produit",String.valueOf(date),1);
        //rcd.ajouterReclamation(r2);
        //System.out.println(rcd.afficherReclamations());
        //rcd.supprimerReclamation(r2);
        //rcd.modifierReclamation();
        
       ///////////////////////////////////////mission/////////////////////
        MissionCRUD mcd = new MissionCRUD();
        LocalDate date = LocalDate.now();
        Mission m = new Mission("Ras jebal","faten",String.valueOf(date),1);
        //mcd.ajouterMission(m);
        //System.out.println(mcd.afficherMissions());
         //mcd.supprimerMission(m);
        mcd.modifierMission(9,"refuser");
    }
    
}
