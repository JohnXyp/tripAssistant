package com.example.tripassistant.RoomData;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Dao_TripPackage {
    @Insert
    void insertTripPackage(TripPackage TP);

    //SQL Query
    @Query("SELECT * FROM TripPackage")
    List<TripPackage> getTripPackage();

    //SQL Query - Delete Data
    @Query("DELETE FROM TripPackage WHERE PackageCode= :id3")
    void deleteTripPackage(int id3);

    //SQL Query - Update Data
    @Query("UPDATE TripPackage SET departureDate=:departureDate, price=:price, Package_TACode=:Package_TACode, Package_STCode=:Package_STCode WHERE PackageCode=:code3")
    void updateTripPackage(String departureDate, String price, int Package_TACode, int Package_STCode, int code3);
}
