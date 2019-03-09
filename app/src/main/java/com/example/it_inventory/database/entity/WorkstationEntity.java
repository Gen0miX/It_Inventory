package com.example.it_inventory.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class WorkstationEntity {
    @PrimaryKey
    @NonNull
    private int id ;
    @ColumnInfo(name = "Screens")
    private boolean screens;
    @ColumnInfo (name = "Portable")
    private boolean portable;
    @ColumnInfo (name = "OS")
    private String os;
    @ColumnInfo (name = "RAM")
    private int ram;
    @ColumnInfo (name = "Storage")
    private int storage;
    @ColumnInfo (name = "Processor")
    private String processor;
    @ColumnInfo (name = "Keyboard_Type")
    private String keyboardType;

}
