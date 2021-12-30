package fhtw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    // I'll get this value from the controller class
/*    public String searchElement = null;*/



    Controller base = new Controller();

    String test = null;



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
        tbl_hersteller.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_hersteller"));
        tbl_objekt.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_objekt"));
        tbl_preis.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_preis"));
        tbl_webseite.setCellValueFactory(new PropertyValueFactory<WebScraper, String>("tbl_webseite"));


        WebScraper crawl = new WebScraper();

 //       String lala = base.searchElement;
        String lala = arrayList.get(0);
        crawl.scrapeWH(lala, 25);
//        crawl.scrapeWH("Playstation 5", 100); //Elemente sind nach Methodenaufruf in meiner Observable list
        tbl_TabelView.setItems(crawl.list); // Hinzufügen meiner Observablelist zur Tableview

    }


    @FXML
    void onTable(ActionEvent event) {


    }

    @FXML
    void onTableviewSearch(ActionEvent event) {

    }



}
