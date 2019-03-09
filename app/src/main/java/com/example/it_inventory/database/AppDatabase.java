package com.example.it_inventory.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.it_inventory.database.dao.OfficeDao;
import com.example.it_inventory.database.dao.WorkstationDao;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.database.entity.WorkstationEntity;

@Database(entities = {OfficeEntity.class, WorkstationEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract OfficeDao officeDao();
    public abstract WorkstationDao workstationDao();
}
