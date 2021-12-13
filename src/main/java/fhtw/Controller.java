package fhtw;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class Controller {//implements Initializable {
private Stage stage;
private Scene scene;
private Parent root;
//webCrawler
        @FXML
        private Button btn_filter;

        @FXML
        private Button btn_search;

        @FXML
        private TextField lbl_Objekt;

        @FXML
        private Label txt_Objekt;

        //Wenn der Button Filter betätigt wird, dann wird auf die Filter FXML gewechselt.

    @FXML
    void onFilter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/Filter.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //Wenn der Button Search betätigt wird, dann wird auf die TabelView FXML gewechselt.
    @FXML
    void onSearch(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/TabelView.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
//Filter FXML
        @FXML
        private Button btn_back;

        @FXML
        private Label lbl_Filter;

    //Wenn der Button Search betätigt wird, dann wird auf die webCrawler FXML gewechselt.
    @FXML
    void onBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webCrawler.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

//TabelView FXML

        @FXML
        private Button btn_newSearch;

        @FXML
        private Label lbl_Tabel;

        // TabelView Gehört noch die Kalsse eingefügt und der Typ (string; Integer;...)

/*        @FXML
        private TableView<Search> tbl_TabelView;

        @FXML
        private TableColumn<Search, String> tbl_objekt;

        @FXML
        private TableColumn<Search, String> tbl_hersteller;

        @FXML
        private TableColumn<Search, String> tbl_webseite;

        @FXML
        private TableColumn< Search, Integer> tbl_preis;*/

        /*ObservableList<Search> searchObservableList = FXCollections.observableArrayList();

        @Override
        public void initialize(URL url, ResourceBundle resource){

        }*/

        @FXML
        private TextField txt_Filter_Objekt;

        @FXML
        private TextField txt_Filter_Preis;

        @FXML
        private TextField txt_Filter_WebSeite;


    //Wenn der Button Search betätigt wird, dann wird auf die webCrawler FXML gewechselt.
        @FXML
        void onnewSearch(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/webCrawler.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }


    }


//Hallo Test