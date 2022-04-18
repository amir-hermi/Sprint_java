/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.sun.xml.internal.stream.buffer.sax.Properties;
import etud.entitiy.Commande;
import etud.entitiy.Utilisateur;
import etud.services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminPanelController implements Initializable {

    @FXML
    private TableColumn<Commande, Integer> ref;
    @FXML
    private TableColumn<Commande, Float> montant;
    @FXML
    private TableColumn<Commande, String> date;
    @FXML
    private TableColumn<Commande, String> status;
    ObservableList<Commande> list;
    @FXML
    private TableView<Commande> table;
    @FXML
    private TableColumn<Commande, Utilisateur> user;
    @FXML
    private TableColumn<Commande, Integer> produits;
    @FXML
    private TableColumn<Commande, String> adresse;
    @FXML
    private Text c;
    @FXML
    private Button triter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ArrayList<Commande> c = new ArrayList<>();
        CommandeService cs = new CommandeService();
        System.out.println(cs.listCommande());
        for (Commande commande : cs.listCommande()) {
            commande.setNbP(commande.getProduits().size());
            c.add(commande);
        }
       updateView(c);
    }
    public void close(){
        Stage close = (Stage) this.triter.getScene().getWindow();
            close.close();
    }
    public void updateView(ArrayList<Commande>c){
  try {
            ref.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("reference"));
            montant.setCellValueFactory(new PropertyValueFactory<Commande, Float>("montant"));
            date.setCellValueFactory(new PropertyValueFactory<Commande, String>("date_creation"));
            status.setCellValueFactory(new PropertyValueFactory<Commande, String>("status"));
            user.setCellValueFactory(new PropertyValueFactory<Commande, Utilisateur>("user"));
            produits.setCellValueFactory(new PropertyValueFactory<Commande, Integer>("nbP"));
            adresse.setCellValueFactory(new PropertyValueFactory<Commande, String>("adresse"));
            list = FXCollections.observableArrayList(c);
            table.setItems(list);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void triter(ActionEvent event) {
        Stage ModifStage = new Stage();
        if (!table.getSelectionModel().getSelectedCells().isEmpty()) {
            
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateCommandeForm.fxml"));
                Parent root = loader.load();
                UpdateCommandeFormController pc = loader.getController();
                table.getSelectionModel().getSelectedItems().stream().forEach(e -> {
                    pc.setData(e.getId(), Integer.parseInt(e.getReference()), e.getStatus());
                });
                close();
                Scene scene = new Scene(root, 400, 400);
                ModifStage.setScene(scene);
                ModifStage.setTitle("Modifier");
                ModifStage.show();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Selectionner une commande");
            alert.show();
        }
    }

}
