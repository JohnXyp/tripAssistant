package com.example.tripassistant;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;
import com.example.tripassistant.RoomData.DB_TripPackage;

public class UpdateTripPackage extends AppCompatActivity {
    //Edit Button
    Button edit;

    CalendarView cv_date;

    /* ****************TripPackage********************* */
    String DepartureDate = "";

    String id3;
    String ta_id;
    String st_id;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_trip_package);

        //Edit Button
        cv_date = (CalendarView)findViewById(R.id.date_update);
        edit = (Button) findViewById(R.id.btn_update_tp);

        price = getIntent().getExtras().getString("price");
        ta_id = getIntent().getExtras().getString("ta_id");
        st_id = getIntent().getExtras().getString("st_id");
        id3 = getIntent().getExtras().getString("id3");


        //CalendarView:
        cv_date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                DepartureDate = (i1 + 1) + "/" + i2 + "/" + i;
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData3();
            }
        });
    }

    public void updateData3() {
        /* ****************TripPackage********************* */
        DB_TripPackage.getInstanceTripPackage(this).TripPackageDao().updateTripPackage(DepartureDate,price,Integer.parseInt(ta_id),Integer.parseInt(st_id),Integer.parseInt(id3));
        finish();

        //Edit Message
        Toast.makeText(this, "Trip Package successfully updated!", Toast.LENGTH_LONG).show();
    } }