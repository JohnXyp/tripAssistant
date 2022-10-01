package com.example.tripassistant;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.tripassistant.RoomData.DB_SuggestedTrip;

public class UpdateSuggestedTrip extends AppCompatActivity {
    //Edit Button
    Button edit;

    //radio group (Trips)
    RadioGroup rg_trips;
    RadioButton rb_venice;
    RadioButton rb_vienna;
    RadioButton rb_prague;
    RadioButton rb_berlin;
    RadioButton rb_barcelona;
    RadioButton rb_paris;
    EditText et_duration;


    /* ****************SuggestedTrip********************* */
    int SuggestedTripCode = 0;
    String SuggestedTripCity = "";
    String SuggestedTripCountry = "";
    String SuggestedTripDuration = "";
    String SuggestedTripKind = "";

    String id2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_suggested_trip);

        //Edit Button
        edit = (Button) findViewById(R.id.btn_update_st);

        rg_trips = (RadioGroup) findViewById(R.id.trips_update); //Radio group (Trips)
        rb_venice = (RadioButton) findViewById(R.id.venice_update);
        rb_vienna = (RadioButton) findViewById(R.id.vienna_update);
        rb_prague = (RadioButton) findViewById(R.id.prague_update);
        rb_berlin = (RadioButton) findViewById(R.id.berlin_update);
        rb_barcelona = (RadioButton) findViewById(R.id.barcelona_update);
        rb_paris = (RadioButton) findViewById(R.id.paris_update);
        et_duration = (EditText) findViewById(R.id.days_update);
        et_duration.setFilters(new InputFilter[]{new MinMaxFilter("2", "6")});

        id2 = getIntent().getExtras().getString("id2");

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData2();
            }
        });
    }

    public void updateData2() {
        /* ****************SuggestedTrip********************* */
        SuggestedTripDuration = et_duration.getText().toString().trim();

        if (rb_venice.isChecked()) {
            SuggestedTripCity = "Venice";
            SuggestedTripCountry = "Italy";
            SuggestedTripKind = "Cruise";
        } else if (rb_vienna.isChecked()) {
            SuggestedTripCity = "Vienna";
            SuggestedTripCountry = "Austria";
            SuggestedTripKind = "Road Trip";
        } else if (rb_prague.isChecked()) {
            SuggestedTripCity = "Prague";
            SuggestedTripCountry = "Czech Republic";
            SuggestedTripKind = "Road Trip";
        } else if (rb_berlin.isChecked()) {
            SuggestedTripCity = "Berlin";
            SuggestedTripCountry = "Germany";
            SuggestedTripKind = "Road Trip";
        } else if (rb_barcelona.isChecked()) {
            SuggestedTripCity = "Barcelona";
            SuggestedTripCountry = "Spain";
            SuggestedTripKind = "Road Trip";
        } else if (rb_paris.isChecked()) {
            SuggestedTripCity = "Paris";
            SuggestedTripCountry = "France";
            SuggestedTripKind = "Road Trip";
        }

        DB_SuggestedTrip.getInstanceSuggestedTrip(this).SuggestedTripDao().updateSuggestedTrip(SuggestedTripCity,SuggestedTripCountry,SuggestedTripDuration,SuggestedTripKind,Integer.parseInt(id2));
        finish();

        //Edit Message
        Toast.makeText(this, "Suggested Trip successfully updated!", Toast.LENGTH_LONG).show();
    }
}