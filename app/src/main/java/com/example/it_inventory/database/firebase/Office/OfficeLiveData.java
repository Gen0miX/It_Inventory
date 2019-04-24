package com.example.it_inventory.database.firebase.Office;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.it_inventory.database.entity.OfficeEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 17.04.2019
 */

// comment-test for commit
public class OfficeLiveData extends LiveData<OfficeEntity>
{

    private static final String TAG = "OfficeLiveData";

    private final DatabaseReference reference ;
    private final OfficeLiveData.MyValueEventListener listener = new OfficeLiveData.MyValueEventListener();


    public OfficeLiveData(DatabaseReference ref) {this.reference = ref;}

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
            OfficeEntity entity = dataSnapshot.getValue(OfficeEntity.class);
            entity.setId(dataSnapshot.getKey());
            setValue(entity);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError){
            Log.e(TAG, "Can't listen to query"+reference, databaseError.toException());
        }
    }


}
