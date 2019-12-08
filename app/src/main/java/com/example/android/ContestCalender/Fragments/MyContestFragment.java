package com.example.android.ContestCalender.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.ContestCalender.ContestActivity;
import com.example.android.ContestCalender.Adpaters.MyContestAdapter;
import com.example.android.ContestCalender.R;
import com.example.android.ContestCalender.data.ContestData;
import com.example.android.ContestCalender.data.ContestViewModels;

import java.util.ArrayList;
import java.util.List;


public class MyContestFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public static final String LOG_TAG = ContestActivity.class.getName();
    private TextView mEmptyView ;
    public String host;

    private LinearLayoutManager mlinearlayoutmanager;

    private List<ContestData> mContest;
    private static final String url_string = "https://clist.by/api/v1/json/contest/?username=Neelabh46&api_key=31913193f20dad9a20fa9d2967bd5d9f01877455";
    private MyContestAdapter mAdapter;


    public MyContestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_my_contest, container, false);

        final RecyclerView contestRecyclerView =  root.findViewById(R.id.recycler_my);
        mEmptyView = root.findViewById(R.id.empty_view_my);
        mContest = new ArrayList<>();
        mAdapter = new MyContestAdapter(mContest,getActivity());        // Set the adapter on the {@link ListView}
        contestRecyclerView.setAdapter(mAdapter);
        mlinearlayoutmanager = new LinearLayoutManager(getActivity());
        contestRecyclerView.setLayoutManager(mlinearlayoutmanager);

        ContestViewModels viewModel = new ViewModelProvider(this).get(ContestViewModels.class);

        viewModel.getAllWords().observe(this, new Observer<List<ContestData>>() {
            @Override
            public void onChanged(@Nullable final List<ContestData> contests) {
                // Update the cached copy of the words in the adapter.
               // ProgressBar bar = root.findViewById(R.id.loading_spinner);
                //bar.setVisibility(View.GONE);
                mContest.clear();
                mContest.addAll(contests);

                mAdapter.notifyDataSetChanged();
                Log.e("help", String.valueOf(mContest.size()));

            }
        });

        return root;
    }


}
