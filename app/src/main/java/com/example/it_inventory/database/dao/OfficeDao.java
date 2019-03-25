package com.example.it_inventory.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.sqlite.SQLiteConstraintException;

import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.database.entity.WorkstationEntity;

import java.util.List;

@Dao
public interface OfficeDao {


    @Insert
    void insert(OfficeEntity officeEntity) throws SQLiteConstraintException;

    @Update
    void update(OfficeEntity officeEntity);

    @Delete
    void delete(OfficeEntity officeEntity);

    @Query("SELECT * FROM offices")
    LiveData<List<OfficeEntity>> getAll();

    @Query("SELECT id FROM offices WHERE Floor = :floor AND Building = :building")
    long getOfficeId(int floor, String building);
}
