/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Officer;
import com.google.gson.Gson;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mert
 */
public class GirisController implements Initializable {

    @FXML
    private ImageView wrongpic;

    @FXML
    private Label wronglab;

    @FXML
    private JFXTextField tcNo;

    @FXML
    private JFXPasswordField password;

    static boolean auth = true;

    @FXML
    private void handlegirisButonuAction(ActionEvent event) throws IOException {

        Gson g = new Gson();
        Officer temp = new Officer(tcNo.getText(), password.getText());
        String json = g.toJson(temp);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url = new URL("http://saviorwebservice.herokuapp.com/polices/login");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");

                    OutputStream os = conn.getOutputStream();
                    os.write(json.getBytes());
                    os.flush();

                    if (conn.getResponseCode() == 500) {
                        wrongpic.setVisible(true);
                        wronglab.setVisible(true);
                        auth = false;
                    }

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String output;
                    output = br.readLine();

                    Gson gson = new Gson();
                    Officer officer = gson.fromJson(output, Officer.class);
                    Model.Session.currentOfficer = officer;

                    conn.disconnect();

                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }

                if (auth == true) {

                    try {
                        Parent homePageParent = FXMLLoader.load(getClass().getResource("/fxml/Master.fxml"));
                        Scene homePageScene = new Scene(homePageParent);
                        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        appStage.setScene(homePageScene);
                        homePageScene.getStylesheets().add("/styles/Styles.css");
                        appStage.setResizable(false);
                        appStage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(GirisController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
