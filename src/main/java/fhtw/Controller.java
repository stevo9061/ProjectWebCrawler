package fhtw;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class Controller {
//public class Controller {


private Stage stage = null;
private Scene scene = null;
private Parent root= null;

    @FXML
    private Button btn_Ok;

    @FXML
    private Label lbl_Search;

    @FXML
    private TextField txt_Textzeile;

    public String searchElement;


//   Wenn der Button Filter betätigt wird, dann wird auf die Filter FXML gewechselt.
//   @FXML //TODO: Brauchen wir das?
//    void onFilter(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("/Filter.fxml"));
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }




    //Wenn der Button Ok betätigt wird, dann wird auf die TabelView FXML gewechselt.
    @FXML
    void onOk(ActionEvent event) throws IOException {



        WebScraper test = new WebScraper("Testversuch");

/** String kann direkt übernommen werden, da die Methode getText() eines Textfeldes einen String zurück liefert */
        searchElement = txt_Textzeile.getText();

        ControllerTableView.arrayList.add(searchElement);
    //TODO: Mein searchElement kann nicht erfolgreich der ControllerTableView übergeben werden.
    //TODO: Kann ich nicht hier meinne Webscraper instanzieren und searchname übergeben?



        Parent root = FXMLLoader.load(getClass().getResource("/TabelView.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

/** I gebe meiner ControllerTableView Klasse das zu suchende Element */
/*        ControllerTableView temp = new ControllerTableView(searchElement);*/
//        setSearchElement(searchElement);


    }


//Filter FXML
/*        @FXML
        private Button btn_back; //Todo: Brauchen wir das?

        @FXML
        private Label lbl_Filter; //Todo: Brauchen wir das?*/

    //Wenn der Button Search betätigt wird, dann wird auf die webCrawler FXML gewechselt.
//    @FXML
//    void onBack(ActionEvent event) throws IOException { // Todo: Brauchen wir das?
//        Parent root = FXMLLoader.load(getClass().getResource("/webCrawler.fxml"));
//        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }





//Wenn der Button Search betätigt wird, dann wird auf die webCrawler FXML gewechselt.
/*        @FXML
        void onTableviewSearch(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/webCrawler.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }*/



}


