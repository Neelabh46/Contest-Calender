package com.example.android.ContestCalender.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.ContestCalender.Contest;
import com.example.android.ContestCalender.ContestActivity;
import com.example.android.ContestCalender.Adpaters.ContestAdapters;
import com.example.android.ContestCalender.ContestViewModel;
import com.example.android.ContestCalender.MyViewModelFactory;
import com.example.android.ContestCalender.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class LiveContestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match

    // TODO: Rename and change types of parameters
    public static final String LOG_TAG = ContestActivity.class.getName();
    private TextView mEmptyView ;
    public String host;

    private LinearLayoutManager mlinearlayoutmanager;

    private ArrayList<Contest> mContest;
    private static final String url_string = "https://clist.by/api/v1/json/contest/?username=Neelabh46&api_key=31913193f20dad9a20fa9d2967bd5d9f01877455";
    private ContestAdapters mAdapter;

    public LiveContestFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root =  inflater.inflate(R.layout.fragment_live_contest, container, false);


        final RecyclerView contestRecyclerView =  root.findViewById(R.id.recycler);
        mEmptyView = root.findViewById(R.id.empty_view);
        mContest = new ArrayList<>();
        mAdapter = new ContestAdapters(mContest,getActivity());        // Set the adapter on the {@link ListView}
        contestRecyclerView.setAdapter(mAdapter);
        mlinearlayoutmanager = new LinearLayoutManager(getActivity());
        contestRecyclerView.setLayoutManager(mlinearlayoutmanager);


        ConnectivityManager cm =
                (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected)
        {
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

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

            ContestViewModel viewModel = new ViewModelProvider(this, new MyViewModelFactory(getActivity().getApplication(),uriBuilder.toString(),orderBy )).get(ContestViewModel.class);
            viewModel.getData().observe(this, new Observer<List<Contest>>() {
                @Override
                public void onChanged(@Nullable final List<Contest> contests) {
                    // Update the cached copy of the words in the adapter.
                    ProgressBar bar = root.findViewById(R.id.loading_spinner);
                    bar.setVisibility(View.GONE);
                    mContest.addAll(contests);
                    mAdapter.notifyDataSetChanged();

                }
            });

        }else
        {
            ProgressBar bar = root.findViewById(R.id.loading_spinner);
            bar.setVisibility(View.GONE);
            mEmptyView.setText("You don't have any fucking internet connection");
            mEmptyView.setVisibility(View.VISIBLE);
        }
        return root;
    }



}
