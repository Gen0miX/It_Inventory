package com.example.it_inventory.database.entity;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;


// office entity
public class OfficeEntity {

    // Office Entity parameters
    private String id;
    private int floor;
    private String building;
    private String sector;
    private String city;
    private String country;

    // Constructor
    public OfficeEntity(){
    }

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
