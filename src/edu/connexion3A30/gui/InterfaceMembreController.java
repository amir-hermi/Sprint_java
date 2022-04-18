/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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

    @FXML
    private void ActionProfilbtn(ActionEvent event) {
    }

    @FXML
    private void ActionLogOffbtn(ActionEvent event) {
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
