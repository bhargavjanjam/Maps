package com.example.vizzu.maps;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.AvoidType;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.example.vizzu.maps.entities.User;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Calendar;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener {

    private GoogleMap mMap;

    private LatLng fromLocation;

    private LatLng toLocation;

//    Button btnDatePicker, btnTimePicker;
//    EditText txtDate, txtTime;
    TextView  txtDate,txtTime,txtCarDet;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        User user = new User();
        user.setEmailId("bhargavjanjam@gmail.com");
        user.setFirstName("Bhargav");
        user.setLastName("Janjam");
        user.save();
        setContentView(R.layout.activity_maps);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.rs5);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

//        btnDatePicker=(Button)findViewById(R.id.btn_date);
//        btnTimePicker=(Button)findViewById(R.id.btn_time);
//        txtDate=(EditText)findViewById(R.id.in_date);
//       txtTime=(EditText)findViewById(R.id.in_time);
//
//        btnDatePicker.setOnClickListener(this);
//        btnTimePicker.setOnClickListener(this);

        txtDate=(TextView)findViewById(R.id.in_date);
        txtTime=(TextView)findViewById(R.id.in_time);
        txtCarDet=(TextView)findViewById(R.id.car_details);

        Button type = (Button) findViewById(R.id.riders);
        type.setPressed(true);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        PlaceAutocompleteFragment fromAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.from);
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        fromAutocompleteFragment.setFilter(typeFilter);

        PlaceAutocompleteFragment toAutocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.to);
        fromAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                fromLocation = place.getLatLng();
            }

            @Override
            public void onError(Status status) {
            }
        });
        toAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                toLocation = place.getLatLng();
                if(fromLocation != null && toLocation != null) {
                    GoogleDirection.withServerKey("AIzaSyAiBuawk3lJaNxPBNBBuSOpfXuIWvmfiVU")
                            .from(fromLocation)
                            .to(toLocation)
                            .avoid(AvoidType.FERRIES)
                            .transportMode(TransportMode.DRIVING)
                            .execute(new DirectionCallback() {

                                @Override
                                public void onDirectionSuccess(Direction direction, String rawBody) {
                                    if (direction.isOK()) {
                                        mMap.clear();
                                        ArrayList<LatLng> latLngs = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
                                        PolylineOptions polylineOptions = DirectionConverter.createPolyline(getApplicationContext(), latLngs, 5, Color.RED);
                                        mMap.addPolyline(polylineOptions);
                                        mMap.addMarker(new MarkerOptions().position(fromLocation));
                                        mMap.addMarker(new MarkerOptions().position(toLocation));
                                        mMap.moveCamera(CameraUpdateFactory.newLatLng(fromLocation));
                                        return;
                                    }
                                    else {
                                        // Do something
                                    }
                                }

                                @Override
                                public void onDirectionFailure(Throwable t) {
                                    // Do something
                                }
                            });
                }
            }

            @Override
            public void onError(Status status) {
            }
        });


    }

    public void changeType(View view){

        Button viewBtn = ((Button) view);
        String buttonName = viewBtn.getText().toString();
        viewBtn.setPressed(true);
        Button type = (Button) findViewById(R.id.type);
        if(buttonName.equals("Riders")) {
            type.setText("Offer a Ride");
            ((Button) findViewById(R.id.passengers)).setPressed(false);
        }else{
            type.setText("Find a Ride");
            ((Button) findViewById(R.id.riders)).setPressed(false);
        }
    }
    public void execute(View view){
        Button viewBtn = ((Button) view);
        if((fromLocation == null && toLocation == null )||(fromLocation.equals("")&& toLocation.equals("")))

            Toast.makeText(this, "Enter start and end address", Toast.LENGTH_LONG).show();
        else if(toLocation == null || toLocation.equals(""))
        {
            Toast.makeText(this, "Enter end address", Toast.LENGTH_LONG).show();
        }
        else if(fromLocation == null || fromLocation.equals(""))
        {
            Toast.makeText(this, "Enter start address", Toast.LENGTH_LONG).show();
        }
        else if(viewBtn.getText().equals("Find a Ride")){
            Intent passIntent = new Intent(MapsActivity.this, PassengerOptions.class);
            Bundle args = new Bundle();
            args.putParcelable("from_position", fromLocation);
            args.putParcelable("to_position", toLocation);
            passIntent.putExtra("bundle", args);
            startActivity(passIntent);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        final LatLng start = new LatLng(37.7681994, -122.444538);
        final LatLng end = new LatLng(37.7749003,-122.4034934);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onClick(View v) {

        if (v == txtDate) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();


        }
        if (v ==txtTime) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,int minute)
                        {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if(v ==txtCarDet)
        {
             startActivity(new Intent(MapsActivity.this,CarDetailsPopupActivity.class));
        }

    }

}
