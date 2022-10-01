package com.example.tripassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PragueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prague);
    }

    //Back to MainActivity
    public void ReturnBack(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    //Open Form
    public void OpenForm(View view) {
        startActivity(new Intent(this, FormActivity.class));
    }

    //Open Map
    public void pragueMap(View view) {
        startActivity(new Intent(this, PragueMap.class));
    }
}