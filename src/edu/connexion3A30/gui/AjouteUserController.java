/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import edu.connexion3A30.entities.Utilisateur;
import edu.connexion3A30.services.PersonneCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class AjouteUserController implements Initializable {

    @FXML
    private TextField adresseU;
    @FXML
    private TextField nomU;
    @FXML
    private TextField prenomU;
    @FXML
    private TextField emailU;
    @FXML
    private DatePicker dateU;
    @FXML
    private TextField telU;

    @FXML
    private TextField pwdU;
    
     private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    private void ajouteUserr(ActionEvent event) {
        
        
         PersonneCRUD sp = new PersonneCRUD();
              StringBuilder errors=new StringBuilder();
        if (nomU.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Whsdfsde ?");
            alert.showAndWait();
            return;
        }
        if (prenomU.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Whsdfsdf ?");
            alert.showAndWait();
            return;
        }
        if (!(telU.getText().matches("\\d{8}")) && telU.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What 's your telephone number ?");
            alert.showAndWait();
            return;

        }
        
        if(dateU.getValue()==null){
            errors.append("- Please enter a Birthday\n");
            return;
        }
         
        if (emailU.getText().isEmpty()
                || !emailU.getText().contains("@")
                || !emailU.getText().contains(".")
                || emailU.getText().indexOf("#", 0) >= 0
                || emailU.getText().indexOf("&", 0) >= 0
                || emailU.getText().indexOf("(", 0) >= 0
                || emailU.getText().indexOf("Â§", 0) >= 0
                || emailU.getText().indexOf("!", 0) >= 0
                || emailU.getText().indexOf("Ã§", 0) >= 0
                || emailU.getText().indexOf("Ã ", 0) >= 0
                || emailU.getText().indexOf("Ã©", 0) >= 0
                || emailU.getText().indexOf(")", 0) >= 0
                || emailU.getText().indexOf("{", 0) >= 0
                || emailU.getText().indexOf("}", 0) >= 0
                || emailU.getText().indexOf("|", 0) >= 0
                || emailU.getText().indexOf("$", 0) >= 0
                || emailU.getText().indexOf("*", 0) >= 0
                || emailU.getText().indexOf("â‚¬", 0) >= 0
                || emailU.getText().indexOf("`", 0) >= 0
                || emailU.getText().indexOf("\'", 0) >= 0
                || emailU.getText().indexOf("\"", 0) >= 0
                || emailU.getText().indexOf("%", 0) >= 0
                || emailU.getText().indexOf("+", 0) >= 0
                || emailU.getText().indexOf("=", 0) >= 0
                || emailU.getText().indexOf("/", 0) >= 0
                || emailU.getText().indexOf("\\", 0) >= 0
                || emailU.getText().indexOf(":", 0) >= 0
                || emailU.getText().indexOf(",", 0) >= 0
                || emailU.getText().indexOf("?", 0) >= 0
                || emailU.getText().indexOf(";", 0) >= 0
                || emailU.getText().indexOf("Â°", 0) >= 0
                || emailU.getText().indexOf("<", 0) >= 0
                || emailU.getText().indexOf(">", 0) >= 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Invalid mail !");
            alert.showAndWait();
            return;
        }
        
        if (adresseU.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your name ?");
            alert.showAndWait();
            return;
        }
        
        if (pwdU.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your password ?");
            alert.showAndWait();
            return;
        }

 Utilisateur m = new Utilisateur(nomU.getText() , prenomU.getText(),"ROLE_USER", emailU.getText(), pwdU.getText(), telU.getText(),"Debloquer",adresseU.getText(),(Date.valueOf(dateU.getValue())) );       // Utilisateur p6 = new Utilisateur("chihe22b11", "cha2211h" , "ROLE_ADMIN" , "chihe1221b@gmail.com" ,"chiheb","9393399" );
        sp.ajouter(m);
        
        try {
            root = FXMLLoader.load(getClass().getResource("gestionUser.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void annulerUserr(ActionEvent event) {
         try {
            root = FXMLLoader.load(getClass().getResource("gestionUser.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    
   
    }
  
       
        
        
        
        
        
      

    
        

    

    
    

