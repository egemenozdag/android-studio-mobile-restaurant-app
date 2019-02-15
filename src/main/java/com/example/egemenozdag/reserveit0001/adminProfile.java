package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminProfile extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        button = (Button) findViewById(R.id.ReservationRequests);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openRequests();
            }
        });

        button = (Button) findViewById(R.id.Evalutions);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openEvalutions();
            }
        });

    }

    public void openRequests(){
        Intent intent = new Intent(adminProfile.this, ApprovalRequests.class);
        startActivity(intent);
    }

    public void openEvalutions(){
        Intent intent = new Intent(adminProfile.this, ApprovalRequests.class);
        startActivity(intent);
    }
}
