/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import edu.connexion3A30.entities.Session;
import edu.connexion3A30.util.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.activation.DataSource;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class LoginController implements Initializable {

    @FXML
    private Button login_membre_btn;
    @FXML
    private PasswordField mdp_membre;
    @FXML
    private TextField email_membre;
    @FXML
    private Button switch_signup_membre_btn;
    
    
     Connection cn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void action_login_membre(ActionEvent event) {
        
        String sql = "Select * from utilisateur where email = ? and password =?   ";
        cn = MyConnection.getInstance().getCnx();
        try {
            String newMD = "";
            String mdp = Hash();
            pst = cn.prepareStatement(sql);
            pst.setString(1, email_membre.getText());
            pst.setString(2, mdp + mdp_membre.getText());
            

            rs = pst.executeQuery();
            if (rs.next()) {
              
               
                 Session.setId(rs.getInt("id"));
                Session.setUsername(rs.getString("username"));
                Session.setEmail(rs.getString("email"));
                Session.setRoles(rs.getString("roles"));
                Session.setEtat(rs.getString("etat"));
               
             if(Session.getEtat().equals("Bloquer")){
                  JOptionPane.showMessageDialog(null, "Vous etes bloquer");
             }
            /* if(!Session.getActivation_token().isEmpty()){
                  JOptionPane.showMessageDialog(null, "vous veillez dabord activer votre compte ");
             }
*/
                
             if(Session.getRoles().equals("[\"ROLE_ADMIN\"]") ){
                  JOptionPane.showMessageDialog(null, "E-mail and Password is Correct");
            try {
                System.out.println(Session.getEtat());
                root = FXMLLoader.load(getClass().getResource("../gui/interfaceMembre.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
                }else if (Session.getRoles().equals("[\"ROLE_USER\"]")& Session.getEtat().equals("Debloquer")){
                     JOptionPane.showMessageDialog(null, "E-mail and Password is Correct");
            try {
                System.out.println(Session.getEtat());
                root = FXMLLoader.load(getClass().getResource("../gui/InterfaceAdmin.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
                    
                }
            
                  
             
               
               
            } else {
                JOptionPane.showMessageDialog(null, "Invalide E-mail Or Password");
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
          
             

    }
    
     public String Hash() throws Exception {

        String mdp_user = "";

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp_user.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

    @FXML
    private void passReset(MouseEvent event) {
        
    }

    @FXML
    private void action_forgot_pass(ActionEvent event) {
              Stage Stage1 ;
        try {
                     Parent root = FXMLLoader.load(getClass().getResource("RestPass.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
        Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
        Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
    });
});
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
    }

    @FXML
    private void switch_signup_membre(ActionEvent event) {
        
        try {
            root = FXMLLoader.load(getClass().getResource("inscriptions.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
