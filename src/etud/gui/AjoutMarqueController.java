/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.Marque;
import etud.services.MarqueCRUD;
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
public class AjoutMarqueController implements Initializable {

    @FXML
    private TextField mlibelle;
    @FXML
    private Button Ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutMarque(ActionEvent event) {
    String libelle = mlibelle.getText();
    Marque m = new Marque(libelle);
    MarqueCRUD m2 = new MarqueCRUD();
    m2.AjoutMarque(m);
    }
    
}
