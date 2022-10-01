package com.example.tripassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.tripassistant.RoomData.DB_TravelAgency;
import com.example.tripassistant.RoomData.TravelAgency;
import com.example.tripassistant.adapter.SavedAdapter;

import java.util.ArrayList;
import java.util.List;

public class SavedActivity extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    //Recycler View
    RecyclerView travelAgency_recycler;

    private List<TravelAgency> travelAgencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        travelAgency_recycler = findViewById(R.id.travelAgency_recycler);
        travelAgency_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        travelAgencyList = new ArrayList<>();
        travelAgencyList = DB_TravelAgency.getInstanceTravelAgency(this).TravelAgencyDao().getTravelAgency();
        travelAgency_recycler.setAdapter(new SavedAdapter(getApplicationContext(), travelAgencyList, new SavedAdapter.DeleteItemClickListener() {
            @Override
            public void onItemDelete(int position, int id) {
                DB_TravelAgency.getInstanceTravelAgency(getApplicationContext()).TravelAgencyDao().deleteTravelAgency(id);
                getData();
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
        //Recreate activity
        recreate();
    }

    public void ClickSaved2(View view) {
        //Redirect activity to Saved2
        MainActivity.redirectActivity(this,Saved2Activity.class);
    }

    public void ClickSaved3(View view) {
        //Redirect activity to Saved3
        MainActivity.redirectActivity(this,Saved3Activity.class);
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