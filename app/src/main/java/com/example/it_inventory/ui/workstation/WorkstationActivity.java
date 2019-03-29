package com.example.it_inventory.ui.workstation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.it_inventory.R;

public class WorkstationActivity extends AppCompatActivity {

    private static final String TAG = "WorkstationDetails";

    private static final int CREATE_WORKSTATION = 0 ;
    private static final int EDIT_WORKSTATION = 1 ;
    private static final int DELETE_OFFICE = 2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstation);
    }
}
