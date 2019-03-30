package com.example.it_inventory.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Switch;

import com.example.it_inventory.R;

public class MySettings extends AppCompatActivity {
    private Switch mySwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            setTheme(R.style.DarkTheme);
        }
        else {
            setTheme(R.style.AppTheme);
            }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mySwitch = findViewById(R.id.myswitch);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {
            mySwitch.setChecked(true);
        }

        mySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
            }
            else
            {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
            }
        });
    }

}
