/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author bilel
 */
public class InterfaceMembreController {

    @FXML
    private AnchorPane rootPane3;
    @FXML
    private Pane paneId;
    @FXML
    private Button profilbtn;
    @FXML
    private Button logoffbtn1;
    @FXML
    private Button matchbtn;
    @FXML
    private Button tournoisbtn;
    @FXML
    private Button magasinbtn;
    @FXML
    private Button mescommandesbtn;
    @FXML
    private Button reclamationbtn;
    
      private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private void ActionProfilbtn(ActionEvent event) {
         try {
            root = FXMLLoader.load(getClass().getResource("UpdateProfile.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ActionLogOffbtn(ActionEvent event) {
               try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void ActionMatchbtn(ActionEvent event) {
    }

    @FXML
    private void ActionTournoisbtn(ActionEvent event) {
    }

    @FXML
    private void ActionMagasinbtn(ActionEvent event) {
    }

    @FXML
    private void ActionMesCommandesbtn(ActionEvent event) {
    }

    @FXML
    private void ActionReclamationbtn(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/inscription.fxml"));
        rootPane3.getChildren().setAll(pane);
    }
    
}
