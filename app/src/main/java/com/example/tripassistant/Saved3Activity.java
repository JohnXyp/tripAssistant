package com.example.tripassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.tripassistant.RoomData.DB_TripPackage;
import com.example.tripassistant.RoomData.TripPackage;
import com.example.tripassistant.adapter.Saved3Adapter;

import java.util.ArrayList;
import java.util.List;

public class Saved3Activity extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    //Recycler View
    RecyclerView tripPackage_recycler;

    private List<TripPackage> tripPackageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved3);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        tripPackage_recycler = findViewById(R.id.tripPackage_recycler);
        tripPackage_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData3();
    }

    private void getData3() {
        tripPackageList = new ArrayList<>();
        tripPackageList = DB_TripPackage.getInstanceTripPackage(this).TripPackageDao().getTripPackage();
        tripPackage_recycler.setAdapter(new Saved3Adapter(getApplicationContext(), tripPackageList, new Saved3Adapter.DeleteItemClickListener3() {
            @Override
            public void onItemDelete3(int position, int id) {
                DB_TripPackage.getInstanceTripPackage(getApplicationContext()).TripPackageDao().deleteTripPackage(id);
                getData3();
            }
        }));
    }

    public void ClickMenu(View view) {
        //Open drawer
        MainActivity.openDrawer(drawerLayout);
    }

    public void ClickLogo(View view) {
        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }

    public void ClickTrips(View view) {
        //Redirect activity to Trips
        MainActivity.redirectActivity(this,MainActivity.class);
    }

    public void ClickSaved(View view) {
        //Redirect activity to Saved
        MainActivity.redirectActivity(this,SavedActivity.class);
    }

    public void ClickSaved2(View view) {
        //Redirect activity to Saved2
        MainActivity.redirectActivity(this,Saved2Activity.class);
    }

    public void ClickSaved3(View view) {
        //Recreate activity
        recreate();
    }

    public void ClickAboutUs(View view) {
        //Redirect activity to About us
        MainActivity.redirectActivity(this,AboutUsActivity.class);
    }

    public void ClickLogout(View view) {
        //Close app
        MainActivity.logout(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Close drawer
        MainActivity.closeDrawer(drawerLayout);
    }
}