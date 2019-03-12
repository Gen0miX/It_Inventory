package com.example.it_inventory.database;

import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.util.Log;

import com.example.it_inventory.database.AppDatabase;
import com.example.it_inventory.database.entity.WorkstationEntity;

public class DatabaseInitializer {

    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDatabase db) {
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static void addWorkstation (final AppDatabase db, final boolean screens, final boolean portable, final String os, final int ram,
                                        final int storage, final String processor, final String keyboardType, final long officeId){
        WorkstationEntity workstationEntity = new WorkstationEntity(screens, portable, os, ram, storage, processor, keyboardType, officeId);
        db.workstationDao().insert(workstationEntity);
    }

    private static void populateWorkstationData(AppDatabase db) {
        db.workstationDao().deleteAll();

        // Methode addWorkstation

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWorkstationData(database);
            return null;
        }

    }

}
