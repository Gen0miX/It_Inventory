package com.example.it_inventory.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.it_inventory.database.entity.OfficeEntity;

import java.util.List;

@Dao
public interface OfficeDao {
    @Query("SELECT * FROM OfficeEntity")
    List<OfficeEntity> getAll();
}