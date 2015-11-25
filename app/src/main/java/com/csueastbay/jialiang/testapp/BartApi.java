package com.csueastbay.jialiang.testapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
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


    public String returnString(String dep, String arr) throws ClientProtocolException, IOException, ParserConfigurationException, SAXException {

        String contentString;
        String URL = "http://api.bart.gov/api/sched.aspx?cmd=depart&orig="+ dep + "&dest=" + arr + "&date=now&key=MW9S-E7SL-26DU-VV8V&b=2&a=2&l=1";


        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(new URL(URL).openStream());
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