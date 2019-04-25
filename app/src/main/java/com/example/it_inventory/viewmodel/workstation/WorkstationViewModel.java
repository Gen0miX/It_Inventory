package com.example.it_inventory.viewmodel.workstation;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.database.repository.WorkstationRepository;
import com.example.it_inventory.util.OnAsyncEventListener;

public class WorkstationViewModel extends AndroidViewModel {

    private WorkstationRepository repository ;

    private Application app ;
    //private Context appContext;

    private MediatorLiveData<WorkstationEntity> observableWorkstation;

    public WorkstationViewModel(@NonNull Application app, final String workstationId, final String officeId, WorkstationRepository workstationRepository){
        super(app);

        repository = workstationRepository;

        //appContext = app.getApplicationContext();

        observableWorkstation = new MediatorLiveData<>();
        observableWorkstation.setValue(null);


        if(workstationId != null) {
            LiveData<WorkstationEntity> workstation = repository.getWorkstation(workstationId, officeId);

            observableWorkstation.addSource(workstation, observableWorkstation::setValue);
        }
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        private final Application app;

        private final String workstationId, officeId;



        private final WorkstationRepository repository;

        public Factory(@NonNull Application app, String workstationId, String officeId){
            this.app = app;
            this.workstationId = workstationId;
            this.officeId = officeId;
            repository=WorkstationRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass){
            return (T) new WorkstationViewModel(app, workstationId, officeId, repository);
        }
    }

    public LiveData<WorkstationEntity> getWorkstation(){return observableWorkstation;}

    public void updateWorkstation(WorkstationEntity workstation, OnAsyncEventListener callback){
        repository.update(workstation, callback);
    }

    public void createWorkstation(WorkstationEntity workstation, OnAsyncEventListener callback){
        repository.insert(workstation, callback);
    }

    public void deleteWorkstation(WorkstationEntity workstation, OnAsyncEventListener callback){
        repository.delete(workstation, callback);
    }

}
