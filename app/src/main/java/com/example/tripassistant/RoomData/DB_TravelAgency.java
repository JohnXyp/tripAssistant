package com.example.tripassistant.RoomData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TravelAgency.class}, exportSchema = false, version = 1)
public abstract class DB_TravelAgency extends RoomDatabase {

    public abstract Dao_TravelAgency TravelAgencyDao();

    private static DB_TravelAgency instance;

    public static synchronized DB_TravelAgency getInstanceTravelAgency(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DB_TravelAgency.class, "DB_TravelAgency").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}
