/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Fournisseur;
import edu.esprit.services.FournisseurCRUD;
import edu.esprit.utils.MyConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FXML Controller class
 *
 * @author HP
 */

public class AddFirstWindowController implements Initializable {
Connection cnx2;
private boolean update;  String query = null;   int fournisseurId;PreparedStatement preparedStatement;  Connection connection = null;

    private Label tfid;
    @FXML
    private Label tfnom_fournisseur;
    @FXML
    private Label tfadresse_fournisseur;
    @FXML
    private Label tfMDP_fournisseur;
    @FXML
    private Label tflastname;
    @FXML
    private Label tftel;
    @FXML
    private TableView<Fournisseur> tvFournisseurs;
    @FXML
    private TableColumn<Fournisseur, Integer> colid;
    @FXML
    private TableColumn<Fournisseur, String> colnom_fournisseur;
    @FXML
    private TableColumn<Fournisseur, String> coladresse_fournisseur;
    @FXML
    private TableColumn<Fournisseur, String> colMDP_fournisseur;
    @FXML
    private TableColumn<Fournisseur, String> collastname;
    @FXML
    private TableColumn<Fournisseur, String> coltel;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private VBox tfvBox;
    @FXML
    private VBox tfv;

    /**
     * Initializes the controller class.
     */
    
    ObservableList<Fournisseur> List ;
    ObservableList<Fournisseur> data=FXCollections.observableArrayList();
    FournisseurCRUD fcrud = new FournisseurCRUD();
    @FXML
    private TextField cid;
    @FXML
    private TextField cnom_fournisseur;
    @FXML
    private TextField cadresse_fournisseur;
    @FXML
    private TextField cMDP_fournisseur;
    @FXML
    private TextField clastname;
    @FXML
    private TextField ctel;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showFournisseur();
        
        //this will allow the table to select multiple rows at once 
        //tvFournisseurs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    }   
    
   public void showFournisseur(){
        
        FournisseurCRUD fcdf = new FournisseurCRUD();
        ArrayList<Fournisseur> f = new ArrayList<>();
        for (Fournisseur fo:fcdf.afficherFournisseur()){
        f.add(fo);
        }       
        colid.setCellValueFactory(new PropertyValueFactory<Fournisseur,Integer>("id"));
        colnom_fournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("nom_fournisseur"));
        coladresse_fournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("adresse_fournisseur"));
        colMDP_fournisseur.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("MDP_fournisseur"));
        collastname.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("lastname"));
        coltel.setCellValueFactory(new PropertyValueFactory<Fournisseur, String>("tel"));
             
        List=FXCollections.observableArrayList(f);
        tvFournisseurs.setItems(List);
    
    }   
/*
  public void settfdid(String message){
        this.tfid.setText(message);
    }        
     public void settfdnom_fournisseur(String message) {
        this.tfnom_fournisseur.setText(message);
    }
    public void settfdadresse_fournisseur(String message) {
        this.tfadresse_fournisseur.setText(message);
    }
    public void settfdMDP_fournisseur(String message) {
        this.tfMDP_fournisseur.setText(message);
    }
    public void settfdlastname(String message) {
        this.tflastname.setText(message);
    }
    public void settfdtel(String message) {
        this.tftel.setText(message);
    }
   */
    @FXML
    private void Add(ActionEvent event) {
        
      //récuperer les donneés saisie dans le formulaire 
        String nom_fournisseur = cnom_fournisseur.getText();
        String adresse_fournisseur = cadresse_fournisseur.getText();
        String MDP_fournisseur = cMDP_fournisseur.getText();
        String lastname = clastname.getText();
        String tel = ctel.getText();         
        //instancier la class Fournisseur
        try{
        Fournisseur f = new Fournisseur( nom_fournisseur, adresse_fournisseur, MDP_fournisseur, lastname, tel); 
        FournisseurCRUD fc =new FournisseurCRUD();
        //invoqué notre méthode d'ajout :ajouterFournisseur()
        fc.ajouterFournisseur2(f);
        refresh();
        showFournisseur(); 
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
       
    }
   /*     
void setUpdate(boolean b) {
        this.update = b;
}*/
    @FXML
    private void Update(ActionEvent event) {
       
        if (cid.getText().isEmpty() || cnom_fournisseur.getText().isEmpty() || cadresse_fournisseur.getText().isEmpty()|| cMDP_fournisseur.getText().isEmpty()|| clastname.getText().isEmpty()|| ctel.getText().isEmpty() ){
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("veuillez remplir tous les champs !");
            alert.showAndWait();
        }
        else if (!(Pattern.matches("[0-9]*", cid.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'idenentifiant doit etre un entier  !");
            alert.showAndWait();
        }
        else if (!(Pattern.matches("[a-z,A-Z]*", cnom_fournisseur.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le nom doit etre string !");
            alert.showAndWait();
        }
        /* else if (!(Pattern.matches("[a-z,A-Z]*", cadresse_fournisseur.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("L'adresse doit etre string !");
            alert.showAndWait();
        }*/
         else if (!(Pattern.matches("[a-z,A-Z]*", cMDP_fournisseur.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("mdp doit etre string !");
            alert.showAndWait();
        }
         else if (!(Pattern.matches("[a-z,A-Z]*", clastname.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le description de categorie doit etre string !");
            alert.showAndWait();
        }
         
         else if (cadresse_fournisseur.getText().isEmpty()
                || !cadresse_fournisseur.getText().contains("@")
                || !cadresse_fournisseur.getText().contains(".")
                || cadresse_fournisseur.getText().indexOf("#", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("&", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("(", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("Â§", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("!", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("Ã§", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("Ã ", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("Ã©", 0) >= 0
                || cadresse_fournisseur.getText().indexOf(")", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("{", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("}", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("|", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("$", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("*", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("â‚¬", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("`", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("\'", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("\"", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("%", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("+", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("=", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("/", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("\\", 0) >= 0
                || cadresse_fournisseur.getText().indexOf(":", 0) >= 0
                || cadresse_fournisseur.getText().indexOf(",", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("?", 0) >= 0
                || cadresse_fournisseur.getText().indexOf(";", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("Â°", 0) >= 0
                || cadresse_fournisseur.getText().indexOf("<", 0) >= 0
                || cadresse_fournisseur.getText().indexOf(">", 0) >= 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Invalid mail !");
            alert.showAndWait();
            return;
        }
        /* else if (!(Pattern.matches("[a-z,A-Z]*", ctel.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Le description de categorie doit etre string !");
            alert.showAndWait();
        }*/

        else {
         int id=Integer.parseInt(cid.getText());
        String nom=cnom_fournisseur.getText();
        String adresse=cadresse_fournisseur.getText();
        String mdp=cMDP_fournisseur.getText();
        String prenom=clastname.getText();
        String tel=ctel.getText();
          Fournisseur c =new Fournisseur();
          int x1=Integer.parseInt(cid.getText());
          c.setId(x1);
          c.setNom_fournisseur(nom);
          c.setAdresse_fournisseur(adresse);
          c.setMDP_fournisseur(mdp);
          c.setLastname(prenom);
          c.setTel(tel);
          
          //Fournisseur p= new Fournisseur();
          FournisseurCRUD fc =new FournisseurCRUD(); 
          fc.modifierFournisseur(c);
                  refresh();

        ObservableList<Fournisseur> items = FXCollections.observableArrayList();
        ArrayList<Fournisseur> listc = new ArrayList<>();
       
        for (Fournisseur r : listc) {
            String ch = r.toString();
            items.add(r);
        }
        tvFournisseurs.setItems(items);
         showFournisseur();   
        
    }
    }  
    
    
    @FXML
    private void Delete(ActionEvent event) {
       /* Fournisseur h = new Fournisseur();
        h.setId(Integer.parseInt(tfid.getText()));
        FournisseurCRUD hotelserv = new FournisseurCRUD();
        hotelserv.supprimerFournisseur(h);
        showFournisseur(); */       
       //supp de la ligne du tableau
        //tvFournisseurs.getItems().removeAll(tvFournisseurs.getSelectionModel().getSelectedItem());        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Êtes-vous sûr de supprimer ce fournisseur??");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Alert xx = new Alert(Alert.AlertType.INFORMATION);
                xx.setContentText("fournisseur est supprimé avec succes!");
                xx.showAndWait();
            }
         Fournisseur f = new Fournisseur()  ;
         FournisseurCRUD fc =new FournisseurCRUD();
         int id = Integer.parseInt(cid.getText());
         f.setId(id);
         fc.supprimerFournisseur(f);
         refresh();
         showFournisseur();   
         
    }    

    @FXML
    private void handelMouseAction(MouseEvent event) {
        Fournisseur Fournisseurss = tvFournisseurs.getSelectionModel().getSelectedItem();
        cid.setText(""+Fournisseurss.getId());
        cnom_fournisseur.setText(Fournisseurss.getNom_fournisseur());
        cadresse_fournisseur.setText(Fournisseurss.getAdresse_fournisseur());
        cMDP_fournisseur.setText(Fournisseurss.getMDP_fournisseur());
        clastname.setText(Fournisseurss.getLastname());
        ctel.setText(Fournisseurss.getTel()); 
        showFournisseur(); 
    }
        
    public void refresh(){
        cid.setText("");
        cnom_fournisseur.setText("");
        cadresse_fournisseur.setText("");
        cMDP_fournisseur.setText("");
        //cMDP_fournisseur.setValue(null);
        clastname.setText("");
        ctel.setText("");
        
    
    }
}