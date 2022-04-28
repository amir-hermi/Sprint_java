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
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class GestionUserController implements Initializable {

    @FXML
    private TableView<Utilisateur> tableviewuser;
    @FXML
    private TableColumn<Utilisateur, String> email_col;
    @FXML
    private TableColumn<Utilisateur, String> username_col;
    @FXML
    private TableColumn<Utilisateur, String> password_col;
    @FXML
    private TableColumn<Utilisateur, String> add_col;
    @FXML
    private TableColumn<?, ?> date_col;
    @FXML
    private TableColumn<Utilisateur, String> numero_col;
    @FXML
    private TableColumn<Utilisateur, String> role_col;
    @FXML
    private TextField recherchetf;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private Button btntri;
    @FXML
    private Button btnHome;
     @FXML
    private TableColumn<Utilisateur, String> last_name;
    
       private Stage stage;
    private Scene scene;
    private Parent root;
    
      PersonneCRUD us =new PersonneCRUD();
   
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
    
        combotri.setItems(ss);
        //refreshlist();
        recherche_avance();
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
       
       
       
    public void recherche_avance(){
       
        
    }
    
    
    
    public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(us.afficher());
        username_col.setCellValueFactory(new PropertyValueFactory<>("Username"));
        last_name.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        
        email_col.setCellValueFactory(new PropertyValueFactory<>("Email"));
       
        password_col.setCellValueFactory(new PropertyValueFactory<>("Password"));
        add_col.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        
        date_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        numero_col.setCellValueFactory(new PropertyValueFactory<>("tel"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("Role"));
        tableviewuser.setItems(data);
        
        FilteredList<Utilisateur> filteredData = new FilteredList<>(data, b -> true);
        recherchetf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Utilisateur -> {
                // If filter text is empty, display all persons.
                                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Utilisateur.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
              
                
                     else  
                         return false;
            });
        });

        tableviewuser.setItems(filteredData);
    }

    @FXML
    private void fillforum(MouseEvent event) {
    }

    @FXML
    private void ajouterUser(ActionEvent event) {
      try {
            root = FXMLLoader.load(getClass().getResource("jouteUser.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifierUser(ActionEvent event) {
      
        
        if (!tableviewuser.getSelectionModel().getSelectedCells().isEmpty()) {
           Stage ModifStage = new Stage();
            try {
                  Stage stage = (Stage) btntri.getScene().getWindow();
                  
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifeUser.fxml"));
                Parent root = loader.load();
                ModifeUserController pc = loader.getController();
                tableviewuser.getSelectionModel().getSelectedItems().stream().forEach(e -> {
                    pc.setData(e.getId() ,e.getUsername(),e.getEmail(),e.getAdresse(),e.getLastname(),e.getPassword(),e.getTel(),e.getDate_naissance());
                });
               
                 Scene scene = new Scene(root, 400, 400);
                ModifStage.setScene(scene);
                ModifStage.setTitle("Modifier");
                ModifStage.show();
                stage.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selectionner une user");
            alert.show();
        }
         
        
    }
    
   


    @FXML
    private void supprimerUser(ActionEvent event) {
         /*  String username=tableviewuser.getSelectionModel().getSelectedItem().getUsername();
        Utilisateur u=us.findByUsername(username);
        us.Delete(u.getId());
        TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Delete Success");
            tray.setMessage("User is deleted");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(1000));
            refreshlist();
*/
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
