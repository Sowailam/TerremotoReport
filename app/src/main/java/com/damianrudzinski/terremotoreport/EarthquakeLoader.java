package com.damianrudzinski.terremotoreport;

import android.content.Context;
// the support library here is incompitable with the classes you are using in the main activity.
// use this instead
import android.content.AsyncTaskLoader;
import java.util.List;


public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if(mUrl==null){
            return null;
        }
        List<Earthquake> result = QueryUtils.fetchEarthquakeData(mUrl);
        return result;
    }
}
