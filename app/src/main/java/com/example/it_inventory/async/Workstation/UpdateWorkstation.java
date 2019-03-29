package com.example.it_inventory.async.Workstation;

import android.app.Application;
import android.os.AsyncTask;

import com.example.it_inventory.ui.BaseApp;
import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.util.OnAsyncEventListener;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 25.03.2019
 */
public class UpdateWorkstation extends AsyncTask<WorkstationEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public UpdateWorkstation(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(WorkstationEntity... params) {
        try {
            for (WorkstationEntity Workstation : params)
                ((BaseApp) application).getDatabase().workstationDao()
                        .update(Workstation);
        } catch (Exception e) {
            this.exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }
    }
}