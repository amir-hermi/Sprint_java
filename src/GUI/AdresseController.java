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
import javafx.scene.control.ListView;
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
    ListView<String> list;
    ObservableList<String> arr = FXCollections.observableArrayList("Java", "JTaL", "CSS");
ObservableList<String> res = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        list.setVisible(false);
        remplirList(arr);
        adresse.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!resultatContains(newValue).isEmpty()) {
                try{
                list.setVisible(true);
                remplirList(resultatContains(newValue));    
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
                 
            }

        });
        
        
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try{
              adresse.setText(newValue);
              list.getSelectionModel().clearSelection();
            remplirList(resultatContains(newValue));  
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }
            
        });
    }
    

    public ObservableList<String> resultatContains(String value) {
        res.clear();
        try{
          this.arr.stream().filter(e -> e.toLowerCase().contains(value.toLowerCase())).forEach(e -> {
            res.add(e);
        });
        if (res.isEmpty()){
            list.setVisible(false);
        }else{
            list.setVisible(true);
        }  
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return res;
    }
    public void remplirList(ObservableList<String> l){
        list.setItems(l);
    }

}
