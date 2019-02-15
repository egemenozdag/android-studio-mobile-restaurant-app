package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RestaurantPage extends AppCompatActivity {

    private Button button;
    TextView name;
    TextView address;
    private FirebaseAuth auth;
    private FirebaseUser firebaseuser;
    private DatabaseReference DB;
    private String pass=null;
    private String gelMail=null;
    private String corpN=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);
        DB = FirebaseDatabase.getInstance().getReference().child(firebaseuser.getUid());
        auth = FirebaseAuth.getInstance();
        firebaseuser = auth.getCurrentUser();
        DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                corpN = dataSnapshot.child("Corp Name").getValue().toString();
                gelMail = dataSnapshot.child("Mail").getValue().toString();
                pass = dataSnapshot.child("Pass").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        name = (TextView) findViewById(R.id.restaurantname);
        address =(TextView) findViewById(R.id.address);

        name.setText(Toast.makeText(getApplicationContext(),corpN,Toast.LENGTH_LONG).show());
        address.setText(Toast.makeText(getApplicationContext(),pass,Toast.LENGTH_LONG).show());

        button = (Button) findViewById(R.id.makeres);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSpareTable();
            }
        });

    }

    public void openSpareTable(){
        Intent intent = new Intent(RestaurantPage.this, SpareTable.class);
        startActivity(intent);
    }
}
