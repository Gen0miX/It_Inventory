package com.example.it_inventory.viewmodel.office;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.database.repository.OfficeRepository;

import java.util.List;

public class OfficeListViewModelSimpler extends AndroidViewModel {

    private OfficeRepository officeRepository ;

    private LiveData<List<OfficeEntity>> offices ;

    public OfficeListViewModelSimpler (Application application){
        super(application);
        officeRepository = new OfficeRepository();
        offices = officeRepository.getAllOffices(application);
    }

    LiveData<List<OfficeEntity>> getOffices(){
        return offices ;
    }


}
