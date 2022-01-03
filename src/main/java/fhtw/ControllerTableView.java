package fhtw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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

    // I'll get this value from the controller class
/*    public String searchElement = null;*/








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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /** Hier sind die Attribute von der ControllerTabelview mit denen vom Webscraper verknüpft */
        /** Für jedes column in unserer Table erstellen wir eine Cell Value Factory **/
        tbl_hersteller.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_hersteller"));
        tbl_objekt.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_objekt"));
        tbl_preis.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_preis"));
        tbl_webseite.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_webseite"));


        WebScraper crawl = new WebScraper();
        String keyword = arrayList.get(0);
        crawl.scrapeWH(keyword, 25);
        tbl_TabelView.setItems(crawl.list); // Hinzufügen meiner Observablelist zur Tableview


        String hersteller = null;
        String objekt = null;
        String price = null;
        String webseite = null;
        ArrayList<String> myArrayList = new ArrayList<String>();

        try {
            CSVWriter willhabenOutput = new CSVWriter(new File("src/main/java/fhtw/output.csv"), null);
            String[] arr = {"Ausgabe von " + keyword};
            willhabenOutput.writeHeader(arr);
            /** Cast arrayList to String Array to call method writeData **/
            //TODO: I need a possibility to store my dynamic content into a STring array, only then i can write into
            // a csv. At best, i create for each column one separate String array.

            // Packen in ein Arraylist String
            for (int i = 0; tbl_preis.getCellData(i) != null; i++) {
                hersteller = tbl_hersteller.getCellData(i);
                objekt = tbl_objekt.getCellData(i);
                price = tbl_preis.getCellData(i);
                webseite = tbl_webseite.getCellData(i);
                myArrayList.add(hersteller + "; " + objekt + "; " + price + "; " + webseite);
                System.out.println(price);
            }
            // Packen in ein String[] Array
            String[] myArray = new String[myArrayList.size()];
            int i = 0;
            for ( String asd : myArrayList) {
                myArray[i] = myArrayList.get(i);
                i++;
            }

            willhabenOutput.writeData(myArray);
            willhabenOutput.close();



        } catch (IOException e) {
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
