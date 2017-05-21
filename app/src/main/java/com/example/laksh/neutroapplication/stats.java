package com.example.laksh.neutroapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class stats extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter<CharSequence>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        spinner();
    }
    public void spinner() {
        spinner = (Spinner) findViewById(R.id.spinnerBluetooth);
        adapter = ArrayAdapter.createFromResource(this, R.array.bluetooth, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position> 0) {
                    Toast.makeText(stats.this, " Device Connected ! ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(stats.this,HomeActivity.class));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
