package com.example.juliewang.assignment_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ViewEntry extends AppCompatActivity {
    //initialize variables
    private static final String FILENAME = "file.sav";
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private Entry current;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        //initialize button
        Button Cancel = (Button) findViewById(R.id.Cancel);

        //initialize proper format for all decimal info, odometer and fuel unit cost overlap
        DecimalFormat odometerFormat = new DecimalFormat("#.#");
        DecimalFormat fuelAmountFormat = new DecimalFormat("#.###");

        //load all entries
        loadFromFile();

        //set entry being displayed to the entry selected to view
        current = entries.get(SavedEntries.selectedEntry);
        TextView date = (TextView) findViewById(R.id.date_info);
        TextView station = (TextView) findViewById(R.id.station_info);
        TextView odometer = (TextView) findViewById(R.id.odometer_info);
        TextView fuel_grade = (TextView) findViewById(R.id.fuel_grade_info);
        TextView fuel_amount = (TextView) findViewById(R.id.fuel_amount_info);
        TextView fuel_unit_cost = (TextView) findViewById(R.id.fuel_unit_cost_info);
        TextView fuel_total_cost = (TextView) findViewById(R.id.fuel_cost_info);
        //show proper date

        date.setText(current.getDate());
        station.setText(current.getStation());
        DecimalFormat odometer_correct_format = new DecimalFormat("#.#");
        odometer.setText(String.valueOf(odometer_correct_format.format(current.getOdometer())));
        fuel_grade.setText(current.getFuel_grade());
        DecimalFormat fuel_amount_correct_format = new DecimalFormat("#.###");
        fuel_amount.setText(String.valueOf(fuel_amount_correct_format.format(current.getFuel_amount())));
        fuel_unit_cost.setText(String.valueOf(String.valueOf(current.getUnit_cost())));
        DecimalFormat total_cost_correct_format= new DecimalFormat("#.##");
        fuel_total_cost.setText(String.valueOf(total_cost_correct_format.format(current.getFuel_total_cost())));


        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void loadFromFile() {
        //Most of code from lab 3, edited to work with my application, loads all entrys into entries
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Entry>>() {}.getType();
            entries = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            entries = new ArrayList<>();
        }
    }
}
