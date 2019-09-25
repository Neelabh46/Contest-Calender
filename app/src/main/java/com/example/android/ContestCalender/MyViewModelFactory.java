package com.example.android.ContestCalender;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.net.URL;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;
    private String mUrl;

    public MyViewModelFactory(Application application, String url, String param) {
        mApplication = application;
        mParam = param;
        mUrl=url;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ContestViewModel(mApplication, mUrl,mParam);
    }
}