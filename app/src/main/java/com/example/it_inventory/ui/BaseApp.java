package com.example.it_inventory.ui;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 25.03.2019
 */

import android.app.Application;

import com.example.it_inventory.database.repository.OfficeRepository;
import com.example.it_inventory.database.repository.WorkstationRepository;
import com.google.firebase.FirebaseApp;

/**
 * Android Application class. Used for accessing singletons.
 */
public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public OfficeRepository getOfficeRepository() {
        return OfficeRepository.getInstance();
    }

    public WorkstationRepository getWorkstationRepository() {
        return WorkstationRepository.getInstance();
    }
}
