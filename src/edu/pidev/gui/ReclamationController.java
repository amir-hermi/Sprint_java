/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import edu.pidev.entites.Reclamation;
import edu.pidev.services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReclamationController implements Initializable {

    private TextField tfCategorie;
    @FXML
    private TextField tfDescription;
    @FXML
    private Button btnReclamer;
    @FXML
    private Button annuler;
    @FXML
    private ComboBox comb;
    @FXML
    private Button historique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ObservableList<String> list = FXCollections.observableArrayList("Changement Produit","Retard Commande","Commande Incorrecte");
         comb.setItems(list);
    }    

    @FXML
    private void saveReclamation(ActionEvent event) {
            String categorie = comb.getSelectionModel().getSelectedItem().toString();
            String description = tfDescription.getText();
            LocalDate date = LocalDate.now();
           if(categorie.isEmpty() || description.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Remplir les champs SVP !!");
                alert.showAndWait();
            }
            else{
            Reclamation r = new Reclamation(categorie,description,String.valueOf(date),1);
            ReclamationCRUD rc = new ReclamationCRUD();
            rc.ajouterReclamation(r);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Vottre reclamation est bien envoyée");
                alert.showAndWait();
           }
            
    }

    @FXML
    private void annuler(ActionEvent event) {
       
        tfDescription.setText(null);
        
    }

    @FXML
    private void select(ActionEvent event) {
    }

    @FXML
    private void historique(ActionEvent event) {
         try {
            Parent parent = FXMLLoader.load(getClass().getResource("Historique.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("Mission.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
