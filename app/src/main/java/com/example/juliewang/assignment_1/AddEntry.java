package com.example.juliewang.assignment_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddEntry extends AppCompatActivity {
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private static final String FILENAME = "file.sav";
    private EditText add_date;
    private EditText add_station;
    private EditText add_odometer;
    private EditText add_fuel_amount;
    private EditText add_fuel_unit_cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String date = add_date.getText().toString();
                    if (date.isEmpty()) {
                        //Toast toast = Toast.makeText(context, text, duration); format
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a date", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (add_station.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a station", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (add_odometer.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a station", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (add_fuel_amount.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a station", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (add_fuel_unit_cost.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a station", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        //ViewEntry latestEntry = new ViewEntry(add_date, add_station, add_odometer, add_fuel_amount, add_fuel_unit_cost);

                    }


                }catch () {
                }
            }

        }
    }



}
