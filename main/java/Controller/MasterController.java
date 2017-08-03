/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mert
 */
public class MasterController implements Initializable {

    // Binding with the FXML
    @FXML
    public AnchorPane content;

    @FXML
    private Label name;

    @FXML
    private Label lastName;

    @FXML
    private void handleanasayfaButonuAction(ActionEvent event) throws IOException {
        content.getChildren().clear();
        content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/fxml/Anasayfa.fxml")));
    }

    @FXML
    private void handlekazaButonuAction(ActionEvent event) throws IOException {
        content.getChildren().clear();
        content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/fxml/OlayBilgisi.fxml")));
    }

    @FXML
    private void handlekisiselButonuAction(ActionEvent event) throws IOException {
        content.getChildren().clear();
        content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/fxml/KisiselBilgiler.fxml")));
    }

    @FXML
    private void handleguncelleButonuAction(ActionEvent event) throws IOException {
        content.getChildren().clear();
        content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/fxml/ProfilGuncelleme.fxml")));
    }

    @FXML
    private void handleoturumKapaButonuAction(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/fxml/Giris.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePageScene);
        homePageScene.getStylesheets().add("/styles/Styles.css");
        appStage.setResizable(false);
        appStage.show();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Update name and surname of the officer
        name.setText(Model.Session.currentOfficer.name);
        lastName.setText(Model.Session.currentOfficer.lastName);
        //Update name and surname of the officer

        try {
            content.getChildren().clear();
            content.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/fxml/Anasayfa.fxml")));
            AnasayfaController.anchor = content;

        } catch (IOException ex) {
            Logger.getLogger(MasterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
