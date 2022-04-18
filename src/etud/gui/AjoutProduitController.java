/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.Produit;
import etud.services.ProduitCRUD;
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
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField pprix;
    @FXML
    private TextField pimage;
    @FXML
    private TextField pquantite;
    @FXML
    private TextField ptaille;
    @FXML
    private TextField pnom;
    private TextField pmarque;
    @FXML
    private TextField pdescription;
    @FXML
    private Button AjouterProduit;
    private TextField psouscategorie;
    @FXML
    private ComboBox<String> combomarque;
    @FXML
    private ComboBox<String> combosouscat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ProduitCRUD p1 = new ProduitCRUD() ; 
        combomarque.setItems(p1.GetListmarque());
        combosouscat.setItems(p1.GetListsouscategorie());
    }    

    @FXML
    private void AjoutProduit(ActionEvent event) {
        ProduitCRUD p1 = new ProduitCRUD() ; 
         float prix = Float.parseFloat(pprix.getText());
        String image = pimage.getText();
        float quantite =Float.parseFloat(pquantite.getText()) ;
        String taille = ptaille.getText();
        String nom = pnom.getText();
        int marque = p1.GetmarqueId( combomarque.getSelectionModel().getSelectedItem().toString()) ; 
        int sous_cat = p1.GetsouscategorieId(combosouscat.getSelectionModel().getSelectedItem().toString()) ; 
        String description = pdescription.getText(); 
        
        Produit p = new Produit (prix,image,(int) quantite,taille,nom,marque,sous_cat,description);
        ProduitCRUD prod1 = new ProduitCRUD();
         prod1.AjoutProduit(p);
    }
    
}
