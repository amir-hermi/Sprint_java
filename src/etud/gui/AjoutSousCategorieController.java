/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.SousCategorie;
import etud.services.ProduitCRUD;
import etud.services.SousCategorieCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class AjoutSousCategorieController implements Initializable {

    @FXML
    private TextField sclibelle;
    @FXML
    private Button Ajouter;
    private TextField scat;
    @FXML
    private ComboBox<String> combocateg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SousCategorieCRUD s = new SousCategorieCRUD() ; 
        combocateg.setItems(s.GetListcategorie() );
        
    }    

    @FXML
    private void AjouterSousCategorie(ActionEvent event) {
                SousCategorieCRUD s = new SousCategorieCRUD() ; 

    String libelle = sclibelle.getText(); 
    int categorie = s.GetcategorieId(combocateg.getSelectionModel().getSelectedItem().toString()) ; 

    SousCategorie c = new SousCategorie(categorie,libelle);
    SousCategorieCRUD c1 = new SousCategorieCRUD();
    c1.AjoutSousCategorie(c);
    }
    
}
