package com.example.vizzu.maps.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Timestamp;

/**
 * Created by vizzu on 9/18/2016.
 */
@Table(name = "Bookings")
public class Booking extends Model{

    @Column(name = "passenger_id")
    private long passengerId;

    @Column(name = "from_latitude")
    private double fromLatitude;

    @Column(name = "from_longitude")
    private double fromLongitude;

    @Column(name = "to_latitude")
    private double toLatitude;

    @Column(name = "to_longitude")
    private double toLongitude;

    @Column(name = "selected_start_time")
    private Timestamp selectedStartTime;

    @Column(name = "actual_start_time")
    private Timestamp actualStartTime;

    @Column(name = "ride_id")
    private Ride ride;

    @Column(name = "fare")
    private int fare;

    public Booking() {
        super();
    }

    public Booking(long passengerId, double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, Timestamp selectedStartTime, Timestamp actualStartTime, Ride ride, int fare) {
        this.passengerId = passengerId;
        this.fromLatitude = fromLatitude;
        this.fromLongitude = fromLongitude;
        this.toLatitude = toLatitude;
        this.toLongitude = toLongitude;
        this.selectedStartTime = selectedStartTime;
        this.actualStartTime = actualStartTime;
        this.ride = ride;
        this.fare = fare;
    }

    public long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(long passengerId) {
        this.passengerId = passengerId;
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

    public Timestamp getSelectedStartTime() {
        return selectedStartTime;
    }

    public void setSelectedStartTime(Timestamp selectedStartTime) {
        this.selectedStartTime = selectedStartTime;
    }

    public Timestamp getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Timestamp actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public int getFare() {
        return fare;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }
}
