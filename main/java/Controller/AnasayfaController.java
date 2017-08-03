package Controller;

import Main.MainApp;
import Model.Cases;
import Model.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import netscape.javascript.JSObject;

public class AnasayfaController implements Initializable, MapComponentInitializedListener {

    private Timer notificationTimer = new Timer();

    String amo = "";

    public static AnchorPane anchor;

    @FXML
    private GoogleMapView mapView;

    public static GoogleMap map;

    public static int caseCount = 0;

    public static boolean newControl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {

        newControl = false;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url1 = new URL("http://saviorwebservice.herokuapp.com/cases/getAllCases");
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setDoOutput(true);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json");

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            (conn.getInputStream())));

                    String output = "";
                    output = br.readLine();
                    Gson gson = new Gson();
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(output);
                    for (JsonElement element : jsonArray) {
                        Cases initialCase = gson.fromJson(element, Cases.class);
                        addCase(initialCase);
                    }

                    conn.disconnect();

                } catch (MalformedURLException e) {

                    e.printStackTrace();

                } catch (IOException e) {

                    e.printStackTrace();

                }
            }
        });

        //Trigger every 5 sec to get new cases
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            URL url1 = new URL("http://saviorwebservice.herokuapp.com/cases/getAllCases");
                            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                            conn.setDoOutput(true);
                            conn.setRequestMethod("GET");
                            conn.setRequestProperty("Content-Type", "application/json");

                            BufferedReader br = new BufferedReader(new InputStreamReader(
                                    (conn.getInputStream())));

                            String output = "";
                            output = br.readLine();
                            Gson gson = new Gson();
                            JsonParser jsonParser = new JsonParser();
                            JsonArray jsonArray = (JsonArray) jsonParser.parse(output);
                            int caseCountMaster = 0;
                            for (JsonElement element : jsonArray) {
                                caseCountMaster++;
                                Cases initialCase = gson.fromJson(element, Cases.class);
                                if (caseCountMaster > caseCount) {
                                    System.out.println("Geldim :)");
                                    newControl = true;
                                    addCase(initialCase);

                                    // create a timer which periodically displays a notification message.
                                    notificationTimer.schedule(
                                            new TimerTask() {
                                        @Override
                                        public void run() {

                                            if (initialCase.caseType.equals("1")) {
                                                amo = "Trafik Kazasi";
                                            }

                                            javax.swing.SwingUtilities.invokeLater(()
                                                    -> MainApp.trayIcon.displayMessage(
                                                            "Yeni Acil Durum!",
                                                            amo,
                                                            java.awt.TrayIcon.MessageType.WARNING
                                                    )
                                            );
                                        }
                                    },
                                            1_000
                                    );
                                    int currentZoom = map.getZoom();
                                    map.setZoom(currentZoom - 1);
                                    map.setZoom(currentZoom);
                                }
                            }

                            conn.disconnect();

                        } catch (MalformedURLException e) {

                            e.printStackTrace();

                        } catch (IOException e) {

                            e.printStackTrace();

                        }
                    }
                });
            }
        }, 15, 5, TimeUnit.SECONDS);
        //Trigger every 5 sec to get new cases

        LatLong officerLocation = new LatLong(Model.Session.currentOfficer.latitude, Model.Session.currentOfficer.longitude);

        String json = "";

        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(Model.Session.currentOfficer.latitude, Model.Session.currentOfficer.longitude))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        MarkerOptions officerLocationMarkerOptions = new MarkerOptions();
        officerLocationMarkerOptions.position(officerLocation);
        officerLocationMarkerOptions.icon("http://www.yardmastersheds.co.uk/skin/frontend/default/yardmaster/images/blue-dot.png");

        Marker officerLocationMarker = new Marker(officerLocationMarkerOptions);

        map.addMarker(officerLocationMarker);

        InfoWindowOptions officerLocationInfo = new InfoWindowOptions();
        officerLocationInfo.content("<h2>Şu an buradasınız</h2>");

        InfoWindow officerInfoWindow = new InfoWindow(officerLocationInfo);

        map.addUIEventHandler(officerLocationMarker, UIEventType.click, (JSObject obj) -> {
            officerInfoWindow.open(map, officerLocationMarker);
        });

    }

    public void addCase(Cases situation) {
        LatLong situationLocation = new LatLong(situation.latitude, situation.longitude);

        MarkerOptions situationLocationMarkerOptions = new MarkerOptions();
        situationLocationMarkerOptions.position(situationLocation);
        if (newControl == true) {
            situationLocationMarkerOptions.icon("http://www.gutierrezdentistry.com/assets/images/ui/contact-map.png");
        }

        Marker situationLocationMarker = new Marker(situationLocationMarkerOptions);

        map.addMarker(situationLocationMarker);

        InfoWindowOptions situationLocationInfo = new InfoWindowOptions();
        situationLocationInfo.content("<h2>" + situation.caseType + "</h2>"
                + situation.description);

        InfoWindow situationInfoWindow = new InfoWindow(situationLocationInfo);

        map.addUIEventHandler(situationLocationMarker, UIEventType.click, (JSObject obj) -> {
            Session.currentCase = situation;
            AnasayfaController.anchor.getChildren().clear();
            try {
                AnasayfaController.anchor.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/fxml/OlayBilgisi.fxml")));
            } catch (IOException ex) {
                Logger.getLogger(AnasayfaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            situationInfoWindow.open(map, situationLocationMarker);
        });

        caseCount++;

        newControl = false;

    }
}
