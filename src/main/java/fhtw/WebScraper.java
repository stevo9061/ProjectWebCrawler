package fhtw;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;


public class WebScraper extends ControllerTableView {

/*    private String id;
    private String description;
    private String price;*/

    private String ID;
    private String tbl_element;
    private String tbl_webseite;
    private String tbl_preis;
    private String tbl_postcode;

    private String searchName;
    Document doc = null;

    public WebScraper(String tbl_hersteller, String tbl_objekt, String tbl_webseite, String tbl_preis, String tbl_postcode) {
        this.ID = tbl_hersteller;
        this.tbl_element = tbl_objekt;
        this.tbl_webseite = tbl_webseite;
        this.tbl_preis = tbl_preis;
        this.tbl_postcode = tbl_postcode;
    }

    WebScraper() {
        
    }

    WebScraper(String searchName) {
        this.searchName = searchName;
    }

    /**
     * Die getter benötigen wir damit sie in die Tableview geladen werden
     */
    public String getTbl_id() {
        return ID;
    }

    public String getTbl_element() {
        return tbl_element;
    }

    public String getTbl_webseite() {
        return tbl_webseite;
    }

    public String getTbl_preis() {
        return tbl_preis;
    }

    public String getTbl_postcode() {
        return tbl_postcode;
    }

  /*  public void setDescription(String description) {
        this.description = description;
    }

   */

    public void scrapeWH(String searchElement, String searchElementNum, String searchPostcode) {
        System.out.println("Starting Willhaben Scraper..");
        /** Wir entfernen hier alle Sonderzeichen mittels Regex, es werden in der Suchzeile nur Buchstaben und Zahlen von 0-9 zugelassen. */
        searchElement = searchElement.replaceAll("[^A-za-z0-9 ]", " ");

        /** Es werden nur Zahlen von 0-9 zugelassen */
        searchPostcode = searchPostcode.replaceAll("[^0-9 ]", " ");



        System.out.println("Searching for " + searchElement + ", " + searchElementNum + " Elements maximum");
        System.out.println();

        StringBuilder whUrl = new StringBuilder();

        whUrl.append("https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz?&isNavigation=true&keyword=");

        /**
         * Der User gibt zB Playstation 5 ein, den Whitespace müssen wir abfangen da dieser in der Url mit einem + versehen wird
         **/
        whUrl.append(searchElement.replace(" ", "+"));

        /**
         * Anzahl der auszugebenden Elemente kann hier eingestellt werden, 25, 50, 100 möglich
         **/
        whUrl.append("&rows=" + searchElementNum);


        try {

            /**
             * it Jsoup bekommen wir den html-Code
             **/
            doc = Jsoup.connect(whUrl.toString()).get();


        }catch (IOException e) {
            e.printStackTrace();
        }

           /**
            * Gib uns den String ab __NEXT_DATA__
            **/
           String out = doc.getElementById("__NEXT_DATA__").toString();

            /**
             * Um unser json weiter verarbeiten zu können müssen wir den String bereinigen,
             * d.h '<script id="__NEXT_DATA__" type="application/json">' und '</script>' müssen wir löschen.
             **/
            StringBuilder outStrForJson = new StringBuilder(out);
            outStrForJson.delete(0, 51); // Delete <script id="__NEXT_DATA__" type="application/json">
            int lastElement = outStrForJson.lastIndexOf("</script>"); // zB: 275505
            outStrForJson.delete(lastElement, lastElement + 9); // Delete </script>
            //    System.out.println(outStrForJson); // Test OK

            // props -> pageProps -> searchResult -> advertSummaryList -> advertSummary (Array)
            // props -> pageProps -> searchResult -> advertSummaryList -> advertSummary (Array) -> 0 -> attributes -> attribute -> 12
            JsonObject myobject = JsonParser.parseString(outStrForJson.toString()).getAsJsonObject();
            JsonObject props = (JsonObject) myobject.get("props");
            JsonObject pageProps = (JsonObject) props.get("pageProps");
            JsonObject searchResult = (JsonObject) pageProps.get("searchResult");
            JsonObject advertSummaryList = (JsonObject) searchResult.get("advertSummaryList");
            JsonArray advertSummary = (JsonArray) advertSummaryList.get("advertSummary");

            JsonArray getArray = advertSummaryList.getAsJsonArray("advertSummary");


            for (JsonElement e : getArray) {
                //Ged Id and description
                JsonObject advertSummaryArray0 = e.getAsJsonObject();
                String id = advertSummaryArray0.get("id").getAsString();
                String description = advertSummaryArray0.get("description").getAsString();


                // Get Price
                JsonObject attributes = (JsonObject) advertSummaryArray0.get("attributes");
                JsonArray attribute = (JsonArray) attributes.get("attribute");
                JsonObject priceJson = attribute.get(12).getAsJsonObject();
//               System.out.println(id + " + " + description + " + " + price );

                // Get postcode
                JsonObject postcodeJson = attribute.get(5).getAsJsonObject();

                /**
                 * Convert postcode to String Class
                 */
                String postcode = postcodeJson.toString(); // "{"name":"POSTCODE","values":["3251"]}"
                StringBuilder outStrPostcode = new StringBuilder(postcode);
                outStrPostcode.delete(0,30);
                int lastElementPostcode = outStrPostcode.lastIndexOf("\"");
                outStrPostcode.delete(lastElementPostcode, lastElementPostcode + 3);

                /**
                 * Check if the postcode from the element matches the user's searched postcode
                 */
                postcode = outStrPostcode.toString();

                /**
                 * We allow that either the postcode line could be empty, the first digit is equal or the whole postcode
                 */
                boolean rest = true;
                //Wenn in der Suchzeile die Postleitzahl leer ist
                if(searchPostcode.isEmpty()) {
                     rest = true;
                    //TODO: "Eingabefeld in Plz ist leer..." kommt nach jeder Abfrage
                    System.out.println("Eingabefeld in Plz ist leer, Programm wird daher nicht nach einer Postleitzahl filtern.");
                     //Wenn die Plz von der Webseite größer als 5 ist, soll "Keine Postleitzahl enthalten" geschrieben werden.
  //                   if(searchPostcode.length() > 5 || (postcode.length() > 5 )) {
  //                      postcode = "Keine Postleitzahl vorhanden";
  //                  }
                // Wenn in der Suchzeile die Plz nicht leer ist und diese größer als 5 ist, soll das Programm beendet werden.
                } else if(searchPostcode.length() > 5 ) {
                    System.err.println("The program can process a postal code with a maximum of 5 digits");
                    System.exit(0);

                // Wenn die Plz von der Webseite größer als 5 ist, soll "Keine Postleitzahl enthalten" geschrieben werden.
                } else if (postcode.length() > 5 ) {
                    postcode = "Keine Postleitzahl enthalten";

                /**
                 * Wenn die erste Ziffer von der Postleitzahl (Webseite) mit der ersten Ziffer von der Plz in der Suchzeile
                 * übereinstimmt ODER die beiden Postleitzahlen gänzlich gleich sind haben wir eine Übereinstimmmung
                 */
                } else if (postcode.charAt(0) == searchPostcode.charAt(0) || postcode.equals(searchPostcode) ){
                    rest = true;
                /**
                 * Wenn keine der beiden Zustände zutrifft, fällt es nicht in unser Suchraster
                 */
                } else {
                    rest = false;
                    System.out.println("Postleitzahl trifft nicht in unser Suchraster.");
                }
                //CHECK: Mit Plz funktioniert der Code, wenn die Plz größer als 5 ist wird angezeigt "Keine Postleitzahl enthalten".
                //CHECK: Wenn die Suchzeile bei Plz nicht leer ist und größer als 5 wird das Programm beendet.

                if(rest) {



                   /**
                    * Convert price to String Class
                    */
                  // {"name":"ADTYPE_ID","values":["67"]}
                  // {"name":"PRICE/AMOUNT","values":["75.0"]}
                  String price = priceJson.toString();
                  StringBuilder outStrForPrice = new StringBuilder(price);
                  if(price.contains("{\"name\":\"PRICE/AMOUNT\",\"values\"")) {
                      outStrForPrice.delete(0, 34);
                  }
                  if(price.contains("{\"name\":\"ADTYPE_ID\",\"values\":[\"67\"]}")) {
                      outStrForPrice.delete(0, 31);
                  }
                  int lastElementPrice = outStrForPrice.lastIndexOf("\"");
                  outStrForPrice.delete(lastElementPrice, lastElementPrice + 3);

                    /**
                     * Check if our String has Digits, if not we don't print the output because the searched article has no price.
                     */
                    price = outStrForPrice.toString();

                    /**
                     * "^[-.0-9 ]+$" is a regular expression (regex) and means just allow Strings with digits 0-9 and comma (.)
                     */
                    boolean result = price.matches("^[-.0-9 ]+$");
                    if(result) {
                      System.out.println(price);
                      WebScraper temp = new WebScraper();
                      temp.tbl_element = description;
                      temp.ID = id;
                      temp.tbl_webseite = "www.willhaben.at";
                      temp.tbl_preis = price;
                      temp.tbl_postcode = postcode;
                      observableList.add(temp);
                    }
                } //TODO: Wenn keine Elemente gefunden wurden soll das Programm beendet werden. In die Kommandozeile schreiben wir keine Einträge gefunden
            }


            System.out.println();


    }

    /**
     * Wird derzeit in unserem Programm nicht verwendet da nur der Preis ausgelesen werden kann
     */
    public void scrapeEB(String keyword, int elements) throws IOException {

        System.out.println("Starting Ebay scraper..");
        System.out.println("Searching for " + keyword);
        System.out.println();

        StringBuilder ebUrl = new StringBuilder();

        ebUrl.append("https://www.ebay.at/sch/i.html?_from=R40&_nkw=" + keyword + "&_sacat=0&_ipg=" + elements);

        /**
         * Der User gibt zB Playstation 5 ein, den Whitespace müssen wir abfangen da dieser in der Url mit einem + versehen wird
         **/
        ebUrl.append(keyword.replace(" ", "+"));

        /**
         * Anzahl der auszugebenden Elemente kann hier eingestellt werden, 25, 50, 100, 200 möglich
         */
        ebUrl.append(elements); //TODO: Filter wäre cool

        Document documentEbay = Jsoup.connect(ebUrl.toString()).get();
//        final String table = documentEbay.select("span.ITALIC").text();
//         System.out.println(table);

        //TODO: Funktioniert nur indem ich alle Preise von der Seite in einer Zeile ausgebe, anders derzeit nicht umsetzbar
        Elements rowa = documentEbay.select(".srp-results.srp-list.clearfix");
        String priceEur = rowa.select("span.s-item__price").text();
        String title = rowa.select(".s-item__title").text();

/*        System.out.println(priceEur);
        System.out.println(title);*/

//        System.out.println(title); //TODO: Titel vielleicht noch abfragen, aber wie mit Javascript?


        /**
         * Es wird das EUR von jeden Preis entfernt
         */
        String[] priceArr = priceEur.split("EUR", 100);

        /**
         * Wir lesen unsere Preise in ein String Array ein
         */
        for (String p : priceArr) {
            System.out.println(p);
        }

    }


}
