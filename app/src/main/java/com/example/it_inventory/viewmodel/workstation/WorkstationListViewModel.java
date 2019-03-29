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

import java.util.List;

public class WorkstationListViewModel extends AndroidViewModel {

    private Application app;

    private WorkstationRepository repository;

    private final MediatorLiveData<List<WorkstationEntity>> observableWorkstations;

    public WorkstationListViewModel(@NonNull Application app, final Long officeId,
                                    WorkstationRepository workstationRepository){
        super(app);
        this.app = app ;

        repository = workstationRepository ;

        observableWorkstations = new MediatorLiveData<>();
        observableWorkstations.setValue(null);

        LiveData<List<WorkstationEntity>> listWorkstationsByOffice =
                workstationRepository.getWorkstationsByOffice(officeId, app);

        observableWorkstations.addSource(listWorkstationsByOffice, observableWorkstations::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        Application app;

        private final long officeId;

        private final WorkstationRepository workstationRepository;

        public Factory(@NonNull Application app, long officeId){
            this.app = app;
            this.officeId = officeId;
            workstationRepository = ((BaseApp)app).getWorkstationRepository();
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
