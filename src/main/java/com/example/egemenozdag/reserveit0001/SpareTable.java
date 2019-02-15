package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SpareTable extends AppCompatActivity {



    EditText dateInput;
    EditText timeinput;
    EditText tableinput;
    EditText number;
    private Button button;

    Reservation e;
    Reservation a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spare_table);


        dateInput = (EditText) findViewById(R.id.DateInput);
        timeinput = (EditText) findViewById(R.id.TimeInput);
        tableinput = (EditText) findViewById(R.id.TableNoForRes);
        number = (EditText) findViewById(R.id.NumberOfPeople);

        e = new Reservation();
        a = new Reservation();

        String reservationinfo= "Date: " + dateInput.getText().toString() + "Time: " + timeinput.getText().toString()
                + "Table: " +tableinput.getText().toString() + "Number of People: " + number.getText().toString();
        e.addReservation(reservationinfo);
        e.addReservation("A");





        button = (Button) findViewById(R.id.MakeReservation);
        button.setOnClickListener(new View.OnClickListener(){




            public void onClick(View v){


                onBackPressed();
            }
        });


    }



}
