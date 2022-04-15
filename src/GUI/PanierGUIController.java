/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.entitiy.Commande;
import etud.entitiy.Produit;
import etud.services.CommandeService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PanierGUIController implements Initializable {


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    public VBox produit (){
        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
       // FileInputStream input=null;
        //try {
         //    input = new FileInputStream("");
        //} catch (FileNotFoundException ex) {
          //  System.out.println(ex.getMessage());
        //}
        //Image image =new Image(input);
        //ImageView imageView = new ImageView(image);
        //imageView.setFitHeight(100);
        //imageView.setFitWidth(100);
        Label nom = new Label("nom");
        Label prix = new Label("prix");
        layout.getChildren().addAll(nom,prix);
        return layout;
        
        
    }
    
}
