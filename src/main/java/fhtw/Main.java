package fhtw;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/webCrawler.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }


    public static void main(String[] args) throws Exception {



        launch(args);

        // TODO: Ein weiterer Thread für den Export


/*        WebScraper crawl = new WebScraper();

        *//** We creat one optional Thread for scrape Willhaben **//*
        Thread wh = new Thread();
       *//** Starting first Thread **//*
        wh.start();
        crawl.scrapeWH("Playstation 5", 25); */



  /*             *//** We create another thread for scrape Ebay **//*
        Thread eb = new Thread();
        *//** Starting second Thread **//*
        eb.start();
        crawl.scrapeEB("Playstation 5", 25);*/


//TODO: Anderen Zeichensatz verwenden, nicht UTF-8 wegen Umlaute und Sonderzeichen (€)

    }
}
