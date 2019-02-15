package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfile extends AppCompatActivity {

     Button button1;
     Button button2;
     TextView namesurname;
     TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        namesurname = (TextView) findViewById(R.id.namesurname);
        username = (TextView) findViewById(R.id.username);
        username.setText("AlpUygur");
        namesurname.setText("Alp Uygur");

        button1 = (Button) findViewById(R.id.makeres);
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openRestaurantPage();
            }
        });

        button2 = (Button) findViewById(R.id.myReservations);
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openRes();
            }
        });

    }
    public void openRestaurantPage(){
        Intent intent = new Intent(UserProfile.this, findingRestaurant.class);
        startActivity(intent);
    }
    public void openRes(){
        Intent intent = new Intent(UserProfile.this, Reservations.class);
        startActivity(intent);
    }
}

