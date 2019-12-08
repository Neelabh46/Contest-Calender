package com.example.android.ContestCalender.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ContestData.class}, version = 1)
public abstract class ContestRoomDatabase extends RoomDatabase {

    public abstract ContestDao contestDao();

    private static volatile ContestRoomDatabase INSTANCE;

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    //new PopulateDbAsync(INSTANCE).execute();
                }
            };
    static ContestRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ContestRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ContestRoomDatabase.class, "word_database")
                            .addCallback(sRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ContestDao mDao;

        PopulateDbAsync(ContestRoomDatabase db) {
            mDao = db.contestDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
           // mDao.deleteAll();
            ContestData contest = new ContestData("name","start","end","url","host");
            mDao.insert(contest);
            //w = new Word("World");
            //mDao.insert(word);
            return null;
        }
    }
}