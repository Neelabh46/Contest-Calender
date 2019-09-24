package com.example.android.ContestCalender.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contest_table")
public class  ContestData{

    @PrimaryKey
    private String mName;

    private String mStart;
    private String mEnd;
    private String mUrl;
    private String mHost;


    public ContestData(String name,String end,String start,String url,String host) {
        mName = name;
        mEnd = end;
        mHost = host;
        mStart = start;
        mUrl = url;
    }


}