package com.example.tripassistant.RoomData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Dao_TravelAgency {
    @Insert
    void insertTravelAgency(TravelAgency TA);

    //SQL Query - Insert Data
    @Query("SELECT * FROM TravelAgency")
    List<TravelAgency> getTravelAgency();

    //SQL Query - Delete Data
    @Query("DELETE FROM TravelAgency WHERE TravelAgencyCode=:id")
    void deleteTravelAgency(int id);

    //SQL Query - Update Data
    @Query("UPDATE TravelAgency SET name=:name, address=:address WHERE TravelAgencyCode=:code")
    void updateTravelAgency(String name, String address, int code);
}
