package com.example.egemenozdag.reserveit0001;

import java.util.ArrayList;

public class Reservation {

    public ArrayList<String> getRes() {
        return res;
    }

    public void setRes(ArrayList<String> res) {
        this.res = res;
    }

    ArrayList<String> res;

    public Reservation(){

        res=new ArrayList<String>();
    }


    public void addReservation(String reservationinfo) {

        this.res.add(reservationinfo);

    }
}
