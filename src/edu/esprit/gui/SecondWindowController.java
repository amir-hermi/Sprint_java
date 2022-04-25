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
import edu.esprit.services.FournisseurCRUD;
import java.net.URL;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class SecondWindowController implements Initializable {
    @FXML
    private TextField tfid;
    //private TextField tfdate;
   // private TextField tfetat;
    @FXML
    private TextField tfquantite;
    @FXML
    private TextField tffournisseur;
    @FXML
    private TextField tfproduit;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Commandestock, Integer> colid;
    @FXML
    private TableColumn<Commandestock, String> coldate;
    @FXML
    private TableColumn<Commandestock, String> coletat;
    @FXML
    private TableColumn<Commandestock, String> colquantite;
    @FXML
    private TableColumn<Fournisseur, String> colfournisseur;
    @FXML
    private TableColumn<Produit, String> colproduit;
    @FXML
    private TableView<Commandestock> tv;

    /**
     * Initializes the controller class.
     */
     ObservableList<Commandestock> List ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showCommandestock();
    }    

    public void showCommandestock(){
        
        CommandestockCRUD fcdf = new CommandestockCRUD();
        ArrayList<Commandestock> c = new ArrayList<>();
        for (Commandestock fo:fcdf.listCommandestock()){
        c.add(fo);
        }       
        colid.setCellValueFactory(new PropertyValueFactory<Commandestock,Integer>("id"));
        coldate.setCellValueFactory(new PropertyValueFactory<Commandestock, String>("date"));
        coletat.setCellValueFactory(new PropertyValueFactory<Commandestock, String>("etat"));
        colquantite.setCellValueFactory(new PropertyValueFactory<Commandestock, String>("quantite"));
        colfournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("fournisseur"));
        colproduit.setCellValueFactory(new PropertyValueFactory<Produit, String>("produit"));
             
        List=FXCollections.observableArrayList(c);
        tv.setItems(List);
    
    }
    
    @FXML
    private void AddCS(ActionEvent event) {
        //String date = tfdate.getText();
        //String etat = tfetat.getText();
        String quantite = tfquantite.getText();
        String lastname = tffournisseur.getText();
        String prod = tfproduit.getText();         
        //instancier la class Fournisseur
        try{
            FournisseurCRUD fcr =new FournisseurCRUD();
            ArrayList<Fournisseur> four = (ArrayList<Fournisseur>) fcr.getFournisseur(lastname);
            ArrayList<Produit> p = (ArrayList<Produit>) fcr.getProduit(prod);
        Commandestock c = new Commandestock( quantite, four.get(0)); 
        CommandestockCRUD fc =new CommandestockCRUD ();
        //invoqué notre méthode d'ajout :ajouterFournisseur()
        fc.ajoutCommandestock(c , p);
        refresh();
       showCommandestock(); 
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }        
    }

    @FXML
    private void DeleteCS(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de supprimer cette commandeStock??");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert xx = new Alert(Alert.AlertType.INFORMATION);
                xx.setContentText("Commandestock est supprimé avec succes!");
                xx.showAndWait();
            }
         Commandestock c = new Commandestock()  ;
         CommandestockCRUD fc =new CommandestockCRUD();
         int id = Integer.parseInt(tfid.getText());
         c.setId(id);
         fc.supprimerCommandestock(c);
         refresh();
         showCommandestock();           
    }

    @FXML
    private void handelMouseAction(MouseEvent event) {
       Commandestock Commandestocks = tv.getSelectionModel().getSelectedItem();
        tfid.setText(""+Commandestocks.getId());
        tfquantite.setText(Commandestocks.getQuantite());
        tffournisseur.setText(""+Commandestocks.getFournisseur());
        tfproduit.setText(""+Commandestocks.getProduit());
        
        showCommandestock(); 
    }
    
    public void refresh(){
       tfid.setText("");
     //tfdate.setText("");
     //tfetat.setText("");
       tfquantite.setText("");
       tffournisseur.setText(" ");
       tfproduit.setText(" ");
        
    }
    
}
