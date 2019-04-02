package com.example.it_inventory.ui.workstation;

import android.arch.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import android.widget.TextView;
import android.widget.Toast;

import com.example.it_inventory.R;

import com.example.it_inventory.database.entity.WorkstationEntity;
import com.example.it_inventory.ui.MainActivity;
import com.example.it_inventory.util.OnAsyncEventListener;
import com.example.it_inventory.viewmodel.workstation.WorkstationViewModel;

public class WorkstationActivity extends AppCompatActivity {

    private static final String TAG = "WorkstationDetails";

    private static final int CREATE_WORKSTATION = 0;
    private static final int EDIT_WORKSTATION = 1;
    private static final int DELETE_WORKSTATION = 2;
    private static final int MOVE_WORKSTATION = 3;

    private WorkstationViewModel viewModel;

    private Toast toast;

    private Switch swScreens;
    private Switch swPortable;
    private EditText etOs;
    private EditText etRam;
    private EditText etStorage;
    private EditText etProcessor;
    private Spinner spKeyboard;
    private TextView twKeyboard;

    private boolean isEditable;

    private long officeId ;

    private WorkstationEntity workstation;

    //Create the activity Workstation by handling if it's a create, edit
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.DarkTheme);
        }
        else
            setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workstation);

        long workstationId = getIntent().getLongExtra("workstationId", 0);
        officeId = getIntent().getLongExtra("officeId", 0);

        initiateView();

        WorkstationViewModel.Factory factory = new WorkstationViewModel.Factory(getApplication(), workstationId);
        viewModel = ViewModelProviders.of(this, factory).get(WorkstationViewModel.class);
        viewModel.getWorkstation().observe(this, workstationEntity -> {
            if(workstationEntity != null){
                workstation = workstationEntity;
                updateContent();
            }
        });

        if(workstationId != 0){
            setTitle("Workstation Details");
        }else{
            setTitle("Create Workstation");
            switchToEdit();
        }
    }

    //Create the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        if(workstation != null){
            menu.add(0, EDIT_WORKSTATION, Menu.NONE, getString(R.string.action_edit))
                    .setIcon(R.drawable.ic_edit_white)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add(0, DELETE_WORKSTATION, Menu.NONE, getString(R.string.action_delete))
                    .setIcon(R.drawable.ic_delete_white)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            menu.add(0, MOVE_WORKSTATION, Menu.NONE, getString(R.string.action_move))
                    .setIcon(R.drawable.ic_move_white)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }else{
            menu.add(0, CREATE_WORKSTATION, Menu.NONE, getString(R.string.action_create_workstation))
                    .setIcon(R.drawable.ic_add_white)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return true;
    }

    //set action for the item's menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == EDIT_WORKSTATION){
            if(isEditable){
                item.setIcon(R.drawable.ic_edit_white);
                switchToEdit();
            }else{
                item.setIcon(R.drawable.ic_done);
                switchToEdit();
            }
        }
        if(item.getItemId() == DELETE_WORKSTATION){
            final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle(getString(R.string.action_delete));
            alertDialog.setCancelable(false);
            alertDialog.setMessage(getString(R.string.message_delete_workstation));
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.action_delete), (dialog, which) -> {
                viewModel.deleteWorkstation(workstation, new OnAsyncEventListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "DeleteWorkstation: Success");
                        onBackPressed();
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Log.d(TAG, "DeleteWorkstation: Failure", e);
                    }
                });
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.action_cancel), (dialog, which) -> alertDialog.dismiss());
            alertDialog.show();
        }
        if(item.getItemId() == CREATE_WORKSTATION){

           if (etRam.getText().toString().isEmpty() || etStorage.getText().toString().isEmpty() ){
                createWorkstation(swScreens.isChecked(), swPortable.isChecked(),
                        etOs.getText().toString(),
                        0,
                        0,
                        etProcessor.getText().toString(),
                        spKeyboard.getSelectedItem().toString());
            }else{
                createWorkstation(swScreens.isChecked(), swPortable.isChecked(),
                        etOs.getText().toString(),
                        Integer.parseInt(etRam.getText().toString()),
                        Integer.parseInt(etStorage.getText().toString()),
                        etProcessor.getText().toString(),
                        spKeyboard.getSelectedItem().toString());
            }
        }
        if(item.getItemId() == MOVE_WORKSTATION){
               Intent intent = new Intent(WorkstationActivity.this, MainActivity.class);
                intent.setFlags(
                        Intent.FLAG_ACTIVITY_NO_ANIMATION |
                                Intent.FLAG_ACTIVITY_NO_HISTORY
                );
                intent.putExtra("workstationId", workstation.getId());
                intent.putExtra("officeId", workstation.getOfficeId());
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    //Initialise the default view
    private void initiateView(){
        isEditable = false;
        swScreens = findViewById(R.id.swScreens);
        swPortable = findViewById(R.id.swPortable);
        etOs = findViewById(R.id.etOs);
        etRam = findViewById(R.id.etRam);
        etStorage = findViewById(R.id.etStorage);
        etProcessor = findViewById(R.id.etProcessor);
        spKeyboard = findViewById(R.id.spKeyboard);
        twKeyboard = findViewById(R.id.twKeyboard);

        swScreens.setFocusable(false);
        swScreens.setClickable(false);
        swPortable.setFocusable(false);
        swPortable.setClickable(false);
        etOs.setFocusableInTouchMode(false);
        etOs.setFocusable(false);
        etOs.setEnabled(false);
        etRam.setFocusableInTouchMode(false);
        etRam.setFocusable(false);
        etRam.setEnabled(false);
        etStorage.setFocusableInTouchMode(false);
        etStorage.setFocusable(false);
        etStorage.setEnabled(false);
        etProcessor.setFocusableInTouchMode(false);
        etProcessor.setFocusable(false);
        etProcessor.setEnabled(false);
        spKeyboard.setEnabled(false);
        spKeyboard.setClickable(false);
        spKeyboard.setVisibility(View.INVISIBLE);
    }

    //Switch  to edit mode
    private void switchToEdit(){
        if(!isEditable){
            swScreens.setFocusable(true);
            swScreens.setClickable(true);
            swPortable.setFocusable(true);
            swPortable.setClickable(true);
            etOs.setFocusableInTouchMode(true);
            etOs.setFocusable(true);
            etOs.setEnabled(true);
            etRam.setFocusableInTouchMode(true);
            etRam.setFocusable(true);
            etRam.setEnabled(true);
            etStorage.setFocusableInTouchMode(true);
            etStorage.setFocusable(true);
            etStorage.setEnabled(true);
            etProcessor.setFocusableInTouchMode(true);
            etProcessor.setFocusable(true);
            etProcessor.setEnabled(true);
            spKeyboard.setVisibility(View.VISIBLE);
            spKeyboard.setEnabled(true);
            spKeyboard.setClickable(true);
            twKeyboard.setVisibility(View.INVISIBLE);
        }else{
            saveChanges(
                        swScreens.isChecked(), swPortable.isChecked(),
                        etOs.getText().toString(),
                        Integer.parseInt(etRam.getText().toString()),
                        Integer.parseInt(etStorage.getText().toString()),
                        etProcessor.getText().toString(),
                        spKeyboard.getSelectedItem().toString()
            );
            swScreens.setFocusable(false);
            swScreens.setClickable(false);
            swPortable.setFocusable(false);
            swPortable.setClickable(false);
            etOs.setFocusableInTouchMode(false);
            etOs.setFocusable(false);
            etOs.setEnabled(false);
            etRam.setFocusableInTouchMode(false);
            etRam.setFocusable(false);
            etRam.setEnabled(false);
            etStorage.setFocusableInTouchMode(false);
            etStorage.setFocusable(false);
            etStorage.setEnabled(false);
            etProcessor.setFocusableInTouchMode(false);
            etProcessor.setFocusable(false);
            etProcessor.setEnabled(false);
            spKeyboard.setEnabled(false);
            spKeyboard.setClickable(false);
                spKeyboard.setVisibility(View.INVISIBLE);
                twKeyboard.setVisibility(View.VISIBLE);
            }
            isEditable = !isEditable;
    }

    //Create a new Workstation
    private void createWorkstation(boolean screens, boolean portable, String os, Integer ram,
                                   Integer storage, String processor, String keyboard){


        if(os.isEmpty()){
            etOs.setError(getString(R.string.workstation_error_os));
            return;
        }
        if(ram == 0){
            etRam.setError(getString(R.string.workstation_error_rem));
            return;
        }
        if(storage == 0){
            etStorage.setError(getString(R.string.workstation_error_storage));
            return;
        }
        if(processor.isEmpty()){
            etProcessor.setError(getString(R.string.workstation_error_processor));
            return;
        }

        workstation = new WorkstationEntity();
        workstation.setScreens(screens);
        workstation.setPortable(portable);
        workstation.setOs(os);
        workstation.setRam(ram);
        workstation.setStorage(storage);
        workstation.setProcessor(processor);
        workstation.setKeyboardType(keyboard);
        workstation.setOfficeId(officeId);

        viewModel.createWorkstation(workstation, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "CreateWorkstation: Success");
                onBackPressed();
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "CreateWorkstation: Failure",e);
            }
        });
    }

    //Save changes when a workstation is edited
    private void saveChanges(boolean screens, boolean portable, String os, int ram,
                                   int storage, String processor, String keyboard){
        workstation.setScreens(screens);
        workstation.setPortable(portable);
        workstation.setOs(os);
        workstation.setRam(ram);
        workstation.setStorage(storage);
        workstation.setProcessor(processor);
        workstation.setKeyboardType(keyboard);

        viewModel.updateWorkstation(workstation, new OnAsyncEventListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "UpdateWorkstation: Success");
                setResponse(true);
                onBackPressed();
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(TAG, "UpdateWorkstation: Failure");
                setResponse(false);
                onBackPressed();
            }
        });
    }

    //Make toast to show the user if a workstation has been edited with no error
    private void setResponse(Boolean response){
        if(response){
            toast = Toast.makeText(this, getString(R.string.action_edited_workstation), Toast.LENGTH_LONG);
            toast.show();
        }else{
            toast = Toast.makeText(this, getString(R.string.action_error_workstation), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    //Update the content after we edit the workstation
    private void updateContent(){
        if(workstation != null){
            swScreens.setChecked(workstation.isScreens());
            swPortable.setChecked(workstation.isPortable());
            etOs.setText(workstation.getOs());
            etRam.setText(workstation.getRamString());
            etStorage.setText(workstation.getStorageString());
            etProcessor.setText(workstation.getProcessor());
            twKeyboard.setText(workstation.getKeyboardType());

        }
    }

}
