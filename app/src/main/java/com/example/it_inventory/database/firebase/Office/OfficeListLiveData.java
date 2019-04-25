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


public class OfficeListLiveData extends LiveData<List<OfficeEntity>> {

    private static final String TAG = "Office" ;

    private final DatabaseReference reference;
    private final OfficeListLiveData.MyValueEventListener listener = new OfficeListLiveData.MyValueEventListener();

    // Livedata: constructor
    public OfficeListLiveData(DatabaseReference ref){
        this.reference = ref;
    }

    // When this livedata is active
    @Override
    protected void onActive() {
        Log.d(TAG, "onActive");
        reference.addValueEventListener(listener);
    }

    // When this livedata is inactive
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
