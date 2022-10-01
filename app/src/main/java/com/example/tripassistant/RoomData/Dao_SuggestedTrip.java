package com.example.tripassistant.RoomData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Dao_SuggestedTrip {
    @Insert
    void insertSuggestedTrip(SuggestedTrip ST);

    //SQL Query
    @Query("SELECT * FROM SuggestedTrip")
    List<SuggestedTrip> getSuggestedTrip();

    //SQL Query - Delete Data
    @Query("DELETE FROM SuggestedTrip WHERE SuggestedTripCode= :id2")
    void deleteSuggestedTrip(int id2);

    //SQL Query - Update Data
    @Query("UPDATE SuggestedTrip SET city=:city, country=:country, duration=:duration, kind=:kind WHERE SuggestedTripCode=:code2")
    void updateSuggestedTrip(String city, String country, String duration, String kind, int code2);
}
