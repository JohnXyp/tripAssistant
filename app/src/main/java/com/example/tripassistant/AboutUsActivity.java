package com.example.tripassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

public class AboutUsActivity extends AppCompatActivity {
    //Initialize variable
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //Assign variable
        drawerLayout = findViewById(R.id.drawer_layout);
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
        //Redirect activity to Saved3
        MainActivity.redirectActivity(this,Saved3Activity.class);
    }

    public void ClickAboutUs(View view) {
        //Recreate activity
        recreate();
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