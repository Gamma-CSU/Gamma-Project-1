package com.csueastbay.jialiang.testapp;

import android.provider.Settings;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Created by Jialiang on 10/13/2015.
 */
public class BartApi {
    // must add command and options
    private String baseURL = "http://api.bart.gov/api/sched.aspx?cmd=" ;
    // API key
    private String key = "&key=MW9S-E7SL-26DU-VV8V&b=2&a=2&l=1" ;
    // Station abbreviations list
    private List<String> stations = new ArrayList<>();
    // Document Builder components
    private DocumentBuilderFactory dbFactory ;
    private DocumentBuilder dBuilder;
    private Document doc;

    // Default constructor adds stations to the list
    BartApi() throws ParserConfigurationException {

        try {
            // Initialize Document Builder pieces
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();

        }
        catch( ParserConfigurationException e){
            e.printStackTrace();
        }

        // Build the list of stations
        stations.add("12th"); //12th St. Oakland City Center
        stations.add("16th"); //16th St. Mission (SF)
        stations.add("19th"); //19th St. Oakland
        stations.add("24th"); //24th St. Mission (SF)
        stations.add("ashb"); //Ashby (Berkeley)
        stations.add("balb"); //Balboa Park (SF)
        stations.add("bayf"); //Bay Fair (San Leandro)
        stations.add("cast"); //Castro Valley
        stations.add("civc"); //Civic Center (SF)
        stations.add("cols"); //Coliseum
        stations.add("colm"); //Colma
        stations.add("conc"); //Concord
        stations.add("daly"); //Daly City
        stations.add("dbrk"); //Downtown Berkeley
        stations.add("dubl"); //Dublin/Pleasanton
        stations.add("deln"); //El Cerrito del Norte
        stations.add("plza"); //El Cerrito Plaza
        stations.add("embr"); //Embarcadero (SF)
        stations.add("frmt"); //Fremont
        stations.add("ftvl"); //Fruitvale (Oakland)
        stations.add("glen"); //Glen Park (SF)
        stations.add("hayw"); //Hayward
        stations.add("lafy"); //Lafayette
        stations.add("lake"); //Lake Merritt (Oakland)
        stations.add("mcar"); //MacArthur (Oakland)
        stations.add("mlbr"); //Millbrae
        stations.add("mont"); //Montgomery St. (SF)
        stations.add("nbrk"); //North Berkeley
        stations.add("ncon"); //North Concord/Martinez
        stations.add("oakl"); //Oakland Int'l Airport
        stations.add("orin"); //Orinda
        stations.add("pitt"); //Pittsburg/Bay Point
        stations.add("phil"); //Pleasant Hill
        stations.add("powl"); //Powell St. (SF)
        stations.add("rich"); //Richmond
        stations.add("rock"); //Rockridge (Oakland)
        stations.add("sbrn"); //San Bruno
        stations.add("sfia"); //San Francisco Int'l Airport
        stations.add("sanl"); //San Leandro
        stations.add("shay"); //South Hayward
        stations.add("ssan"); //South San Francisco
        stations.add("ucty"); //Union City
        stations.add("wcrk"); //Walnut Creek
        stations.add("wdub"); //West Dublin
        stations.add("woak"); //West Oakland

    }// end Default BartApi()


    /**
     * @function count
     * @return string of count
     * @throws SAXException
     * @throws IOException
     */
    public String count() throws SAXException, IOException{
        String command = "count";

        try {
            doc = dBuilder.parse(baseURL + command + key);
        }
        catch ( SAXException e){
            e.printStackTrace();
        }
        catch ( IOException e){
            e.printStackTrace();
        }

        return ("Date: " + doc.getElementsByTagName("date").item(0) +
                "\nTime: " + doc.getElementsByTagName("time").item(0) +
                "\nTrain count: " + doc.getElementsByTagName("traincount").item(0) );
    }// end count

    /**
     * @function routeName
     * @ purpose get route name from for Route Info->Routes from the <route> under <routes>
     * @param doc - which is a <route>
     * @return string - of route name
     */
    private String routeName( Document doc){
        return doc.getElementsByTagName("name").item(0).toString();
    }// End routeName


    /**
     * @function routeNumber
     * @ purpose get route number from for Route Info->Routes from the <route> under <routes>
     * @param doc - which is a <route>
     * @return int - of route number
     */
    private int routeNumber( Document doc){
        return Integer.parseInt(doc.getElementsByTagName("number").item(0).toString());
    }// end routeNumber


    /**
     * @function routeAbbrev
     * @ purpose get route abbreviation from for Route Info->Routes from the <route> under <routes>
     * @param doc - which is a <route>
     * @return string - of route abbreviation
     */
    private String routeAbbrev( Document doc){
        return doc.getElementsByTagName("abbr").item(0).toString();
    }// end routeAbbrev


    /**
     * @function route_print
     * @purpose the return a string with the formatted route components
     * @param doc
     * @return
     */
    private String route_print(Document doc){
        return (routeName(doc) + "\n" +
                routeNumber(doc) + "\n" +
                routeAbbrev(doc) + "\n" );
    }// end route_print

    public String route() throws SAXException, IOException {
        String command = "routes";
        String contentString = "";

        try {
            doc = dBuilder.parse(baseURL + command + key);
        }
        catch ( SAXException e){
            e.printStackTrace();
        }
        catch ( IOException e){
            e.printStackTrace();
        }

        NodeList routes = doc.getElementsByTagName("route");
/* Need to print a list of the routes under <routes>
       for (int i = 0; i < routes.getLength(); i++){
           route_print( routes.item(i) );
       }
*/

        return contentString;
    }// end route


    /**
     * @function routeinfo
     * @param routeNumber
     * @return
     * @throws SAXException
     * @throws IOException
     */
    public String routeinfo(String routeNumber) throws SAXException, IOException {

        String contentString = ""; // will have the command and flags added
        String command = "routeinfo" ;
        String routeinfo_route = "route" ;

        try {
            doc = dBuilder.parse(baseURL + command + "&" + routeNumber + key);
        }
        catch ( SAXException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("routes");

        contentString = "Origin: " + doc.getElementsByTagName("origin") + "\t" +
                "Destination: " + doc.getElementsByTagName("destination") + "\n" ;

        contentString = contentString + "Intermediate stations: \n";
        contentString = contentString + doc.getElementsByTagName("station").item(0);

        // Iterate throught eh list of stations, default value is i
        for( int i = 1; i < Integer.getInteger(doc.getElementsByTagName("num_stns").toString(), i) ; i++ ){
            contentString = ", " + contentString + doc.getElementsByTagName("station").item(i);
        }// end for


        return contentString;
    }// end public class routeinfo




    public String returnString() throws IOException, ParserConfigurationException, SAXException {

        String contentString;


        Document doc = dBuilder.parse(new URL("http://api.bart.gov/api/sched.aspx?cmd=depart&orig=ASHB&dest=CIVC&date=now&key=MW9S-E7SL-26DU-VV8V&b=2&a=2&l=1").openStream());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("trip");

        contentString = "Origin: " + doc.getElementsByTagName("origin").item(0).getTextContent() + "\n" +
                "Destination: " + doc.getElementsByTagName("destination").item(0).getTextContent() + "\n";

        for (int i = 0; i < nList.getLength(); i++)
        {
            Node nNode = nList.item(i);
            Element eElement = (Element) nNode;
            String origTimeMin = eElement.getAttribute("origTimeMin");
            String destTimeMin = eElement.getAttribute("destTimeMin");

            contentString = contentString + "Time to Origin: " + origTimeMin + "\n" + "Time to Destination: " + destTimeMin + "\n\n";
        }
        return contentString;
    }
}
