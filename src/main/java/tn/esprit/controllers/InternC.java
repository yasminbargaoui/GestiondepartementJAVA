package tn.esprit.controllers;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tn.esprit.entities.Departement;
import tn.esprit.entities.Internship;
import tn.esprit.service.InternshipService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InternC {

    private int id;
    private Internship i;
    InternshipService is = new InternshipService();

    @FXML
    private Button btnmod;

    @FXML
    private Button btndel;

    @FXML
    private ImageView qrcode;

    @FXML
    private DatePicker tf_date;

    @FXML
    private TextArea tf_desc;

    @FXML
    private TextField tf_periode;

    @FXML
    private TextField tf_tech;

    @FXML
    private TextField tf_title;




        public void setDataf(Internship q) {
            this.i = q;
            id=q.getId();
            tf_title.setText(q.getTitle());
            tf_title.setEditable(false);
            tf_desc.setText(q.getDescription());
            tf_desc.setEditable(false);
            tf_tech.setText(q.getTechnology());
            tf_tech.setEditable(false);
            tf_periode.setText(q.getPeriod());
            tf_periode.setEditable(false);
            btnmod.setVisible(false);
            btndel.setVisible(false);
            try {
                // Define the date format that matches the expected format of dateString
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Parse the dateString to a LocalDate object
                LocalDate date = LocalDate.parse(q.getStartdate(), formatter);

                // Set the parsed LocalDate to the DatePicker
                tf_date.setValue(date);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing the date: " + e.getMessage());
                // Handle error or set a default date if needed
            }
            tf_date.setEditable(false);
        }
        //qr code
        public void setData(Internship q) {
            this.i = q;
            id=q.getId();
            tf_title.setText(q.getTitle());
            tf_title.setEditable(false);
            tf_desc.setText(q.getDescription());
            tf_desc.setEditable(false);
            tf_tech.setText(q.getTechnology());
            tf_tech.setEditable(false);
            tf_periode.setText(q.getPeriod());
            tf_periode.setEditable(false);
            try {

                // Define the date format that matches the expected format of dateString
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // Parse the dateString to a LocalDate object
                LocalDate date = LocalDate.parse(q.getStartdate(), formatter);

                // Set the parsed LocalDate to the DatePicker
                tf_date.setValue(date);
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing the date: " + e.getMessage());
                // Handle error or set a default date if needed
            }
            tf_date.setEditable(false);
            try {
                QRCodeWriter qrCodeWriter = new QRCodeWriter();
                String Information = "titre : " +q.getTitle()+ "\n" + "description d : " + q.getDescription() + "\n" + "technologie : " + q.getTechnology() + "\n" + "start date : " + q.getStartdate()+"\n"+"periode :"+q.getPeriod();
                int width = 300;
                int height = 300;

                BufferedImage bufferedImage = null;
                BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
                bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                bufferedImage.createGraphics();

                Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
                graphics.setBackground(java.awt.Color.WHITE);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(java.awt.Color.BLACK);

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (byteMatrix.get(i, j)) {
                            graphics.fillRect(i, j, 1, 1);
                        }
                    }
                }

                System.out.println("Success...");

                qrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

                //  ImageView qrc = new ImageView();
                // TODO
            } catch (WriterException ex) {
                Logger.getLogger(InternC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    /*
    //qr code with img
    public void setData(Internship q) {
        this.i = q;
        id = q.getId();
        tf_title.setText(q.getTitle());
        tf_title.setEditable(false);
        tf_desc.setText(q.getDescription());
        tf_desc.setEditable(false);
        tf_tech.setText(q.getTechnology());
        tf_tech.setEditable(false);
        tf_periode.setText(q.getPeriod());
        tf_periode.setEditable(false);
        try {
            // Define the date format that matches the expected format of dateString
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Parse the dateString to a LocalDate object
            LocalDate date = LocalDate.parse(q.getStartdate(), formatter);
            // Set the parsed LocalDate to the DatePicker
            tf_date.setValue(date);
        } catch (DateTimeParseException e) {
            System.err.println("Error parsing the date: " + e.getMessage());
            // Handle error or set a default date if needed
        }
        tf_date.setEditable(false);
        try {
            // Combine all the information into a single string
            String imageUrl = q.getImgUrl(); // Récupérez l'URL de l'image depuis l'objet Internship
            String information = "titre:" + q.getTitle() + ";description:" + q.getDescription() + ";technologie:" + q.getTechnology() + ";start_date:" + q.getStartdate() + ";periode:" + q.getPeriod() + ";img_url:" + imageUrl; // Combinez l'URL de l'image avec les autres informations

            // Generate QR code with combined information
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            int width = 300;
            int height = 300;
            BitMatrix byteMatrix = qrCodeWriter.encode(information, BarcodeFormat.QR_CODE, width, height);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setBackground(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            // Set the generated QR code image to the ImageView
            qrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));

        } catch (WriterException ex) {
            Logger.getLogger(InternC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     */

    @FXML
    void delete(ActionEvent event) {
        try {
            // Create a confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.setContentText("Are you sure delete this internship offer?");

            // Add "OK" and "Cancel" buttons to the dialog
            confirmationDialog.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

            // Show the confirmation dialog and wait for the user's response
            ButtonType userResponse = confirmationDialog.showAndWait().orElse(ButtonType.CANCEL);

            // If the user clicked "OK" in the confirmation dialog, proceed with the deletion
            if (userResponse == ButtonType.OK) {
                // Create a new User instance with the provided ID
                Internship eventToDelete = new Internship(this.id, 0, "", "", "", "", "", "");

                // Call the method to delete the user entity
                is.supprimerEntite(eventToDelete);

            }
        } catch (NumberFormatException e) {
            // Handle the case where the ID entered by the user is not a valid integer
            // Display an error message or handle it as appropriate for your application
            e.printStackTrace(); // Or log the error
        }
    }


    @FXML
    void update(ActionEvent event) {
        if (Objects.equals(btnmod.getText(), "Update")) {
            btnmod.setText("Done");
            System.out.println(this.id);
            tf_date.setEditable(true);
            tf_periode.setEditable(true);
            tf_tech.setEditable(true);
            tf_desc.setEditable(true);
            tf_title.setEditable(true);
        } else {
            LocalDate currentDate = LocalDate.now();
            if (tf_periode.getText().isEmpty() || tf_desc.getText().isEmpty() || tf_title.getText().isEmpty() || tf_tech.getText().isEmpty() || tf_date.getValue().isBefore(currentDate) || tf_date.getValue() == null) {
                // Afficher un message d'alerte
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs manquants");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs !");
                alert.showAndWait();
                return;
            }
            Internship p = new Internship(this.id, this.id, tf_title.getText(), tf_desc.getText(), tf_tech.getText(), i.getTypeinternship(), String.valueOf(tf_date.getValue()), tf_periode.getText());
            is.modifierEntite(p);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Valider");
            alert.setHeaderText(null);
            alert.setContentText("Depatement Modified !");
            alert.showAndWait();
            tf_date.setEditable(false);
            tf_periode.setEditable(false);
            tf_tech.setEditable(false);
            tf_desc.setEditable(false);
            tf_title.setEditable(false);
            System.out.println(this.id);
            btnmod.setText("Update");
        }
    }


}