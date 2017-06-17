package com.damianrudzinski.terremotoreport;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>> {

    // the http protocol is no longer used, i guess
    private static final String JSON_RESPONSE = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    public static final String LOG_TAG = MainActivity.class.getName();

    private static final int EARTHQUAKE_LOADER_ID = 1;

    private EarthquakeAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // create a new adapter and give it an empty list just until the Loader finishes
        // if you don't do this step you will get a NullPointerException when you try adapter.addAll(earthquakes);
        // because you didn't create the adapter yet
        adapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);

    }
    // this method would be useless now
    private void updateUi(ArrayList<Earthquake> earthquakes) {

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        adapter = new EarthquakeAdapter(this, earthquakes);

        earthquakeListView.setAdapter(adapter);

    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        return new EarthquakeLoader(this, JSON_RESPONSE);
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        if (earthquakes != null && !earthquakes.isEmpty()) {
            adapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
        adapter.clear();
    }


//    private class EarthquakesAsyncTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {
//
//        @Override
//        protected ArrayList<Earthquake> doInBackground(String... urls) {
//            if (urls.length < 1 || urls == null) {
//                return null;
//            }
//            // Perform the HTTP request for earthquake data and process the response.
//            ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(urls[0]);
//            return earthquakes;
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
//            super.onPostExecute(earthquakes);
//
//            if (earthquakes == null) {
//                return;
//            }
//            updateUi(earthquakes);
//        }
//    }
    }
}


