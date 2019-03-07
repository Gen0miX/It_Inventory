package com.example.it_inventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



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
    }

    public void sendMessage(View view) {
     /*   Intent intent = new Intent(this, EditActivity.class);
        EditText editTextRam = (EditText) findViewById(R.id.edit_RAM);
        EditText editTextStorage = (EditText) findViewById(R.id.edit_storage);
        EditText editTextProcessor = (EditText) findViewById(R.id.edit_processor);
        String ram = editTextRam.getText().toString();
        String storage = editTextStorage.getText().toString();
        String processor = editTextProcessor.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/
    }
}
