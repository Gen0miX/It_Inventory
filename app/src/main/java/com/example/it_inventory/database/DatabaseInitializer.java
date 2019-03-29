package com.example.it_inventory.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.it_inventory.database.entity.OfficeEntity;
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

    private static void addOffice (final AppDatabase db, final int floor, final String building, final String sector,
                                   final String city, final String country) {
        OfficeEntity officeEntity = new OfficeEntity(floor, building, sector, city, country);
        db.officeDao().insert(officeEntity);
    }

    // Adding of the elements in the database
    private static void populateDatabaseData(AppDatabase db) {
        db.workstationDao().deleteAll();

        addOffice(db, 1, "building1", "Marketing", "Sion", "Switzerland");
        addWorkstation(db, true, false, "Windows 10", 16, 1000, "Intel i7-9700K", "QWERTZ", db.officeDao().getOfficeId(1, "building1"));
        addWorkstation(db, false, true, "Windows 10", 8, 500, "AMD Ryzen 7 2700X", "QWERTZ", db.officeDao().getOfficeId(1, "building1"));
        addWorkstation(db, true, true, "Windows 10", 16, 2000, "Intel i7-9700K", "QWERTZ", db.officeDao().getOfficeId(1, "building1"));
        addWorkstation(db, false, false, "Linux", 16, 1000, "Intel i7-8700", "QWERTY", db.officeDao().getOfficeId(1, "building1"));

        addOffice(db, 2, "building2", "Selling", "Sion", "Switzerland");
        addWorkstation(db, false, true, "Windows 10", 8, 500, "Intel i5-9600K", "QWERTZ", db.officeDao().getOfficeId(2, "building1"));
        addWorkstation(db, true, false, "Mac OS", 16, 1000, "Intel i7", "QWERTY", db.officeDao().getOfficeId(2, "building1"));
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateDatabaseData(database);
            return null;
        }

    }

}
