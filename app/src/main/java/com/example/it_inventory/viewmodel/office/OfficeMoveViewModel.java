package com.example.it_inventory.viewmodel.office;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.database.repository.OfficeRepository;
import com.example.it_inventory.ui.BaseApp;

import java.util.List;

public class OfficeMoveViewModel extends AndroidViewModel {

    private Application app ;

    private OfficeRepository repository ;

    private final MediatorLiveData<List<OfficeEntity>> observableOffices;

    public OfficeMoveViewModel(@NonNull Application app, final long officeId,
                               OfficeRepository officeRepository){
        super(app);
        this.app = app ;
        repository = officeRepository;

        observableOffices = new MediatorLiveData<>();
        observableOffices.setValue(null);

        LiveData<List<OfficeEntity>> listOffices =
                officeRepository.getOfficesMove(officeId, app);

        observableOffices.addSource(listOffices, observableOffices::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory{
        @NonNull
        Application app;

        private final long officeId;

        private final OfficeRepository officeRepository;

        public Factory(@NonNull Application app, long officeId){
            this.app = app;
            this.officeId=officeId;
            officeRepository = ((BaseApp)app).getOfficeRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass){
            return (T) new OfficeMoveViewModel(app, officeId, officeRepository);
        }
    }

    public LiveData<List<OfficeEntity>> getOfficeMoveable(){
        return observableOffices;
    }

}
