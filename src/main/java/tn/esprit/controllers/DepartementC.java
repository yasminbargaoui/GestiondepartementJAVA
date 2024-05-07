package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import tn.esprit.entities.Departement;
import tn.esprit.entities.Internship;
import tn.esprit.service.DepartementService;
import tn.esprit.service.InternshipService;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartementC {

    @FXML
    private Button btnmod;
    @FXML
    private Button btndel;
    @FXML
    private Button btnadd;

    @FXML
    private TextArea desc;

    @FXML
    private GridPane grid;

    @FXML
    private Label headmaster;

    @FXML
    private Label name;

    @FXML
    private Pane pn_add;

    @FXML
    private Pane pn_info;

    @FXML
    private Pane pn_intern;

    @FXML
    private TextField tf_desc;

    @FXML
    private TextField tf_name;

    @FXML
    private TextField tf_periode;

    @FXML
    private DatePicker tf_start;

    @FXML
    private TextField tf_tech;

    @FXML
    private TextField tf_title;

    @FXML
    private TextField tf_type;

    @FXML
    void back(ActionEvent event) {
        pn_info.toFront();
    }

    @FXML
    void intern(ActionEvent event) {
        grid.getChildren().clear();
        pn_intern.toFront();
        if (btnmod.isVisible()) {
            displayg();
        }else{
            displaygf();
        }
    }
    DepartementService ds = new DepartementService();
    InternshipService is = new InternshipService();
    private Departement d ;
    private int id;
    public void setData(Departement q) {
        this.d = q;
        id=q.getId();
        // headmaster.setText("Headmaster id :"+String.valueOf(q.getHeadmaster_id()));
        name.setText("Name :"+q.getName());
        desc.setText(q.getDescription());
        desc.setEditable(false);
        tf_name.setVisible(false);
    }
    public void setDataf(Departement q) {
        this.d = q;
        id=q.getId();
        //headmaster.setText("Headmaster id :"+String.valueOf(q.getHeadmaster_id()));
        name.setText("Name :"+q.getName());
        desc.setText(q.getDescription());
        desc.setEditable(false);
        tf_name.setVisible(false);
        btnmod.setVisible(false);
        btndel.setVisible(false);
        btnadd.setVisible(false);
    }

    @FXML
    void create(ActionEvent event) {
        LocalDate currentDate = LocalDate.now();
        if (tf_title.getText().isEmpty() || tf_desc.getText().isEmpty() ||tf_tech.getText().isEmpty() ||tf_type.getText().isEmpty()||tf_periode.getText().isEmpty()||tf_start.getValue().isBefore(currentDate)||tf_start.getValue()==null){
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields !");
            alert.showAndWait();
            return;
        }
        Internship p = new Internship(this.id,tf_title.getText(),tf_desc.getText(),tf_tech.getText(),tf_type.getText(),String.valueOf(tf_start.getValue()),tf_periode.getText());
        is.ajouterEntite(p);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Valider");
        alert.setHeaderText(null);
        alert.setContentText("Intership created successfully !");
        alert.showAndWait();
        tf_title.clear();
        tf_desc.clear();
        tf_type.clear();
        tf_tech.clear();
        tf_periode.clear();
        pn_info.toFront();
    }



    @FXML
    void delete(ActionEvent event) {
        try {
            // Create a confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.setContentText("Are you sure delete this departement?");

            // Add "OK" and "Cancel" buttons to the dialog
            confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            // Show the confirmation dialog and wait for the user's response
            ButtonType userResponse = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

            // If the user clicked "OK" in the confirmation dialog, proceed with the deletion
            if (userResponse == ButtonType.OK) {
                // Create a new User instance with the provided ID
                Departement eventToDelete = new Departement(this.id,0,"","");

                // Call the method to delete the user entity
                ds.supprimerEntite(eventToDelete);
                /*
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Dashboard.fxml"));
                Dashboard itemController = fxmlLoader.getController();
                ActionEvent e = new ActionEvent();
                itemController.refresh(e);

                 */
            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID entered by the user is not a valid integer
            // Display an error message or handle it as appropriate for your application
            e.printStackTrace(); // Or log the error
        }
    }

    @FXML
    void toadd(ActionEvent event) {
        pn_add.toFront();
    }

    @FXML
    void update(ActionEvent event) {
        if (Objects.equals(btnmod.getText(), "Update")) {
            btnmod.setText("Done");
            tf_name.setVisible(true);
            desc.setEditable(true);
        } else {
            if (tf_name.getText().isEmpty() || desc.getText().isEmpty() ) {
                // Afficher un message d'alerte
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return;
            }
            Departement p = new Departement(this.id,4,tf_name.getText(),desc.getText());
            ds.modifierEntite(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Valider");
            alert.setHeaderText(null);
            alert.setContentText("Depatement Modified !");
            alert.showAndWait();
            tf_name.setVisible(false);
            desc.setEditable(false);
            btnmod.setText("Update");
        }
    }

    private void displayg()
    {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = is.Getall(this.id);
        Departement pppp = new Departement();
        int column = 0;
        int row = 2;
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Intern.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    InternC itemController = fxmlLoader.getController();
                    int id=resultSet2.getInt("id");
                    int idd=resultSet2.getInt("departement_id");
                    String title=resultSet2.getString("title");
                    String description=resultSet2.getString("description");
                    String technology=resultSet2.getString("technology");
                    String typeinternship=resultSet2.getString("typeinternship");
                    String startdate=resultSet2.getString("startdate");
                    String period=resultSet2.getString("period");
                    Internship ppppp = new Internship(id,idd,title,description,technology,typeinternship,startdate,period);
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

    private void displaygf()
    {
        ///////////////////////////////////////////////////////////////
        ResultSet resultSet2 = is.Getall(this.id);
        Departement pppp = new Departement();
        int column = 0;
        int row = 2;
        try {
            while (resultSet2.next()) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/Intern.fxml"));
                try {
                    AnchorPane anchorPane = fxmlLoader.load();
                    InternC itemController = fxmlLoader.getController();
                    int id=resultSet2.getInt("id");
                    int idd=resultSet2.getInt("departement_id");
                    String title=resultSet2.getString("title");
                    String description=resultSet2.getString("description");
                    String technology=resultSet2.getString("technology");
                    String typeinternship=resultSet2.getString("typeinternship");
                    String startdate=resultSet2.getString("startdate");
                    String period=resultSet2.getString("period");
                    Internship ppppp = new Internship(id,idd,title,description,technology,typeinternship,startdate,period);
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