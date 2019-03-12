package com.example.it_inventory.database.repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.it_inventory.database.AppDatabase;
import com.example.it_inventory.database.entity.WorkstationEntity;

import java.util.List;

public class WorkstationRepository {

    private static WorkstationRepository instance ;

    public WorkstationRepository(){}

    public static WorkstationRepository getInstance(){
        if (instance == null) {
            synchronized (WorkstationRepository.class){
                if(instance == null){
                    instance = new WorkstationRepository();
                }
            }
        }
        return instance ;
    }


    public LiveData<List<WorkstationEntity>> getAllWorkstations (Context context){
        return AppDatabase.getInstance(context).workstationDao().getAll();
    }

}
