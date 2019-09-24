/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.ContestCalender;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;


import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;


import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ContestActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Contest>> {

    public static final String LOG_TAG = ContestActivity.class.getName();
    private TextView mEmptyView ;
    public String host;

    private LinearLayoutManager mlinearlayoutmanager;

    private ArrayList<Contest> mContest;
    private static final String url_string = "https://clist.by/api/v1/json/contest/?username=Neelabh46&api_key=31913193f20dad9a20fa9d2967bd5d9f01877455";
     private ContestAdapters mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.contest_activity);

        final RecyclerView contestRecyclerView = findViewById(R.id.recycler);
        mEmptyView = findViewById(R.id.empty_view);
        mContest = new ArrayList<>();
        mAdapter = new ContestAdapters(mContest);        // Set the adapter on the {@link ListView}
        contestRecyclerView.setAdapter(mAdapter);
        mlinearlayoutmanager = new LinearLayoutManager(this);
        contestRecyclerView.setLayoutManager(mlinearlayoutmanager);

        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected)
        {
            LoaderManager loaderamanager = getLoaderManager();
            loaderamanager.initLoader(1,null,this);

        }else
        {
            ProgressBar bar = findViewById(R.id.loading_spinner);
            bar.setVisibility(View.GONE);
            mEmptyView.setText("You don't have any fucking internet connection");
            mEmptyView.setVisibility(View.VISIBLE);
        }
        contestRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, contestRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Contest currentEarthquake = mAdapter.getItem(position);

                        Uri earthquakeUri = Uri.parse(currentEarthquake.getMurl());

                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
                        startActivity(websiteIntent);
                        // do whatever
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }
    @Override
    public Loader<ArrayList<Contest>> onCreateLoader(int i, Bundle bundle) {
       SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String orderBy = sharedPrefs.getString(
                getString(R.string.settings_selection_key),
                getString(R.string.settings_selection_default)
        );

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        String currenttime= formatter.format(date);
        Uri baseUri = Uri.parse(url_string);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        Log.e("time",currenttime);

        uriBuilder.appendQueryParameter("resource__name", orderBy);
        uriBuilder.appendQueryParameter("end__gte", currenttime);
        uriBuilder.appendQueryParameter("order_by", "start");

        return new ContestLoader(this, uriBuilder.toString(),orderBy);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Contest>> loader, ArrayList<Contest> earthquakes) {
        // Clear the adapter of previous earthquake data

       // mEmptyView.setText(R.string.no_earthquakes);

        ProgressBar bar = findViewById(R.id.loading_spinner);
        bar.setVisibility(View.GONE);
        if (earthquakes != null && !earthquakes.isEmpty()) {

            mContest.clear();
            mContest.addAll(earthquakes);
            mAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onLoaderReset(Loader<ArrayList<Contest>> loader) {
        mContest.clear();
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}