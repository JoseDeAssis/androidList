package com.example.exemplocrudtenis;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Tennis.class}, version = 1)
public abstract class AppTennisRoomDatabase extends RoomDatabase {

    private static AppTennisRoomDatabase instance;
    public abstract TennisDao tennisDao();

    public static synchronized AppTennisRoomDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppTennisRoomDatabase.class, "appTennis_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

}
