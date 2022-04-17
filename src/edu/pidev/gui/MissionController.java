/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import edu.pidev.entites.Mission;
import edu.pidev.services.MissionCRUD;
import edu.pidev.utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class MissionController implements Initializable {

    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfLivreur;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button annuler;
    @FXML
    private DatePicker tfDate;
    private boolean update;
    int missionId;
    String query = null;
     Connection connection = null;
      ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Connection cnx2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveMission(ActionEvent event) {
            connection = MyConnection.getConnect();
            String adresse = tfAdresse.getText();
            String livreur = tfLivreur.getText();
            String date = String.valueOf(tfDate.getValue());
              //LocalDate date = LocalDate.now();
            if(adresse.isEmpty() || livreur.isEmpty() || date.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Remplir les champs SVP !!");
                alert.showAndWait();
            }
            else{
       getQuery();
       insert();
           
    }
    }

    @FXML
    private void annuler(ActionEvent event) {
        tfAdresse.setText(null);
        tfLivreur.setText(null);
        tfDate.setValue(null);
    }

    @FXML
    private void getDate(ActionEvent event) {
        LocalDate myDate = tfDate.getValue();

    }
    
    void setUpdate(boolean b) {
        this.update = b;

    }
    
     void setTextField(int id, String adresse, String livreur) {

        missionId = id;
        tfAdresse.setText(adresse);
        tfLivreur.setText(livreur);
        
    

    }
     private void getQuery() {

        if (update == false) {
            
            query = "INSERT INTO  `mission`( `adresse`, `livreur`, `date`) VALUES (?,?,?)";

        }else{
            query = "UPDATE `mission` SET "
                    + "`adresse`=?,"
                    + "`livreur`=?,"
                    + "`date`= ? WHERE id = '"+missionId+"'";
        }

    }

    private void insert() {
      
 try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tfAdresse.getText());
            preparedStatement.setString(2, tfLivreur.getText());
            preparedStatement.setString(3, String.valueOf(tfDate.getValue()));
           
//           preparedStatement.executeUpdate();
          
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("la mission est bien affectée");
                alert.showAndWait();
                
        } catch (SQLException ex) {
            Logger.getLogger(MissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
