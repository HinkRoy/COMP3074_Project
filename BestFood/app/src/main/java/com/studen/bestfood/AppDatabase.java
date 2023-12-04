package com.studen.bestfood;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CafeteriaInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract CafeteriaInfoDao cafeteriaInfoDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "BestFoodDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}


