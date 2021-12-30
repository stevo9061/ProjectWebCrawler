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

    private String id;
    private String description;
    private String price;

    private String tbl_hersteller;
    private String tbl_objekt;
    private String tbl_webseite;
    private String tbl_preis;

    private String searchName;

    WebScraper() {
        
    }

    WebScraper(String searchName) {
        this.searchName = searchName;
    }


    public String getTbl_hersteller() {
        return tbl_hersteller;
    }


    public String getTbl_objekt() {
        return tbl_objekt;
    }

    public String getTbl_webseite() {
        return tbl_webseite;
    }

    public String getTbl_preis() {
        return tbl_preis;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void scrapeWH(String keyword, int elements) {
        System.out.println("Starting Willhaben scraper..");
        System.out.println("Searching for " + keyword);
        System.out.println();

        StringBuilder whUrl = new StringBuilder();

        whUrl.append("https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz?&isNavigation=true&keyword=");

        /** Der User gibt zB Playstation 5 ein, den Whitespace müssen wir abfangen da dieser in der Url mit einem + versehen wird **/
        whUrl.append(keyword.replace(" ", "+"));

        /** Anzahl der auszugebenden Elemente kann hier eingestellt werden, 25, 50, 100 möglich **/
        whUrl.append("&rows=" + elements);

        Document doc = null;

        try {

            /** Mit Jsoup bekommen wir den html-Code **/
           doc = Jsoup.connect(whUrl.toString()).get();


        }catch (IOException e) {
            e.printStackTrace();
        }

           /** Gib uns den String ab __NEXT_DATA__ **/
           String out = doc.getElementById("__NEXT_DATA__").toString();

            /** Um unser json weiter verarbeiten zu können müssen wir den String bereinigen,
             * d.h '<script id="__NEXT_DATA__" type="application/json">' und '</script>' müssen wir löschen. **/
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

//            ObservableList<String> table = FXCollections.observableArrayList();

            for (JsonElement e : getArray) {
                JsonObject advertSummaryArray0 = e.getAsJsonObject();
                String id = advertSummaryArray0.get("id").getAsString();
                String description = advertSummaryArray0.get("description").getAsString();


                JsonObject attributes = (JsonObject) advertSummaryArray0.get("attributes");
                JsonArray attribute = (JsonArray) attributes.get("attribute");
                JsonObject priceJson = attribute.get(12).getAsJsonObject();
//               System.out.println(id + " + " + description + " + " + price );

                /** Convert to String Class */
                String price = priceJson.toString();
                StringBuilder outStrForPrice = new StringBuilder(price);
                outStrForPrice.delete(0, 34);
                int lastElementPrice = outStrForPrice.lastIndexOf("\"");
                outStrForPrice.delete(lastElementPrice, lastElementPrice + 3);

                /**  Check if our String has Digits, if not we don't print the output because the searched article has no price. */
                price = outStrForPrice.toString();
                /**  "^[-.0-9 ]+$" is a regular expression (regex) and means just allow Strings with digits 0-9 and comma (.) */
              boolean result = price.matches("^[-.0-9 ]+$");
                if(result) {
                   System.out.println(price);
                   WebScraper temp = new WebScraper();
                   temp.tbl_objekt = description;
                   temp.tbl_hersteller = id;
                   temp.tbl_webseite = "www.willhaben.at";
                   temp.tbl_preis = price;
                   list.add(temp);

                }

            }


            System.out.println();


    }

    public void scrapeEB(String keyword, int elements) throws IOException {

        System.out.println("Starting Ebay scraper..");
        System.out.println("Searching for " + keyword);
        System.out.println();

        StringBuilder ebUrl = new StringBuilder();

        ebUrl.append("https://www.ebay.at/sch/i.html?_from=R40&_nkw=" + keyword + "&_sacat=0&_ipg=" + elements);

        /** Der User gibt zB Playstation 5 ein, den Whitespace müssen wir abfangen da dieser in der Url mit einem + versehen wird **/
        ebUrl.append(keyword.replace(" ", "+"));

        /** Anzahl der auszugebenden Elemente kann hier eingestellt werden, 25, 50, 100, 200 möglich **/
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


        /** Es wird das EUR von jeden Preis entfernt **/
        String[] priceArr = priceEur.split("EUR", 100);

        /** Wir lesen unsere Preise in ein String Array ein **/
        for (String p : priceArr) {
            System.out.println(p);
        }

    }


}
