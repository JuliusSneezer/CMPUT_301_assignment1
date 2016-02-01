package com.example.juliewang.assignment_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class EditEntry extends AppCompatActivity {
    //initialize variables
    private static final String FILENAME = "file.sav";
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    private Entry current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);

        //initialize buttons
        Button Cancel = (Button) findViewById(R.id.Cancel);
        Button Done = (Button) findViewById(R.id.Done);

        //loading entries
        loadFromFile();

        //set current entry to be edited  the be the entry the user has selected
        current = entries.get(SavedEntries.selectedEntry);
        //project classmate helped me with the final
        //these will ensure that the update overrides previous entries
        final TextView date = (TextView) findViewById(R.id.edit_date);
        final TextView station = (TextView) findViewById(R.id.edit_station);
        final TextView odometer = (TextView) findViewById(R.id.edit_odometer);
        final TextView fuel_grade = (TextView) findViewById(R.id.edit_fuel_grade);
        final TextView fuel_amount = (TextView) findViewById(R.id.edit_fuel_amount);
        final TextView fuel_unit_cost = (TextView) findViewById(R.id.edit_fuel_unit_cost);
        //sets text for user input
        date.setText(current.getDate());
        station.setText(current.getStation());
        fuel_grade.setText(current.getFuel_grade());
        odometer.setText(String.valueOf(new DecimalFormat("#.#").format(current.getOdometer())));
        fuel_amount.setText(String.valueOf(new DecimalFormat("#.###").format(current.getFuel_amount())));
        fuel_unit_cost.setText(String.valueOf(new DecimalFormat("#.#").format(current.getUnit_cost())));



        //return to main menu and cancels action
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //saves the input
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //gets all info and converts it to proper type
                    String date_ = date.getText().toString();
                    String station_ = station.getText().toString();
                    String odometer_ = odometer.getText().toString();
                    Double odometer_final = Double.parseDouble(odometer_);
                    String fuel_grade_ = fuel_grade.getText().toString();
                    String fuel_amount_ = fuel_amount.getText().toString();
                    Double fuel_amount_final = Double.parseDouble(fuel_amount_);
                    String fuel_unit_cost_ = fuel_unit_cost.getText().toString();
                    Double fuel_unit_cost_final = Double.parseDouble(fuel_unit_cost_);

                    //checks user input for type inconsistency
                    if (date_.isEmpty()) {
                        Toast toast = Toast.makeText(EditEntry.this, "Please enter a date", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    if (station_.isEmpty()) {
                        Toast toast = Toast.makeText(EditEntry.this, "Please enter a station", Toast.LENGTH_SHORT);
                        toast.show();
                    }


                    if (odometer_final.equals("")) {
                        Toast toast = Toast.makeText(EditEntry.this, "Please enter an odometer reading", Toast.LENGTH_SHORT);
                        toast.show();

                    }

                    if (fuel_amount_.equals("")) {
                        Toast toast = Toast.makeText(EditEntry.this, "Please enter a fuel amount", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    if (fuel_unit_cost_final.equals("")) {
                        Toast toast = Toast.makeText(EditEntry.this, "Please enter a fuel unit cost", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                    if (fuel_grade_.equals("")) {
                        Toast toast = Toast.makeText(EditEntry.this, "Please enter the total cost of fuel ", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        //calculate total fuel cost
                        Double fuel_total_cost = fuel_amount_final * (fuel_unit_cost_final / 100);
                        //updates the entry in Entry
                        current.setEntry(date_, station_, odometer_final, fuel_amount_final, fuel_unit_cost_final, fuel_grade_, fuel_total_cost);

                        //saves change
                        saveInFile();
                        finish();
                    }

                } catch (NumberFormatException e) {
                    //final check for inconsistencies
                    Toast toast = Toast.makeText(EditEntry.this, "Not all data present", Toast.LENGTH_SHORT);
                    toast.show();
                }

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

        } catch (FileNotFoundException e) {
            entries = new ArrayList<>();
        }
    }

    private void saveInFile() {
        //everything in saveInFile is copy/pasted from lonelytwitter exercise in the lab
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(entries, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}