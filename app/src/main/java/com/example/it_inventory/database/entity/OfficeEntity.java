package com.example.it_inventory.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity (tableName = "offices")
public class OfficeEntity {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id")
    private long id;
    @ColumnInfo(name = "Floor")
    private int floor;
    @ColumnInfo (name = "Building")
    private String building;
    @ColumnInfo (name = "Sector")
    private String sector;
    @ColumnInfo (name = "City")
    private String city;
    @ColumnInfo (name = "Country")
    private String country;
    @ColumnInfo (name = "IdWorkstation")
    private int idWorkstation;

    public OfficeEntity(int floor, String building, String sector, String city, String country) {
        this.floor = floor;
        this.building = building;
        this.sector = sector;
        this.city = city;
        this.country = country;
        //this.idWorkstation = idWorkstation;
    }

    public long getId() {
        return id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getIdWorkstation() {
        return idWorkstation;
    }

    public void setIdWorkstation(int idWorkstation) {
        this.idWorkstation = idWorkstation;
    }
}
