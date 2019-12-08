package com.example.android.ContestCalender.data;


import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContestRepository {

    int flag;
    private ContestDao mContestDao;
    private LiveData<List<ContestData>> mAllContests;

    ContestRepository(Application application) {
        ContestRoomDatabase db = ContestRoomDatabase.getDatabase(application);
        mContestDao = db.contestDao();
        mAllContests = mContestDao.getAllContests();
    }

    LiveData<List<ContestData>> getAllContests() {
        return mAllContests;
    }
    public String Search(String name)
    {
       return mContestDao.Search(name);
    }

    public void insert(ContestData contest)
    {
      mContestDao.insert(contest);
    }


}


