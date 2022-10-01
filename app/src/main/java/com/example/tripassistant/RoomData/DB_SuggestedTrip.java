package com.example.tripassistant.RoomData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SuggestedTrip.class}, exportSchema = false, version = 1)
public abstract class DB_SuggestedTrip extends RoomDatabase {

    public abstract Dao_SuggestedTrip SuggestedTripDao();

    private static DB_SuggestedTrip instance;

    public static synchronized DB_SuggestedTrip getInstanceSuggestedTrip(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), DB_SuggestedTrip.class, "DB_SuggestedTrip").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        }
        return instance;
    }
}
