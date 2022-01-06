package com.example.smartphoneorientation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sensor.db";
    public static final String TABLE_NAME = "accelerometer";

    public static final String COL_1 = "aXValue";
    public static final String COL_2 = "aYValue";
    public static final String COL_3 = "aZValue";
    //public static final String COL_4 = "gXValue";
    //public static final String COL_5 = "gYValue";
    //public static final String COL_6 = "gZValue";
    public static final String COL_4 = "mXValue";
    public static final String COL_5 = "mYValue";
    public static final String COL_6 = "mZValue";
    public static final String COL_7 = "latitude";
    public static final String COL_8 = "longitude";
    public static final String COL_9 = "timestamp";
    public static final String COL_10 = "rXValue";
    public static final String COL_11 = "rYValue";
    public static final String COL_12 = "rZValue";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("create table " + TABLE_NAME + " (aXValue DECIMAL(2,5),aYValue DECIMAL(2,5),aZValue DECIMAL(2,5),mXValue DECIMAL(2,5),mYValue DECIMAL(2,5),mZValue DECIMAL(2,5),latitude DECIMAL(2,5),longitude DECIMAL(2,5),timestamp BIGINT PRIMARY KEY, rXValue DECIMAL(2,5),rYValue DECIMAL(2,5),rZValue DECIMAL(2,5))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean saveDimensions(float aX, float aY, float aZ, float mX, float mY, float mZ, float rX, float rY, float rZ, double latitude, double longitude) {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault()).format(new Date());
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("timestamp", timeStamp + ":" + System.currentTimeMillis());
        contentValues.put("aXValue", aX);
        contentValues.put("aYValue", aY);
        contentValues.put("aZValue", aZ);
        //contentValues.put("gXValue", gX);
        //contentValues.put("gYValue", gY);
        //contentValues.put("gZValue", gZ);
        contentValues.put("mXValue", mX);
        contentValues.put("mYValue", mY);
        contentValues.put("mZValue", mZ);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude", longitude);
        contentValues.put("rXValue", rX);
        contentValues.put("rYValue", rY);
        contentValues.put("rZValue", rZ);
        long check = db.insert(TABLE_NAME, null, contentValues);
        if (check == -1) {
            Log.d("TEST", "FAIL");
        }
        return true;
    }
    public Cursor raw() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME , new String[]{});

        return res;
    }

    public void delete(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}