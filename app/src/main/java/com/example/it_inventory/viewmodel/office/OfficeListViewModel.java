package com.example.it_inventory.viewModel.office;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.it_inventory.ui.BaseApp;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.database.repository.OfficeRepository;

import java.util.List;

public class OfficeListViewModel extends AndroidViewModel {

    private OfficeRepository repository ;

    private Application app ;

    private MediatorLiveData<List<OfficeEntity>> observableOffice ;

    public OfficeListViewModel(@NonNull Application app, OfficeRepository officeRepository){

        super(app);

        this.app = app ;

        repository = officeRepository ;

        observableOffice = new MediatorLiveData<>();
        observableOffice.setValue(null);

        LiveData<List<OfficeEntity>> offices = repository.getAllOffices();

        observableOffice.addSource(offices, observableOffice::setValue);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application app ;

        private final OfficeRepository repository ;

        public Factory (@NonNull Application app){
            this.app = app ;
            repository = ((BaseApp)app).getOfficeRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new OfficeListViewModel(app, repository);
        }
    }

    public LiveData<List<OfficeEntity>> getOffices(){
        return observableOffice;
    }
}
