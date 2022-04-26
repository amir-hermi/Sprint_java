/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import edu.connexion3A30.entities.Utilisateur;
import edu.connexion3A30.services.PersonneCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class ModifeUserController implements Initializable {

    @FXML
    private TextField adresseU;
    @FXML
    private TextField nomU;
    @FXML
    private TextField prenomU;
    @FXML
    private TextField emailU;
    @FXML
    private DatePicker dateU;
    @FXML
    private TextField telU;
    @FXML
    private TextField pwdU;
    
    int id ; 
    
     PersonneCRUD us =new PersonneCRUD();
           ObservableList<Utilisateur> data=FXCollections.observableArrayList();
    
       private Stage stage;
    private Scene scene;
    private Parent root;
        long id_modif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
       
    }    


    @FXML
    private void annulerUserr(ActionEvent event) {
         try {
            root = FXMLLoader.load(getClass().getResource("gestionUser.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    

    @FXML
    private void ModifeUser(ActionEvent event) {
      
        /*  PersonneCRUD sp = new PersonneCRUD();
       int options = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "Vous etes sure de Modifier  cet utilisateur" ,"SERIOUS QUESTION", options, 3);
        if (result == JOptionPane.YES_OPTION) {
        Utilisateur m = new Utilisateur(nomU.getText() , prenomU.getText(),"ROLE_USER", emailU.getText(), pwdU.getText(), telU.getText(),"Debloquer",adresseU.getText(),(Date.valueOf(dateU.getValue())) );       // Utilisateur p6 = new Utilisateur("chihe22b11", "cha2211h" , "ROLE_ADMIN" , "chihe1221b@gmail.com" ,"chiheb","9393399" );
        
        sp.modifierUtlisateur(m);
       
        } else if (result == JOptionPane.NO_OPTION) {
           
        } 
*/
         PersonneCRUD u1 =new PersonneCRUD();
            Utilisateur u=new Utilisateur();
        StringBuilder errors=new StringBuilder();
        if(nomU.getText().trim().isEmpty()){
            errors.append("- Please enter a First Name\n");//string s --- s+="erreur"
        }
        if(prenomU.getText().trim().isEmpty()){
           
            errors.append("- Please enter a Last Name\n");
        }
        if(emailU.getText().trim().isEmpty()){
            errors.append("- Please enter a Email\n");
        }
      
        if(pwdU.getText().trim().isEmpty()){
            errors.append("- Please enter a Password\n");
        }
        if(adresseU.getText().trim().isEmpty()){
            errors.append("- Please enter Adress\n");
        }
        if(telU.getText().trim().isEmpty()){
            errors.append("- Please enter a Phone number\n");
        }
        if(dateU.getValue()==null){
            errors.append("- Please enter a Birthday\n");
        }
        try{
            Integer.parseInt(telU.getText());
        }catch(NumberFormatException e){
            errors.append("- Please enter a valid number\n");
        }
       /* if(us.usernameExist(usernametf.getText()) && usernametf.getText().equals(username_modif)){
            errors.append("- Username already exist");
        }*/ 
        if(errors.length()>0){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errors");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
            
            u.setAdresse(adresseU.getText());
            
            u.setDate_naissance(java.sql.Date.valueOf(dateU.getValue()));
            u.setEmail(emailU.getText());
           
            u.setTel(telU.getText());
            u.setPassword(pwdU.getText());
            u.setLastname(prenomU.getText());
            u.setUsername(nomU.getText());
            u.setId(id);
           
            us.UpdatePersonne1(u);
            TrayNotification tray = new TrayNotification();
            
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle("Update user Success");
            tray.setMessage("You successufuly updated user in ur application");
            tray.setNotificationType(NotificationType.NOTICE);
            tray.showAndDismiss(Duration.millis(1000));
            
             try {
            root = FXMLLoader.load(getClass().getResource("gestionUser.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
        
    }

    void setData(int id, String username, String email, String adresse, String lastname, String password, String tel, Date date_naissance) {
      this.id = id ; 
      nomU.setText(username);
      emailU.setText(email);
      adresseU.setText(adresse);
      prenomU.setText(lastname);
      pwdU.setText(password);
      telU.setText(tel);
      dateU.setValue(LocalDate.MAX);
    }
    
    
      
   

    
    

   
    
}
