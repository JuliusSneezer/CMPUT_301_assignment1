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
Button cancelEdit = (Button) findViewById(R.id.Cancel);
Button doneEdit = (Button) findViewById(R.id.Done);

//load all the log entries
loadFromFile();

//set entry to the entry selected to edit
current = entries.get(SavedEntries.selectedEntry);


//show proper date
final TextView dateInfo = (TextView) findViewById(R.id.edit_date);
dateInfo.setText(current.getDate());

//show proper station
final TextView stationInfo = (TextView) findViewById(R.id.edit_station);
stationInfo.setText(current.getStation());

//show proper odometer reading
final TextView odometerInfo = (TextView) findViewById(R.id.edit_odometer);
odometerInfo.setText(String.valueOf(current.getOdometer()));

//show proper fuel grade
final TextView fuelGradeInfo = (TextView) findViewById(R.id.edit_fuel_grade);
fuelGradeInfo.setText(current.getFuel_grade());

//show proper fuel amount
final TextView fuelAmountInfo = (TextView) findViewById(R.id.edit_fuel_amount);
fuelAmountInfo.setText(String.valueOf(current.getFuel_amount()));

//show proper fuel unit cost
final TextView fuelUnitCostInfo = (TextView) findViewById(R.id.edit_fuel_unit_cost);
fuelUnitCostInfo.setText(String.valueOf(current.getFuel_total_cost()));

//return to main menu without making any changes to the entry
cancelEdit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish();
    }
});

//return to main menu and save changes made to the entry if all info entered properly
doneEdit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        try{
            //gets all info and converts it to proper type
            String date = dateInfo.getText().toString();
            String station = stationInfo.getText().toString();
            String odometerString = odometerInfo.getText().toString();
            double odometer = Double.parseDouble(odometerString);
            String fuelGrade = fuelGradeInfo.getText().toString();
            String fuelAmountString = fuelAmountInfo.getText().toString();
            double fuelAmount = Double.parseDouble(fuelAmountString);
            String fuelUnitCostString = fuelUnitCostInfo.getText().toString();
            double fuelUnitCost = Double.parseDouble(fuelUnitCostString);

            //make sure no string fields are blank
            if (date.equals("")){
                Toast.makeText(EditEntry.this, "You need to enter a date", Toast.LENGTH_SHORT).show();
            } else if (station.equals("")){
                Toast.makeText(EditEntry.this, "You need to enter a station", Toast.LENGTH_SHORT).show();
            } else if (fuelGrade.equals("")){
                Toast.makeText(EditEntry.this, "You need to enter a fuel grade", Toast.LENGTH_SHORT).show();
            } else {
                //calculate fuel cost and save the changes to the entry, then return to main menu
                Double fuelCost = fuelAmount * (fuelUnitCost/100);
                current.setEntry(date, station, odometer, fuelAmount, fuelUnitCost, fuelGrade, fuelCost);
                saveInFile();
                finish();
            }
        } catch (NumberFormatException e) {
            //if any number blank or not a vaild number, that error is caught here
            Toast.makeText(EditEntry.this, "Not all data present", Toast.LENGTH_SHORT).show();
        }
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

private void saveInFile() {
//Most of code from lab 3, edited to work with my application, saves all entrys in entries into a file
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