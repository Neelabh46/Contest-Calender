package com.example.android.ContestCalender.Adpaters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import com.example.android.ContestCalender.Fragments.LiveContestFragment;
import com.example.android.ContestCalender.Fragments.MyContestFragment;
import com.example.android.ContestCalender.R;

public class ContestFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;



    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new LiveContestFragment();
        } else {
            return new MyContestFragment();
        }
    }

    public ContestFragmentPagerAdapter(Context context, @NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        mContext = context;
    }


    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        if(position==0)
            return mContext.getString(R.string.Live_contest_header);
        else
            return mContext.getString(R.string.My_contest_header);


    }

}