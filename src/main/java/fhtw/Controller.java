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

/**
 * Controller class - for interacting with the first GUI window
 */


public class Controller implements Initializable {


    private Stage stage = null;
    private Scene scene = null;
    final private Parent root = null;

    private String searchElement = null;
    private String searchNumber = null;
    private String searchRegion = null;


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


    /**
     * @param event When the btn_Ok button is pressed, it triggers this event and it switches to the TabelView FXML.
     * @throws IOException throws an IO exception
     */
    @FXML
    void onOk(ActionEvent event) throws IOException {


        // String can be taken directly, because the method getText() of a text field returns a string
        searchElement = txt_Textzeile.getText();

        // I pass the search term of my array list, which the ControllerTableView can then also access
        ControllerTableView.arraylistGlobal.add(searchElement);

        searchRegion = txt_TextzeileRegion.getText();
        ControllerTableView.arraylistGlobal.add(searchRegion);

        // Here we switch to the next GUI window, to the TableView.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/TableView.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    /**
     * The controller can define an initialize() method, which will be called once on an implementing controller
     * when the contents of its associated document have been completely loaded. This allows the implementing class
     * to perform any necessary post-processing on the content.
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> list = FXCollections.observableArrayList("25", "50", "100");
        combo.setItems(list);
    }

    /**
     * @param event If one of the three elements 25, 50 or 100 is selected in the combo box, this selection can be
     *              further processed and written into an array list.
     */
    @FXML
    void combo_Ok(ActionEvent event) {
        if (combo.getValue().contains("25") || combo.getValue().contains("50") || combo.getValue().contains("100")) {
            searchNumber = combo.getValue();
            ControllerTableView.arraylistGlobal.add(searchNumber);
        } else
            System.exit(1);
    }


}


