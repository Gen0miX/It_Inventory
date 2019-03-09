package com.example.it_inventory.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class OfficeEntity {
    @PrimaryKey
    @NonNull
    private int id;
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
}
