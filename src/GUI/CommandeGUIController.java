/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.entitiy.Commande;
import etud.utils.MyListener;
import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class CommandeGUIController implements Initializable {

    @FXML
    private Text nameLabel;
    @FXML
    private Text ref;
    @FXML
    private Text montant;
    @FXML
    private Text total_produit;
    @FXML
    private Text date;
    @FXML
    private Text adresse;
    @FXML
    private Text status;
    Commande commande;
    MyListener listener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
                listener.onClickListener(commande);
    }
    public void setData(MyListener l , Commande c , int total){
         this.commande =c;
        this.listener = l;
        ref.setText(commande.getReference());
        montant.setText(String.valueOf(commande.getMontant()));
        total_produit.setText(String.valueOf(total));
        adresse.setText("mezel");
        date.setText(commande.getDate_creation());
        status.setText(commande.getStatus());
        if(commande.getStatus().equals("Annulée")){
            status.setStyle("-fx-fill: red;");
        }else
        if(commande.getStatus().equals("Confirmée")){
            status.setStyle("-fx-fill: green;");
        }else
        if(commande.getStatus().equals("En attente")){
            status.setStyle("-fx-fill: gray;");
        }else{
            status.setStyle("-fx-fill: green;");
        }
    }
    
}
