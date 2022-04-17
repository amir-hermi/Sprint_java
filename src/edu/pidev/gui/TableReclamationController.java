/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import edu.pidev.entites.Reclamation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.pidev.utils.MyConnection;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import java.sql.PreparedStatement;
import java.time.LocalDate;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class TableReclamationController implements Initializable {

    @FXML
    private TableColumn<Reclamation, Integer> colId;
    @FXML
    private TableColumn<Reclamation, String> colDate;
    @FXML
    private TableColumn<Reclamation, String> colCateg;
    @FXML
    private TableColumn<Reclamation, String> colStatus;
    @FXML
    private TableColumn<Reclamation, String> colAction;
    @FXML
    private TableColumn<Reclamation, String> colSupp;
    @FXML
    private TableView<Reclamation> tvReclamation;
     Reclamation reclamation = null ;
    String query = null;
  Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         afficherReclamation();
    } 
     public Connection getCnx()
    {
        Connection cnx;
        try {
            cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/sporttech", "root", "");
            return cnx;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
                   }
    }
    
    public ObservableList<Reclamation> getReclamationList(){
     ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
     Connection cnx = getCnx();
      String requete = "SELECT * FROM reclamation";
      Statement st;
      ResultSet rs ;
      try {
      st = cnx.createStatement();
      rs = st.executeQuery(requete);
      Reclamation r;
       while(rs.next())
            {
          
                r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setStatus(rs.getString("Status"));
                r.setCategorie_reclamation(rs.getString("categorie_reclamation"));
                r.setDate(rs.getString("date"));
                
            ReclamationList.add(r);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
         return ReclamationList;
    }
    
    
   public void afficherReclamation() {
      ObservableList<Reclamation> list = getReclamationList();
      colCateg.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("categorie_reclamation"));
      colDate.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("date"));
      colStatus.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("Status"));
      colId.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("id"));
     
      
        
         Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFoctory = (TableColumn<Reclamation, String> param) -> {
            // make cell containing buttons
            final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                       
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                reclamation = tvReclamation.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `reclamation` WHERE id  ="+reclamation.getId();
                                connection = MyConnection.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                               Refresh();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(TableReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                       

                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                      

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         colSupp.setCellFactory(cellFoctory);
          tvReclamation.setItems(list);
         
    
    }
   
    @FXML
    private void Refresh()
    {
     ObservableList<Reclamation> ReclamationList = FXCollections.observableArrayList();
     Connection cnx = getCnx();
      String requete = "SELECT * FROM reclamation";
      Statement st;
      ResultSet rs ;
      try {
      st = cnx.createStatement();
      rs = st.executeQuery(requete);
      Reclamation r;
       while(rs.next())
            {
          
                r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setStatus(rs.getString("Status"));
                r.setCategorie_reclamation(rs.getString("categorie_reclamation"));
                r.setDate(rs.getString("date"));
                
            ReclamationList.add(r);
            tvReclamation.setItems(ReclamationList);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
        
    }
    
}
