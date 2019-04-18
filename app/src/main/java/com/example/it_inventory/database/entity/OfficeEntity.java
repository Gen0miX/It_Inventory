package com.example.it_inventory.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


//@Entity (tableName = "offices")
public class OfficeEntity {

    // Office Entity parameters
    //@PrimaryKey (autoGenerate = true)
    //@ColumnInfo (name = "id")
    private String id;
    //@ColumnInfo(name = "Floor")
    private int floor;
    //@ColumnInfo (name = "Building")
    private String building;
    //@ColumnInfo (name = "Sector")
    private String sector;
    //@ColumnInfo (name = "City")
    private String city;
    //@ColumnInfo (name = "Country")
    private String country;

    // Constructor by default : ignored
    //@Ignore
    public OfficeEntity(){
    }

    // Constructor
   /* public OfficeEntity(int floor, String building, String sector, String city, String country) {
        this.floor = floor;
        this.building = building;
        this.sector = sector;
        this.city = city;
        this.country = country;
    }*/


    // Getters and Setters
    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id ;
    }

    public int getFloor() {
        return floor;
    }

    public String getFloorString(){ return Integer.toString(floor); }

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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("floor", floor);
        result.put("building", building);
        result.put("sector", sector);
        result.put("city", city);
        result.put("country", country);

        return result;

    }

}
