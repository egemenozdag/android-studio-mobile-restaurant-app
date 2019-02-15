package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Reservations extends AppCompatActivity {


    public Reservations(){

    }

    ListView reslist;
    ArrayAdapter<String> adapter;
    Button button;
    AdapterView a;

    public ArrayList<String> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<String> reservations) {
        this.reservations = reservations;
    }

    public ArrayList<String> reservations;
    Reservation e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        reslist = (ListView) findViewById(R.id.reslist);

        e =  new Reservation();
        reservations = new ArrayList<String>();

        for(int i=0; i<e.res.size(); i++){
            reservations.add(e.res.get(i));
        }


        adapter = new ArrayAdapter<String> (

                Reservations.this,
                android.R.layout.simple_list_item_1,
                reservations

        );

        reslist.setAdapter(adapter);

    }

}
