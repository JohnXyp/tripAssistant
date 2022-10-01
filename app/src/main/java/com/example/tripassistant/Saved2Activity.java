package com.example.tripassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.tripassistant.RoomData.DB_SuggestedTrip;
import com.example.tripassistant.RoomData.SuggestedTrip;
import com.example.tripassistant.adapter.Saved2Adapter;

import java.util.ArrayList;
import java.util.List;

public class Saved2Activity extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    //Recycler View
    RecyclerView suggestedTrip_recycler;

    private List<SuggestedTrip> suggestedTripList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved2);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        suggestedTrip_recycler = findViewById(R.id.suggestedTrip_recycler);
        suggestedTrip_recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData2();
    }

    private void getData2() {
        suggestedTripList = new ArrayList<>();
        suggestedTripList = DB_SuggestedTrip.getInstanceSuggestedTrip(this).SuggestedTripDao().getSuggestedTrip();

        suggestedTrip_recycler.setAdapter(new Saved2Adapter(getApplicationContext(), suggestedTripList, new Saved2Adapter.DeleteItemClickListener2() {
            @Override
            public void onItemDelete2(int position, int id) {
                DB_SuggestedTrip.getInstanceSuggestedTrip(getApplicationContext()).SuggestedTripDao().deleteSuggestedTrip(id);
                getData2();
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
        //Recreate activity
        recreate();
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