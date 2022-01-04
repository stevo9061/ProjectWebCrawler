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
    private TableColumn<WebScraper, String> tbl_hersteller;

    @FXML
    private TableColumn<WebScraper, String> tbl_objekt;

    @FXML
    private TableColumn<WebScraper, String> tbl_preis;

    @FXML
    private TableColumn<WebScraper, String> tbl_webseite;


   ObservableList<WebScraper> list = FXCollections.observableArrayList();
   static ArrayList<String> arrayList = new ArrayList<>();
   private ArrayList<WebScraper> myArrayListTwo = new ArrayList<>();
   private String hersteller = null;
   private String objekt = null;
   private String price = null;
   private String webseite = null;

    /** Hier sind die Attribute von der ControllerTabelview mit denen vom Webscraper verknüpft
        Für jedes column in unserer Table erstellen wir eine Cell Value Factory **/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tbl_hersteller.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_hersteller"));
        tbl_objekt.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_objekt"));
        tbl_preis.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_preis"));
        tbl_webseite.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_webseite"));


        WebScraper crawl = new WebScraper();
        // Wir nehmen das searchElement
        String keywordElementNumber = arrayList.get(0);

        // Wir nehmen das searchNumber
        String keywordSearchElement = arrayList.get(1);

        crawl.scrapeWH(keywordSearchElement, keywordElementNumber);
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
                        hersteller = tbl_hersteller.getCellData(i);
                        objekt = tbl_objekt.getCellData(i);
                        price = tbl_preis.getCellData(i);
                        webseite = tbl_webseite.getCellData(i);
                        myArrayList.add(hersteller + "; " + objekt + "; " + price + "; " + webseite); //TODO: ArraylistTwo probieren
                        System.out.println(price);
//                      System.out.println(Thread.currentThread() );
//                      System.out.println(i);
                        myArrayListTwo.add(new WebScraper(tbl_hersteller.getCellData(i), tbl_objekt.getCellData(i),
                                tbl_webseite.getCellData(i), tbl_preis.getCellData(i)));


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
            String[] myArray = new String[myArrayList.size()];
            int i = 0;
            for ( String iter : myArrayList) {
                myArray[i] = myArrayList.get(i);
                i++;
            }

            willhabenOutput.writeData(myArray);
            willhabenOutput.close();



        } catch (IOException e) {
            e.printStackTrace();
        }

        //Erstellen eines *.xls Files
        ExcelWriter excelWriter = new ExcelWriter();
//        excelWriter.createTest();


        try  {

            excelWriter.createFile("src/main/java/fhtw/WillhabenExport.xls", myArrayListTwo);

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
