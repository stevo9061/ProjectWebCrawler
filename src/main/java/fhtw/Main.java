package fhtw;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;



public class Main extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/webCrawler.fxml"));
        Scene scene1 = new Scene(root);
        stage.setScene(scene1);
        stage.show();
    }
    public static void main(String[] args) throws IOException {

        final String url = "https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz/konsolen/playstation-2800?sfId=e382f4e2-8bc3-4cc4-b62a-5721a629b306&rows=25&isNavigation=true&keyword=playstation+5";



        try {
//TODO: Anderen Zeichensatz verwenden, nicht UTF-8 wegen Umlaute und Sonderzeichen (€)

            final Document document = Jsoup.connect(url).get();

/*            for (Element row : document.select(

                "div.Box-sc-wfmb7k-0.BapResultListContainer___StyledBox-sc-11k8qlr-0.wtbxX.jHgQFl")) {

               System.out.println(row.text());
               System.out.println(document.select("div.Box-sc-wfmb7k-0.BapResultListContainer___StyledBox-sc-11k8qlr-0.wtbxX.jHgQFl"));


            }*/


            Elements row = document.select("div.Box-sc-wfmb7k-0.uwLFq");
//            System.out.println(row.text());

            for(int i = 0; i < 20; i++) {
                if(row.select("div.eBImnY.Box-sc-wfmb7k-0:nth-of-type(" + i + ")").text().equals("")) {

                } else {
                    final String tableZero =
                            row.select("div.eBImnY.Box-sc-wfmb7k-0:nth-of-type(" + i + ")").text();
                    System.out.println(tableZero);
                }

            }




        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        launch(args);
    }
}

















//        try {
////
//            // name":"PRICE/AMOUNT","values":["670.0" sind zb die 670 euro wenn ich die habe soll mir das dokument in ein excel ausgegeben werden
//
//            String address = "https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz?sfId=51834ca7-ebcf-4d9d-92bc-9347b297915b&isNavigation=true&keyword=Playstation%205";
//            URL url = new URL(address);
//            System.out.println("protocol = " + url.getProtocol());
//            System.out.println("host = " + url.getHost());
//
//
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setDoOutput(true);
//            con.setRequestMethod("GET");
//            con.connect();
//            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
//            out.write("GET /index.html HTTP/1.1");
//            InputStreamReader reader = new InputStreamReader(url.openStream());
//
//
//
//            // Hier stecken dann die Daten die wir haben wollen (der output)
//
//            BufferedReader buffer = new BufferedReader(reader);
//            System.out.println("Lese Daten von " + address);
//            String value = null;
//            StringBuilder price = new StringBuilder();
//            int count = 0;
//            String compareValue = "head";
//            ArrayList<String> lines = new ArrayList<>();
//
//
//            while ((value = buffer.readLine()) != null) {
//
//        // Ich füge dem ArrayList eine weitere Zeile hinzu
////                str.replaceAll("[^ABCD]", ""
//
////            System.out.println(value.replaceAll("[^head]", ""));
//            lines.add(value);
//
////            int indexFound = value.indexOf("head");
////              int indexFound = value.indexOf("AMOUNT");
////            if (indexFound > -1) {
////                count++;
////            }
//            for(String s : lines) {
//                int indexFound = value.indexOf("AMOUNT");
//                if (indexFound > -1) {
//                    count++;
//            }
//
//            }
//
//
//
//            System.out.println(value);
//            }
//
//            System.out.println("Number of apperances of the word values are " + count);
//
//        } catch (ProtocolException protocolException) {
//            protocolException.printStackTrace();
//        } catch (MalformedURLException malformedURLException) {
//            malformedURLException.printStackTrace();
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//        } catch (Exception e) {
//            // Generelles Exception-Handling zu Demo-Zwecken
//            e.printStackTrace();
//        }




