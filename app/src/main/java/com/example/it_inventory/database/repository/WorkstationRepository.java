package com.example.it_inventory.database.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.database.firebase.Workstations.WorkstationListLiveData;
import com.example.it_inventory.database.firebase.Workstations.WorkstationLiveData;
import com.example.it_inventory.util.OnAsyncEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

    /*public void insert(final WorkstationEntity Workstation, OnAsyncEventListener callback,
                       Application application) {
        new CreateWorkstation(application, callback).execute(Workstation);
    }*/

    public void insert(final WorkstationEntity Workstation, OnAsyncEventListener callback) {
        String id = FirebaseDatabase.getInstance()
                .getReference("workstations").push().getKey();
        FirebaseDatabase.getInstance()
                .getReference("workstations")
                .child(id)
                .setValue(Workstation, (databaseError, databaseReference) ->
                {
                    if (databaseError != null) {
                        callback.onFailure(databaseError.toException());
                    } else {
                        callback.onSuccess();
                    }
                });
    }

    /*public void update(final WorkstationEntity Workstation, OnAsyncEventListener callback,
                       Application application) {
        new UpdateWorkstation(application, callback).execute(Workstation);
    }*/

    public void update(final WorkstationEntity Workstation, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
                .getReference("workstations")
                .child(Workstation.getId())
                .updateChildren(Workstation.toMap(),
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

    /*public void delete(final WorkstationEntity Workstation, OnAsyncEventListener callback,
                   Application application) {
    new DeleteWorkstation(application, callback).execute(Workstation);
    }*/

    public void delete(final WorkstationEntity Workstation, OnAsyncEventListener callback) {
        FirebaseDatabase.getInstance()
            .getReference()
            .child(Workstation.getId())
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




    /*public LiveData<WorkstationEntity> getWorkstation(final long workstationId, Context context){
        return AppDatabase.getInstance(context).workstationDao().getWorkstation(workstationId);
    }*/

    public LiveData<WorkstationEntity> getWorkstation(final String workstationId){
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("workstations")
                .child(workstationId);
        return new WorkstationLiveData(reference);
    }

    /*public LiveData<List<WorkstationEntity>> getWorkstationsByOffice(final long officeId, Application application){
        return ((BaseApp)application).getDatabase().workstationDao().getWorkstationsByOfficeId(officeId);
    }*/

    public LiveData<List<WorkstationEntity>> getWorkstationsByOffice(final String officeId, Application application){
        DatabaseReference reference = FirebaseDatabase.getInstance()
                .getReference("workstations")
                .child(officeId); // pas sûr

        return new WorkstationListLiveData(reference, officeId);
    }

}
