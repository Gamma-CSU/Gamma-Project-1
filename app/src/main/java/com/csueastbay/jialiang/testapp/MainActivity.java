package com.csueastbay.jialiang.testapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;


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
    int flag = 0;
    Spinner spinner1,spinner2;
    Button btnSubmit;
    String dep,arr; //holds the 4 character codes for the departure and arrival stations.

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
        addListenerOnButton();
        new ApiDisplay().execute("");
        /*
        if (flag == 1) {
            new ApiDisplay().execute("");
        }
        */
    }

    public class ApiDisplay extends AsyncTask<String, Void, String> {

        private String ApiString;
        @Override
        protected String doInBackground(String...params) {

            BartApi testBartString = new BartApi();
            try
            {
                while (flag == 0) {
                }

                ApiString = testBartString.returnString(dep,arr);
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
            /*
            TextView test = (TextView)findViewById(R.id.test);
            test.setText(ApiString);
            */
            flag = 0;
            new ApiDisplay().execute("");

            Toast.makeText(MainActivity.this,
                   ApiString,
                    Toast.LENGTH_LONG).show();
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

    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spin1);
        spinner2 = (Spinner) findViewById(R.id.spin2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {
            //When submit button pressed:
            //display departure and arrival choices on screen,
            // and store them in appropriate variables

            @Override
            public void onClick(View v) {
                String sameStations = "Please choose two different stations!";
/*
                Toast.makeText(MainActivity.this,
                        "Planned Trip : " +
                                "\nDeparture: " + stationToSymbol(String.valueOf(spinner1.getSelectedItem())) +
                                "\nArrival: " + stationToSymbol(String.valueOf(spinner2.getSelectedItem())),
                        Toast.LENGTH_SHORT).show();
                        */

                dep = stationToSymbol(String.valueOf(spinner1.getSelectedItem()));
                arr = stationToSymbol(String.valueOf(spinner2.getSelectedItem()));
                if (dep.equals(arr)){
                    Toast.makeText(MainActivity.this,
                            sameStations,
                            Toast.LENGTH_LONG).show();
                }
                else {
                    flag = 1;
                }

            }

        });
    }


    String stationToSymbol(String station) {
        String stationSymbol = "return string";

        if (station.equals("12th St. Oakland City Center")) {
            stationSymbol = "12TH";
        }
        if (station.equals("16th St. Mission")) {
            stationSymbol = "16TH";
        }
        if (station.equals("19th St. Oakland")) {
            stationSymbol = "19TH";
        }
        if (station.equals("24th St. Mission")) {
            stationSymbol = "24TH";
        }
        if (station.equals("Ashby")) {
            stationSymbol = "ASHB";
        }
        if (station.equals("Balboa Park")) {
            stationSymbol = "BALB";
        }
        if (station.equals("Bay Fair")) {
            stationSymbol = "BAYF";
        }
        if (station.equals("Castro Valley")) {
            stationSymbol = "CAST";
        }
        if (station.equals("Civic Center/UN Plaza")) {
            stationSymbol = "CIVC";
        }
        if (station.equals("Coliseum")) {
            stationSymbol = "COLS";
        }
        if (station.equals("Colma")) {
            stationSymbol = "COLM";
        }
        if (station.equals("Concord")) {
            stationSymbol = "CONC";
        }
        if (station.equals("Daly City")) {
            stationSymbol = "DALY";
        }
        if (station.equals("Downtown Berkeley")) {
            stationSymbol = "DBRK";
        }
        if (station.equals("Dublin/Pleasanton")) {
            stationSymbol = "DUBL";
        }
        if (station.equals("El Cerrito del Norte")) {
            stationSymbol = "DELN";
        }
        if (station.equals("El Cerrito Plaza")) {
            stationSymbol = "PLZA";
        }
        if (station.equals("Embarcadero")) {
            stationSymbol = "EMBR";
        }
        if (station.equals("Fremont")) {
            stationSymbol = "FRMT";
        }
        if (station.equals("Fruitvale")) {
            stationSymbol = "FTVL";
        }
        if (station.equals("Glen Park")) {
            stationSymbol = "GLEN";
        }
        if (station.equals("Hayward")) {
            stationSymbol = "HAYW";
        }
        if (station.equals("Lafayette")) {
            stationSymbol = "LAFY";
        }
        if (station.equals("Lake Merritt")) {
            stationSymbol = "LAKE";
        }
        if (station.equals("MacArthur")) {
            stationSymbol = "MCAR";
        }
        if (station.equals("Millbrae")) {
            stationSymbol = "MLBR";
        }
        if (station.equals("Montgomery St.")) {
            stationSymbol = "MONT";
        }
        if (station.equals("North Berkeley")) {
            stationSymbol = "NBRK";
        }
        if (station.equals("North Concord/Martinez")) {
            stationSymbol = "NCON";
        }
        if (station.equals("Oakland Intl Airport")) {
            stationSymbol = "OAKL";
        }
        if (station.equals("Orinda")) {
            stationSymbol = "ORIN";
        }
        if (station.equals("Pittsburg/Bay Point")) {
            stationSymbol = "PITT";
        }
        if (station.equals("Pleasant Hill/Contra Costa Centre")) {
            stationSymbol = "PHIL";
        }
        if (station.equals("Powell St.")) {
            stationSymbol = "POWL";
        }
        if (station.equals("Richmond")) {
            stationSymbol = "RICH";
        }
        if (station.equals("Rockridge")) {
            stationSymbol = "ROCK";
        }
        if (station.equals("San Bruno")) {
            stationSymbol = "SBRN";
        }
        if (station.equals("San Francisco Intl Airport")) {
            stationSymbol = "SFIA";
        }
        if (station.equals("San Leandro")) {
            stationSymbol = "SANL";
        }
        if (station.equals("South Hayward")) {
            stationSymbol = "SHAY";
        }
        if (station.equals("South San Francisco")) {
            stationSymbol = "SSAN";
        }
        if (station.equals("Union City")) {
            stationSymbol = "UCTY";
        }
        if (station.equals("Walnut Creek")) {
            stationSymbol = "WCRK";
        }
        if (station.equals("West Dublin/Pleasanton")) {
            stationSymbol = "WDUB";
        }
        if (station.equals("West Oakland")) {
            stationSymbol = "WOAK";
        }

        return stationSymbol;
    }

}