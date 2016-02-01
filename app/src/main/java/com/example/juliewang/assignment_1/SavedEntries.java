package com.example.juliewang.assignment_1;
import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class SavedEntries extends AppCompatActivity {
    private ArrayList<Entry> entries = new ArrayList<Entry>();
    public static int selectedEntry;
    //http://developer.android.com/reference/android/widget/RadioGroup.html
    private RadioButton[] radioArray;
    private double total = 0;
    private static final String FILENAME = "file.sav";
    protected Entry current;

    protected void onStart() {
        super.onStart();
        loadFromFile();

        TextView totalFuelAmount = (TextView) findViewById(R.id.total_fuel_cost);
        total = 0;
        for (int i = 0; i < entries.size(); i++) {
            current = entries.get(i);
            total = total + current.getFuel_total_cost();
        }

        DecimalFormat fuelFormat = new DecimalFormat("#.##");
        totalFuelAmount.setText(String.valueOf(fuelFormat.format(total)));

        radioArray = new RadioButton[entries.size()];
        addButtons(entries.size());
        for (int i = 1; i <= radioArray.length; i++) {
            RadioButton button = radioArray[i - 1];
            button.setChecked(false);
        }
        if (SavedEntries.selectedEntry != -1) {
            RadioButton button = radioArray[SavedEntries.selectedEntry];
            button.setChecked(true);
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_entries);

        loadFromFile();
        Button saveCancel = (Button) findViewById(R.id.Cancel);
        radioArray = new RadioButton[entries.size()];
        addButtons(entries.size());
        Button selectOK = (Button) findViewById(R.id.selectOK);
        RadioGroup radio = (RadioGroup) findViewById(R.id.radioArray);

        for (int i = 1; i <= radioArray.length; i++) {
            RadioButton button = radioArray[i - 1];
            button.setChecked(false);
        }
        SavedEntries.selectedEntry = -1;

        selectOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (MainActivity.CheckEdit == false)

                {
                    if (SavedEntries.selectedEntry != -1) {
                        startActivity(new Intent(SavedEntries.this, ViewEntry.class));


                    } else {
                        Toast toast = Toast.makeText(SavedEntries.this, "No Entry Chosen, Please select one ", Toast.LENGTH_SHORT);
                        toast.show();
                    }

                }
                if (MainActivity.CheckEdit == true) {
                    if (SavedEntries.selectedEntry != -1) {
                        startActivity(new Intent(SavedEntries.this, EditEntry.class));
                        MainActivity.CheckEdit = false;
                        finish();
                    } else {
                        Toast toast = Toast.makeText(SavedEntries.this, "No Entry Chosen, Please select one ", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }

        });
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int ID) {
                for (int i = 0; i < entries.size(); i++) {
                    if (ID == radioArray[i].getId()) {
                        SavedEntries.selectedEntry = i;
                    }
                }
            }

        });

        saveCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SavedEntries.selectedEntry = -1;

    }

    private void loadFromFile() {
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
    private void addButtons(int size) {
        ((ViewGroup) findViewById(R.id.radioArray)).removeAllViews();
        for (int i = 1; i <= size; i++) {
            RadioButton button = new RadioButton(this);
            button.setId(1-1 + i);//TODO 1-1 because just using i raises an error
            if (MainActivity.CheckEdit == true) {
                button.setText("Select" + (button.getId()) + "to edit");
            } else {
                button.setText("Select" + (button.getId()) + "to view");
            }
            button.setChecked(false);
            radioArray[i - 1] = button;
            ((ViewGroup) findViewById(R.id.radioArray)).addView(radioArray[i - 1]);
        }
    }

}
