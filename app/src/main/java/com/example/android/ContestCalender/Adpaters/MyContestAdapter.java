package com.example.android.ContestCalender.Adpaters;



import android.content.Context;
import android.graphics.Color;


import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.ContestCalender.MyViewHolder;
import com.example.android.ContestCalender.R;
import com.example.android.ContestCalender.data.ContestData;
import com.example.android.ContestCalender.data.ContestViewModels;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MyContestAdapter extends RecyclerView.Adapter<MyViewHolder> {


    // ... view holder defined above...

    // Store a member variable for the contacts
    private List<ContestData> mContests;
    Context mContext;
    // Pass in the contact array into the constructor
    public MyContestAdapter(List<ContestData> contests, Context context) {

        mContests = contests;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contestView = inflater.inflate(R.layout.my_list_item, parent, false);

        // Return a new holder instance
        MyViewHolder viewHolder = new MyViewHolder(contestView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        // Get the data model based on position
        final ContestData contest = mContests.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.mName;
        String names = contest.getmName();
        textView.setText(names);

        TextView Start = viewHolder.mStart;
        String starts = contest.getmStart();
        Start.setText("START: " + dateFormatter(starts));

        TextView End = viewHolder.mEnd;
        String ends = contest.getmEnd();
        End.setText("END: " + dateFormatter(ends));
        setBackgrounds(starts, viewHolder);

        ImageView imgview = viewHolder.mImageView;
        imgview.setImageResource(R.drawable.codechef);
        String contestHost = contest.getmHost();


        if (contestHost.equals("codeforces.com")) {
            imgview.setImageResource(R.drawable.codeforces);
        }
        if (contestHost.equals("codechef.com")) {
            imgview.setImageResource(R.drawable.codechef);
        }
        if (contestHost.equals("topcoder.com")) {
            imgview.setImageResource(R.drawable.topcoder);
        }
        if (contestHost.equals("hackerearth.com")) {
            imgview.setImageResource(R.drawable.hackerearth);
        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContests.size();
    }

    public String dateFormatter(String date) {
        SimpleDateFormat gmt = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date1 = new Date();
        try {
            date1 = gmt.parse(date);
        } catch (ParseException e) {

        }
        SimpleDateFormat ist = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        ist.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        return ist.format(date1);
    }



    public void setBackgrounds(String start, MyViewHolder view) {
        SimpleDateFormat gmt = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date1 = new Date();
        try {
            date1 = gmt.parse(start);
        } catch (ParseException e) {

        }
        Date cDate = new Date();

        if (date1.before(cDate)) {
            String startDate = gmt.format(date1);
            String curDate = gmt.format(cDate);
            Log.e("START", startDate);
            Log.e("CURRENT", curDate);

            view.status.setText("Running");
            view.status.setTextColor(Color.rgb(20, 133, 15));

        }else
        {
            view.status.setText("Upcoming");
            view.status.setTextColor(Color.BLACK);
        }


    }


}

