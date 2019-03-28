package com.example.it_inventory.ui.workstation;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.it_inventory.R;
import com.example.it_inventory.adapter.WorkstationsAdapter;
import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.util.RecyclerViewItemClickListener;
import com.example.it_inventory.viewmodel.workstation.WorkstationListViewModel;


import java.util.ArrayList;
import java.util.List;

public class WorkstationsActivity extends AppCompatActivity {

    private WorkstationListViewModel workstationListViewModel;

    private WorkstationsAdapter<WorkstationEntity> adapter;

    private List<WorkstationEntity> workstations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstations);

        long officeId = getIntent().getLongExtra("officeId", 0);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_workstations);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        /*DividerItemDecoration dividerItemDecorationV = new DividerItemDecoration(recyclerView.getContext(),
                                                                                GridLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecorationV);*/


        workstations = new ArrayList<>();
        adapter = new WorkstationsAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });


        WorkstationListViewModel.Factory factory = new WorkstationListViewModel.Factory(getApplication(), officeId);
        workstationListViewModel = ViewModelProviders.of(this, factory).get(WorkstationListViewModel.class);
        workstationListViewModel.getWorkstationByOfficeId().observe(this, workstationEntities -> {
            if(workstationEntities != null){
                workstations = workstationEntities;
                adapter.setData(workstations);
            }
        });

        recyclerView.setAdapter(adapter);


    }
}
