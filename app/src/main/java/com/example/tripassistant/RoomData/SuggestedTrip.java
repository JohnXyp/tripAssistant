package com.example.tripassistant.RoomData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "SuggestedTrip") //Προτεινόμενη Εκδρομή
public class SuggestedTrip {
    //Primary Key
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "SuggestedTripCode") @NonNull
    private int SuggestedTripCode;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "duration")
    private String duration;

    @ColumnInfo(name = "kind")
    private String kind;

    //Getters-Setters
    public int getSuggestedTripCode() {
        return SuggestedTripCode;
    }
    public void setSuggestedTripCode(int suggestedTripCode) {
        SuggestedTripCode = suggestedTripCode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
}
