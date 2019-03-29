package com.example.it_inventory.database.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

// attribution of a foreignkey --> link with the offices
@Entity (tableName = "workstations", foreignKeys = @ForeignKey(entity = OfficeEntity.class,
                                                               parentColumns = "id",
                                                               childColumns = "officeId" , onDelete = ForeignKey.CASCADE))
public class WorkstationEntity {

    // Workstation parameters
    @PrimaryKey (autoGenerate = true)
    private long id ;
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
    @ColumnInfo(name = "officeId")
    private long officeId ;


    // Constructor by default : ignored
    @Ignore
    public WorkstationEntity(){
    }

    // WorkstationEntity: constructor
    public WorkstationEntity(boolean screens, boolean portable, String os, int ram, int storage,
                              String processor, String keyboardType, long officeId ){
        this.screens = screens ;
        this.portable = portable ;
        this.os = os ;
        this.ram = ram ;
        this.storage = storage ;
        this.processor = processor ;
        this.keyboardType = keyboardType ;
        this.officeId = officeId ;
    }

    // Getters and setters
    public long getId(){
        return id ;
    }

    public void setId(long id){
        this.id = id ;
    }

    public boolean isScreens() {
        return screens;
    }

    public void setScreens(boolean screens) {
        this.screens = screens;
    }

    public boolean isPortable() {
        return portable;
    }

    public void setPortable(boolean portable) {
        this.portable = portable;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getRam() {
        return ram;
    }

    public String getRamString(){ return Integer.toString(ram);}

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getStorageString(){return Integer.toString(storage);}

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getKeyboardType() {
        return keyboardType;
    }

    public void setKeyboardType(String keyboardType) {
        this.keyboardType = keyboardType;
    }

    public long getOfficeId(){
        return officeId ;
    }
    public void setOfficeId(long officeId){
        this.officeId = officeId ;
    }
}
