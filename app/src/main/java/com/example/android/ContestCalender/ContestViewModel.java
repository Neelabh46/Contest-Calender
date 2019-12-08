package com.example.android.ContestCalender;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContestViewModel extends AndroidViewModel {
    public ContestLiveData data;
    public ContestViewModel(Application application, String url,String hosts) {
        super(application);
        data = new ContestLiveData(application,url,hosts);
    }
    public LiveData<List<Contest>> getData() {
        return data;
    }
}