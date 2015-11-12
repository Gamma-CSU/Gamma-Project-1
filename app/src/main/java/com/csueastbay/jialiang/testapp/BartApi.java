package com.csueastbay.jialiang.testapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
    String baseURL = "http://api.bart.gov/api/sched.aspx?cmd=" ;
    // API key
    String key = "key=MW9S-E7SL-26DU-VV8V&b=2&a=2&l=1" ;
    // Station abbreviations list
    List<String> stations = new ArrayList<>();

    // Default constructor adds stations to the list
    BartApi() {
        stations.add("DALY"); // Daly City
        stations.add("BALB"); // Balboa
        stations.add("GLEN"); // Glen Park
        stations.add("24TH"); // 24th st
        stations.add("16TH"); // 16th st
        stations.add("CIVC"); // Civic center
        stations.add("POWL"); // Powell
        stations.add("MONT"); // Montgomery
        stations.add("EMBR"); // Embarcadero
        stations.add("WOAK"); // West Oakland
        stations.add("LAKE"); // Lake Merritt
        stations.add("FTVL"); // Fruitvale
        stations.add("COLS"); // Coliseum
        stations.add("SANL"); // San Leandro
        stations.add("BAYF"); // Bayfaire
        stations.add("HAYW"); // Hayward
        stations.add("SHAY"); // South Hayward
        stations.add("UCTY"); // Union City
        stations.add("FRMT"); // Fremont
    }// end Default BartApi()


    private static InputStream doGet(String url) throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        InputStream inputStream = response.getEntity().getContent();

        return inputStream;
    }


    /*
    Var: String newBaseURL - add the command and flags to the baseURL inherited from BartApi baseURL
    Var: String routeinfo_command - routeinfo command string
    Var: String routeinfo_route
     */
    public class routeinfo {

        String newBaseURL; // will have the command and flags added
        String routeinfo_command = "routeinfo" ;
        String routeinfo_route = "route" ;

        routeinfo(){ newBaseURL = baseURL + routeinfo_command; }



        }
    }// end public class routeinfo
    public String returnString() throws ClientProtocolException, IOException, ParserConfigurationException, SAXException {

        String contentString;

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
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