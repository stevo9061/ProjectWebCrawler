package fhtw;


import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Stefan Bittgen, Manuel Brüger
 * @version 1.0.
 */





public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {



        Parent root = FXMLLoader.load(getClass().getResource("/Webscraper.fxml"));
        Scene scene1 = new Scene(root);
        File cssStyling = new File("styles/application.css");
//        System.out.println(cssStyling.getAbsolutePath());
        scene1.getStylesheets().add(getClass().getResource("/styles/application.css").toExternalForm());

        Image image = new Image("/icons/AppIcon.png");
        stage.getIcons().add(image);

        stage.setTitle("Web Scraper");
        stage.setScene(scene1);
        stage.show();
    }



    public static void main(String[] args) throws Exception {



        launch(args);



    }
}
