package com.example.android.ContestCalender;

public class Contest {

    private String name;
    private String start;
    private String end;
    private String murl;
    private String host;

    public Contest(String nam,String star,String en,String url,String ht)
    {
        name=nam;
        start=star;
        end=en;
        murl=url;
        host=ht;
    }

    public String getName()
    {
        return name;
    }
    public String getStart()
    {
        String[] parts = start.split("T");
        String starts = parts[0] + " "+parts[1];
        return starts;
    }
    public String getEnd()
    {
        String[] partss = end.split("T");
        String ends = partss[0] + " "+partss[1];
        return ends;
    }
    public String getMurl()
    {
        return murl;
    }
    public String getHost()
    {
        return host;
    }
}
