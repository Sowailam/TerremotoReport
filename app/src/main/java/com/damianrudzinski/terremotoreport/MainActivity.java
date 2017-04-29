package com.damianrudzinski.terremotoreport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        earthquakes.add(new Earthquake("San Francisco"));
        earthquakes.add(new Earthquake("London"));
        earthquakes.add(new Earthquake("Tokyo"));
        earthquakes.add(new Earthquake("Mexico City"));
        earthquakes.add(new Earthquake("Moscow"));
        earthquakes.add(new Earthquake("Rio de Janeiro"));
        earthquakes.add(new Earthquake("Paris"));

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);

        earthquakeListView.setAdapter(adapter);
    }
}
