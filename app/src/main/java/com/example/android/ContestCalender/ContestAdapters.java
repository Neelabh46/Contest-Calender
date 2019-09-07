package com.example.android.ContestCalender;

import android.content.Context;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class ContestAdapters extends RecyclerView.Adapter<ViewHolders> {


    // ... view holder defined above...

    // Store a member variable for the contacts
    private ArrayList<Contest> mContests;

    // Pass in the contact array into the constructor
    public ContestAdapters(ArrayList<Contest> contests) {

        mContests = contests;
    }

    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contestView = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new holder instance
        ViewHolders viewHolder = new ViewHolders(contestView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolders viewHolder, int position) {
        // Get the data model based on position
        Contest contest = mContests.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.mName;
        String names = contest.getName();
        textView.setText(names);

        TextView Start = viewHolder.mStart;
        String starts = contest.getStart();
        Start.setText("START: " + dateFormatter(starts));

        TextView End = viewHolder.mEnd;
        String ends = contest.getEnd();
        End.setText("END: " + dateFormatter(ends));
        setBackgrounds(starts, viewHolder);

        ImageView imgview = viewHolder.mImageView;
        imgview.setImageResource(R.drawable.codechef);
        String contestHost = contest.getHost();

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

    public Contest getItem(int pos) {
        return mContests.get(pos);
    }

    public void setBackgrounds(String start, ViewHolders view) {
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

            view.mView.setBackgroundColor(Color.rgb(135, 232, 116));

        }else
        {
            view.mView.setBackgroundColor(Color.WHITE);
        }


    }


}

