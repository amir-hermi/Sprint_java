/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pidev.gui;

import edu.pidev.entites.Mission;
import edu.pidev.services.MissionCRUD;
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
public class TableMissionController implements Initializable {

    @FXML
    private TableView<Mission> tvMission;
    @FXML
    private TableColumn<Mission, String> colAdresse;
    @FXML
    private TableColumn<Mission, String> colLivreur;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableColumn<Mission, String> colDate;
    @FXML
    private TableColumn<Mission, String> colStatus;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfLivreur;
    @FXML
    private TextField tfDate;
    @FXML
    private TableColumn<Mission, Integer> colId;
    @FXML
    private TextField tfId;
    @FXML
    private TableColumn<Mission, String> colAction;
    @FXML
    private Button Refresh;
    Mission mission = null ;
    String query = null;
  Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    /**
     * Initializes the controller class.
     */
   @FXML
    private void handleButtonAction(ActionEvent event)
    {
    if(event.getSource()== btnInsert)
            {
               // ajouterMission(event);
            }
    else if (event.getSource()== btnUpdate)
            {
                modifierMission();
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherMission();
        
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
    
    public ObservableList<Mission> getMissionList(){
     ObservableList<Mission> MissionList = FXCollections.observableArrayList();
     Connection cnx = getCnx();
      String requete = "SELECT * FROM mission";
      Statement st;
      ResultSet rs ;
      try {
      st = cnx.createStatement();
      rs = st.executeQuery(requete);
      Mission m;
       while(rs.next())
            {
          
                m = new Mission();
                m.setId(rs.getInt("id"));
                m.setStatus(rs.getString("Status"));
                m.setAdresse(rs.getString("adresse"));
                m.setLivreur(rs.getString("livreur"));
                m.setDate(rs.getString("date"));
                
            MissionList.add(m);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
         return MissionList;
    }
    
    
   public void afficherMission() {
      ObservableList<Mission> list = getMissionList();
      colAdresse.setCellValueFactory(new PropertyValueFactory<Mission, String>("adresse"));
      colLivreur.setCellValueFactory(new PropertyValueFactory<Mission, String>("livreur"));
      colDate.setCellValueFactory(new PropertyValueFactory<Mission, String>("date"));
      colStatus.setCellValueFactory(new PropertyValueFactory<Mission, String>("Status"));
      colId.setCellValueFactory(new PropertyValueFactory<Mission, Integer>("id"));
     
      
        //add cell of button edit 
         Callback<TableColumn<Mission, String>, TableCell<Mission, String>> cellFoctory = (TableColumn<Mission, String> param) -> {
            // make cell containing buttons
            final TableCell<Mission, String> cell = new TableCell<Mission, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            try {
                                mission = tvMission.getSelectionModel().getSelectedItem();
                                query = "DELETE FROM `mission` WHERE id  ="+mission.getId();
                                connection = MyConnection.getConnect();
                                preparedStatement = connection.prepareStatement(query);
                                preparedStatement.execute();
                                Refresh();
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(TableMissionController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                           

                          

                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            mission = tvMission.getSelectionModel().getSelectedItem();
                            FXMLLoader loader = new FXMLLoader ();
                            loader.setLocation(getClass().getResource("Mission.fxml"));
                            try {
                                loader.load();
                            } catch (IOException ex) {
                                Logger.getLogger(TableMissionController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            MissionController missionController = loader.getController();
                            missionController.setUpdate(true);
                            missionController.setTextField(mission.getId(), mission.getAdresse(),mission.getLivreur() 
                                   );
                            Parent parent = loader.getRoot();
                            Stage stage = new Stage();
                            stage.setScene(new Scene(parent));
                            stage.initStyle(StageStyle.UTILITY);
                            stage.show();
                            

                           

                        });

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         colAction.setCellFactory(cellFoctory);
          tvMission.setItems(list);
         
    
    }
     
    @FXML
   public void ajouterMission(ActionEvent event)
     {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("Mission.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("Mission.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(TableMissionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
     }
   
   public void modifierMission()
   {
    String requete = "UPDATE mission SET adresse='"+tfAdresse.getText()+"', livreur ="+tfLivreur.getText()
           +" WHERE id="+tfId.getText()+"";
    executeQuery(requete);
    afficherMission();
   }

    private void executeQuery(String requete) {
        Connection cnx = getCnx();
         Statement st;
          try {
      st = cnx.createStatement();
      st.executeUpdate(requete);
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
    }
    @FXML
    private void Refresh()
    {
     ObservableList<Mission> MissionList = FXCollections.observableArrayList();
     Connection cnx = getCnx();
      String requete = "SELECT * FROM mission";
      Statement st;
      ResultSet rs ;
      try {
      st = cnx.createStatement();
      rs = st.executeQuery(requete);
      Mission m;
       while(rs.next())
            {
          
                m = new Mission();
                m.setId(rs.getInt("id"));
                m.setStatus(rs.getString("Status"));
                m.setAdresse(rs.getString("adresse"));
                m.setLivreur(rs.getString("livreur"));
                m.setDate(rs.getString("date"));
                
            MissionList.add(m);
            tvMission.setItems(MissionList);
            }
           
        } catch (SQLException ex) {
             System.err.println(ex.getMessage()); 
        }
        
    }
 
}