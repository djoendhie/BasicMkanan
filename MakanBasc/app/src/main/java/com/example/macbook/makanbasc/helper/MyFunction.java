package com.example.macbook.makanbasc.helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class MyFunction extends AppCompatActivity {
public Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    c=this;

    }


    public void mytoast(String pesan){
            Toast.makeText(c, pesan, Toast.LENGTH_SHORT).show();

       }

    public void aksesclass(Class kelastujuan){
        startActivity(new Intent(c,kelastujuan));
    }

}
