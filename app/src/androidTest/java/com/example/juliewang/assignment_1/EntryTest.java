package com.example.juliewang.assignment_1;

import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;

import java.util.ArrayList;


public class EntryTest extends ActivityInstrumentationTestCase2 {
    private ArrayList<Entry> entries = new ArrayList<Entry>();

    public EntryTest() {
        super(MainActivity.class);
    }

    public void testDate() {

        Entry test = new Entry("0", "0", (double) 0, (double) 0, (double) 0, "0", (double) 0);
        String date = new String("testing");
        test.setDate(date);
        entries.add(test);
        assertEquals(date, test.getDate());
        entries.clear();
    }

    public void testStation() {
        Entry test = new Entry("0", "0", (double) 0, (double) 0, (double) 0, "0", (double) 0);
        String station = new String("testing");
        test.setStation(station);
        entries.add(test);
        assertEquals(station, test.getStation());
        entries.clear();
    }

    public void testOdometer() {
        Entry test = new Entry("0", "0", (double) 0, (double) 0, (double) 0, "0", (double) 0);
        double odometer = 1234;
        test.setOdometer(odometer);
        entries.add(test);
        assertEquals(odometer, test.getOdometer());
        entries.clear();
    }

    public void testFuelGrade() {
        Entry test = new Entry("0", "0", (double) 0, (double) 0, (double) 0, "0", (double) 0);
        String fuel_grade = new String("testing");
        test.setFuel_grade(fuel_grade);
        entries.add(test);
        assertEquals(fuel_grade, test.getFuel_grade());
        entries.clear();
    }

    public void testFuel_Amount(){
        Entry test = new Entry("0", "0", (double) 0, (double) 0, (double) 0, "0", (double) 0);
        double fuel_amount = 1234;
        test.setFuel_amount(fuel_amount);
        entries.add(test);
        assertEquals(fuel_amount, test.getFuel_amount());
        entries.clear();
    }

    public void testFuelUnitCost(){
        Entry test = new Entry("0", "0", (double) 0, (double) 0, (double) 0, "0", (double) 0);
        double fuel_unit_cost = 1234;
        test.setUnit_cost(fuel_unit_cost);
        entries.add(test);
        assertEquals(fuel_unit_cost, test.getUnit_cost());
        entries.clear();
    }



}