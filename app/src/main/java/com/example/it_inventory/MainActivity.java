package com.example.it_inventory;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.it_inventory.adapter.OfficeAdapter;
import com.example.it_inventory.database.AppDatabase;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.viewmodel.office.OfficeListViewModel;


import java.util.List;

import static com.example.it_inventory.database.AppDatabase.initializeDemoData;


public class MainActivity extends AppCompatActivity {
     private OfficeListViewModel officeListViewModel;



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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final OfficeAdapter adapter = new OfficeAdapter(this);
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
