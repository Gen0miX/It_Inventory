package com.example.it_inventory.viewmodel.workstation;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.it_inventory.ui.BaseApp;
import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.database.repository.WorkstationRepository;
import com.example.it_inventory.util.OnAsyncEventListener;

import java.util.List;

public class WorkstationListViewModel extends AndroidViewModel {

    private Application app;

    private WorkstationRepository repository;

    private final MediatorLiveData<List<WorkstationEntity>> observableWorkstations;

    public WorkstationListViewModel(@NonNull Application app, final String officeId,
                                     WorkstationRepository workstationRepository){
        super(app);

        repository = workstationRepository ;

        observableWorkstations = new MediatorLiveData<>();
        observableWorkstations.setValue(null);

            LiveData<List<WorkstationEntity>> listWorkstationsByOffice =
                    workstationRepository.getWorkstationsByOffice(officeId);
            observableWorkstations.addSource(listWorkstationsByOffice, observableWorkstations::setValue);

    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        Application app;

        private final String officeId;



        private final WorkstationRepository workstationRepository;

        public Factory(@NonNull Application app, String officeId){
            this.app = app;
            this.officeId = officeId;
            workstationRepository = WorkstationRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass){
            return(T) new WorkstationListViewModel(app, officeId, workstationRepository);
        }
    }

    public LiveData<List<WorkstationEntity>> getWorkstationByOfficeId(){
        return observableWorkstations ;
    }

}
