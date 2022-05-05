/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.entitiy.Commande;
import etud.entitiy.Utilisateur;
import etud.services.CommandeService;
import etud.services.PaymentServiceImpl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class PaymentGUIController implements Initializable {

    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private TextField number;
    @FXML
    private TextField cvc;
    @FXML
    private TextField exp_month;
    @FXML
    private TextField exp_year;
    @FXML
    private Button confirmer;
    Commande c;
    @FXML
    private TextField nom;
    @FXML
    private HBox showError;
    @FXML
    private Text error;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showError.setVisible(false);
        final String imageURI = new File("E:/Workspace_Dev/java/Sprint_java/images/img1.png").toURI().toString();
        final Image image = new Image(imageURI);
        img1.setImage(image);
        final String imageURI2 = new File("E:/Workspace_Dev/java/Sprint_java/images/img2.png").toURI().toString();
        final Image image2 = new Image(imageURI2);
        img2.setImage(image2);
        final String imageURI3 = new File("E:/Workspace_Dev/java/Sprint_java/images/img3.png").toURI().toString();
        final Image image3 = new Image(imageURI3);
        img3.setImage(image3);
        
        
        number.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 16) {
                String s = newValue.substring(0, 16);
                number.setText(s);
            }
        });
        cvc.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 3) {
                String s = newValue.substring(0, 3);
                cvc.setText(s);
            }
        });
        exp_month.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 1) {
                String s = newValue.substring(0, 1);
                exp_month.setText(s);
            }
        });
        exp_year.textProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue.length() > 4) {
                String s = newValue.substring(0, 4);
                exp_year.setText(s);
            }
        });

    }
    public boolean validation(){
        boolean test = true;
        if(number.getText().isEmpty() || cvc.getText().isEmpty() || exp_month.getText().isEmpty() || exp_year.getText().isEmpty() || nom.getText().isEmpty()){
            test=!test;
        }
        return test;
   
            }

    public void setData(Commande c) {
        this.c = c;
        System.out.println(c);
    }

    @FXML
    private void Confirmer(ActionEvent event) {
        Stage stage=new Stage();
        if(validation()){
            PaymentServiceImpl payment = new PaymentServiceImpl();
        String num = number.getText();
        String cvcc = cvc.getText();
        String exp_m = exp_month.getText();
        String exp_y = exp_year.getText();
        String stripeIdCust = payment.createCustomer(c.getUser());
        if (payment.checkVisa(stripeIdCust, num, exp_m, exp_y, cvcc)) {
            showError.setVisible(false);
            if (payment.creationCard(c.getUser())) {
                try {
                    payment.chargeCreditCard(c);
                    Stage close = (Stage) this.cvc.getScene().getWindow();
                    close.close();
                    FXMLLoader loader =new FXMLLoader(getClass().getResource("Home.fxml"));
                    AnchorPane root = loader.load();
                    HomeController pc = loader.getController();//jebna lcontroller mtaa personneDet
                    Scene scene = new Scene(root, 1200, 800);
                    stage.setScene(scene);
                    stage.show();
                    Notifications notify = Notifications.create().title("Success")
					.text("Votre payement est bien effectueé")
					.hideAfter(javafx.util.Duration.seconds(5))
					.position(Pos.BOTTOM_RIGHT);
			notify.darkStyle();
			notify.showConfirm();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                
                Alert alert = new Alert(Alert.AlertType.WARNING, "Cannot create carte for customor ");
                alert.show();
            }

        } else {
            number.setStyle("-fx-border-color: red ;\n"
                        + "  -fx-focus-color: blue ;");
                cvc.setStyle("-fx-border-color: red ;\n"
                        + "  -fx-focus-color: blue ;");
                exp_month.setStyle("-fx-border-color: red ;\n"
                        + "  -fx-focus-color: blue ;");
                exp_year.setStyle("-fx-border-color: red ;\n"
                        + "  -fx-focus-color: blue ;");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Carte refusée ! ");
            alert.show();
        }
        }else{
            error.setText("Verifier vos champs !");
            showError.setVisible(true);
            
            
        }
        
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage stage=new Stage();
        try {
            CommandeService cs = new CommandeService();
            cs.deleteCommande(c);
            Stage close = (Stage) this.cvc.getScene().getWindow();
            close.close();
            FXMLLoader loader =new FXMLLoader(getClass().getResource("Home.fxml"));
            AnchorPane root = loader.load();
            HomeController pc = loader.getController();//jebna lcontroller mtaa personneDet 
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
