package com.example.android.ContestCalender;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;


public class ContestAdapter extends ArrayAdapter<Contest> {



    private static final String LOCATION_SEPARATOR = " of ";
    public ContestAdapter(Activity context, ArrayList<Contest> WORDS) {

        super(context, 0, WORDS);
    }
    public String dateFormatter(String date)
    {
        SimpleDateFormat gmt = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
        gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date1 = new Date();
        try
        {
             date1 = gmt.parse(date);
        }catch (ParseException e )
        {
            Log.e("Proton bsdka","Bansal bsdka");
            Log.e("bs",date);
        }
        SimpleDateFormat ist = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        ist.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        return ist.format(date1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Contest currentQuake = getItem(position);


        TextView dateTextView = (TextView) listItemView.findViewById(R.id.start);

        String starts = currentQuake.getStart();

        dateTextView.setText("START: " +dateFormatter(starts));



        TextView timeTextView = (TextView) listItemView.findViewById(R.id.End);
        String ends = currentQuake.getEnd();
        timeTextView.setText("END: "+dateFormatter(ends));

        TextView nametextview = (TextView) listItemView.findViewById(R.id.name);
        String naems = currentQuake.getName();
        nametextview.setText(naems);
        ImageView imgview =(ImageView) listItemView.findViewById(R.id.image);
        imgview.setImageResource(R.drawable.codechef);
        String neel = currentQuake.getHost();
        Log.e("Error",neel);
        if(neel.equals("codeforces.com"))
        {
            Log.e("Big",neel);
            imgview.setImageResource(R.drawable.codeforces);

        }
        if(neel.equals("codechef.com"))
        {
           Log.v("errrrr","codecheffffff");
           imgview.setImageResource(R.drawable.codechef);

        }
        if(neel.equals("topcoder.com"))
        {

            imgview.setImageResource(R.drawable.topcoder);
        }
        if(neel.equals("hackerearth.com"))
        {
            imgview.setImageResource(R.drawable.hackerearth);
        }
        return listItemView;
    }
}
