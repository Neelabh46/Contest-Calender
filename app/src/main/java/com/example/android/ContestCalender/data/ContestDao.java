package com.example.android.ContestCalender.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContestDao {

    @Insert
    void insert(ContestData contest);

    @Query("DELETE FROM contest_table")
    void deleteAll();

    @Delete
    void deleteContest(ContestData contest);

    @Query("SELECT * from contest_table ")
    LiveData<List<ContestData>> getAllContests();
}