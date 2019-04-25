package com.example.it_inventory.database.firebase.Office;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.it_inventory.database.entity.OfficeEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;


// comment-test for commit
public class OfficeLiveData extends LiveData<OfficeEntity>
{

    private static final String TAG = "OfficeLiveData";

    private final DatabaseReference reference ;
    private final OfficeLiveData.MyValueEventListener listener = new OfficeLiveData.MyValueEventListener();

    // Livedata : constructor
    public OfficeLiveData(DatabaseReference ref) {this.reference = ref;}

    // When this livedata is active
    @Override
    protected void onActive(){
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    // When this livedata is inactive
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
