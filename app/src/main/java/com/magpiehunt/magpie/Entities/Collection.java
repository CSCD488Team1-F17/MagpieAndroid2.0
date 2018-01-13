package com.magpiehunt.magpie.Entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by James on 1/10/2018.
 */

@Entity(tableName = "Collections")
public class Collection {

    @PrimaryKey
    private int CID;
    private boolean available;
    private String name;
    private String city;
    private String state;
    private int zipCode;
    private String rating;
    private String description;
    private boolean ordered;
    private String abbreviation;

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}
/*
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
 */