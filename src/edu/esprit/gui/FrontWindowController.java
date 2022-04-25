/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Commandestock;
import edu.esprit.entities.Fournisseur;
import edu.esprit.entities.Produit;
import edu.esprit.services.CommandestockCRUD;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontWindowController implements Initializable {

    @FXML
    private TableView<Commandestock> tv;
    @FXML
    private TableColumn<Commandestock, Integer> colid;
    @FXML
    private TableColumn<Commandestock, String> coldate;
    @FXML
    private TableColumn<Commandestock, String> colquantite;
    @FXML
    private TableColumn<Fournisseur, String> colfournisseur;
    @FXML
    private TableColumn<Produit, String> colproduit;
ObservableList<Commandestock> List ;
private CommandestockCRUD CommandestockCRUD = new CommandestockCRUD();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        addEtatColumn();
        showCommandestock();
        
    }    

    @FXML
    private void handelMouseAction(MouseEvent event) {
    }
    public void showCommandestock(){
        
        CommandestockCRUD fcdf = new CommandestockCRUD();
        ArrayList<Commandestock> c = new ArrayList<>();
        for (Commandestock fo:fcdf.listCommandestock()){
        c.add(fo);
        }       
        colid.setCellValueFactory(new PropertyValueFactory<Commandestock,Integer>("id"));
        coldate.setCellValueFactory(new PropertyValueFactory<Commandestock, String>("date"));

        colquantite.setCellValueFactory(new PropertyValueFactory<Commandestock, String>("quantite"));
        colfournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("fournisseur"));
        colproduit.setCellValueFactory(new PropertyValueFactory<Produit, String>("produit"));
             
        List=FXCollections.observableArrayList(c);
        tv.setItems(List);
    
    }
    
     private void addEtatColumn() {
        TableColumn<Commandestock, Void> colBtn = new TableColumn("Etat");
        
        Callback<TableColumn<Commandestock, Void>, TableCell<Commandestock, Void>> cellFactory = new Callback<TableColumn<Commandestock, Void>, TableCell<Commandestock, Void>>() {
            
            public TableCell<Commandestock, Void> call(final TableColumn<Commandestock, Void> param) {
                
                
                final TableCell<Commandestock, Void> cell = new TableCell<Commandestock, Void>() {
                    ComboBox combEtat = new ComboBox();
                  
                    {
                        combEtat.getItems().add("Accepter");
                        combEtat.getItems().add("Refuser");
                          CommandestockCRUD pcd = new CommandestockCRUD();
                       for(Commandestock u : pcd.listCommandestock()){
                         combEtat.getSelectionModel().selectedItemProperty().addListener((ObservableValue options, Object oldValue, Object newValue) -> {
                             //combEtat.getSelectionModel().setSelectedItem("b");

                            Commandestock data = getTableView().getItems().get(getIndex());
                            data.setEtat((String) newValue);
                          CommandestockCRUD.modifierCommandestock(data);
                        });
                       }
                      
                    }
                    
                    
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Commandestock data = getTableView().getItems().get(getIndex());
                            combEtat.setValue(data.getEtat());
                            setGraphic(combEtat);
                        }
                    }
                };
                return cell;
            }
        };
        
        colBtn.setCellFactory(cellFactory);
        
        tv.getColumns().add(colBtn);
        
    }
}
