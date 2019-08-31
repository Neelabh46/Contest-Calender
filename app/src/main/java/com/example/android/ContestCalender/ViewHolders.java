package com.example.android.ContestCalender;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolders extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mName;
    public TextView mStart;
    public TextView mEnd;

    public ViewHolders(View item_view)
    {
        super(item_view);
        mImageView = (ImageView) item_view.findViewById(R.id.image);
        mName = (TextView) item_view.findViewById(R.id.name);
        mStart = (TextView) item_view.findViewById(R.id.start);
        mEnd= (TextView) item_view.findViewById(R.id.End);
    }

}
