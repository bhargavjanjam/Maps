package com.example.vizzu.maps.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
 * Created by vizzu on 9/18/2016.
 */
@Table(name = "Rides")
public class Ride extends Model{

    @Column(name = "rider_id")
    private long riderId;

    @Column(name = "from_latitude")
    private double fromLatitude;

    @Column(name = "from_longitude")
    private double fromLongitude;

    @Column(name = "to_latitude")
    private double toLatitude;

    @Column(name = "to_longitude")
    private double toLongitude;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "fare")
    private int fare;

    public Ride() {
        super();
    }

    public Ride(long riderId, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude ,Timestamp startTime , int fare) {
        this.riderId = riderId;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.startTime = startTime;
        this.fare = fare;
    }


    public double getFromLatitude() {
        return fromLatitude;
    }

    public void setFromLatitude(double fromLatitude) {
        this.fromLatitude = fromLatitude;
    }

    public double getFromLongitude() {
        return fromLongitude;
    }

    public void setFromLongitude(double fromLongitude) {
        this.fromLongitude = fromLongitude;
    }

    public double getToLatitude() {
        return toLatitude;
    }

    public void setToLatitude(double toLatitude) {
        this.toLatitude = toLatitude;
    }

    public double getToLongitude() {
        return toLongitude;
    }

    public void setToLongitude(double toLongitude) {
        this.toLongitude = toLongitude;
    }

    public long getRiderId() {
        return riderId;
    }

    public void setRiderId(long riderId) {
        this.riderId = riderId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }
}
