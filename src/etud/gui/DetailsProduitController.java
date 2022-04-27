/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etud.gui;

import etud.entities.Produit;
import javax.swing.JFileChooser;
import etud.services.ProduitCRUD;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author hassen
 */
public class DetailsProduitController implements Initializable {

    @FXML
    private TableColumn<Produit, Float> tvprix;
    @FXML
    private TableColumn<Produit, String> tvimage;
    @FXML
    private TableColumn<Produit, Integer> tvquantite;
    @FXML
    private TableColumn<Produit, String> tvtaille;
    @FXML
    private TableColumn<Produit, String> tvnom;
    @FXML
    private TableColumn<Produit, String> tvmarque;
    @FXML
    private TableColumn<Produit, String> tvsouscategorie;
    @FXML
    private TableColumn<Produit, String> tvdescription;
    private TableColumn<Produit, String> tvaction;
    
    ObservableList<Produit> data=FXCollections.observableArrayList();
    @FXML
    private TableView<Produit> tvproduit;
    @FXML
    private TextField pprix;
    @FXML
    private TextField pimage;
    @FXML
    private TextField pquantite;
    @FXML
    private TextField ptaille;
    @FXML
    private TextField pnom;
    @FXML
    private ComboBox<String> combosouscat;
    @FXML
    private ComboBox<String> combomarque;
    @FXML
    private TextField pdescription;
    @FXML
    private Button AjouterProduit;
    @FXML
    private TextField tfid;
    @FXML
    private Button updateproduit;
    @FXML
    private Button deleteproduit;
    @FXML
    private Button btnupload;
    @FXML
    private TextField recherche;
    @FXML
    private PieChart chart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        ProduitCRUD p1 = new ProduitCRUD() ; 
        combomarque.setItems(p1.GetListmarque());
        combosouscat.setItems(p1.GetListsouscategorie());
        refreshlist();
        
       
        tvproduit.setOnMouseClicked( e -> {
            Produit p = new Produit();
            p = tvproduit.getSelectionModel().getSelectedItem() ; 
            tfid.setText( String.valueOf( p.getId())) ; 
            pprix.setText(String.valueOf( p.getPrix()) ) ;
            pimage.setText(p.getImage()) ;
            pquantite.setText( String.valueOf(p.getQuantite()) )  ; 
            ptaille.setText(p.getTaille()) ;
            pnom.setText( p.getNom()) ; 
            combosouscat.setValue( p1.GetsouscategorieName( p.getSousCategorie()) );
            combomarque.setValue( p1.GetmarqueName( p.getMarque() ) );
            pdescription.setText(p.getdescription()) ; 
            });
       
        
        stat() ; 


        
        
        
        
    }    
    
    
    public void stat()
    {
        ObservableList<PieChart.Data> piechartdata = FXCollections.observableArrayList( ) ;
        ProduitCRUD sp = new ProduitCRUD();
        List<Produit> ProduitList = new ArrayList<>( sp.AfficheProduit()) ; 
        Map<String, Long> count = ProduitList.stream().collect(Collectors.groupingBy(e -> sp.GetmarqueName(e.getMarque()),Collectors.counting() )) ;
        for (Map.Entry<String, Long> entry : count.entrySet() ) {
       
            piechartdata.add( new PieChart.Data(entry.getKey(), entry.getValue()) ) ; 
        }

        chart.setData(piechartdata) ;
    
    
    
    
    }
    
    public void refreshlist(){
        
        
            ProduitCRUD p = new ProduitCRUD ();
            Produit prod = new Produit() ; 
            ObservableList<Produit> list = p.AfficheProduit() ;
      
        tvprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tvquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        tvtaille.setCellValueFactory(new PropertyValueFactory<>("taille"));
        tvnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tvimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        tvmarque.setCellValueFactory( cellData -> new SimpleStringProperty( p.GetmarqueName(cellData.getValue().getMarque() ) ) );
        tvsouscategorie.setCellValueFactory( cellData -> new SimpleStringProperty( p.GetsouscategorieName(cellData.getValue().getSousCategorie() ) ) );
        tvdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        
        FilteredList<Produit> filteredData = new FilteredList<>(list, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Produit -> {
                // If filter text is empty, display all persons.
                                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Produit.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                }
                if (p.GetmarqueName(Produit.getMarque()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; 
                }
                
                     else  
                         return false;
            });
        });

        tvproduit.setItems(filteredData);
        
    }
    

    @FXML
    private void AjoutProduit(ActionEvent event) {
        ProduitCRUD p1 = new ProduitCRUD() ; StringBuilder errors=new StringBuilder();
        
        
        if (pprix.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer un prix ");
            alert.showAndWait();
            return;
            
        }
        
        if (pimage.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une image ");
            alert.showAndWait();
            return;
            
        }
        
        if (pquantite.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une quantite ");
            alert.showAndWait();
            return;
            
        }
        
        if (ptaille.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer un taille ");
            alert.showAndWait();
            return;
            
        }
        
        if (pnom.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer un nom");
            alert.showAndWait();
            return;
            
        }
        
        if (pdescription.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une description");
            alert.showAndWait();
            return;
            
        }

        float prix = Float.parseFloat(pprix.getText());
        String image = pimage.getText();
        float quantite =Float.parseFloat(pquantite.getText()) ;
        String taille = ptaille.getText();
        String nom = pnom.getText();
        int marque = p1.GetmarqueId( combomarque.getSelectionModel().getSelectedItem().toString()) ; 
        int sous_cat = p1.GetsouscategorieId(combosouscat.getSelectionModel().getSelectedItem().toString()) ; 
        String description = pdescription.getText(); 
        
        Produit p = new Produit (prix,image,(int) quantite,taille,nom,marque,sous_cat,description);
        ProduitCRUD prod1 = new ProduitCRUD();
         prod1.AjoutProduit(p);
         refreshlist();
         stat() ; 
        
    }

    @FXML
    private void updateproduit(ActionEvent event) {
        
        if (pprix.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer un prix ");
            alert.showAndWait();
            return;
            
        }
        
        if (pimage.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une image ");
            alert.showAndWait();
            return;
            
        }
        
        if (pquantite.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une quantite ");
            alert.showAndWait();
            return;
            
        }
        
        if (ptaille.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer un taille ");
            alert.showAndWait();
            return;
            
        }
        
        if (pnom.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer un nom");
            alert.showAndWait();
            return;
            
        }
        
        if (pdescription.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setContentText("entrer une description");
            alert.showAndWait();
            return;
            
        }
        
        ProduitCRUD p1 = new ProduitCRUD() ; 
        int id = Integer.parseInt(tfid.getText()) ; 
        float prix = Float.parseFloat(pprix.getText());
        String image = pimage.getText();
        float quantite =Float.parseFloat(pquantite.getText()) ;
        String taille = ptaille.getText();
        String nom = pnom.getText();
        int marque = p1.GetmarqueId( combomarque.getSelectionModel().getSelectedItem().toString()) ; 
        int sous_cat = p1.GetsouscategorieId(combosouscat.getSelectionModel().getSelectedItem().toString()) ; 
        String description = pdescription.getText(); 
        
        Produit p = new Produit ( id , prix,image,(int) quantite,taille,nom,marque,sous_cat,description);
        ProduitCRUD prod1 = new ProduitCRUD();
         prod1.UpdateProduit(p);
         refreshlist();
         stat();
        
    }

    @FXML
    private void deleteproduit(ActionEvent event) {
        ProduitCRUD p1 = new ProduitCRUD() ; 
        Produit p = new Produit() ; 
        int id = Integer.parseInt(tfid.getText()) ; 
        p.setId(id);
        p1.DeleteProduit(p);
         refreshlist();
         stat();
        
    }

    @FXML
    private void uploadimage(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null) ; 
        File f =chooser.getSelectedFile();
        String filename = f.getAbsolutePath() ; 
        pimage.setText(filename);

        
    }
    
    
    
   
}
