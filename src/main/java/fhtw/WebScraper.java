package fhtw;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * In this class, the data is extracted from Willhaben and further processed using various libraries.
 */

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
     * The getter we need so that they are loaded into the tableview
     */


    /**
     * @return is the ID of our Willhaben element
     */
    public String getTbl_id() {
        return ID;
    }

    /**
     * @return is the item itself that was found, the item description from the seller is here
     */

    public String getTbl_element() {
        return tbl_element;
    }

    /**
     * @return the website (https://www.willhaben.at)
     */

    public String getTbl_webseite() {
        return tbl_webseite;
    }

    /**
     * @return is the price of the element
     */

    public String getTbl_preis() {
        return tbl_preis;
    }

    /**
     * @return is the postal code of the element
     */

    public String getTbl_postcode() {
        return tbl_postcode;
    }

    /**
     * In this method we get the *.html code from Willhaben and process it further. Here we want to extract from the
     * json data store the information we want to process.
     *
     * @param searchElement    The element the user wants to search
     * @param searchElementNum The number of elements to search for (choose from 25, 50, 100)
     * @param searchPostcode   In which postal code to search
     */

    public void scrapeWH(String searchElement, String searchElementNum, String searchPostcode) {
        System.out.println("Starting Willhaben Scraper..");
        /** We remove here all special characters by means of regex, only letters and numbers from 0-9 are allowed in the search line. */
        searchElement = searchElement.replaceAll("[^A-za-z0-9 ]", " ");

        /** Only numbers from 0-9 are allowed */
        searchPostcode = searchPostcode.replaceAll("[^0-9 ]", " ");


        System.out.println("Searching for " + searchElement + ", " + searchElementNum + " Elements maximum");
        System.out.println();

        StringBuilder whUrl = new StringBuilder();

        whUrl.append("https://www.willhaben.at/iad/kaufen-und-verkaufen/marktplatz?&isNavigation=true&keyword=");

        /**
         * The user enters e.g. Playstation 5, we have to catch the whitespace because it will be marked with a + in the url.
         **/
        whUrl.append(searchElement.replace(" ", "+"));

        /**
         * Number of elements to be output can be set here, 25, 50, 100 possible
         **/
        whUrl.append("&rows=" + searchElementNum);


        try {

            /**
             * With Jsoup we get the html code
             **/
            doc = Jsoup.connect(whUrl.toString()).get();


        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Give us the string __NEXT_DATA__
         **/
        String out = doc.getElementById("__NEXT_DATA__").toString();

        /**
         * To be able to process our json further we have to clean up the string,
         * that means '<script id="__NEXT_DATA__" type="application/json">' and '</script>' we have to delete.
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
            outStrPostcode.delete(0, 30);
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
            if (searchPostcode.isEmpty()) {
                rest = true;

                // If in the search line the zip code is not empty and it is greater than 5, the program should be terminated.
            } else if (searchPostcode.length() > 5) {
                System.err.println("The program can process a postal code with a maximum of 5 digits");
                System.exit(0);

                // If the postal code from the web page is greater than 5, "No postal code included" should be written.
            } else if (postcode.length() > 5) {
                postcode = "Keine Postleitzahl enthalten";

                /**
                 * If the first digit of the postal code (web page) matches the first digit of the postal code
                 * in the search line OR the two zip codes are completely the same we have a match.
                 */
            } else if (postcode.charAt(0) == searchPostcode.charAt(0) || postcode.equals(searchPostcode)) {
                rest = true;
                /**
                 * If neither condition applies, it does not fall into our search grid.
                 */
            } else {
                rest = false;
                System.out.println("Postleitzahl trifft nicht in unser Suchraster.");
            }


            if (rest) {


                /**
                 * Convert price to String Class
                 */
                // {"name":"ADTYPE_ID","values":["67"]}
                // {"name":"PRICE/AMOUNT","values":["75.0"]}
                String price = priceJson.toString();
                StringBuilder outStrForPrice = new StringBuilder(price);
                if (price.contains("{\"name\":\"PRICE/AMOUNT\",\"values\"")) {
                    outStrForPrice.delete(0, 34);
                }
                if (price.contains("{\"name\":\"ADTYPE_ID\",\"values\":[\"67\"]}")) {
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
                if (result) {
                    System.out.println(price);
                    WebScraper temp = new WebScraper();
                    temp.tbl_element = description;
                    temp.ID = id;
                    temp.tbl_webseite = "www.willhaben.at";
                    temp.tbl_preis = price;
                    temp.tbl_postcode = postcode;
                    observableList.add(temp);
                }
            }
        }


        System.out.println();


    }

    /**
     * Currently not used in our program because only the price can be read, for this, in our opinion, more in-depth
     * knowledge of Javascript is necessary.
     *
     * @param keyword  is the element to be searched
     * @param elements is the number of elements to search for
     * @throws IOException throws an IO exception
     */
    public void scrapeEB(String keyword, int elements) throws IOException {

        System.out.println("Starting Ebay scraper..");
        System.out.println("Searching for " + keyword);
        System.out.println();

        StringBuilder ebUrl = new StringBuilder();

        ebUrl.append("https://www.ebay.at/sch/i.html?_from=R40&_nkw=" + keyword + "&_sacat=0&_ipg=" + elements);

        /**
         * The user enters e.g. Playstation 5, we have to catch the whitespace because it will be marked with a + in the url.
         **/
        ebUrl.append(keyword.replace(" ", "+"));

        /**
         * Number of elements to be output can be set here, 25, 50, 100, 200 possible
         */
        ebUrl.append(elements);

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
         * It is removed the EUR from any price
         */
        String[] priceArr = priceEur.split("EUR", 100);

        /**
         * We read our prices into a string array
         */
        for (String p : priceArr) {
            System.out.println(p);
        }

    }


}
