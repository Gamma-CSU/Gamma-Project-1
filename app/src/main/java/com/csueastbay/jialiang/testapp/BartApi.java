package com.csueastbay.jialiang.testapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.BufferedReader;
import org.xmlpull.v1.XmlPullParserException;
import java.io.InputStreamReader;

/**
 * Created by Jialiang on 10/13/2015.
 */
public class BartApi {

    private static InputStream doGet(String url) throws ClientProtocolException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = httpClient.execute(request);
        InputStream inputStream = response.getEntity().getContent();

        return inputStream;
    }

    public String returnString() throws ClientProtocolException, IOException
    {
        InputStream inputStream = doGet("http://api.bart.gov/api/bsa.aspx?cmd=count&key=MW9S-E7SL-26DU-VV8V");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder out = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        return out.toString();
    }
}
