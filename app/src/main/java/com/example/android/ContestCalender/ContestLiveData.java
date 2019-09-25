package com.example.android.ContestCalender;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContestLiveData extends LiveData<List<Contest>> {
    private final Context context;
    private String mUrl;
    private String mhost;
    public ContestLiveData(Context context,String url,String hosts) {
        this.context = context;
        mhost=hosts;
        mUrl=url;
        loadData();
    }
    private void loadData() {
        new AsyncTask<Void,Void,List<Contest>>() {


            @Override
            protected List<Contest> doInBackground(Void... voids) {
                List<Contest> contests = QueryUtils.fetchEarthquakeData(mUrl,mhost);
                return contests;
                //return null;
            }

            @Override
            protected void onPostExecute(List<Contest> data) {
                setValue(data);
            }
        }.execute();
    }
}