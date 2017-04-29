package com.damianrudzinski.terremotoreport;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by damianrudzinski on 28.04.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    TextView magnitude;
    TextView city;
    TextView date;


    public EarthquakeAdapter(Context context, List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_view, parent, false);

        }
        final Earthquake local_earthquake = getItem(position);

        magnitude = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        city = (TextView) listItemView.findViewById(R.id.city_text_view);
        date = (TextView) listItemView.findViewById(R.id.date_text_view);

        return listItemView;
    }
}
