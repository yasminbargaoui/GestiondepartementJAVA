package tn.esprit.controllers;

import com.opencsv.CSVWriter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import tn.esprit.entities.Departement;
import tn.esprit.service.DepartementService;

import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Dashboard {

    @FXML
    private GridPane grid;

    @FXML
    private Pane pn_addevent;

    @FXML
    private Pane pn_eventlist;

    @FXML
    private TextArea tf_desc;

    @FXML
    private TextField tf_hmid;

    @FXML
    private TextField tf_chercher;

    @FXML
    private TextField tf_nom;

    DepartementService ds = new DepartementService();

    @FXML
    void addevent(ActionEvent event) {
        //|| tf_hmid.getText().isEmpty()  DANS IF POUR FAIRE LA LISTE DERAULNTE
        if (tf_nom.getText().isEmpty() || tf_desc.getText().isEmpty() ) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty flields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields !");
            alert.showAndWait();
            return;
        }
        String description = tf_desc.getText();
        String nom = tf_nom.getText();
        //Integer.parseInt(tf_hmid.getText()), dans le constructeur
        Departement p = new Departement( nom, description);
        ds.ajouterEntite(p);
        tf_nom.clear();
        tf_desc.clear();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Valider");
        alert.setHeaderText(null);
        alert.setContentText("Departement added successfully !");
        alert.showAndWait();
        grid.getChildren().clear();
        displayg();
        pn_eventlist.toFront();
    }

    @FXML
    void refresh(ActionEvent event) {
        grid.getChildren().clear();
        displayg();
    }

    @FXML
    void tocreateevent(ActionEvent event) {
        pn_addevent.toFront();
    }

    @FXML
    void toeventlist(ActionEvent event) {
        grid.getChildren().clear();
        displayg();
        pn_eventlist.toFront();
    }

    private void chercher(String s ) {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = ds.search(s);
        Departement pppp = new Departement();
        int column = 0;
        int row = 2;
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Departement.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    DepartementC itemController = fxmlLoader.getController();
                    int id = resultSet2.getInt("id");
                    int head = resultSet2.getInt("headmaster_id");
                    String name = resultSet2.getString("name");
                    String description = resultSet2.getString("description");
                    Departement ppppp = new Departement(id, head, name, description);
                    itemController.setData(ppppp);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void displayg() {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = ds.Getall();
        Departement pppp = new Departement();
        int column = 0;
        int row = 2;
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Departement.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    DepartementC itemController = fxmlLoader.getController();
                    int id = resultSet2.getInt("id");
                    int head = resultSet2.getInt("headmaster_id");
                    String name = resultSet2.getString("name");
                    String description = resultSet2.getString("description");
                    Departement ppppp = new Departement(id, head, name, description);
                    itemController.setData(ppppp);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    void excel(ActionEvent event) {
        List<String> ls = ds.afficherEntites();
        List<String[]> dataList = new ArrayList<>();

        // Ajouter l'en-tête du fichier CSV
        dataList.add(new String[]{"ID", "Name", "Description"});

        // Conversion de chaque chaîne pour extraire les valeurs des propriétés
        for (String s : ls) {
            // Supprimez les parties inutiles de la chaîne
            String[] parts = s.split(", ");
            String id = parts[0].substring(parts[0].indexOf('=') + 1);
           // String headmasterId = parts[1].substring(parts[1].indexOf('=') + 1);
            String name = parts[2].substring(parts[2].indexOf('\'') + 1, parts[2].lastIndexOf('\''));
            String description = parts[3].substring(parts[3].indexOf('\'') + 1, parts[3].lastIndexOf('\''));

            // Ajoutez les valeurs extraites dans un tableau de chaînes

            String[] fields = {id,  name, description};
            dataList.add(fields);
        }

        // Écriture des données dans le fichier CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\yasmin\\Desktop\\test.csv"))) {
            writer.writeAll(dataList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void go(ActionEvent event) {
        grid.getChildren().clear();
        chercher(tf_chercher.getText());
    }

    private void sort() {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = ds.sort();
        Departement pppp = new Departement();
        int column = 0;
        int row = 2;
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Departement.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    DepartementC itemController = fxmlLoader.getController();
                    int id = resultSet2.getInt("id");
                    int head = resultSet2.getInt("headmaster_id");
                    String name = resultSet2.getString("name");
                    String description = resultSet2.getString("description");
                    Departement ppppp = new Departement(id, head, name, description);
                    itemController.setData(ppppp);
                    if (column == 1) {
                        column = 0;
                        row++;
                    }
                    grid.add(anchorPane, column++, row); //(child,column,row)
                    //set grid width
                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);
                    //set grid height
                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);
                    GridPane.setMargin(anchorPane, new Insets(10));
                } catch (IOException ex) {
                    Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void sort(ActionEvent event) {
        grid.getChildren().clear();
        sort();
    }

}



