/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import etud.entitiy.Commande;
import etud.entitiy.Panier;
import etud.entitiy.Produit;
import etud.entitiy.Utilisateur;
import etud.services.CommandeService;
import etud.services.PanierService;
import etud.utils.MyListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class HomeController implements Initializable {

    @FXML
    private TextField searshInput;
    @FXML
    private Button search;
    @FXML
    private VBox chosenFruitCard;
    @FXML
    private Label fruitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private Button addToCart;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Image image;
    private MyListener listener;
    @FXML
    private Text qteInput;
    @FXML
    private ComboBox<String> tailleInput;
    private ArrayList<Produit> choosenP = new ArrayList<>();
    @FXML
    private Button inc;
    @FXML
    private Button dec;
    @FXML
    private VBox commandeLayout;
    @FXML
    private Text montantLabel;
    float total = 0;
    @FXML
    private Button commander;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        commandeLayout.setVisible(false);
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "M",
                        "XS",
                        "S",
                        "L",
                        "XL",
                        "XXL",
                        "XXXL"
                );
        tailleInput.setItems(options);

        PanierService cs = new PanierService();
        if (cs.afficheProduit().size() > 0) {
            setchoosenProduit(cs.afficheProduit().get(0));
            listener = new MyListener() {
                @Override
                public void onClickListener(Produit p) {
                    setchoosenProduit(p);
                }
            };

        }
        int colm = 0;
        int row = 1;
        try {
            for (Produit p : cs.afficheProduit()) {

                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ProduitComponent.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitCom produitComm = fxmlloader.getController();
                produitComm.setData(p, listener);
                if (colm == 4) {
                    colm = 0;
                    row++;
                }
                grid.add(anchorPane, colm++, row);
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_PREF_SIZE);
                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException ex) {
            System.out.println("error : " + ex.getMessage());
        }

    }

    public void setchoosenProduit(Produit p) {
        this.choosenP.clear();
        fruitNameLable.setText(p.getNom());
        fruitPriceLabel.setText(String.valueOf(p.getPrix()));
        // image = new Image(getClass().getResourceAsStream("E:\\Workspace_Dev\\java\\SportTechJava\\activshop_panier_logo.png"));
        //fruitImg.setImage(image);
        this.choosenP.add(p);
    }

    public void setchoosenProduit1(Produit p) {
        this.choosenP.clear();
        fruitNameLable.setText(p.getNom());
        fruitPriceLabel.setText(String.valueOf(p.getPrix()));
        qteInput.setText(String.valueOf(p.getQantite()));
        tailleInput.getSelectionModel().select(p.getTaille());

        // image = new Image(getClass().getResourceAsStream("E:\\Workspace_Dev\\java\\SportTechJava\\activshop_panier_logo.png"));
        //fruitImg.setImage(image);
        this.choosenP.add(p);
    }

    @FXML
    private void search(ActionEvent event) {
    }

    @FXML
    private void addToPanier(ActionEvent event) {
        PanierService ps = new PanierService();
        int qte = Integer.parseInt(qteInput.getText());
        String taille = this.tailleInput.getValue();
        //si la page est commande on modifie le produit
        if (commandeLayout.isVisible()) {

            ps.UpdateProduit(this.choosenP.get(0).getId(), qte, taille);
            this.total = 0;
            //Alert alert = new Alert(Alert.AlertType.WARNING, "Produit existe dans panier");
            //alert.show();
            //update les information de prodduit 
            showCommande(event);
            // setchoosenProduit1(this.choosenP.get(0));
        } else {
            // si la page est listProduit on ajoute le produit au panier
            // Utilisateur u =new Utilisateur(1);
            for (Panier panier : ps.listPanier(1)) {
                if (panier.getListPorduits().contains(this.choosenP.get(0))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Produit existe dans panier");
                    alert.show();
                } else {
                    ps.UpdateProduit(this.choosenP.get(0).getId(), qte, taille);
                    ps.remplirPanier(1, this.choosenP.get(0));
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Produit ajouter au panier avec succes");
                    alert.show();
                }
            }
        }
    }

    @FXML
    private void showReclamation(MouseEvent event) {
    }

    @FXML
    private void showAccount(MouseEvent event) {
    }

    @FXML
    private void incQte(ActionEvent event) {

        int newVal = Integer.parseInt(this.qteInput.getText());
        newVal++;
        this.qteInput.setText(String.valueOf(newVal));

    }

    @FXML
    private void decQte(ActionEvent event) {
        int newVal = Integer.parseInt(this.qteInput.getText());
        if (newVal > 1) {
            newVal--;
            this.qteInput.setText(String.valueOf(newVal));
        }
    }

    @FXML
    private void showCommande(ActionEvent event) {
        commandeLayout.setVisible(true);
        addToCart.setText("Modifier");
        grid.getChildren().clear();
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "M",
                        "XS",
                        "S",
                        "L",
                        "XL",
                        "XXL",
                        "XXXL"
                );
        tailleInput.setItems(options);
        tailleInput.getSelectionModel().select(0);
        PanierService cs = new PanierService();
        ArrayList<Produit> list = new ArrayList<>();
        for (Panier panier : cs.listPanier(1)) {
            list = panier.getListPorduits();
        }
        if (list.size() > 0) {
            setchoosenProduit1(cs.afficheProduit().get(0));
            listener = new MyListener() {
                @Override
                public void onClickListener(Produit p) {
                    setchoosenProduit1(p);
                }
            };

        }
        int colm = 0;
        int row = 1;
        try {

            for (Produit p : list) {
                this.total = total + (p.getPrix() * p.getQantite());
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("ProduitEditComm.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ProduitEditCommController pedc = fxmlloader.getController();
                pedc.setData(p, listener);
                if (colm == 1) {
                    colm = 0;
                    row++;
                }
                grid.add(anchorPane, colm++, row);

                GridPane.setMargin(anchorPane, new Insets(5));

            }
        } catch (IOException ex) {
            System.out.println("error : " + ex.getMessage());
        }
        this.montantLabel.setText(String.valueOf(total));
    }

    @FXML
    private void passerCommande(ActionEvent event) {
        if (total == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "vous devez remplir la panier");
            alert.show();
        }else{
        CommandeService cs = new CommandeService();
        PanierService ps = new PanierService();
        Commande c = new Commande();
        Utilisateur u = new Utilisateur(1);
        ArrayList<Produit> list = new ArrayList<>();
        for (Panier panier : ps.listPanier(1)) {
            list = panier.getListPorduits();
        }
        String date = LocalDateTime.now().toString();
        c.setMontant(total);
        c.setProduits(list);
        c.setUser(u);
        c.setStatus("En attente");
        c.setReference(String.valueOf(new Random().nextInt(9999999)));
        c.setDate_creation(date);
        cs.ajoutCommande(c);
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Commande effectuer avec succes");
        alert.show();
        total=0;
        showCommande(event);
        
        }
    }

}
