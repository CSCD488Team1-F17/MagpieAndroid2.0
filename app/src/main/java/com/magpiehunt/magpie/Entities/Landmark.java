package com.magpiehunt.magpie.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by James on 1/10/2018.
 */

@Entity(tableName = "Landmarks", foreignKeys = @ForeignKey(
                                            entity = Collection.class,
                                            parentColumns = "CID",
                                            childColumns = "CID"))
public class Landmark {

    @PrimaryKey
    private int LID;
    private int CID;
    private String LandmarkName;
    private double latitude;
    private double longitude;
    private String landmarkDescription;
    private String QRCode;
    private int picID;
    private int badgeID;
    private int orderNum;

    public int getLID() {
        return LID;
    }

    public void setLID(int LID) {
        this.LID = LID;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public String getLandmarkName() {
        return LandmarkName;
    }

    public void setLandmarkName(String landmarkName) {
        LandmarkName = landmarkName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLandmarkDescription() {
        return landmarkDescription;
    }

    public void setLandmarkDescription(String landmarkDescription) {
        this.landmarkDescription = landmarkDescription;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public int getPicID() {
        return picID;
    }

    public void setPicID(int picID) {
        this.picID = picID;
    }

    public int getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(int badgeID) {
        this.badgeID = badgeID;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

}
/*
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
 */