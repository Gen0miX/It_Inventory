package com.example.it_inventory;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.viewModel.viewModel.workstation.WorkstationListViewModel;

import java.util.List;

public class WorkstationsActivity extends AppCompatActivity {
    private WorkstationListViewModel workstationListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstations);

        workstationListViewModel = ViewModelProviders.of(this).get(workstationListViewModel.getClass());
        workstationListViewModel.getWorkstationsByOffice().observe(this, new Observer<List<WorkstationEntity>>() {
            @Override
            public void onChanged(@Nullable List<WorkstationEntity> workstationEntities) {
                Toast.makeText(WorkstationsActivity.this,"On change", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
