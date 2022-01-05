package fhtw;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.stage.Stage;



public class Controller implements Initializable {


private Stage stage = null;
private Scene scene = null;
private Parent root= null;

    @FXML
    private Button btn_Ok;

    @FXML
    private Label lbl_Search;

    @FXML
    private TextField txt_Textzeile;

    @FXML
    private TextField txt_TextzeileRegion;

    @FXML
    private ComboBox<String> combo;


    private String searchElement;
    private String searchNumber;
    private String searchRegion;


/** Wenn der Button Ok betätigt wird, dann wird auf die TabelView FXML gewechselt. */
    @FXML
    void onOk(ActionEvent event) throws IOException {


/** String kann direkt übernommen werden, da die Methode getText() eines Textfeldes einen String zurück liefert */
        searchElement = txt_Textzeile.getText();
        ControllerTableView.arrayList.add(searchElement);


        searchRegion = txt_TextzeileRegion.getText();
  //      for (int i = 0; i < 10; i++) {


/*            if (searchRegion == null || searchRegion.trim().isEmpty()) {


      //          ControllerTableView.arrayList.add(null);


            } else {

            }*/
 //   }

        ControllerTableView.arrayList.add(searchRegion);

        Parent root = FXMLLoader.load(getClass().getResource("/TabelView.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();






    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("25", "50", "100");
        combo.setItems(list);
    }


    @FXML
    void combo_Ok(ActionEvent event) {
        searchNumber = combo.getValue();
        ControllerTableView.arrayList.add(searchNumber);

    }


}


