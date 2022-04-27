/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.entitiy.Commande;
import etud.entitiy.Utilisateur;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdresseController implements Initializable {

    @FXML
    private TextField adresse;
    @FXML
    private TableView<String> table;
 ObservableList<String> list;
    @FXML
    private TableColumn<String, String> res;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    ArrayList<String> resultat = new ArrayList<>();

        table.setVisible(false);
        resultat.add("jendouba");
            resultat.add("Tunis");
            try {
            res.setCellValueFactory(new PropertyValueFactory<String, String>(""));

             list = FXCollections.observableArrayList(resultat);
            table.setItems(list);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        adresse.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.equals(" ")){
                table.setVisible(true);
                
            }
           
        });
    }    
    
}
