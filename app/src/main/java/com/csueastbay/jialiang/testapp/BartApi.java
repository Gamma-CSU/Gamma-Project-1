package com.csueastbay.jialiang.testapp;

import java.io.IOException;
import java.net.*;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jialiang on 10/13/2015.
 */
public class BartApi {
    // must add command and options
    private String baseURL = "http://api.bart.gov/api/sched.aspx?cmd=" ;
    // API key
    private String key = "MW9S-E7SL-26DU-VV8V";

    // Hashmap to store station code and name pairs
    Map stations = new HashMap<String, String>();

    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder ;
    private Document doc;

    // Default constructor adds stations to the list
    BartApi() throws ParserConfigurationException {

        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
        stations.put("12th", "12th St. Oakland City Center");
        stations.put("16th", "16th St. Mission(SF)");
        stations.put("19th", "12th St. 19th St. Oakland");
        stations.put("24th", "24th St. Mission(SF)");
        stations.put("ashb", "Ashby (Berkeley)");
        stations.put("balb", "Balboa Park(SF)");
        stations.put("bayf", "Bay Fair(San Leandro)");
        stations.put("cast", "Castro Valley");
        stations.put("civc", "Civic Center(SF)");
        stations.put("cols", "Coliseum");
        stations.put("colm", "Colma");
        stations.put("conc", "Concord");
        stations.put("daly", "Daly City");
        stations.put("dbrk", "Downtown Berkeley");
        stations.put("dubl", "Dublin/Pleasanton");
        stations.put("deln", "El Cerrito del Norte");
        stations.put("plza", "El Cerrito Plaza");
        stations.put("embr", "Embarcadero(SF)");
        stations.put("frmt", "Fremont");
        stations.put("ftvl", "Fruitvale(Oakland)");
        stations.put("glen", "Glen Park(SF)");
        stations.put("hayw", "Hayward");
        stations.put("lafy", "Lafayette");
        stations.put("lake", "Lake Merritt(Oakland)");
        stations.put("mcar", "MacArthur (Oakland)");
        stations.put("mlbr", "Millbrae");
        stations.put("mont", "Montgomery St. (SF)");
        stations.put("nbrk", "North Berkeley");
        stations.put("ncon", "North Concord/Martinez");
        stations.put("oakl", "Oakland Int'l Airport");
        stations.put("orin", "Orinda");
        stations.put("pitt", "Pittsburg/Bay Point");
        stations.put("phil", "Pleasant Hill");
        stations.put("powl", "Powell St. (SF)");
        stations.put("rich", "Richmond");
        stations.put("rock", "Rockridge(Oakland)");
        stations.put("sbrn", "San Bruno");
        stations.put("sfia", "San Francisco Int'l Airport");
        stations.put("sanl", "San Leandro");
        stations.put("shay", "South Hayward");
        stations.put("ssan", "South San Francisco");
        stations.put("ucty", "Union City");
        stations.put("wcrk", "Walnut Creek");
        stations.put("wdub", "West Dublin");
        stations.put("woak", "West Oakland");
    }// end Default BartApi()

    // Returns String for Destination Time
    public String printDestinationTime(String origin, String destination) throws IOException, ParserConfigurationException, SAXException {

        String contentString;
        doc = dBuilder.parse(new URL(baseURL + "depart&orig=" + origin + "&dest=" + destination + "&date=now&key=" + key + "&b=2&a=2&l=1").openStream());
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("trip");

        contentString = "Origin: " + stations.get(doc.getElementsByTagName("origin").item(0).getTextContent().toLowerCase()).toString() + "\n" +
                "Destination: " + stations.get(doc.getElementsByTagName("destination").item(0).getTextContent().toLowerCase()).toString() + "\n";

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

    // Prints Fare
    public String printFare(String origin, String destination) throws IOException, ParserConfigurationException, SAXException {

        String contentString;
        doc = dBuilder.parse(new URL(baseURL + "fare&orig=" + origin + "&dest=" + destination + "&date=now&key=" + key).openStream());
        doc.getDocumentElement().normalize();

        String fare = doc.getElementsByTagName("fare").item(0).getTextContent();
        contentString = "$" + fare;
        return contentString;
    }

    // Prints advisory if any
    public String printAdvisory() throws IOException, ParserConfigurationException, SAXException {

        String contentString = "";
        doc = dBuilder.parse(new URL(baseURL + "bsa&date=now&key=" + key).openStream());
        doc.getDocumentElement().normalize();

        if (doc.getElementsByTagName("type").getLength() == 0)
        {
            contentString = "Nothing to Report";
        }
        else
        {
            String bsa = doc.getElementsByTagName("type").item(0).getTextContent();
            contentString = bsa;
        }

        return contentString;
    }
}