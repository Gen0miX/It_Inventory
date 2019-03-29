package com.example.it_inventory.async.Office;

import android.app.Application;
import android.os.AsyncTask;

import com.example.it_inventory.ui.BaseApp;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.util.OnAsyncEventListener;

/**
 * Author: Samuel Pinto Da Silva
 * Creation date:
 * Last update date: 25.03.2019
 */


public class CreateOffice extends AsyncTask<OfficeEntity, Void, Void> {

    private Application application;
    private OnAsyncEventListener callback;
    private Exception exception;

    public CreateOffice(Application application, OnAsyncEventListener callback) {
        this.application = application;
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(OfficeEntity... params) {
        try {
            for (OfficeEntity Office : params)
                ((BaseApp) application).getDatabase().officeDao()
                        .insert(Office);
        } catch (Exception e) {
            exception = e;
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

