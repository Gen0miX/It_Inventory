package com.example.it_inventory.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.sqlite.SQLiteConstraintException;

import com.example.it_inventory.database.entity.WorkstationEntity;

import java.util.List;

@Dao
public interface WorkstationDao {
    @Query("SELECT * FROM workstations")
    LiveData<List<WorkstationEntity>> getAll();

    @Insert
    void insert(WorkstationEntity workstationEntity) throws SQLiteConstraintException;

    @Update
    void update(WorkstationEntity workstationEntity);

    @Delete
    void delete(WorkstationEntity workstationEntity);

    @Query("DELETE FROM workstations")
    void deleteAll();

    @Query("SELECT * FROM workstations WHERE officeId = :officeId")
    LiveData<List<WorkstationEntity>> getWorkstationsByOfficeId(long officeId);
}
