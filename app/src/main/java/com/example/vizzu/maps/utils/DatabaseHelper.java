package com.example.vizzu.maps.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vizzu on 9/6/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Ride_share.db";
    public static final String USERS_TABLE_NAME="users";
    public static final String USERS_COL_USER_ID="id";
    public static final String USERS_COL_FIRST_NAME="first_name";
    public static final String USERS_COL_LAST_NAME="last_name";
    public static final String RIDES_TABLE_NAME="rides";
    public static final String RIDES_COL_RIDE_ID="id";
    public static final String RIDES_COL_RIDER="rider_id";
    public static final String RIDES_COL_FROM_LAT="from_lat";
    public static final String RIDES_COL_FROM_LONG="from_long";
    public static final String RIDES_COL_TO_LAT="to_lat";
    public static final String RIDES_COL_TO_LONG="to_long";
    public static final String RIDES_COL_START_TIME="start_time";
    public static final String RIDES_COL_FARE="fare";
    public static final String BOOKINGS_TABLE_NAME="bookings";
    public static final String BOOKINGS_COL_BOOKING_ID ="id";
    public static final String BOOKINGS_COL_PASSENGER_ID="passenger_id";
    public static final String BOOKINGS_COL_RIDE="ride_id";
    public static final String BOOKINGS_COL_FROM_LAT="from_lat";
    public static final String BOOKINGS_COL_FROM_LONG="from_long";
    public static final String BOOKINGS_COL_TO_LAT="to_lat";
    public static final String BOOKINGS_COL_TO_LONG="to_long";
    public static final String BOOKINGS_COL_START_TIME="start_time";
    public static final String BOOKINGS_COL_FARE="fare";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + USERS_TABLE_NAME + "("+USERS_COL_USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USERS_COL_FIRST_NAME+" TEXT,"+USERS_COL_LAST_NAME+" TEXT,PHNO NUMBER)");
        db.execSQL("create table " + BOOKINGS_TABLE_NAME + "("+ BOOKINGS_COL_BOOKING_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+BOOKINGS_COL_PASSENGER_ID+" INTEGER,"+ BOOKINGS_COL_RIDE+" INTEGER, "+BOOKINGS_COL_FROM_LAT+" NUMERIC,"+BOOKINGS_COL_FROM_LONG+" NUMERIC," +
                BOOKINGS_COL_TO_LAT+" NUMERIC, "+BOOKINGS_COL_TO_LONG+" NUMERIC, "+BOOKINGS_COL_START_TIME+" DATETIME, "+BOOKINGS_COL_FARE+" NUMERIC)");
        db.execSQL("create table " + RIDES_TABLE_NAME + "("+RIDES_COL_RIDE_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+RIDES_COL_RIDER+" INTEGER,"+RIDES_COL_FROM_LAT+" NUMERIC,"+RIDES_COL_FROM_LONG+" NUMERIC," +
                RIDES_COL_TO_LAT+" NUMERIC, "+RIDES_COL_TO_LONG+" NUMERIC, "+RIDES_COL_START_TIME+" DATETIME, "+RIDES_COL_FARE+" NUMERIC)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
