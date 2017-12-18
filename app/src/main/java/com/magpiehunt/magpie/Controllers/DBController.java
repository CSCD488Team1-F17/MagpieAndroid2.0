package com.magpiehunt.magpie.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by James on 12/14/2017.
 */

public class DBController extends SQLiteOpenHelper {

    public DBController(Context context)
    {
        super(context, "magpie.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE Collections(\n" +
                "  CID INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                "  Available BOOL DEFAULT 1,\n" +
                "  Name VARCHAR(100) NOT NULL,\n" +
                "  City VARCHAR(100) DEFAULT \"Spokane\",\n" +
                "  State VARCHAR(100) DEFAULT \"Washington\",\n" +
                "  ZipCode INTEGER DEFAULT 99207,\n" +
                "  Rating VARCHAR(100) DEFAULT \"E\",\n" +
                "  Description VARCHAR(1000) NOT NULL, \n" +
                "  Ordered BOOL DEFAULT 0,\n" +
                "  Abbreviation VARCHAR(4) NOT NULL\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                ")";
        database.execSQL(query);
        query = "CREATE TABLE Landmarks(\n" +
                "  LID INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                "  CID INTEGER NOT NULL,\n" +
                "  LandmarkName VARCHAR(100) NOT NULL,\n" +
                "  Latitude DOUBLE DEFAULT 0.0 NOT NULL,\n" +
                "  Longitude DOUBLE DEFAULT 0.0 NOT NULL,\n" +
                "  LandmarkDescription VARCHAR(1000) NOT NULL,\n" +
                "  QRCode VARCHAR(625) DEFAULT \"{ EMPTY }\",\n" +
                "  PicID INTEGER DEFAULT 0,\n" +
                "  BadgeID INTEGER DEFAULT 0,\n" +
                "  OrderNum INTEGER,\n" +
                "  \n" +
                "  FOREIGN KEY (CID) REFERENCES Collections(CID) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                "  \n" +
                ")";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        String query = "DROP TABLE IF EXISTS Collections";
        database.execSQL(query);
        query = "DROP TABLE IF EXISTS Landmarks";
        database.execSQL(query);
        onCreate(database);
    }

    public void insertCollection(HashMap<String, String> queryValues)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("CID", queryValues.get("CID"));
        values.put("Available", queryValues.get("Available"));
        values.put("Name", queryValues.get("Name"));
        values.put("City", queryValues.get("City"));
        values.put("State", queryValues.get("State"));
        values.put("ZipCode", queryValues.get("ZipCode"));
        values.put("Rating", queryValues.get("Rating"));
        values.put("Description", queryValues.get("Description"));
        values.put("Ordered", queryValues.get("Ordered"));
        values.put("Abbreviation", queryValues.get("Abbreviation"));
        database.insert("Collections", null, values);
        database.close();
    }

    public void insertLandmark(HashMap<String, String> queryValues)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("LID", queryValues.get("LID"));
        values.put("CID", queryValues.get("CID"));
        values.put("LandmarkName", queryValues.get("LandmarkName"));
        values.put("Latitude", queryValues.get("Latitude"));
        values.put("Longitude", queryValues.get("Longitude"));
        values.put("LandmarkDescription", queryValues.get("LandmarkDescription"));
        values.put("QRCode", queryValues.get("QRCode"));
        values.put("PicID", queryValues.get("PicID"));
        values.put("BadgeID", queryValues.get("BadgeID"));
        values.put("OrderNum", queryValues.get("OrderNum"));
        database.insert("Landmarks", null, values);
        database.close();
    }
    public ArrayList<HashMap<String, String>> getAllCollections()
    {
        ArrayList<HashMap<String, String>> CollectionsList;
        CollectionsList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Collections";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("CID", cursor.getString(0));
                map.put("Available", cursor.getString(1));
                map.put("Name", cursor.getString(2));
                map.put("City", cursor.getString(3));
                map.put("State", cursor.getString(4));
                map.put("ZipCode", cursor.getString(5));
                map.put("Rating", cursor.getString(6));
                map.put("Description", cursor.getString(7));
                map.put("Ordered", cursor.getString(8));
                map.put("Abbreviation", cursor.getString(9));
                CollectionsList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return CollectionsList;
    }
    public ArrayList<HashMap<String, String>> getAllLandmarks()
    {
        ArrayList<HashMap<String, String>> LandmarksList;
        LandmarksList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM Landmarks";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("LID", cursor.getString(0));
                map.put("CID", cursor.getString(1));
                map.put("LandmarksName", cursor.getString(2));
                map.put("Latitude", cursor.getString(3));
                map.put("Longitude", cursor.getString(4));
                map.put("LandmarkDescription", cursor.getString(5));
                map.put("QRCode", cursor.getString(6));
                map.put("PicID", cursor.getString(7));
                map.put("BadgeID", cursor.getString(8));
                map.put("OrderNum", cursor.getString(9));
                LandmarksList.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return LandmarksList;
    }



}
