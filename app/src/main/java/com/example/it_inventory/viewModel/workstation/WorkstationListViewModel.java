package com.example.it_inventory.viewModel.workstation;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.it_inventory.BaseApp;
import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.database.repository.OfficeRepository;
import com.example.it_inventory.database.repository.WorkstationRepository;
import com.example.it_inventory.util.OnAsyncEventListener;

import java.util.List;

public class WorkstationListViewModel extends AndroidViewModel {

    private Application app ;

    private WorkstationRepository repository ;

    private final MediatorLiveData<List<WorkstationEntity>> observableWorkstations ;

    public WorkstationListViewModel (@NonNull Application app, final long officeId,
                                     OfficeRepository officeRepository, WorkstationRepository workstationRepository) {
        super(app);

        this.app = app ;

        repository = workstationRepository ;

        observableWorkstations = new MediatorLiveData<>();
        observableWorkstations.setValue(null);

        LiveData<List<WorkstationEntity>> workstations = repository.getWorkstationsByOffice(officeId, app);

        observableWorkstations.addSource(workstations, observableWorkstations::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application app ;

        private final long officeId ;

        private final OfficeRepository officeRepository ;

        private final WorkstationRepository workstationRepository ;

        public Factory(@NonNull Application app, long officeId){
            this.app = app ;
            this.officeId = officeId ;
            officeRepository = ((BaseApp)app).getOfficeRepository();
            workstationRepository = ((BaseApp)app).getWorkstationRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new WorkstationListViewModel(app, officeId, officeRepository, workstationRepository);
        }
    }

    public LiveData<List<WorkstationEntity>> getWorkstationsByOffice() {
        return observableWorkstations ;
    }

    public void deleteWorkstation(WorkstationEntity workstationEntity, OnAsyncEventListener callback){
        repository.delete(workstationEntity, callback, app);
    }

}
