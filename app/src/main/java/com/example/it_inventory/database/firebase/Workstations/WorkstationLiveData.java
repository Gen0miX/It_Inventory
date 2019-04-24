package com.example.it_inventory.database.firebase.Workstations;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.it_inventory.database.entity.WorkstationEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 17.04.2019
 */
public class WorkstationLiveData extends LiveData<WorkstationEntity> {

    private static final String TAG = "WorkstationLiveData";

    private final DatabaseReference reference ;
    private final String owner ;
    private final WorkstationLiveData.MyValueEventListener listener = new WorkstationLiveData.MyValueEventListener();

    public WorkstationLiveData(DatabaseReference ref){
        this.reference = ref ;
        owner = ref.getParent().getParent().getKey();
    }

    @Override
    protected void onActive(){
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    protected void onInactive(){
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener{
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot){
            WorkstationEntity entity = dataSnapshot.getValue(WorkstationEntity.class);
            entity.setId(dataSnapshot.getKey());
            entity.setOfficeId(owner);
            setValue(entity);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError){
            Log.e(TAG, "Can't listen to query "+ reference, databaseError.toException());
        }
    }

}
