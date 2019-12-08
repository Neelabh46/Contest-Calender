package com.example.android.ContestCalender;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolders extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mName;
    public TextView mStart;
    public TextView mEnd;
    public ConstraintLayout mView;
    public Button mButton;
    public TextView status;

    public ViewHolders(View item_view)
    {
        super(item_view);
        mImageView = item_view.findViewById(R.id.image);
        mName =  item_view.findViewById(R.id.name);
        mStart =  item_view.findViewById(R.id.start);
        mEnd=  item_view.findViewById(R.id.End);
        mView= item_view.findViewById(R.id.ConstraintLayout);
        mButton = item_view.findViewById(R.id.bAdd);
        status = item_view.findViewById(R.id.textView);
    }

}
