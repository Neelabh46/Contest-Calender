package com.example.android.ContestCalender;

import android.content.Context;
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
        String naems = contest.getName();
        textView.setText(naems);

        TextView Start = viewHolder.mStart;
        String starts = contest.getStart();
        Start.setText("START: " + dateFormatter(starts));

        TextView End = viewHolder.mEnd;
        String ends = contest.getEnd();
        End.setText("END: " + dateFormatter(ends));

        ImageView imgview = viewHolder.mImageView;
        imgview.setImageResource(R.drawable.codechef);
        String neel = contest.getHost();
        Log.e("Error", neel);
        if (neel.equals("codeforces.com")) {
            Log.e("Big", neel);
            imgview.setImageResource(R.drawable.codeforces);

        }
        if (neel.equals("codechef.com")) {
            Log.v("errrrr", "codecheffffff");
            imgview.setImageResource(R.drawable.codechef);

        }
        if (neel.equals("topcoder.com")) {

            imgview.setImageResource(R.drawable.topcoder);
        }
        if (neel.equals("hackerearth.com")) {
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
            Log.e("Proton bsdka", "Bansal bsdka");
            Log.e("bs", date);
        }
        SimpleDateFormat ist = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        ist.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        return ist.format(date1);
    }
    public Contest getItem(int pos)
    {
        return mContests.get(pos);
    }


}

