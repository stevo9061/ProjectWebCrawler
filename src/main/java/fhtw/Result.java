package fhtw;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Result {

//advertSummaryList
private List<Todo> todos = null;


public List<Todo> getTodos() {
    return todos;
}

public void setTodos(List<Todo> searchResult) {
    this.todos = todos;
}

// TODO: Aus Main, überprüfen ob es noch notwendig ist für uns





/*             File json = new File("src/main/java/fhtw/Simple.json");

           FileWriter writer = new FileWriter(json);
            writer.write(outStrForJson.toString());
            writer.flush();
            writer.close();

            //willhaben json einlesen
            br = new BufferedReader(new FileReader(json));
            Result result = gson.fromJson(br, Result.class);

            // test json einlesen
*//*            br = new BufferedReader(new FileReader("data.json"));
            Result result = gson.fromJson(br, Result.class);*//*


            if (result != null) {


                System.out.println(result.getTodos());


                for (Todo t : result.getTodos()) {
                    System.out.println(t.getId());
                }
            }*/




           /* String newa = outStrForJson.toString();
            Result result = gson.fromJson(newa, Result.class);

            List<Todo> list1 = new ArrayList<Todo>();
            Todo neu = new Todo();
            neu.setDescription("lalelu");
            Todo neutwo = new Todo();
            neutwo.setDescription("Python");
            Todo three = new Todo();
            three.setDescription("Java");
            list1.add(neu);
            list1.add(neutwo);
            list1.add(three);

            result.setTodos(list1);

            System.out.println(result.getTodos().toString());


            System.out.println(neu.getDescription());
            System.out.println(result.getTodos());*/

   /*         if (result != null) {

                for (Todo t : result.getTodos()) {
                    System.out.println(t.getDescription() + " - " + t.getName() + " - " + t.getId());
                }
            }*/




    //TODO: Zeilen so unterteilen wie auf Willhaben
    // EBAY //
//            final Document documentEbay = Jsoup.connect(urlEbay).get();

/*           for (Element rowa : documentEbay.select("div.srp-main-content.clearfix.srp-main-content--flex")) {
               if (rowa.select("span.ITALIC").text().equals("")) {

               } else {
                   final String table =
                           rowa.select("span.ITALIC").text();
                   System.out.println(table);

               }

           }*/


    //                final String table = documentEbay.select("span.ITALIC").text();
    //       System.out.println(table); //TODO: Die Zeile brauchen wir











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







}
