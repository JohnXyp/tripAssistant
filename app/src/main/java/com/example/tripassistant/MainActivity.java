package com.example.tripassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.example.tripassistant.adapter.RecentsAdapter;
import com.example.tripassistant.adapter.TopPlacesAdapter;
import com.example.tripassistant.model.RecentsData;
import com.example.tripassistant.model.TopPlacesData;

public class MainActivity extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    //Recyclers - Adapters
    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);

        List<RecentsData> recentDataList = new ArrayList<>();

        recentDataList.add(new RecentsData("Venice","Italy","from 150$", R.drawable.venice));
        recentDataList.add(new RecentsData("Vienna","Austria","from 300$", R.drawable.vienna));
        recentDataList.add(new RecentsData("Prague","Czech Republic","from 200$", R.drawable.prague));
        recentDataList.add(new RecentsData("Berlin","Germany","from 280$", R.drawable.berlin));
        recentDataList.add(new RecentsData("Barcelona","Spain","from 450$", R.drawable.barcelona));
        recentDataList.add(new RecentsData("Paris","France","from 510$", R.drawable.paris));

        setRecentRecycler(recentDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();

        topPlacesDataList.add(new TopPlacesData("Paris","France","from 510$", R.drawable.paris));
        topPlacesDataList.add(new TopPlacesData("Venice","Italy","from 150$", R.drawable.venice));
        topPlacesDataList.add(new TopPlacesData("Barcelona","Spain","from 450$", R.drawable.barcelona));

        setTopPlacesRecycler(topPlacesDataList);
    }

    private void setRecentRecycler(List<RecentsData> recentDataList) {
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentDataList);

        recentRecycler.setAdapter(recentsAdapter);
    }

    private void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList) {
        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);

        topPlacesRecycler.setAdapter(topPlacesAdapter);
    }

    public void ClickMenu(View view) {
        //Open drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        //Close drawer layout
        //Check condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            //When drawer is open Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickTrips(View view) {
        //Recreate activity
        recreate();
    }

    public void ClickSaved(View view) {
        //Redirect activity to Saved
        redirectActivity(this,SavedActivity.class);
    }

    public void ClickSaved2(View view) {
        //Redirect activity to Saved2
        redirectActivity(this,Saved2Activity.class);
    }

    public void ClickSaved3(View view) {
        //Redirect activity to Saved3
        redirectActivity(this,Saved3Activity.class);
    }

    public void ClickAboutUs(View view) {
        //Redirect activity to About us
        redirectActivity(this,AboutUsActivity.class);
    }

    public void ClickLogout(View view) {
        //Close app
        logout(this);
    }

    public static void logout(Activity activity) {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Logout message
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to exit ?");

        //Yes button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Exit app
                activity.finishAffinity();
                System.exit(0);
            }
        });
        //No button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Dismiss dialog
                dialogInterface.dismiss();
            }
        });
        //Show message
        builder.show();
    }

    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize intent
        Intent intent  = new Intent(activity, aClass);

        //Set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Start activity
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}