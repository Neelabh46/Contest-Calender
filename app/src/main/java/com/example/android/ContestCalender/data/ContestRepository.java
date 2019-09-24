package com.example.android.ContestCalender.data;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContestRepository {

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


    public void insert(ContestData contest) {
        new insertAsyncTask(mContestDao).execute(contest);
    }

    private static class insertAsyncTask extends AsyncTask<ContestData, Void, Void> {

        private ContestDao mAsyncTaskDao;

        insertAsyncTask(ContestDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ContestData... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}


