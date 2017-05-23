package com.damianrudzinski.terremotoreport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_RESPONSE = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EarthquakesAsyncTask task = new EarthquakesAsyncTask();
        task.execute(JSON_RESPONSE);

    }

    private void updateUi(ArrayList<Earthquake> earthquakes) {

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        earthquakeListView.setAdapter(adapter);
    }

    private class EarthquakesAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {

        @Override
        protected ArrayList<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls == null) {
                return null;
            }
            // Perform the HTTP request for earthquake data and process the response.
            ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(urls[0]);
            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            super.onPostExecute(earthquakes);

            if (earthquakes == null) {
                return;
            }
            updateUi(earthquakes);
        }
    }
}


