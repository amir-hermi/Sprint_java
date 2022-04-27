/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.entitiy.Produit;
import etud.services.CommandeService;
import etud.services.PanierService;
import etud.utils.MyListener;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class ProduitEditCommController implements Initializable {

    @FXML
    private Text nameLabel;
    @FXML
    private Text priceLable;
    private Produit produit;
private MyListener listenner;
    @FXML
    private Text qte;
    @FXML
    private Text taille;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView img;

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
        qte.setText(String.valueOf(produit.getQantite()));
        taille.setText(produit.getTaille());
        final String imageURI2 = new File("C:/Users/hp/Desktop/amirtawtaw/public/images/" + p.getImage()).toURI().toString();
        final Image image2 = new Image(imageURI2);
        img.setImage(image2);
            }

   
    
}
