package com.example.android.ContestCalender.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contest_table")
public class  ContestData{

    @PrimaryKey
    @NonNull
    public String mName;

    public  String mStart;
    public  String mEnd;
    public  String mUrl;
    public  String mHost;


    public ContestData(String name,String end,String start,String url,String host) {
        mName = name;
        mEnd = end;
        mHost = host;
        mStart = start;
        mUrl = url;
    }

    public String getmEnd() {
        return mEnd;
    }

    public String getmHost() {
        return mHost;
    }

    public String getmName() {
        return mName;
    }

    public String getmStart() {
        return mStart;
    }

    public String getmUrl() {
        return mUrl;
    }
}