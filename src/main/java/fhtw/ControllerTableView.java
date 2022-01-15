package fhtw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerTableView implements Initializable {



    /**
     * Interacts with TabelView FXML
     */
    @FXML
    private Button btn_tableviewSearch;

    @FXML
    private Label lbl_Tabel;

    @FXML
    private Label lbl_TabelFilter;

    @FXML
    private TextField txt_Filter_Hersteller;

    @FXML
    private TextField txt_Filter_Preis;

    @FXML
    private TextField txt_Filter_Suchbegriff;

    @FXML
    private TextField txt_Filter_Webseite;


    @FXML
    TableView<WebScraper> tbl_TableView;

    @FXML
    private TableColumn<WebScraper, String> tbl_id;

    @FXML
    private TableColumn<WebScraper, String> tbl_element;

    @FXML
    private TableColumn<WebScraper, String> tbl_preis;

    @FXML
    private TableColumn<WebScraper, String> tbl_webseite;

    @FXML
    private TableColumn<WebScraper, String> tbl_postcode;


   ObservableList<WebScraper> observableList = FXCollections.observableArrayList();
   static ArrayList<String> arrayList = new ArrayList<>();
   private ArrayList<WebScraper> myArrayListTwo = new ArrayList<>();
   private ArrayList<String> arraylistCsv = new ArrayList<>();
   private String id = null;
   private String element = null;
   private String price = null;
   private String webseite = null;
   private String postcode = null;

    /**
     * Hier sind die Attribute von der ControllerTableview mit denen von der Klasse WebScraper verknüpft.
     * Für jedes Column in unserer Table erstellen wir eine Cell Value Factory
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tbl_id.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_id"));
        tbl_element.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_element"));
        tbl_preis.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_preis"));
        tbl_webseite.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_webseite"));
        tbl_postcode.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_postcode"));

        WebScraper crawl = new WebScraper();
        // Hier nehmen wir uns die Elementanzahl aus dem ArrayList welches wir in Controller hinzugefügt haben
        String keywordElementNumber = arrayList.get(0);

        // Hier nehmen wir uns den Suchbegriff aus dem ArrayList welches wir in Controller hinzugefügt haben
        String keywordSearchElement = arrayList.get(1);

        // Hier nehmen wir uns die Plz aus dem ArrayList welches wir in Controller hinzugefügt haben
        String keywordRegion = arrayList.get(2);

        // Hier gehts los, wir starten die Methode von unserem Objekt crawl
        crawl.scrapeWH(keywordSearchElement, keywordElementNumber, keywordRegion);

        // Hinzufügen meiner Observablelist zur Tableview
        tbl_TableView.setItems(crawl.observableList);

        ArrayList<String> myArrayList = new ArrayList<String>();




        /**
         * Runnable ist eine funktionale Schnittstelle die eine run() Methode enthält
         * In der run() Methode definiere ich was mein Thread abarbeiten soll
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                /** Erstellen eines *.csv Files */
                CSVWriter willhabenOutput = null;

                try {
                    willhabenOutput = new CSVWriter(new File("src/main/java/fhtw/WillhabenExport.csv"), null);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Erste Zeile des *.csv Outputs mit Suchbegriff
                String[] arr = {"Ausgabe von " + keywordSearchElement};


                // Hier schreiben wir die erste Zeile schon in den Stream
                try {
                    willhabenOutput.writeHeader(arr);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                /** Wir holen uns hier einzeln die Elemente heraus wie wir möchten, und speichern diese in unsere ArrayList
                 für das *.csv und *.xls File */
                for (int i = 0; tbl_preis.getCellData(i) != null; i++) {
                    id = tbl_id.getCellData(i);
                    element = tbl_element.getCellData(i);
                    price = tbl_preis.getCellData(i);
                    webseite = tbl_webseite.getCellData(i);
                    postcode = tbl_postcode.getCellData(i);
                    myArrayList.add(id + "; " + element + "; " + price + "; " + webseite); //TODO: ArraylistTwo probieren
                    arraylistCsv.add(id + "; " + element + "; " + price + "; " + webseite); //TODO: For CSV, mit dem sollts gehen
//                        System.out.println(price);
//                      System.out.println(Thread.currentThread() );
//                      System.out.println(i);
                    myArrayListTwo.add(new WebScraper(tbl_id.getCellData(i), tbl_element.getCellData(i),
                            tbl_webseite.getCellData(i), tbl_preis.getCellData(i), tbl_postcode.getCellData(i) ));


                }
                String[] myArray = new String[arraylistCsv.size()];

                int i = 0;
                for ( String iter : arraylistCsv) {

                    myArray[i] = arraylistCsv.get(i);
                    i++;
                }
                try {
                    willhabenOutput.writeData(myArray);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    willhabenOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };


        // Ein Thread wird erstmal hergerichtet
        Thread thread = new Thread( runnable);
        // Jetzt wird die run() Methode von dem runnable Objekt ausgeführt
        thread.start();




        /** Erstellen eines *.xls Files */
        ExcelWriter excelWriter = new ExcelWriter();

        try  {

            excelWriter.createFile("src/main/java/fhtw/WillhabenExport.xls", myArrayListTwo); //TODO: Postcode ist null

        } catch(Exception e) {
            e.printStackTrace();
        }




    }


    @FXML
    void onTableviewSearch(ActionEvent event) {

        //TODO: Ist glaub ich fürs sortieren
    }



}
