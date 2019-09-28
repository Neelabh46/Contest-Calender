package com.example.android.ContestCalender.data;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.ContestCalender.Contest;

import java.util.List;

public class ContestViewModels extends AndroidViewModel {

    private ContestRepository mRepository;

    private LiveData<List<ContestData>> mAllWords;

    private ContestData mContestData;
    public ContestViewModels(Application application) {
        super(application);
        mRepository = new ContestRepository(application);
        mAllWords = mRepository.getAllContests();
    }

    public LiveData<List<ContestData>> getAllWords() {
        return mAllWords;
    }

    public String Search(Contest contest)
    {
       return mRepository.Search(contest.getName());
    }
    public void insert(Contest contest)
    {
        mContestData = new ContestData(contest.getName(),contest.getEnd(),contest.getStart(),contest.getMurl(),contest.getHost());
        mRepository.insert(mContestData);
    }
}
