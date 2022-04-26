/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class GestionController implements Initializable {

    @FXML
    private TableView<?> tableviewuser;
    @FXML
    private TableColumn<?, ?> username_col;
    @FXML
    private TableColumn<?, ?> last_name;
    @FXML
    private TableColumn<?, ?> email_col;
    @FXML
    private TableColumn<?, ?> password_col;
    @FXML
    private TableColumn<?, ?> add_col;
    @FXML
    private TableColumn<?, ?> date_col;
    @FXML
    private TableColumn<?, ?> numero_col;
    @FXML
    private TableColumn<?, ?> role_col;
    @FXML
    private TextField recherchetf;
    @FXML
    private ComboBox<?> combotri;
    @FXML
    private Button btntri;
    @FXML
    private Button btnHome;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void trilist(ActionEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) {
    }
    
}
