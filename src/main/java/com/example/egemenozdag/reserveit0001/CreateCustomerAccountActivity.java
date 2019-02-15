
package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class CreateCustomerAccountActivity extends AppCompatActivity {

    private Button button;
    private TextInputLayout userName;
    private TextInputLayout Name;
    private TextInputLayout LastName;
    private TextInputLayout email;
    private TextInputLayout password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        userName = (TextInputLayout) findViewById(R.id.UserName) ;
        Name = (TextInputLayout) findViewById(R.id.name) ;
        LastName = (TextInputLayout) findViewById(R.id.lastname) ;
        email = (TextInputLayout) findViewById(R.id.email) ;
        password = (TextInputLayout) findViewById(R.id.password) ;



        button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                openLoginActivity();
            }
        });

    }

    public void openLoginActivity(){
        Intent intent = new Intent(CreateCustomerAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}