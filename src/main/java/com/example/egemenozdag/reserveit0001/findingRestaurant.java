package com.example.egemenozdag.reserveit0001;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class findingRestaurant extends AppCompatActivity {


    ListView RestaurantSearch;
    ArrayAdapter<String> adapter;
    Button button;
    AdapterView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_finding);

        RestaurantSearch = (ListView) findViewById(R.id.findingRestaurant);

        ArrayList<String> arrayRestaurant = new ArrayList<>();
        arrayRestaurant.add("Aspava");
        arrayRestaurant.add("Route");
        arrayRestaurant.add("Noxus");
        arrayRestaurant.add("OT");
        arrayRestaurant.add("Bigos");
        arrayRestaurant.add("Ezgi");
       // arrayRestaurant.addAll(Arrays.asList(getResources().getStringArray(R.array.my_Restaurants)));

        adapter = new ArrayAdapter<String> (

                findingRestaurant.this,
                android.R.layout.simple_list_item_1,
                arrayRestaurant

        );

        RestaurantSearch.setAdapter(adapter);

        RestaurantSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 4) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 5) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 6) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }

                if (position == 7) {
                    Intent myIntent = new Intent(view.getContext(), RestaurantPage.class);
                    startActivityForResult(myIntent, 0);
                }
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.findingRestaurant);
        SearchView searchView = (SearchView) item.getActionView();




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });



        return super.onCreateOptionsMenu(menu);
    }

    public void openRestaurantProfile(){
        Intent intent = new Intent(findingRestaurant.this, restaurantProfile.class);
        startActivity(intent);
    }
}



