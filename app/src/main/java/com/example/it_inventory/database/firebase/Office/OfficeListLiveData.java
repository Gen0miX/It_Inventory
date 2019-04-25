package com.example.it_inventory.database.firebase.Office;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.it_inventory.database.entity.OfficeEntity;
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
public class OfficeListLiveData extends LiveData<List<OfficeEntity>> {

    private static final String TAG = "Office" ;

    private final DatabaseReference reference;
    private final OfficeListLiveData.MyValueEventListener listener = new OfficeListLiveData.MyValueEventListener();

    public OfficeListLiveData(DatabaseReference ref){
        this.reference = ref;
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "onInactive");
    }

    private class MyValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot){
            setValue(toOfficeList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError){
            Log.e(TAG, "Can't listen to query "+reference, databaseError.toException());
        }
    }

    private List<OfficeEntity> toOfficeList(DataSnapshot snapshot){
        List<OfficeEntity> offices = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()){
            OfficeEntity entity = childSnapshot.getValue(OfficeEntity.class);
            entity.setId(childSnapshot.getKey());
            offices.add(entity);
        }
        return offices ;
    }


}
