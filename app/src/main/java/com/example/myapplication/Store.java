package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Store extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        ImageView featured = findViewById(R.id.featured);
        Picasso.get().load("https://media.valorant-api.com/weaponskinchromas/9667983e-4c8c-e5b2-68d7-be84f9b3d46c/displayicon.png").into(featured);
        Picasso.get().load("https://media.valorant-api.com/weaponskinchromas/20ebb41a-4edc-0fa3-f3e9-b3b5d91e8524/displayicon.png").into((ImageView) findViewById(R.id.offer1));
        Picasso.get().load("https://media.valorant-api.com/weaponskins/9e648b20-4ed5-1f34-87a9-979cbe9a958a/displayicon.png").into((ImageView) findViewById(R.id.offer2));
        Picasso.get().load("https://media.valorant-api.com/weaponskins/57523cf0-4574-968b-9f17-168e3bdb6d0d/displayicon.png").into((ImageView) findViewById(R.id.offer3));
        Picasso.get().load("https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png").into((ImageView) findViewById(R.id.offer5));
        //
        Picasso.get().load("https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png").into((ImageView) findViewById(R.id.offer4));
        //
        //https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png
        //
    }
}