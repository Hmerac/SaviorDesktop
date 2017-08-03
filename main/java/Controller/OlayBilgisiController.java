package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.Session;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Mert
 */
public class OlayBilgisiController implements Initializable {

    @FXML
    public JFXTextField olaykategorisi;

    @FXML
    private JFXTextField olayadresi;

    @FXML
    private JFXTextField plaka;

    @FXML
    private JFXTextArea aciklama;

    @FXML
    private Label yazi;

    @FXML
    private JFXButton ekipbutonu;

    @FXML
    private ImageView tik;

    @FXML
    private ImageView foto;

    @FXML
    private void handlefotografButonuAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/FotografGoruntuleme.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root1);
        Stage stage = new Stage();
        stage.setTitle("Savior");
        stage.getIcons().add(new Image("/images/companyicon.png"));
        scene.getStylesheets().add("/styles/Styles.css");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleekipButonuAction(ActionEvent event) throws IOException {
        BufferedImage image = null;
        URL url = new URL("http://www.myiconfinder.com/uploads/iconsets/128-128-c0829a49b2acd49adeab380f70eb680a-accept.png");
        image = ImageIO.read(url);
        Image card = SwingFXUtils.toFXImage(image, null);
        tik.setImage(card);
        yazi.setText("Ekip yollandı.");
        ekipbutonu.setVisible(false);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*olaykategorisi.setText("Trafik Kazası");

        if (Session.currentCase.licenceNumber == null) {
            plaka.setText("Plaka yok");
        } else {
            plaka.setText(Session.currentCase.licenceNumber);
        }

        if (Session.currentCase.caseAddress == null) {
            olayadresi.setText("Adres yok");
        } else {
            olayadresi.setText(Session.currentCase.caseAddress);
        }
        
        aciklama.setText(Session.currentCase.description);

        if (Session.currentCase.casePhotosBase64 != null) {
            byte[] imageBytes = null;

            imageBytes = org.apache.commons.codec.binary.Base64.decodeBase64(Session.currentCase.casePhotosBase64);
            try {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBytes));
                Image card = SwingFXUtils.toFXImage(img, null);
                foto.setImage(card);
            } catch (IOException ex) {
                Logger.getLogger(OlayBilgisiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/

    }

}
