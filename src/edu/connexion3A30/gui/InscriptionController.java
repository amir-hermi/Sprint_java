/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.connexion3A30.gui;

import edu.connexion3A30.entities.Utilisateur;
import edu.connexion3A30.services.PersonneCRUD;
import edu.connexion3A30.util.Mailapi;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import java.util.ResourceBundle;
import java.util.UUID;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author bilel
 */
public class InscriptionController implements Initializable {
     String emailPW = null;

    @FXML
    private Button add_membre_btn;
    @FXML
    private TextField prenom_membre;
    @FXML
    private TextField nom_membre;
    @FXML
    private TextField tel_membre;
    @FXML
    private TextField email_membre;
    @FXML
    private TextField src;
    @FXML
    private PasswordField mdp_membre;
    @FXML
    private Button switch_signin_membre_btn;
    @FXML
    private ImageView qr;
    @FXML
    private ImageView img;
    
    
    
     private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private DatePicker date_membre;
    @FXML
    private TextArea adresse_membre;
    @FXML
    private Label age;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date_membre.valueProperty().addListener((ov, oldValue, newValue) -> {
           PersonneCRUD pcd = new PersonneCRUD();
      
        int a = pcd.calculateAge(Date.valueOf(newValue));
        age.setText(String.valueOf(a));
        setAge("votre age est :"+String.valueOf(a)+" Ans");
        });
         
        
    }    

    @FXML
    private void action_add_membre(ActionEvent event) {
        
         PersonneCRUD sp = new PersonneCRUD();
           String mail = email_membre.getText();
               Utilisateur u = new Utilisateur();
              StringBuilder errors=new StringBuilder();
        if (prenom_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your name ?");
            alert.showAndWait();
            return;
        }
        if (nom_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your name ?");
            alert.showAndWait();
            return;
        }
        if (!(tel_membre.getText().matches("\\d{8}")) && tel_membre.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What 's your telephone number ?");
            alert.showAndWait();
            return;

        }
        
        if(date_membre.getValue()==null){
            errors.append("- Please enter a Birthday\n");
            return;
        }
         
        if (email_membre.getText().isEmpty()
                || !email_membre.getText().contains("@")
                || !email_membre.getText().contains(".")
                || email_membre.getText().indexOf("#", 0) >= 0
                || email_membre.getText().indexOf("&", 0) >= 0
                || email_membre.getText().indexOf("(", 0) >= 0
                || email_membre.getText().indexOf("Â§", 0) >= 0
                || email_membre.getText().indexOf("!", 0) >= 0
                || email_membre.getText().indexOf("Ã§", 0) >= 0
                || email_membre.getText().indexOf("Ã ", 0) >= 0
                || email_membre.getText().indexOf("Ã©", 0) >= 0
                || email_membre.getText().indexOf(")", 0) >= 0
                || email_membre.getText().indexOf("{", 0) >= 0
                || email_membre.getText().indexOf("}", 0) >= 0
                || email_membre.getText().indexOf("|", 0) >= 0
                || email_membre.getText().indexOf("$", 0) >= 0
                || email_membre.getText().indexOf("*", 0) >= 0
                || email_membre.getText().indexOf("â‚¬", 0) >= 0
                || email_membre.getText().indexOf("`", 0) >= 0
                || email_membre.getText().indexOf("\'", 0) >= 0
                || email_membre.getText().indexOf("\"", 0) >= 0
                || email_membre.getText().indexOf("%", 0) >= 0
                || email_membre.getText().indexOf("+", 0) >= 0
                || email_membre.getText().indexOf("=", 0) >= 0
                || email_membre.getText().indexOf("/", 0) >= 0
                || email_membre.getText().indexOf("\\", 0) >= 0
                || email_membre.getText().indexOf(":", 0) >= 0
                || email_membre.getText().indexOf(",", 0) >= 0
                || email_membre.getText().indexOf("?", 0) >= 0
                || email_membre.getText().indexOf(";", 0) >= 0
                || email_membre.getText().indexOf("Â°", 0) >= 0
                || email_membre.getText().indexOf("<", 0) >= 0
                || email_membre.getText().indexOf(">", 0) >= 0) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("Invalid mail !");
            alert.showAndWait();
            return;
        }
        
        if (adresse_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your name ?");
            alert.showAndWait();
            return;
        }
        
        if (mdp_membre.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("What's your password ?");
            alert.showAndWait();
            return;
        }
u.setActivation_token(UUID.randomUUID().toString());
        System.out.println(u.getActivation_token());
 Utilisateur m = new Utilisateur(nom_membre.getText() , prenom_membre.getText(),"ROLE_ADMIN", email_membre.getText(), mdp_membre.getText(), tel_membre.getText(),"Debloquer",adresse_membre.getText(),u.getActivation_token(),(Date.valueOf(date_membre.getValue())) );       // Utilisateur p6 = new Utilisateur("chihe22b11", "cha2211h" , "ROLE_ADMIN" , "chihe1221b@gmail.com" ,"chiheb","9393399" );
    
    
 sp.ajouter(m);
 


String content = "Activation de votre compte\n"
                        + "veuillez clicker sur le lien ci-dessus pour l activer  votre compte\n"
                        + "http://127.0.0.1:8000/activation/" + u.getActivation_token();
              //  u.setActivation_token(null);
       // System.out.println("bilel");        
                
        Mailapi.send("sporttech007@gmail.com", "Zayani321",email_membre.getText(), "Activation de votre compte", "veuillez clicker sur le lien ci-dessus pour l activer  votre compte:\n "+ content);
                



          
  
        //CodeQr(event);
       // affichImage();
        affichpdp();
        //refresh();
        
        
        
        
        
      

    
        

    }

    
      public void affichImage() {
        String Id_stat = nom_membre.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        //qr.setImage(im);
    }
      
      
    @FXML
    private String importImg(ActionEvent event) {
        String id =email_membre.getText();
          Path to = null;
         String  m = null;
         String path = "src/imagesUser/";
         JFileChooser chooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg","jpeg","PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
           m = chooser.getSelectedFile().getAbsolutePath();
//            System.out.println("You chose to open this file: " +m
//                    );
            
            if(chooser.getSelectedFile() != null){
                
               try {
                   Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to = Paths.get(path+"\\"+id+".png");
                   CopyOption[] options = new CopyOption[]{
                       StandardCopyOption.REPLACE_EXISTING,
                       StandardCopyOption.COPY_ATTRIBUTES
                   };
                   Files.copy(from, to, options);
                   System.out.println("added");
//                saveSystem(selectedFile, )
                       System.out.println(to);

               } catch (IOException ex) {
                   System.out.println();
               }
            }
             src.setText(to.toString());
        
    }
      return to.toString(); 
    }
    
     public void affichpdp() {
        String Id_stat = email_membre.getText();
        String f_name = Id_stat;
        String Path_name = new File("src/imagesUser/").getAbsolutePath();
        String image = Path_name + "\\" + Id_stat + ".PNG";
        //System.out.println(image);
        ImageView i = new ImageView();
        File f = new File(image);
        Image im = new Image(f.toURI().toString());
        img.setImage(im);
    }

    @FXML
    private void action_switch_signin_membre(ActionEvent event) {
        
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void CodeQr(ActionEvent event) {
      String Id_stat = nom_membre.getText();

        String LoginCode = email_membre.getText() + " " + mdp_membre.getText();
        ByteArrayOutputStream out = QRCode.from(LoginCode)
                .to(ImageType.PNG).stream();
        String f_name = Id_stat;
        String Path_name = new File("src/QrCode/").getAbsolutePath();
        try {
            FileOutputStream fout = new FileOutputStream(new File(Path_name + "/" + (f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();
            System.out.println(Path_name);

        } catch (Exception e) {
            System.out.println(e);

        }

    }
    public void setAge(String age) {
        this.age.setText(String.valueOf(age));
    }

    private void age(ActionEvent event) {
        PersonneCRUD pcd = new PersonneCRUD();
        int a = pcd.calculateAge(Date.valueOf(date_membre.getValue()) );
        
        age.setText(String.valueOf(a));
        setAge(String.valueOf(a));
    }
    
    public void refresh(){
       
        nom_membre.setText("");
        prenom_membre.setText("");
        date_membre.setValue(null);
        email_membre.setText("");
        tel_membre.setText("");
        mdp_membre.setText("");
        age.setText("");
        adresse_membre.setText("");
    }
    
    
}
