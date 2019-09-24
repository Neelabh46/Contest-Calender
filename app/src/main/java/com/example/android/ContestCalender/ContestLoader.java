
package com.example.android.ContestCalender;
import android.content.AsyncTaskLoader;
import android.content.Context;


import java.util.ArrayList;


public class ContestLoader extends AsyncTaskLoader<ArrayList<Contest>> {

    private static final String LOG_TAG = ContestLoader.class.getName();

    private String mUrl;
    private String hosts;

    public ContestLoader(Context context, String url,String host) {
        super(context);
        mUrl = url;
        hosts=host;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<Contest> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        ArrayList<Contest> earthquakes = QueryUtils.fetchEarthquakeData(mUrl,hosts);
        return earthquakes;
    }
}