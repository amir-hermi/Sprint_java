/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;
import etud.entitiy.Commande;
import etud.entitiy.Panier;
import etud.entitiy.Produit;
import etud.entitiy.Utilisateur;
import etud.services.CommandeService;
import etud.services.PanierService;
import etud.utils.MyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

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
    private ArrayList<Commande> choosenC = new ArrayList<>();
    private ArrayList<Produit> choosenRecommandation = new ArrayList<>();
    private boolean isCommandeLayout = false;
    private boolean recEvent = false;
    @FXML
    private Button inc;
    @FXML
    private Button dec;
    @FXML
    private VBox PanierLayout;
    @FXML
    private Text montantLabel;
    float total = 0;
    @FXML
    private Button commander;
    @FXML
    private HBox qteLayout;
    @FXML
    private HBox orLayout;
    @FXML
    private Label tailleText;
    @FXML
    private ImageView panier;
    @FXML
    private ScrollPane scroll2;
    @FXML
    private GridPane relGrid;
    private boolean test = false;
    @FXML
    private ImageView delete;
    @FXML
    private Text element;
    @FXML
    private Text mtTot;
    @FXML
    private Text label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setText("Liste des Produits");
        setDetaille();
        final String imageURI2 = new File("E:/Workspace_Dev/java/Sprint_java/panier.png").toURI().toString();
        final Image image2 = new Image(imageURI2);
        panier.setImage(image2);
        final String imageURI = new File("E:/Workspace_Dev/java/Sprint_java/delete.png").toURI().toString();
        final Image image = new Image(imageURI);
        delete.setImage(image);
        delete.setVisible(false);
        showProduits();

    }

    public void setchoosenProduit(Produit p) {
        recEvent = false;
        this.choosenP.clear();
        fruitNameLable.setText(p.getNom());
        fruitPriceLabel.setText(String.valueOf(p.getPrix()));
        qteInput.setText("1");
        final String imageURI = new File("C:/Users/hp/Desktop/amirtawtaw/public/images/" + p.getImage()).toURI().toString();
        final Image image = new Image(imageURI);
        fruitImg.setImage(image);
        this.choosenP.add(p);
    }

    public void setchoosenProduit1(Produit p) {
        recEvent = false;
        addToCart.setText("Modifier");
        this.choosenP.clear();
        this.choosenRecommandation.clear();
        fruitNameLable.setText(p.getNom());
        fruitPriceLabel.setText(String.valueOf(p.getPrix()));
        qteInput.setText(String.valueOf(p.getQantite()));
        tailleInput.getSelectionModel().select(p.getTaille());
        final String imageURI = new File("C:/Users/hp/Desktop/amirtawtaw/public/images/" + p.getImage()).toURI().toString();
        final Image image = new Image(imageURI);
        fruitImg.setImage(image);
        this.choosenP.add(p);
    }
     public void setchoosenProduitRec(Produit p) {
         recEvent = true;
        this.choosenP.clear();
        addToCart.setText("Ajouter");
        fruitNameLable.setText(p.getNom());
        fruitPriceLabel.setText(String.valueOf(p.getPrix()));
        qteInput.setText(String.valueOf(p.getQantite()));
        tailleInput.getSelectionModel().select(p.getTaille());
        final String imageURI = new File("C:/Users/hp/Desktop/amirtawtaw/public/images/" + p.getImage()).toURI().toString();
        final Image image = new Image(imageURI);
        fruitImg.setImage(image);
        this.choosenRecommandation.add(p);
    }

    public void setchoosenCommande(Commande c) {
        recEvent = false;
        this.choosenC.clear();
        fruitNameLable.setText(c.getReference());
        fruitPriceLabel.setText("");
        tailleInput.getSelectionModel().select(c.getStatus());
        

        // image = new Image(getClass().getResourceAsStream("E:\\Workspace_Dev\\java\\SportTechJava\\activshop_panier_logo.png"));
        //fruitImg.setImage(image);
        this.choosenC.add(c);
        final String imageURI = new File("E:/Workspace_Dev/java/Sprint_java/images/"+c.getId()+".png").toURI().toString();
        final Image image = new Image(imageURI);
        fruitImg.setImage(image);
    }

    @FXML
    private void search(ActionEvent event) {
        delete.setVisible(false);
        showProduits();
    }

    @FXML
    private void addToPanier(ActionEvent event) {
        PanierService ps = new PanierService();
        int qte = Integer.parseInt(qteInput.getText());
        String taille = this.tailleInput.getValue();
        //si la page est commande on modifie le produit
        if (PanierLayout.isVisible() && recEvent==false) {
            ps.UpdateProduit(this.choosenP.get(0).getId(), qte, taille);
            this.total = 0;
            Alert alert = new Alert(Alert.AlertType.WARNING, "Produit modifier avec succes");
            alert.show();
            //update les information de prodduit 
             showPanier();
             this.choosenP = this.choosenP;
        } else if (isCommandeLayout) {
            CommandeService cs = new CommandeService();
            cs.UpdateCommande(choosenC.get(0).getId(), tailleInput.getValue());
            Alert alert = new Alert(Alert.AlertType.WARNING, "Commande modifier avec succes");
            alert.show();
            showCommande(event);
        
        }else
        {
            // si la page est listProduit on ajoute le produit au panier
            // Utilisateur u =new Utilisateur(1);
            for (Panier panier : ps.listPanier(1)) {
                if (panier.getListPorduits().contains(this.choosenP.get(0))) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Produit existe dans panier , vous voulez augmenter la quantite ? ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        PanierService x = new PanierService();
                        x.UpdateProduit(this.choosenP.get(0).getId(), this.choosenP.get(0).getQantite() + 1, this.choosenP.get(0).getTaille());
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "Success Quantite augmenter");
                        alert1.show();
                        //setDetaille();
                        showProduits();
                    }
                } else {

                    ps.UpdateProduit(this.choosenP.get(0).getId(), qte, taille);
                    ps.remplirPanier(1, this.choosenP.get(0));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Produit ajouter au panier avec succes");
                    alert.show();
                    setDetaille();
                }
            }
        }
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

    private void showPanier(ActionEvent event) {

        showPanier();
    }

    public void showPanier() {
        recEvent = false;
        addToCart.setVisible(true);
        tailleInput.setVisible(true);
        tailleText.setVisible(true);
        label.setText("Panier");
        setDetaille();
        isCommandeLayout = false;
        delete.setVisible(true);
        qteLayout.setVisible(true);
        orLayout.setVisible(true);
        scroll2.setVisible(true);
        scroll.setPrefWidth(350);
        chosenFruitCard.setStyle("-fx-background-color: #009B5A;\n"
                + "    -fx-background-radius: 30;");
        total = 0;
        PanierLayout.setVisible(true);
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
            setchoosenProduit1(list.get(0));
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

            int colm1 = 0;
            int row1 = 1;
            try {
                 Utilisateur u = new Utilisateur(1);
                ArrayList<Produit> listRecommondation = new ArrayList<>();
        for (Produit produit : cs.Recommandation(u)) {
            listRecommondation.add(produit);
        }
                if (list.size() > 0) {
            listener = new MyListener() {
                @Override
                public void onClickListener(Produit p) { 
                    setchoosenProduitRec(p);
                }
            };

        }
               
                for (Produit p : cs.Recommandation(u)) {
                    FXMLLoader fxmlloader = new FXMLLoader();
                    fxmlloader.setLocation(getClass().getResource("ProduitComponent.fxml"));
                    AnchorPane anchorPane = fxmlloader.load();
                    ProduitCom produitComm = fxmlloader.getController();
                    produitComm.setData(p, listener);
                    if (colm1 == 3) {
                        colm1 = 0;
                        row1++;
                    }
                    relGrid.add(anchorPane, colm1++, row1);
                    GridPane.setMargin(anchorPane, new Insets(10));
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        } catch (IOException ex) {
            System.out.println("error : " + ex.getMessage());
        }
        this.montantLabel.setText(String.valueOf(total));
    }

    @FXML
    private void passerCommande(ActionEvent event) {
        
        Stage showPayment = new Stage();
        if (total == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Vous devez remplir la panier");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "vous voulez passer la commande ?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {

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
                    total = 0;
                    Stage close = (Stage) this.addToCart.getScene().getWindow();
                    close.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentGUI.fxml"));
                    Parent root = loader.load();
                    PaymentGUIController pc = loader.getController();
                    pc.setData(c);
                    Scene scene = new Scene(root, 400, 400);
                    showPayment.setScene(scene);
                    showPayment.setTitle("Payment");
                    showPayment.show();
                    //showPanier(event);
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }

        }
    }

    @FXML
    private void showCommande(ActionEvent event) {
        addToCart.setVisible(false);
        tailleInput.setVisible(false);
        tailleText.setVisible(false);
        label.setText("Commandes");
        setDetaille();
        delete.setVisible(false);
        scroll2.setVisible(false);
        scroll.setPrefWidth(1200);
       
        chosenFruitCard.setStyle("-fx-background-color: #B018F5;\n"
                + "    -fx-background-radius: 30;");
        isCommandeLayout = true;
        addToCart.setText("Modifier");

        grid.getChildren().clear();
        PanierLayout.setVisible(false);
        tailleText.setText("Status");
        orLayout.setVisible(false);
        qteLayout.setVisible(false);
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "En attente",
                        "Confirmée",
                        "Annulée",
                        "En cours de preparation",
                        "Livraison en cours",
                        "Livrée"
                );
        tailleInput.setItems(options);
        CommandeService cs = new CommandeService();
        Utilisateur u = new Utilisateur(1);
        if (cs.listCommande(u).size() > 0) {
            setchoosenCommande(cs.listCommande(u).get(0));
            listener = new MyListener() {
                @Override
                public void onClickListener(Commande c) {
                    setchoosenCommande(c);
                }
            };
        }
        int colm = 0;
        int row = 1;
        try {

            for (Commande c : cs.listCommande(u)) {
                int totalP = 0;
                for (Produit p : c.getProduits()) {
                    totalP = totalP + 1;
                }
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("CommandeGUI.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                CommandeGUIController commController = fxmlloader.getController();
                commController.setData(listener, c, totalP);
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

    }

    @FXML
    private void showProduits(ActionEvent event) {

        showProduits();
    }

    private void setDetaille() {
        PanierService cs = new PanierService();
        this.element.setText(String.valueOf(cs.nbProduit(1)));
        this.mtTot.setText(String.valueOf(cs.totalMontantProduit(1)));
    }

    private void showProduits() {
        addToCart.setVisible(true);
        tailleInput.setVisible(true);
        tailleText.setVisible(true);
        label.setText("Liste des Produits");
        setDetaille();
        addToCart.setText("Ajouter au panier");
        isCommandeLayout = false;
        delete.setVisible(false);
        qteLayout.setVisible(true);
        orLayout.setVisible(true);
        scroll2.setVisible(false);
        scroll.setPrefWidth(1200);
        chosenFruitCard.setStyle("-fx-background-color: #F16C31;\n"
                + "    -fx-background-radius: 30;");
        grid.getChildren().clear();
        PanierLayout.setVisible(false);
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
                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException ex) {
            System.out.println("error : " + ex.getMessage());
        }

    }

    @FXML
    private void deleteP(MouseEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Commande modifier avec succes");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            PanierService cs = new PanierService();
            cs.deleteProduitFromPanier(1, choosenP.get(0));
            showPanier();
        }

    }

    @FXML
    private void panier(MouseEvent event) {
        showPanier();
    }

}
