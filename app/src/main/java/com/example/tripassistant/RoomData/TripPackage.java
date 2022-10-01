package com.example.tripassistant.RoomData;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TripPackage")
public class TripPackage { //Πακέτο Εκδρομής
    //Primary Key
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "PackageCode") @NonNull
    private int PackageCode;

    //Foreign Key
    @ColumnInfo(name = "Package_TACode") @NonNull
    private int Package_TACode;

    //Foreign Key
    @ColumnInfo(name = "Package_STCode") @NonNull
    private int Package_STCode;

    @ColumnInfo(name = "departureDate")
    private String departureDate;

    @ColumnInfo(name = "price")
    private String price;

    //Getters-Setters
    public int getPackageCode() {
        return PackageCode;
    }
    public void setPackageCode(int packageCode) {
        PackageCode = packageCode;
    }

    public int getPackage_TACode() {
        return Package_TACode;
    }
    public void setPackage_TACode(int package_TACode) {
        Package_TACode = package_TACode;
    }

    public int getPackage_STCode() {
        return Package_STCode;
    }
    public void setPackage_STCode(int package_STCode) {
        Package_STCode = package_STCode;
    }

    public String getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}