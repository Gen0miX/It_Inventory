package com.example.it_inventory.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.it_inventory.R;
import com.example.it_inventory.adapter.OfficeAdapter;
import com.example.it_inventory.database.AppDatabase;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.ui.office.OfficeActivity;
import com.example.it_inventory.ui.workstation.WorkstationsActivity;
import com.example.it_inventory.util.RecyclerViewItemClickListener;
import com.example.it_inventory.viewModel.office.OfficeListViewModel;
import com.example.it_inventory.viewModel.office.OfficeMoveViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.it_inventory.database.AppDatabase.initializeDemoData;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "OfficesList";

    private OfficeListViewModel officeListViewModel;
    private OfficeMoveViewModel officeMoveViewModel;
    private OfficeAdapter<OfficeEntity> adapter ;
    private List<OfficeEntity> offices;
    private long officeId;
    private long workstationId;



    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        setTitle("Offices");
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.settings:
                Intent MySettings = new Intent(MainActivity.this, MySettings.class);
                startActivity(MySettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


       /* //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.settings) {
            //Menu item pressed
           // Toast.makeText(this,"MySettings menu was pressed.", Toast.LENGTH_SHORT).show();
            Intent settings = new Intent(MainActivity.this, Settings.class);
            startActivity(settings);
            return true; //Indicated menu press was handled
        }

        return super.onOptionsItemSelected(item);*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        officeId = getIntent().getLongExtra("officeId", 0);
        workstationId = getIntent().getLongExtra("workstationId", 0);

        if(officeId == 0){
            initializeDemoData(AppDatabase.getInstance(this));

            RecyclerView recyclerView = findViewById(R.id.recyclerview_office);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);

            offices = new ArrayList<>();
            adapter = new OfficeAdapter<>(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.d(TAG, "Clicked position: "+ position);
                    Log.d(TAG, "Clicked on: "+offices.get(position).getBuilding());

                    Intent intent = new Intent(MainActivity.this, OfficeActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    intent.putExtra("officeId", offices.get(position).getId());
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View v, int position) {
                    Log.d(TAG, "longClicked position:" + position);
                    Log.d(TAG, "longClicked on: " + offices.get(position).toString());

                    Intent intent = new Intent(MainActivity.this, OfficeActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    intent.putExtra("officeId", offices.get(position).getId());
                    startActivity(intent);
                }
            });

            FloatingActionButton fab = findViewById(R.id.floatingActionAddOffice);
            fab.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, OfficeActivity.class);
                intent.setFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                Intent.FLAG_ACTIVITY_NO_HISTORY
                );
                startActivity(intent);
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

            //display the office list for moving the workstations if officeId != 0
        }else{


            RecyclerView recyclerView = findViewById(R.id.recyclerview_office);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    LinearLayoutManager.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);

            offices = new ArrayList<>();
            adapter = new OfficeAdapter<>(new RecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Log.d(TAG, "Clicked position: "+ position);
                    Log.d(TAG, "Clicked on: "+offices.get(position).getBuilding());

                    Intent intent = new Intent(MainActivity.this, WorkstationsActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    intent.putExtra("officeId", offices.get(position).getId());
                    intent.putExtra("workstationId", workstationId);
                    startActivity(intent);
                }

                @Override
                public void onItemLongClick(View v, int position) {
                    Log.d(TAG, "longClicked position:" + position);
                    Log.d(TAG, "longClicked on: " + offices.get(position).toString());

                    Intent intent = new Intent(MainActivity.this, WorkstationsActivity.class);
                    intent.setFlags(
                            Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                    Intent.FLAG_ACTIVITY_NO_HISTORY
                    );
                    intent.putExtra("officeId", offices.get(position).getId());
                    intent.putExtra("workstationId", workstationId);
                    startActivity(intent);

                }
            });

            OfficeMoveViewModel.Factory factory = new OfficeMoveViewModel.Factory(getApplication(), officeId);
            officeMoveViewModel = ViewModelProviders.of(this, factory).get(OfficeMoveViewModel.class);
            officeMoveViewModel.getOfficeMoveable().observe(this, officeEntities -> {
                if(officeEntities != null) {
                    offices = officeEntities;
                    adapter.setData(offices);
                }
            });
            recyclerView.setAdapter(adapter);

        }




    }

}
