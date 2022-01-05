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



    /** Interacts with TabelView FXML */
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
    TableView<WebScraper> tbl_TabelView;

    @FXML
    private TableColumn<WebScraper, String> tbl_beschreibung;

    @FXML
    private TableColumn<WebScraper, String> tbl_element;

    @FXML
    private TableColumn<WebScraper, String> tbl_preis;

    @FXML
    private TableColumn<WebScraper, String> tbl_webseite;

    @FXML
    private TableColumn<WebScraper, String> tbl_postcode;


   ObservableList<WebScraper> list = FXCollections.observableArrayList();
   static ArrayList<String> arrayList = new ArrayList<>();
   private ArrayList<WebScraper> myArrayListTwo = new ArrayList<>();
   private String beschreibung = null;
   private String element = null;
   private String price = null;
   private String webseite = null;
   private String postcode = null;

    /** Hier sind die Attribute von der ControllerTabelview mit denen vom Webscraper verknüpft
        Für jedes column in unserer Table erstellen wir eine Cell Value Factory **/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tbl_beschreibung.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_beschreibung"));
        tbl_element.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_element"));
        tbl_preis.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_preis"));
        tbl_webseite.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_webseite"));
        tbl_postcode.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_postcode"));

        WebScraper crawl = new WebScraper();
        // Wir nehmen das searchElement
        String keywordElementNumber = arrayList.get(0);

        // Wir nehmen das searchNumber
        String keywordSearchElement = arrayList.get(1);

        // Wir nehmen das searchRegion
        String keywordRegion = arrayList.get(2);

        crawl.scrapeWH(keywordSearchElement, keywordElementNumber, keywordRegion);
        tbl_TabelView.setItems(crawl.list); // Hinzufügen meiner Observablelist zur Tableview


/*      String hersteller = null;
        String objekt = null;
        String price = null;
        String webseite = null;           */
        ArrayList<String> myArrayList = new ArrayList<String>();

        // Erstellen eines *.csv Files //TODO: CSV Export geht nimma
        try {
            CSVWriter willhabenOutput = new CSVWriter(new File("src/main/java/fhtw/WillhabenExport.csv"), null);
            String[] arr = {"Ausgabe von " + keywordSearchElement};
            willhabenOutput.writeHeader(arr);
            // Cast arrayList to String Array to call method writeData
            // Packen in ein Arraylist String


//            System.out.println(Thread.currentThread());
            /** Runnable ist eine funktionale Schnittstelle die eine run() Methode enthält
                In der run() Methode definiere ich was mein Thread abarbeiten soll **/
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; tbl_preis.getCellData(i) != null; i++) {
                        beschreibung = tbl_beschreibung.getCellData(i);
                        element = tbl_element.getCellData(i);
                        price = tbl_preis.getCellData(i);
                        webseite = tbl_webseite.getCellData(i);
                        postcode = tbl_postcode.getCellData(i);
                        myArrayList.add(beschreibung + "; " + element + "; " + price + "; " + webseite); //TODO: ArraylistTwo probieren
                        System.out.println(price);
//                      System.out.println(Thread.currentThread() );
//                      System.out.println(i);
                        myArrayListTwo.add(new WebScraper(tbl_beschreibung.getCellData(i), tbl_element.getCellData(i),
                                tbl_webseite.getCellData(i), tbl_preis.getCellData(i), tbl_postcode.getCellData(i) ));


                    }

                }
            };
            // Ein Thread wird erstmal hergerichtet
            Thread thread = new Thread( runnable);
            // Jetzt wird die run() Methode von dem runnable Objekt ausgeführt
            thread.start();

   /*         for (int i = 0; tbl_preis.getCellData(i) != null; i++) {
                hersteller = tbl_hersteller.getCellData(i);
                objekt = tbl_objekt.getCellData(i);
                price = tbl_preis.getCellData(i);
                webseite = tbl_webseite.getCellData(i);
                myArrayList.add(hersteller + "; " + objekt + "; " + price + "; " + webseite);
                System.out.println(price);

                myArrayListTwo.add(new WebScraper(tbl_hersteller.getCellData(i), tbl_objekt.getCellData(i),
                        tbl_webseite.getCellData(i), tbl_preis.getCellData(i)));


            }*/

            // Packen in ein String[] Array
//            String[] myArray = new String[runnable.myArrayList.size()];
            String[] myArray = new String[myArrayList.size()]; //TODO: Das Problem ist, das meine myArrayList weggeräumt wird sobald ich aus der run methode bin
            int i = 0;                                         //TODO: KAnns nur mit der myArrayListTwo probieren aber die is vom Typ <WebScraper>
            for ( String iter : myArrayList) {
                myArray[i] = myArrayList.get(i);
                i++;
            }

            willhabenOutput.writeData(myArray); // TODO: Hier kommt die Exception, deswgen geht csv nicht mehr
            willhabenOutput.close();



        } catch (IOException e) {
            e.printStackTrace();
        }

        //Erstellen eines *.xls Files
        ExcelWriter excelWriter = new ExcelWriter();
//        excelWriter.createTest();


        try  {

            excelWriter.createFile("src/main/java/fhtw/WillhabenExport.xls", myArrayListTwo); //TODO: Postcode ist null

        } catch (Exception e) {
            e.printStackTrace();
        }




    }


    @FXML
    void onTable(ActionEvent event) {


    }

    @FXML
    void onTableviewSearch(ActionEvent event) {

    }



}
