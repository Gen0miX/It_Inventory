package com.example.it_inventory.database.firebase.Workstations;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.it_inventory.database.entity.WorkstationEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 17.04.2019
 */
public class WorkstationListLiveData extends LiveData<List<WorkstationEntity>> {

    private static final String TAG = "WorkstationListLiveData";

    private final DatabaseReference reference ;
    private final String owner;
    private final MyValueEventListener listener = new MyValueEventListener();

    public WorkstationListLiveData(DatabaseReference ref, String owner){
        this.reference = ref ;
        this.owner = owner;
    }

    @Override
    protected void onActive(){
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive(){
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener{
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot){
            setValue(toWorkstations(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError){
            Log.e(TAG, "Can't listen to query "+ reference, databaseError.toException());
        }
    }

    private List<WorkstationEntity> toWorkstations(DataSnapshot snapshot){
        List<WorkstationEntity> workstations = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()){
            WorkstationEntity entity = childSnapshot.getValue(WorkstationEntity.class);
            entity.setId(childSnapshot.getKey());
            entity.setOfficeId(owner);
            workstations.add(entity);
        }
        return workstations ;
    }
}
