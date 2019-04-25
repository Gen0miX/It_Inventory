package com.example.it_inventory.database.entity;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

// Workstation Entity
public class WorkstationEntity {

    // Workstation Entity : parameters
    private String id ;
    private boolean screens;
    private boolean portable;
    private String os;
    private Double ram;
    private Double storage;
    private String processor;
    private String keyboardType;
    private String officeId;

    public WorkstationEntity(){
    }

    // getters and setters

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getRam() {
        return ram;
    }

    public void setRam(Double ram) {
        this.ram = ram;
    }

    public Double getStorage() {
        return storage;
    }

    public void setStorage(Double storage) {
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

    public String getOfficeId(){
        return officeId;
    }

    public void setOfficeId(String officeId){
        this.officeId = officeId ;
    }

    public String getRamString(){ return ram.toString(); }

    public String getStorageString(){ return storage.toString(); }


    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof WorkstationEntity)) return false;
        WorkstationEntity o = (WorkstationEntity) obj;
        return o.getId().equals(this.getId());
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("screens", screens);
        result.put("portable", portable);
        result.put("os", os);
        result.put("ram", ram);
        result.put("storage", storage);
        result.put("processor", processor);
        result.put("keyboardType", keyboardType);

        return result;
    }
}
