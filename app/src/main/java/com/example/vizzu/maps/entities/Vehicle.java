package com.example.vizzu.maps.entities;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Bhargav.Janjam on 11/26/2016.
 */
@Table(name = "vehicle")
public class Vehicle extends Model{

    @Column(name = "vehicle_no",unique = true)
    private String vehicleNo;

    @Column(name = "capacity")
    private String capacity;

    @Column(name = "owner_id")
    private User user;

    public Vehicle(String vehicleNo, String capacity, User user) {
        this.vehicleNo = vehicleNo;
        this.capacity = capacity;
        this.user = user;
    }

    public Vehicle() {
        super();
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
