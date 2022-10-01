package com.example.tripassistant.RoomData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TripPackage.class}, exportSchema = false, version = 1)
public abstract class DB_TripPackage extends RoomDatabase {

    public abstract Dao_TripPackage TripPackageDao();

    private static DB_TripPackage instance;

    public static synchronized DB_TripPackage getInstanceTripPackage(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DB_TripPackage.class, "DB_TripPackage").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}

