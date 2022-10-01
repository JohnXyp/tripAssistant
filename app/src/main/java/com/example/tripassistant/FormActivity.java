package com.example.tripassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripassistant.RoomData.DB_SuggestedTrip;
import com.example.tripassistant.RoomData.DB_TravelAgency;
import com.example.tripassistant.RoomData.DB_TripPackage;
import com.example.tripassistant.RoomData.SuggestedTrip;
import com.example.tripassistant.RoomData.TravelAgency;
import com.example.tripassistant.RoomData.TripPackage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {
    //Price Button
    Button price;
    //Submit Button
    Button save;
    //Clear Button
    Button clear;
    //username, phone, gmail:
    EditText et_username;
    EditText et_phone;
    EditText et_gmail;
    //Radio group (Travel Agencies)
    RadioGroup rg_travelAgencies;
    RadioButton rb_Oik;
    RadioButton rb_Xypt;
    RadioButton rb_Panag;
    //radio group (Trips)
    RadioGroup rg_trips;
    RadioButton rb_venice;
    RadioButton rb_vienna;
    RadioButton rb_prague;
    RadioButton rb_berlin;
    RadioButton rb_barcelona;
    RadioButton rb_paris;
    EditText et_duration;

    CalendarView cv_date;
    TextView tv_price;

    /* ****************TravelAgency********************* */
    String TravelAgencyName = "";
    String TravelAgencyAddress = "";
    /* ****************SuggestedTrip********************* */
    String SuggestedTripCity = "";
    String SuggestedTripCountry = "";
    String SuggestedTripDuration = "";
    String SuggestedTripKind = "";
    /* ****************TripPackage********************* */
    static int travelAgencyCode;
    static int suggestedTripCode;
    String DepartureDate = "";
    String PackagePrice = "";

    //Firestore
    private static final String TAG = "FormActivity";
    private static final String Username_Title = "Username";
    private static final String Gmail_Title = "Gmail";
    private static final String Phone_Title = "Phone";
    private static final String Hotel_Title = "Hotel";
    String username = "";
    String gmail = "";
    String phone = "";
    String hotel = "";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        //Price Button
        price = (Button) findViewById(R.id.btnPrice);
        //Submit Button
        save = (Button) findViewById(R.id.submit);
        //Clear Button
        clear = (Button) findViewById(R.id.clear);
        //Username, phone, gmail:
        et_username = (EditText) findViewById(R.id.username);
        et_phone = (EditText) findViewById(R.id.phone);
        et_gmail = (EditText) findViewById(R.id.gmail);

        rg_travelAgencies = (RadioGroup) findViewById(R.id.travel_agencies); //Radio group (Travel Agencies)
        rb_Oik = (RadioButton) findViewById(R.id.oik);
        rb_Xypt = (RadioButton) findViewById(R.id.xypt);
        rb_Panag = (RadioButton) findViewById(R.id.panag);

        rg_trips = (RadioGroup) findViewById(R.id.trips); //Radio group (Trips)
        rb_venice = (RadioButton) findViewById(R.id.venice);
        rb_vienna = (RadioButton) findViewById(R.id.vienna);
        rb_prague = (RadioButton) findViewById(R.id.prague);
        rb_berlin = (RadioButton) findViewById(R.id.berlin);
        rb_barcelona = (RadioButton) findViewById(R.id.barcelona);
        rb_paris = (RadioButton) findViewById(R.id.paris);
        et_duration = (EditText) findViewById(R.id.days);

        cv_date = (CalendarView) findViewById(R.id.date);
        tv_price = (TextView) findViewById(R.id.price);

        //Days Range [2-6]:
        et_duration.setFilters(new InputFilter[]{new MinMaxFilter("2", "6")});

        //Show Price:
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrice();
            }
        });

        //CalendarView:
        cv_date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                DepartureDate = (i1 + 1) + "/" + i2 + "/" + i;
            }
        });

        //Clear Fields:
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Clear all fields:
                et_username.getText().clear();
                et_phone.getText().clear();
                et_gmail.getText().clear();
                rg_trips.clearCheck();
                rg_travelAgencies.clearCheck();
                et_duration.getText().clear();
                tv_price.setText("");
            }
        });

        //Save Data:
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }

    private void showPrice() {
        SuggestedTripDuration = et_duration.getText().toString().trim();

        //All Possible Prices:
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("2") && rb_venice.isChecked()) {
            tv_price.setText("150$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("3") && rb_venice.isChecked()) {
            tv_price.setText("170$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("4") && rb_venice.isChecked()) {
            tv_price.setText("190$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("5") && rb_venice.isChecked()) {
            tv_price.setText("210$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("6") && rb_venice.isChecked()) {
            tv_price.setText("230$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("2") && rb_vienna.isChecked()) {
            tv_price.setText("300$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("3") && rb_vienna.isChecked()) {
            tv_price.setText("320$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("4") && rb_vienna.isChecked()) {
            tv_price.setText("340$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("5") && rb_vienna.isChecked()) {
            tv_price.setText("360$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("6") && rb_vienna.isChecked()) {
            tv_price.setText("380$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("2") && rb_prague.isChecked()) {
            tv_price.setText("200$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("3") && rb_prague.isChecked()) {
            tv_price.setText("220$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("4") && rb_prague.isChecked()) {
            tv_price.setText("240$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("5") && rb_prague.isChecked()) {
            tv_price.setText("260$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("6") && rb_prague.isChecked()) {
            tv_price.setText("300$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("2") && rb_berlin.isChecked()) {
            tv_price.setText("270$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("3") && rb_berlin.isChecked()) {
            tv_price.setText("290$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("4") && rb_berlin.isChecked()) {
            tv_price.setText("300$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("5") && rb_berlin.isChecked()) {
            tv_price.setText("320$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("6") && rb_berlin.isChecked()) {
            tv_price.setText("340$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("2") && rb_barcelona.isChecked()) {
            tv_price.setText("450$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("3") && rb_barcelona.isChecked()) {
            tv_price.setText("470$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("4") && rb_barcelona.isChecked()) {
            tv_price.setText("490$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("5") && rb_barcelona.isChecked()) {
            tv_price.setText("500$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("6") && rb_barcelona.isChecked()) {
            tv_price.setText("520$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("2") && rb_paris.isChecked()) {
            tv_price.setText("500$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("3") && rb_paris.isChecked()) {
            tv_price.setText("550$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("4") && rb_paris.isChecked()) {
            tv_price.setText("600$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("5") && rb_paris.isChecked()) {
            tv_price.setText("650$");
        }
        if (rb_Oik.isChecked() && SuggestedTripDuration.equals("6") && rb_paris.isChecked()) {
            tv_price.setText("710$");
        }

        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("2") && rb_venice.isChecked()) {
            tv_price.setText("140$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("3") && rb_venice.isChecked()) {
            tv_price.setText("160$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("4") && rb_venice.isChecked()) {
            tv_price.setText("180$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("5") && rb_venice.isChecked()) {
            tv_price.setText("200$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("6") && rb_venice.isChecked()) {
            tv_price.setText("220$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("2") && rb_vienna.isChecked()) {
            tv_price.setText("320$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("3") && rb_vienna.isChecked()) {
            tv_price.setText("340$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("4") && rb_vienna.isChecked()) {
            tv_price.setText("360$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("5") && rb_vienna.isChecked()) {
            tv_price.setText("380$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("6") && rb_vienna.isChecked()) {
            tv_price.setText("400$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("2") && rb_prague.isChecked()) {
            tv_price.setText("200$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("3") && rb_prague.isChecked()) {
            tv_price.setText("230$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("4") && rb_prague.isChecked()) {
            tv_price.setText("260$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("5") && rb_prague.isChecked()) {
            tv_price.setText("290$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("6") && rb_prague.isChecked()) {
            tv_price.setText("340$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("2") && rb_berlin.isChecked()) {
            tv_price.setText("280$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("3") && rb_berlin.isChecked()) {
            tv_price.setText("300$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("4") && rb_berlin.isChecked()) {
            tv_price.setText("320$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("5") && rb_berlin.isChecked()) {
            tv_price.setText("340$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("6") && rb_berlin.isChecked()) {
            tv_price.setText("360$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("2") && rb_barcelona.isChecked()) {
            tv_price.setText("450$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("3") && rb_barcelona.isChecked()) {
            tv_price.setText("460$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("4") && rb_barcelona.isChecked()) {
            tv_price.setText("470$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("5") && rb_barcelona.isChecked()) {
            tv_price.setText("480$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("6") && rb_barcelona.isChecked()) {
            tv_price.setText("510$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("2") && rb_paris.isChecked()) {
            tv_price.setText("510$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("3") && rb_paris.isChecked()) {
            tv_price.setText("530$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("4") && rb_paris.isChecked()) {
            tv_price.setText("560$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("5") && rb_paris.isChecked()) {
            tv_price.setText("600$");
        }
        if (rb_Xypt.isChecked() && SuggestedTripDuration.equals("6") && rb_paris.isChecked()) {
            tv_price.setText("630$");
        }

        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("2") && rb_venice.isChecked()) {
            tv_price.setText("230$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("3") && rb_venice.isChecked()) {
            tv_price.setText("255$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("4") && rb_venice.isChecked()) {
            tv_price.setText("280$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("5") && rb_venice.isChecked()) {
            tv_price.setText("290$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("6") && rb_venice.isChecked()) {
            tv_price.setText("310$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("2") && rb_vienna.isChecked()) {
            tv_price.setText("330$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("3") && rb_vienna.isChecked()) {
            tv_price.setText("350$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("4") && rb_vienna.isChecked()) {
            tv_price.setText("370$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("5") && rb_vienna.isChecked()) {
            tv_price.setText("390$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("6") && rb_vienna.isChecked()) {
            tv_price.setText("420$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("2") && rb_prague.isChecked()) {
            tv_price.setText("280$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("3") && rb_prague.isChecked()) {
            tv_price.setText("290$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("4") && rb_prague.isChecked()) {
            tv_price.setText("300$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("5") && rb_prague.isChecked()) {
            tv_price.setText("320$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("6") && rb_prague.isChecked()) {
            tv_price.setText("340$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("2") && rb_berlin.isChecked()) {
            tv_price.setText("280$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("3") && rb_berlin.isChecked()) {
            tv_price.setText("280$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("4") && rb_berlin.isChecked()) {
            tv_price.setText("300$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("5") && rb_berlin.isChecked()) {
            tv_price.setText("320$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("6") && rb_berlin.isChecked()) {
            tv_price.setText("340$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("2") && rb_barcelona.isChecked()) {
            tv_price.setText("390$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("3") && rb_barcelona.isChecked()) {
            tv_price.setText("420$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("4") && rb_barcelona.isChecked()) {
            tv_price.setText("450$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("5") && rb_barcelona.isChecked()) {
            tv_price.setText("480$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("6") && rb_barcelona.isChecked()) {
            tv_price.setText("550$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("2") && rb_paris.isChecked()) {
            tv_price.setText("540$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("3") && rb_paris.isChecked()) {
            tv_price.setText("580$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("4") && rb_paris.isChecked()) {
            tv_price.setText("620$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("5") && rb_paris.isChecked()) {
            tv_price.setText("650$");
        }
        if (rb_Panag.isChecked() && SuggestedTripDuration.equals("6") && rb_paris.isChecked()) {
            tv_price.setText("700$");
        }
    }

    private void saveData() {
        if (et_username.getText().toString().isEmpty() || et_phone.getText().toString().isEmpty() || et_gmail.getText().toString().isEmpty()
                || rg_trips.getCheckedRadioButtonId() == -1 || rg_travelAgencies.getCheckedRadioButtonId() == -1
                || et_duration.getText().toString().isEmpty() || DepartureDate.equals("")) {
            //Wrong details message.
            Toast.makeText(this, "Submission failed...You must fill all Details!", Toast.LENGTH_LONG).show();
        } else {
            /* ****************TravelAgency********************* */
            if (rb_Oik.isChecked()) {
                TravelAgencyName = "Oikonomidis Tours";
                TravelAgencyAddress = "Kleanthous 26";
                hotel = "Oikonomidis Apartments";
            } else if (rb_Xypt.isChecked()) {
                TravelAgencyName = "Xypterakis Tourist Organization";
                TravelAgencyAddress = "Papandreou 33";
                hotel = "Xypterakis Hotels";
            } else if (rb_Panag.isChecked()) {
                TravelAgencyName = "Panagiotakopoulos Travel Agency";
                TravelAgencyAddress = "Kolomvou 97";
                hotel = "Panagiotakopoulos Rooms";
            }

            TravelAgency travelAgency = new TravelAgency();
            travelAgency.setName(TravelAgencyName);
            travelAgency.setAddress(TravelAgencyAddress);
            DB_TravelAgency.getInstanceTravelAgency(this).TravelAgencyDao().insertTravelAgency(travelAgency);

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

            SuggestedTrip suggestedTrip = new SuggestedTrip();
            suggestedTrip.setCity(SuggestedTripCity);
            suggestedTrip.setCountry(SuggestedTripCountry);
            suggestedTrip.setDuration(SuggestedTripDuration);
            suggestedTrip.setKind(SuggestedTripKind);
            DB_SuggestedTrip.getInstanceSuggestedTrip(this).SuggestedTripDao().insertSuggestedTrip(suggestedTrip);

            /* ****************TripPackage********************* */
            PackagePrice = tv_price.getText().toString().trim();

            travelAgencyCode++;
            suggestedTripCode++;

            TripPackage tripPackage = new TripPackage();
            tripPackage.setPackage_TACode(travelAgencyCode);
            tripPackage.setPackage_STCode(suggestedTripCode);
            tripPackage.setDepartureDate(DepartureDate);
            tripPackage.setPrice(PackagePrice);
            DB_TripPackage.getInstanceTripPackage(this).TripPackageDao().insertTripPackage(tripPackage);

            //Submit Message
            Toast.makeText(this, "Your trip has been successfully submitted!", Toast.LENGTH_LONG).show();

            //Firestore
            username = et_username.getText().toString().trim();
            gmail = et_gmail.getText().toString().trim();
            phone = et_phone.getText().toString().trim();

            Map<String, Object> note = new HashMap<>();

            note.put(Username_Title, username);
            note.put(Gmail_Title, gmail);
            note.put(Phone_Title, phone);
            note.put(Hotel_Title, hotel);

            db.collection("users")
                    .add(note)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

            //Clear all fields:
            et_username.getText().clear();
            et_phone.getText().clear();
            et_gmail.getText().clear();
            rg_trips.clearCheck();
            rg_travelAgencies.clearCheck();
            et_duration.getText().clear();
            tv_price.setText("");

            startActivity(new Intent(this, MainActivity.class));
        }
    }
}