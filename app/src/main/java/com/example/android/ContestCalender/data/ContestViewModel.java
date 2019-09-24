package com.example.android.ContestCalender.data;


import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContestViewModel extends AndroidViewModel {

    private ContestRepository mRepository;

    private LiveData<List<ContestData>> mAllWords;

    public ContestViewModel(Application application) {
        super(application);
        mRepository = new ContestRepository(application);
        mAllWords = mRepository.getAllContests();
    }

    LiveData<List<ContestData>> getAllWords() {
        return mAllWords;
    }

    public void insert(ContestData contest) {
        mRepository.insert(contest);
    }
}
