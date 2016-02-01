package com.example.juliewang.assignment_1;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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
import java.util.IllegalFormatException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddEntry extends AppCompatActivity {
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private static final String FILENAME = "file.sav";
    private EditText add_date;
    private EditText add_station;
    private EditText add_fuel_grade;
    private EditText add_odometer;
    private EditText add_fuel_amount;
    private EditText add_fuel_unit_cost;
    private EditText add_fuel_total_cost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);



        Button addSave = (Button) findViewById(R.id.addSave);
        Button addCancel = (Button) findViewById(R.id.addCancel);
        add_date = (EditText) findViewById(R.id.add_date);
        add_station = (EditText) findViewById(R.id.add_station);
        add_fuel_grade = (EditText) findViewById(R.id.add_fuel_grade);
        add_odometer = (EditText) findViewById(R.id.add_odometer);
        add_fuel_amount = (EditText) findViewById(R.id.add_fuel_amount);
        add_fuel_unit_cost = (EditText) findViewById(R.id.add_fuel_unit_cost);
        add_fuel_total_cost =  (EditText) findViewById(R.id.add_fuel_total_cost);
        addSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String date = add_date.getText().toString();
                    if (date.isEmpty()) {
                        //Toast toast = Toast.makeText(context, text, duration); format
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a date", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    String station = add_station.getText().toString();
                    if (station.isEmpty()) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a station", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    Double odometer = Double.parseDouble(add_odometer.getText().toString());
                    if (odometer.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter an odometer reading", Toast.LENGTH_SHORT);
                        toast.show();

                    }
                    Double fuel_amount = Double.parseDouble(add_fuel_amount.getText().toString());
                    if (fuel_amount.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a fuel amount", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    Double fuel_unit_cost = Double.parseDouble(add_fuel_unit_cost.getText().toString());
                    if (fuel_unit_cost.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter a fuel unit cost", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    String fuel_grade = add_fuel_grade.getText().toString();
                    if (fuel_grade.equals("")) {
                        Toast toast = Toast.makeText(AddEntry.this, "Please enter the total cost of fuel ", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Double fuel_total_cost = fuel_amount * (fuel_unit_cost/100);
                        Entry mostRecentEntry = new Entry(date, station, odometer, fuel_amount, fuel_unit_cost, fuel_grade, fuel_total_cost);
                        loadFromFile();
                        entries.add(mostRecentEntry);

                        saveInFile();
                        finish();


                    }
                }catch (NumberFormatException e) {
                    Toast toast = Toast.makeText(AddEntry.this, "Error: Entry not complete. Please Complete", Toast.LENGTH_SHORT);
                    toast.show();

                }
            }

        });
        addCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void loadFromFile() {
        //everything in loadFromFile is copy/pasted from lonelytwitter exercise in the lab
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Entry>>() {
            }.getType();
            entries = gson.fromJson(in, listType);

        }catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            entries = new ArrayList<Entry>();
        }
        catch (IOException e) {
            //TODO Auto-generated catch block
            throw new RuntimeException();

        }
    }

    private void saveInFile() {
        //everything in saveinFile is copy/pasted from lonelytwitter exercise in the lab
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(entries, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
             //TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }




}
