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

public class OfficeMoveListLiveData extends LiveData<List<OfficeEntity>> {
    private static final String TAG = "Office" ;

    private final DatabaseReference reference;
    private final String officeId;
    private final OfficeMoveListLiveData.MyValueEventListener listener = new OfficeMoveListLiveData.MyValueEventListener();

    public OfficeMoveListLiveData(DatabaseReference ref, String officeId){
        this.reference = ref;
        this.officeId = officeId;
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
            setValue(toOfficeMoveList(dataSnapshot));
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError){
            Log.e(TAG, "Can't listen to query "+reference, databaseError.toException());
        }
    }

    private List<OfficeEntity> toOfficeMoveList(DataSnapshot snapshot){
        List<OfficeEntity> offices = new ArrayList<>();
        for(DataSnapshot childSnapshot : snapshot.getChildren()){
            OfficeEntity entity = childSnapshot.getValue(OfficeEntity.class);
            if(entity.getId() != officeId){
                entity.setId(childSnapshot.getKey());
                offices.add(entity);
            }
        }
        return offices ;
    }

}
