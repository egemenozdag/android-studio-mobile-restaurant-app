package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class createRestaurantAccount extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_restaurant_account);

        button = (Button) findViewById(R.id.saveRestaurant);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openLoginActivity();
            }
        });






    }
    public void userAccount(){

    }
    public void openLoginActivity(){
        Intent intent = new Intent(createRestaurantAccount.this, LoginActivity.class);
        startActivity(intent);
    }
}