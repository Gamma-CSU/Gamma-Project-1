package com.csueastbay.jialiang.testapp;

import java.io.IOException;
import java.net.*;
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

    public String returnString() throws IOException, ParserConfigurationException, SAXException {

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