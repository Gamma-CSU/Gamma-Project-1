package com.csueastbay.jialiang.testapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /// Sample usesage of the three return string functions.
        /// Use the flags DEPARTTIME, FARE, and BSA to call the proper methods.
        new ApiDisplay("DALY", "BALB", "DEPARTTIME").execute("");
        new ApiDisplay("DALY", "BALB", "FARE").execute("");
        new ApiDisplay("DALY", "BALB", "BSA").execute("");
    }

    public class ApiDisplay extends AsyncTask<String, Void, String> {

        private String ReturnString;
        private String Origin;
        private String Destination;
        private String MethodFlag;

        ApiDisplay(String o, String d, String f)
        {
            Origin = o;
            Destination = d;
            MethodFlag = f;
        }

        @Override
        protected String doInBackground(String...params) {
            try
            {
                BartApi testBartString = new BartApi();
                if(MethodFlag == "DEPARTTIME") {
                    ReturnString = testBartString.printDestinationTime(Origin, Destination);
                }
                else if(MethodFlag == "FARE") {
                    ReturnString = testBartString.printFare(Origin, Destination);
                }
                else if (MethodFlag == "BSA"){
                    ReturnString = testBartString.printAdvisory();
                }
                return "";
            }
            catch (IOException e)
            {
                return "Not Working";
            }
            catch (ParserConfigurationException e)
            {
                e.printStackTrace();
            }
            catch (SAXException e)
            {
                e.printStackTrace();
            }
            return "Not Working";
        }
        @Override
        protected void onPostExecute(String result) {
            TextView test = (TextView)findViewById(R.id.test);
            test.setText(ReturnString);
            // might want to change "executed" for the returned string passed
            // into onPostExecute() but that is upto you
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}