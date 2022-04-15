/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.entitiy.Commande;
import etud.entitiy.Produit;
import etud.utils.MyListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ProduitCom implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;
private Produit produit;
private MyListener listenner;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
        listenner.onClickListener(produit);
    }
    public void setData(Produit p , MyListener l){
        this.produit =p;
        this.listenner = l;
        nameLabel.setText(produit.getNom());
        priceLable.setText(String.valueOf(produit.getPrix()) );
       // Image image = new Image(getClass().getResourceAsStream("E:\\Workspace_Dev\\java\\SportTechJava\\activshop_panier_logo.png"));
    }
    
}
