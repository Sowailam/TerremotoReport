package com.damianrudzinski.terremotoreport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;

import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by damianrudzinski on 28.04.2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private TextView magnitudeOfAnEarthquake;
    private TextView offsetOfAnEarthquake;
    private TextView cityOfAnEarthquake;
    private TextView dateOfAnEarthquake;
    private TextView timeOfAnEarthquake;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;
    private GradientDrawable magnitudeCircle;

    private static final String LOCATION_SEPARATOR = " of ";

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

        magnitudeOfAnEarthquake = (TextView) listItemView.findViewById(R.id.magnitude_text_view);
        magnitudeOfAnEarthquake.setText(magnitudeFormatter(local_earthquake.getEarthquakeMagnitude(), "0.0"));

        magnitudeCircle = (GradientDrawable) magnitudeOfAnEarthquake.getBackground();
        int magnitudeColor = getMagnitudeColor(local_earthquake.getEarthquakeMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

        cityOfAnEarthquake = (TextView) listItemView.findViewById(R.id.city_text_view);
        offsetOfAnEarthquake = (TextView) listItemView.findViewById(R.id.offset_text_view);

        String originalLocation = local_earthquake.getEarthquakeCity();
        String primaryLocation;
        String locationOffSet;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffSet = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffSet = "Near ";
            primaryLocation = originalLocation;
        }
        offsetOfAnEarthquake.setText(locationOffSet);
        cityOfAnEarthquake.setText(primaryLocation);

        dateOfAnEarthquake = (TextView) listItemView.findViewById(R.id.date_text_view);
        dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        dateOfAnEarthquake.setText(dateFormatter.format(new Date(local_earthquake.getEarthquakeDate())));

        timeOfAnEarthquake = (TextView) listItemView.findViewById(R.id.time_text_view);
        timeFormatter = new SimpleDateFormat("h:mm a");
        timeOfAnEarthquake.setText(timeFormatter.format(new Date(local_earthquake.getEarthquakeDate())));


        return listItemView;
    }

    private String magnitudeFormatter(double magnitude, String format) {
        DecimalFormat magnitudeFormat = new DecimalFormat(format);
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
