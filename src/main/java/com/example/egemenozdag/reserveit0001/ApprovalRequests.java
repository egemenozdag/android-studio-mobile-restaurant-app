package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ApprovalRequests extends AppCompatActivity {


    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approval_requests);

        button = (Button) findViewById(R.id.accept);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                accept();
            }
        });

        button = (Button) findViewById(R.id.decline);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                decline();
            }
        });

    }

    public void accept(){
        Intent intent = new Intent(ApprovalRequests.this, adminProfile.class);
        startActivity(intent);
    }
    public void decline(){
        Intent intent = new Intent(ApprovalRequests.this, adminProfile.class);
        startActivity(intent);
    }


}
