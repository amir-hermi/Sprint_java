/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import edu.connexion3A30.entities.Session;
import edu.connexion3A30.entities.Utilisateur;
import edu.connexion3A30.services.PersonneCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class GestionUserController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableviewuser;
    @FXML
    private TableColumn<?, ?> email_col;
    @FXML
    private TableColumn<Utilisateur, String> username_col;
    @FXML
    private TableColumn<Utilisateur, String> password_col;
    @FXML
    private TableColumn<?, ?> add_col;
    @FXML
    private TableColumn<?, ?> date_col;
    @FXML
    private TableColumn<?, ?> numero_col;
    @FXML
    private TableColumn<Utilisateur, String> role_col;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField usernametf;
    @FXML
    private PasswordField passwordpf;
    @FXML
    private TextField numerotf;
    @FXML
    private ComboBox<Utilisateur> comborole;
    @FXML
    private TextField recherchetf;
    @FXML
    private ComboBox<?> combotri;
    @FXML
    private DatePicker dpdate;
    @FXML
    private Button btntri;
    @FXML
    private Button btnHome;
    
      PersonneCRUD us =new PersonneCRUD();
    @FXML
    private TableColumn<Utilisateur, String> last_name;
    @FXML
    private TableColumn<Utilisateur, String> etat_col;
    @FXML
    private TextField lastname;
       ObservableList<Utilisateur> data=FXCollections.observableArrayList();
    
    ObservableList<String> ss=FXCollections.observableArrayList();
    private PersonneCRUD personneCRUD = new PersonneCRUD();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ss.add("Par nom");
        ss.add("Par id");
     
        addEtatColumn();
        refreshlist();
    }    
    
       private void addEtatColumn() {
        TableColumn<Utilisateur, Void> colBtn = new TableColumn("Etat");
        
        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> cellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {
            
            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {
                
                
                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {
                    ComboBox combEtat = new ComboBox();
                  
                    {
                        combEtat.getItems().add("Debloquer");
                        combEtat.getItems().add("Bloquer");
                          PersonneCRUD pcd = new PersonneCRUD();
                       for(Utilisateur u : pcd.afficher()){
                         combEtat.getSelectionModel().selectedItemProperty().addListener((ObservableValue options, Object oldValue, Object newValue) -> {
                             //combEtat.getSelectionModel().setSelectedItem("b");
                             
                             
                            Utilisateur data = getTableView().getItems().get(getIndex());
                            data.setEtat((String) newValue);
                          personneCRUD.UpdatePersonne(data);
                        });
                       }
                      
                    }
                    
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Utilisateur data = getTableView().getItems().get(getIndex());
                            combEtat.setValue(data.getEtat());
                            setGraphic(combEtat);
                        }
                    }
                };
                return cell;
            }
        };
        
        colBtn.setCellFactory(cellFactory);
        
        tableviewuser.getColumns().add(colBtn);
        
    }
    
    
    
    public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(us.afficher());
        username_col.setCellValueFactory(new PropertyValueFactory<>("Username"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        
        email_col.setCellValueFactory(new PropertyValueFactory<>("Email"));
       
        password_col.setCellValueFactory(new PropertyValueFactory<>("Password"));
    
        
        date_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        numero_col.setCellValueFactory(new PropertyValueFactory<>("tel"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("Role"));
        tableviewuser.setItems(data);
    }

    @FXML
    private void fillforum(MouseEvent event) {
    }

    @FXML
    private void ajouterUser(ActionEvent event) {
    }

    @FXML
    private void modifierUser(ActionEvent event) {
    }

    @FXML
    private void supprimerUser(ActionEvent event) {
    }

    @FXML
    private void imprimer(ActionEvent event) {
    }

    @FXML
    private void trilist(ActionEvent event) {
        if(combotri.getValue().equals("Par nom")){
            ObservableList<Utilisateur> tri1=FXCollections.observableArrayList();
            tri1=FXCollections.observableArrayList(us.sortByNom());
            tableviewuser.setItems(tri1);
            
        }
        else if(combotri.getValue().equals("Par id")){
            ObservableList<Utilisateur> tri2=FXCollections.observableArrayList();
            tri2=FXCollections.observableArrayList(us.sortById());
            tableviewuser.setItems(tri2);
        }
    }

    @FXML
    private void goBack(ActionEvent event) {
         btnHome.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            stage.setTitle("");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/InterfaceMembre.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
