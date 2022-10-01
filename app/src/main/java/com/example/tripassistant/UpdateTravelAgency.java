package com.example.tripassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.tripassistant.RoomData.DB_TravelAgency;

public class UpdateTravelAgency extends AppCompatActivity {
    //Edit Button
    Button edit;

    //Radio group (Travel Agencies)
    RadioGroup rg_travelAgencies;
    RadioButton rb_Oik;
    RadioButton rb_Xypt;
    RadioButton rb_Panag;

    String id;
    /* ****************TravelAgency********************* */
    String TravelAgencyName = "";
    String TravelAgencyAddress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_travel_agency);

        //Edit Button
        edit = (Button) findViewById(R.id.btn_update_ta);

        rg_travelAgencies = (RadioGroup) findViewById(R.id.travel_agencies_update); //Radio group (Travel Agencies)
        rb_Oik = (RadioButton) findViewById(R.id.oik_update);
        rb_Xypt = (RadioButton) findViewById(R.id.xypt_update);
        rb_Panag = (RadioButton) findViewById(R.id.panag_update);

        id = getIntent().getExtras().getString("id");

        //Save Data:
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
    }

    public void updateData() {
        /* ****************TravelAgency********************* */
        if (rb_Oik.isChecked()) {
            TravelAgencyName = "Oikonomidis Tours";
            TravelAgencyAddress = "Kleanthous 26";
            //hotel = "Oikonomidis Apartments";
        } else if (rb_Xypt.isChecked()) {
            TravelAgencyName = "Xypterakis Tourist Organization";
            TravelAgencyAddress = "Papandreou 33";
            //hotel = "Xypterakis Hotels";
        } else if (rb_Panag.isChecked()) {
            TravelAgencyName = "Panagiotakopoulos Travel Agency";
            TravelAgencyAddress = "Kolomvou 97";
            //hotel = "Panagiotakopoulos Rooms";
        }

        DB_TravelAgency.getInstanceTravelAgency(this).TravelAgencyDao().updateTravelAgency(TravelAgencyName,TravelAgencyAddress,Integer.parseInt(id));
        finish();

        //Edit Message
        Toast.makeText(this, "Travel Agency successfully updated!", Toast.LENGTH_LONG).show();
    }
}