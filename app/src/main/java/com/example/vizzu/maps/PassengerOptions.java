package com.example.vizzu.maps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.vizzu.maps.entities.Ride;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bhargav.Janjam on 11/26/2016.
 */
public class PassengerOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent thisIntent = getIntent();
        Bundle bundle = thisIntent.getParcelableExtra("bundle");
        LatLng fromPosition = bundle.getParcelable("from_position");
        LatLng toPosition = bundle.getParcelable("to_position");
        Ride ride = findRidesBetween(fromPosition, toPosition);
        if(ride == null){
            Toast.makeText(this, "No Ride Found", Toast.LENGTH_LONG).show();
        }
    }

    Ride findRidesBetween(LatLng fromLocation, LatLng toLocation){
        List<Ride> ongoingRides = new Select().from(Ride.class).where("status <> ?","completed").execute();
        if(ongoingRides != null && !ongoingRides.isEmpty()) {
            for(Ride ride : ongoingRides){
                LatLng from = new LatLng(ride.getFromLatitude(), ride.getFromLongitude());
                LatLng to = new LatLng(ride.getToLatitude(), ride.getToLongitude());
                List<LatLng> route = new ArrayList<>();
                route.add(fromLocation);
                route.add(toLocation);
                if(PolyUtil.isLocationOnPath(fromLocation, route, true) &&
                PolyUtil.isLocationOnPath(toLocation, route, true)){
                    return ride;
                }
            }
        }
        return null;
    }
}
