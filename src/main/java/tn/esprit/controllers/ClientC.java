package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import tn.esprit.entities.Departement;
import tn.esprit.service.DepartementService;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientC {

    @FXML
    private GridPane grid;

    DepartementService ds = new DepartementService();

    @FXML
    void refresh(ActionEvent event) {
        grid.getChildren().clear();
        displayg();
    }
    private void displayg() {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = ds.Getall();
        Departement pppp = new Departement();
        int column = 0;
        int row = 2;
        try {
            //tantque resultt***
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Departement.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    DepartementC itemController = fxmlLoader.getController();
                    int id=resultSet2.getInt("id");
                    int head =resultSet2.getInt("headmaster_id");
                    String name=resultSet2.getString("name");
                    String description=resultSet2.getString("description");
                    Departement ppppp = new Departement(id,head,name,description);
                    itemController.setDataf(ppppp);
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

}
