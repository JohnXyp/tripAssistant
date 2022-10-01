package com.example.tripassistant.RoomData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TravelAgency") //Ταξιδιωτικό Γραφείο
public class TravelAgency {
    //Primary Key
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "TravelAgencyCode") @NonNull
    private int TravelAgencyCode;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "address")
    private String address;

    //Getters-Setters
    public int getTravelAgencyCode() {
        return TravelAgencyCode;
    }
    public void setTravelAgencyCode(int travelAgencyCode) {
        TravelAgencyCode = travelAgencyCode;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
