package com.example.it_inventory;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.it_inventory.adapter.OfficeAdapter;
import com.example.it_inventory.database.entity.OfficeEntity;
import com.example.it_inventory.util.OnAsyncEventListener;
import com.example.it_inventory.viewmodel.office.OfficeListViewModel;
import com.example.it_inventory.viewmodel.office.OfficeViewModel;

public class OfficeActivity extends AppCompatActivity {

    private static final String TAG = "OfficeDetails";

    private static final int CREATE_OFFICE = 0 ;
    private static final int EDIT_OFFICE = 1 ;
    private static final int DELETE_OFFICE = 2 ;

    private OfficeViewModel viewModel ;

    private Toast toast ;

    private EditText etName;
    private EditText etFloor;
    private EditText etSector;
    private EditText etCity;
    private EditText etCountry;

    private boolean isEditable;

    private OfficeEntity office ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        long officeId = getIntent().getLongExtra("officeId", 0);


        initiateView();

        //Possible que conversion de marche pas
        OfficeViewModel.Factory factory = new OfficeViewModel.Factory(getApplication(), officeId);
        viewModel = ViewModelProviders.of(this, factory).get(OfficeViewModel.class);
        viewModel.getOffice().observe(this, officeEntity -> {
            if(officeEntity != null) {
                office = officeEntity ;
                updateContent();
            }
        });

        if(officeId != 0){
            setTitle(R.string.title_office_details);
        }else {
            setTitle(R.string.title_office_create);
            switchToEdit();
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        if(office != null){
            menu.add(0, EDIT_OFFICE, Menu.NONE, getString(R.string.action_edit))
                    .setIcon(R.drawable.ic_edit_white)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add(0, DELETE_OFFICE, Menu.NONE, getString(R.string.action_delete))
                    .setIcon(R.drawable.ic_delete_white)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }else{
            menu.add(0, CREATE_OFFICE, Menu.NONE, getString(R.string.action_create_office))
                    .setIcon(R.drawable.ic_white_add)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == EDIT_OFFICE){
            if(isEditable){
                item.setIcon(R.drawable.ic_edit_white);
                switchToEdit();
            }else{
                item.setIcon(R.drawable.ic_done);
                switchToEdit();
            }
        }
        if(item.getItemId() == DELETE_OFFICE){
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.action_delete));
            alertDialog.setCancelable(false);
            alertDialog.setMessage("Do you really want to delete this office ?");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_delete), (dialog, which) -> {
                viewModel.deleteOffice(office, new OnAsyncEventListener(){

                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "deleteClient: Success");
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "deleteClient: Failure", e);
                    }
                });
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
            alertDialog.show();
        }
        if(item.getItemId() == CREATE_OFFICE){
            createOffice(etName.getText().toString(),
                    Integer.parseInt(etFloor.getText().toString()),
                    etSector.getText().toString(),
                    etCity.getText().toString(),
                    etCountry.getText().toString());
        }
        return super.onOptionsItemSelected(item);
    }

    private void initiateView(){
        isEditable = false;
        etName = findViewById(R.id.etName);
        etFloor = findViewById(R.id.etFloor);
        etSector = findViewById(R.id.etSector);
        etCity = findViewById(R.id.etCity);
        etCountry = findViewById(R.id.etCountry);

        etName.setFocusable(false);
        etName.setEnabled(false);
        etFloor.setFocusable(false);
        etFloor.setEnabled(false);
        etSector.setFocusable(false);
        etSector.setEnabled(false);
        etCity.setFocusable(false);
        etCity.setEnabled(false);
        etCountry.setFocusable(false);
        etCountry.setEnabled(false);
    }

    private void switchToEdit(){
        if(!isEditable){
            etName.setFocusable(true);
            etName.setEnabled(true);
            etFloor.setFocusable(true);
            etFloor.setEnabled(true);
            etSector.setFocusable(true);
            etSector.setEnabled(true);
            etCity.setFocusable(true);
            etCity.setEnabled(true);
            etCountry.setFocusable(true);
            etCountry.setEnabled(true);
        }else{
            saveChanges(
                    etName.getText().toString(),
                    Integer.parseInt(etFloor.getText().toString()),
                    etSector.getText().toString(),
                    etCity.getText().toString(),
                    etCountry.getText().toString()
            );
            etName.setFocusable(false);
            etName.setEnabled(false);
            etFloor.setFocusable(false);
            etFloor.setEnabled(false);
            etSector.setFocusable(false);
            etSector.setEnabled(false);
            etCity.setFocusable(false);
            etCity.setEnabled(false);
            etCountry.setFocusable(false);
            etCountry.setEnabled(false);
        }
        isEditable = !isEditable;
    }

    private void createOffice(String name, int floor, String sector, String city, String country ){
        office = new OfficeEntity();
        office.setBuilding(name);
        office.setFloor(floor);
        office.setSector(sector);
        office.setCity(city);
        office.setCountry(country);

        viewModel.createOffice(office, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "CreateClient: Success");
                onBackPressed();
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "CreateClient: Failure", e);
            }
        });

    }

    public void saveChanges(String name, int floor, String sector, String city, String country ){
        office.setBuilding(name);
        office.setFloor(floor);
        office.setSector(sector);
        office.setCity(city);
        office.setCountry(country);

        viewModel.updateOffice(office, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "updateOffice: Success");
                setResponse(true);
                onBackPressed();
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void setResponse(Boolean response) {
        if (response) {
            toast = Toast.makeText(this, getString(R.string.action_edited_office), Toast.LENGTH_LONG);
            toast.show();
        } else {
            toast = Toast.makeText(this, getString(R.string.action_error), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void updateContent(){
        if(office != null){
            etName.setText(office.getBuilding());
            etFloor.setText(office.getFloorString());
            etSector.setText(office.getSector());
            etCity.setText(office.getCity());
            etCountry.setText(office.getCountry());
        }
    }

}
