package fhtw;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;



public class Controller {//implements Initializable
//public class Controller {
private Stage stage;
private Scene scene;
private Parent root;



        //Wenn der Button Filter betätigt wird, dann wird auf die Filter FXML gewechselt.
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


        Parent root = FXMLLoader.load(getClass().getResource("/TabelView.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



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


