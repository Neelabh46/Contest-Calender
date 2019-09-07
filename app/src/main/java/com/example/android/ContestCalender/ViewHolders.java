package com.example.android.ContestCalender;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolders extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mName;
    public TextView mStart;
    public TextView mEnd;
    public ConstraintLayout mView;

    public ViewHolders(View item_view)
    {
        super(item_view);
        mImageView = item_view.findViewById(R.id.image);
        mName =  item_view.findViewById(R.id.name);
        mStart =  item_view.findViewById(R.id.start);
        mEnd=  item_view.findViewById(R.id.End);
        mView= item_view.findViewById(R.id.ConstraintLayout);
    }

}
