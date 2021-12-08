package fhtw;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;



public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/webCrawler.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {

        final String urlWillhaben = "https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz/konsolen/playstation-2800?sfId=e382f4e2-8bc3-4cc4-b62a-5721a629b306&rows=25&isNavigation=true&keyword=playstation+5";

        final String urlEbay = "https://www.ebay.at/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313&_nkw=Playstation+5&_sacat=0";

        try {
//TODO: Anderen Zeichensatz verwenden, nicht UTF-8 wegen Umlaute und Sonderzeichen (€)


            // Willhaben //
            final Document documentWillhaben = Jsoup.connect(urlWillhaben).get();

            // Mit Jsoup bekommen wir den html-Code
            Document doc = Jsoup.connect(urlWillhaben).get();
            String out = doc.getElementById("__NEXT_DATA__").toString();

            /* Das müssen wir löschen von unseren String out: <script id="__NEXT_DATA__" type="application/json">
               bis </script> */

            StringBuilder outStrForJson = new StringBuilder(out);
            outStrForJson.delete(0, 51); // Delete <script id="__NEXT_DATA__" type="application/json">

            int lastElement = outStrForJson.lastIndexOf("</script>"); // 275505
            outStrForJson.delete(lastElement, lastElement + 9); // Delete </script>
            System.out.println(outStrForJson); // Test OK




            String[] strings = out.split("HEADING\",\"values\":");


     /*       for (String s : strings) {
                System.out.println(s);
            }*/

  //          System.out.println(strings[0]);



/*

            System.out.println(out);
            System.out.println(); */


//            System.out.println(documentWillhaben.outerHtml());
            ArrayList<String> willhabenList = new ArrayList<String>();



  //          for (Element row : documentWillhaben.select("div.Box-sc-wfmb7k-0.uwLFq")) {
/*            for (Element row : documentWillhaben.select(".eLrTTy")) {

                // Komplette Beschreibung mit Preis
                String desc = row.select(".eLrTTy").text();

                // Nur Preis
                String price = row.select(".gZusnz").text();
                // Parse price
                if(price.codePointAt(0) == 8364) {
                    price = (price.replace("€", ""));
                }

                willhabenList.add(price);
                System.out.println(price);

            }*/





            //TODO: Zeilen so unterteilen wie auf Willhaben
            // EBAY //

            final Document documentEbay = Jsoup.connect(urlEbay).get();





/*           for (Element rowa : documentEbay.select("div.srp-main-content.clearfix.srp-main-content--flex")) {
               if (rowa.select("span.ITALIC").text().equals("")) {

               } else {
                   final String table =
                           rowa.select("span.ITALIC").text();
                   System.out.println(table);

               }

           }*/






                    final String table = documentEbay.select("span.ITALIC").text();
                    System.out.println(table);
/*                    ArrayList<String> subString = new ArrayList<String>();


                    int index = table.indexOf("+",5);
//                    String subString;

                    if (index != -1) {
                        subString.add(table.substring(0,index));
                        System.out.println(subString);
                    }



            index = table.indexOf("+",15);

            if (index != -1) {
                subString.add(table.substring(0,index));
                System.out.println(subString);
            }*/




        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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




