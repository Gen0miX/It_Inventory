package com.example.it_inventory.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.it_inventory.ui.BaseApp;
import com.example.it_inventory.async.Office.CreateOffice;
import com.example.it_inventory.async.Office.DeleteOffice;
import com.example.it_inventory.async.Office.UpdateOffice;
import com.example.it_inventory.database.AppDatabase;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 25.03.2019
 */
public class OfficeRepository {

    private static OfficeRepository instance ;

    public OfficeRepository(){}

    public static OfficeRepository getInstance(){
        if (instance == null) {
            synchronized (OfficeRepository.class){
                if(instance == null){
                    instance = new OfficeRepository();
                }
            }
        }
        return instance ;
    }

    /*public void insert(final OfficeEntity Office, OnAsyncEventListener callback,
                       Application application) {
        new CreateOffice(application, callback).execute(Office);
    }*/

    public void insert(final OfficeEntity Office, OnAsyncEventListener callback) {
        String id = FirebaseDatabase.getInstance()
                .getReference("offices").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference()
                .child(id)
                .setValue(Office, (databaseError, databaseReference) ->
                {
                    if(databaseError!=null)
                    {
                        callback.onFailure(databaseError.toException());
                    }
                    else
                    {
                        callback.onSuccess();
                    }
                });
    }

    /*public void update(final OfficeEntity Office, OnAsyncEventListener callback,
                       Application application) {
        new UpdateOffice(application, callback).execute(Office);
    }*/

    public void update(final OfficeEntity Office, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("offices")
                .child(Long.toString(Office.getId()))
                .updateChildren(Office.toMap(),
                        (databaseError, databaseReference) ->
                        {
                            if(databaseError!=null)
                            {
                                callback.onFailure(databaseError.toException());
                            }
                            else
                            {
                                callback.onSuccess();
                            }
                        });
    }

    /*public void delete(final OfficeEntity Office, OnAsyncEventListener callback,
                       Application application) {
        new DeleteOffice(application, callback).execute(Office);
    }*/

    public void delete(final OfficeEntity Office, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference()
                .child(Long.toString(Office.getId()))
                .removeValue((databaseError, databaseReference) ->
                {
                    if(databaseError != null)
                    {
                        callback.onFailure(databaseError.toException());
                    }
                    else
                    {
                        callback.onSuccess();
                    }
                });
    }

    /*
    public LiveData<OfficeEntity> getOffice(final long officeId, Context context){
        return AppDatabase.getInstance(context).officeDao().getOffice(officeId);
    }*/

    public LiveData<OfficeEntity> getOffice(final long officeId){
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("offices")
                .child(Long.toString(officeId));
        return new OfficeLiveData(reference);
    }
    /*
    public LiveData<List<OfficeEntity>> getAllOffices (Application application){
        return ((BaseApp)application).getDatabase().officeDao().getAll();
    }*/

    public LiveData<List<OfficeEntity>> getAllOffices (){
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("offices");
        return new OfficeLiveData(reference);
    }

    public LiveData<List<OfficeEntity>> getOfficesMove(long officeId, Application app){
        return ((BaseApp)app).getDatabase().officeDao().getOfficesMove(officeId);
    }

}
