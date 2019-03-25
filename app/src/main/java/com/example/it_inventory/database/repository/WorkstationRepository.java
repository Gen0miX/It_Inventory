package com.example.it_inventory.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.it_inventory.async.Workstation.CreateWorkstation;
import com.example.it_inventory.async.Workstation.DeleteWorkstation;
import com.example.it_inventory.async.Workstation.UpdateWorkstation;
import com.example.it_inventory.database.AppDatabase;
import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.util.OnAsyncEventListener;

import java.util.List;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 25.03.2019
 */
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

    public void insert(final WorkstationEntity Workstation, OnAsyncEventListener callback,
                       Application application) {
        new CreateWorkstation(application, callback).execute(Workstation);
    }

    public void update(final WorkstationEntity Workstation, OnAsyncEventListener callback,
                       Application application) {
        new UpdateWorkstation(application, callback).execute(Workstation);
    }

    public void delete(final WorkstationEntity Workstation, OnAsyncEventListener callback,
                       Application application) {
        new DeleteWorkstation(application, callback).execute(Workstation);
    }


    public LiveData<List<WorkstationEntity>> getAllWorkstations (Context context){
        return AppDatabase.getInstance(context).workstationDao().getAll();
    }
}
