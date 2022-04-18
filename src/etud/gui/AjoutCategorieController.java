/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.Categorie;
import etud.services.CategorieCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class AjoutCategorieController implements Initializable {

    @FXML
    private Button Ajouter;
    @FXML
    private TextField clibelle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutCategorie(ActionEvent event) {
    
    String libelle = clibelle.getText();    
    Categorie c = new Categorie(libelle);
    CategorieCRUD c1 = new CategorieCRUD();
    c1.AjoutCategorie(c);
    }
    
}
