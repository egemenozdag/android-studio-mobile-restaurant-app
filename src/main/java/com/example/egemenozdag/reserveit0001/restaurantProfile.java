package com.example.egemenozdag.reserveit0001;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class restaurantProfile extends AppCompatActivity {

    private Button button;
    Reservation e;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

        e= new Reservation();
        e.addReservation("A");

        button = (Button) findViewById(R.id.editREST);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openCreateRestaurant();
            }
        });

        button = (Button) findViewById(R.id.editDesign);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openeditDesign();
            }
        });

        button = (Button) findViewById(R.id.SpareTable);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openSpareTable();
            }
        });

        button = (Button) findViewById(R.id.reservations);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openReservations();
            }
        });

        Button buttonLoadImage = (Button) findViewById(R.id.addMenu);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        Button buttonLoadImage2 = (Button) findViewById(R.id.addPlan);
        buttonLoadImage2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        Button buttonLoadImage3 = (Button) findViewById(R.id.addReport);
        buttonLoadImage3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

    }
    public void openCreateRestaurant(){
        Intent intent = new Intent(restaurantProfile.this, createRestaurant.class);
        startActivity(intent);
    }
    public void openSpareTable(){
        Intent intent = new Intent(restaurantProfile.this, SpareTable.class);
        startActivity(intent);
    }

    public void openeditDesign(){
        Intent intent = new Intent(restaurantProfile.this, editDesign.class);
        startActivity(intent);
    }

    public void openReservations(){
        Intent intent = new Intent(restaurantProfile.this, Reservations.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.imageView6);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }

    }
}
