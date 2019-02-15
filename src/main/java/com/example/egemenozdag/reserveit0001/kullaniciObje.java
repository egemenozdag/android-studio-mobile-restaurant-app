package com.example.egemenozdag.reserveit0001;

import android.widget.TextView;

public class kullaniciObje {

     static String userName;
    static String Name;
    static  String LastName;
    static   String email;
    static   String password;
    public static kullaniciObje[] kullanici = new kullaniciObje[1000];
    public static int userNumerator ;


    public kullaniciObje(String user, String isim, String soyad, String mail, String parola){
        this.userName = user;
        this.Name = isim;
        this.LastName = soyad;
        this.email = mail;
        this.password = parola;
    }

    public static void gelistir(String user, String isim, String soyad, String mail, String parola){
        kullanici[userNumerator++] = new kullaniciObje(user,isim,soyad,mail,parola);
    }



}