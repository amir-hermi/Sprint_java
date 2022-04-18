/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.services.CommandeService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class UpdateCommandeFormController implements Initializable {

    @FXML
    private Text ref;
    @FXML
    private ComboBox<String> status;
    @FXML
    private Button confirmer;
    private int id;

    /**
     * Initializes the controller class.
     */
    URL url;
    ResourceBundle rb;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "En attente",
                        "Confirmée",
                        "Annulée",
                        "En cours de preparation",
                        "Livraison en cours",
                        "Livrée"
                );
        status.setItems(options);

    }

    public void setData(int id, int ref, String status) {
        this.status.getSelectionModel().select(status);
        this.ref.setText(String.valueOf(ref));
        this.id = id;
        System.out.println(this.ref.getText());
    }

    @FXML
    private void confirmer(ActionEvent event) {
        try {
            Stage stage = new Stage();
            CommandeService cs = new CommandeService();
            cs.UpdateCommande(id, status.getSelectionModel().getSelectedItem());
            FXMLLoader loader =new FXMLLoader(getClass().getResource("AdminPanel.fxml"));
            AnchorPane root = loader.load();
            AdminPanelController pc = loader.getController();//jebna lcontroller mtaa personneDet 
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.show();
             Stage close = (Stage) this.status.getScene().getWindow();
            close.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }

    }

}
