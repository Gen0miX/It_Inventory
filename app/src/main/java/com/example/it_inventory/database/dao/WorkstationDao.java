package com.example.it_inventory.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.it_inventory.database.entity.WorkstationEntity;

import java.util.List;

@Dao
public interface WorkstationDao {
    @Query("SELECT * FROM workstationentity")
    List<WorkstationEntity> getAll();
}
