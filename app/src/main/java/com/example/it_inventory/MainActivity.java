package com.example.it_inventory;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.example.it_inventory.database.entity.OfficeEntity;


import java.util.List;


public class MainActivity extends AppCompatActivity {
    // private OfficeListViewModel officeListViewModel;



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        setTitle("Bureaux");
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // officeListViewModel = ViewModelProviders.of(this).get(officeListViewModel.getClass());
      //  officeListViewModel.getOffices().observe(this, new Observer<List<OfficeEntity>>() {
     //       @Override
       //     public void onChanged(@Nullable List<OfficeEntity> officeEntities) {
     //           Toast.makeText(MainActivity.this, "On changed", Toast.LENGTH_SHORT).show();
     //       }
     //   });
    }

}
