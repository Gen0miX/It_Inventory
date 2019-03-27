package com.example.it_inventory;

import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.it_inventory.adapter.OfficeAdapter;
import com.example.it_inventory.database.AppDatabase;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.util.RecyclerViewItemClickListener;
import com.example.it_inventory.viewmodel.office.OfficeListViewModel;


import java.util.ArrayList;
import java.util.List;

import static com.example.it_inventory.database.AppDatabase.initializeDemoData;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "OfficesList";

    private OfficeListViewModel officeListViewModel;
    private OfficeAdapter<OfficeEntity> adapter ;
    private List<OfficeEntity> offices;



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        setTitle("Offices");
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initializeDemoData(AppDatabase.getInstance(this));

        RecyclerView recyclerView = findViewById(R.id.recyclerview_office);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        offices = new ArrayList<>();
        adapter = new OfficeAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "Clicked position: "+ position);
                Log.d(TAG, "Clicked on: "+offices.get(position).getBuilding());
            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });



        OfficeListViewModel.Factory factory = new OfficeListViewModel.Factory(getApplication());
        officeListViewModel = ViewModelProviders.of(this, factory).get(OfficeListViewModel.class);
        officeListViewModel.getOffices().observe(this, officeEntities -> {
            if(officeEntities != null) {
                offices = officeEntities;
                adapter.setData(offices);
            }
        });

        recyclerView.setAdapter(adapter);



       // officeListViewModel = ViewModelProviders.of(this).get(officeListViewModel.getClass());
      //  officeListViewModel.getOffices().observe(this, new Observer<List<OfficeEntity>>() {
     //       @Override
       //     public void onChanged(@Nullable List<OfficeEntity> officeEntities) {
     //           Toast.makeText(MainActivity.this, "On changed", Toast.LENGTH_SHORT).show();
     //       }
     //   });
    }

}
