//date (entered, yyyy-mm-dd format, e.g., 2016-01-18)
//station (entered, textual, e.g., Costco)
//odometer reading (entered in km, numeric to 1 decimal place)
//fuel amount (entered in L, numeric to 3 decimal places)
//fuel unit cost (entered in cents per L, numeric to 1 decimal place)
package com.example.juliewang.assignment_1;


import java.util.Date;
/**
 * Created by juliewang on 16-01-23.
 */
public abstract class Entry {
    protected String date;
    protected String station;
    protected Double odometer;
    protected Double fuel_amount;
    protected Double unit_cost;

    public Entry(String date, String station, Double odometer, Double fuel_amount, Double unit_cost) {
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.fuel_amount = fuel_amount;
        this.unit_cost = unit_cost;

    }
    public void setEntry(String date, String station, Double odometer, Double fuel_amount, Double unit_cost){
        this.date = date;
        this.station = station;
        this.odometer = odometer;
        this.fuel_amount = fuel_amount;
        this.unit_cost = unit_cost;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public void setOdometer(Double odometer) {
        this.odometer = odometer;
    }

    public void setFuel_amount(Double fuel_amount) {
        this.fuel_amount = fuel_amount;
    }

    public void setUnit_cost(Double unit_cost) {
        this.unit_cost = unit_cost;
    }

    public Double getUnit_cost() {
        return unit_cost;
    }

}

