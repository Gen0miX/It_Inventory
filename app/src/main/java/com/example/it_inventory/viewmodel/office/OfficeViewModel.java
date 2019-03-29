package com.example.it_inventory.viewmodel.office;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.database.repository.OfficeRepository;
import com.example.it_inventory.util.OnAsyncEventListener;

public class OfficeViewModel extends AndroidViewModel {

    private OfficeRepository repository ;

    private Application app ;

    private Context appContext ;

    private MediatorLiveData<OfficeEntity> observableOffice ;

    public OfficeViewModel(@NonNull Application app, final long officeId, OfficeRepository officeRepository){

        super(app);

        this.app = app ;

        repository = officeRepository ;

        appContext = app.getApplicationContext();

        observableOffice = new MediatorLiveData<>();
        observableOffice.setValue(null);

        LiveData<OfficeEntity> office = repository.getOffice(officeId, appContext);

        observableOffice.addSource(office, observableOffice::setValue);

    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application app ;

        private final long idOffice;

        private final OfficeRepository repository ;

        public Factory (@NonNull Application app, long idOffice){
            this.app = app ;
            this.idOffice = idOffice;
            repository = OfficeRepository.getInstance();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return (T) new OfficeViewModel(app, idOffice, repository);
        }
    }

    public LiveData<OfficeEntity> getOffice(){
        return observableOffice;
    }


    public void updateOffice(OfficeEntity office, OnAsyncEventListener callback){
        repository.update(office, callback, app);
    }

    public void createOffice(OfficeEntity office, OnAsyncEventListener callback){
        repository.insert(office, callback, app);
    }

    public void deleteOffice(OfficeEntity office, OnAsyncEventListener callback){
        repository.delete(office, callback, app);
    }

}
