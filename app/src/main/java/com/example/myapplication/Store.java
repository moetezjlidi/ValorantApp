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

        ImageView featured = findViewById(R.id.bundleimg);
        Picasso.get().load("https://images-ext-1.discordapp.net/external/FVdz8kbC349nMjgNLhbkiGtXJ8nPtOcU5Y98Xgj6sIw/https/media.valorant-api.com/bundles/cd095669-4a29-a7f3-e00d-f694186863cc/displayicon2.png?width=1385&height=676").into(featured);
        Picasso.get().load("https://media.valorant-api.com/weaponskinchromas/20ebb41a-4edc-0fa3-f3e9-b3b5d91e8524/displayicon.png").into((ImageView) findViewById(R.id.offer1_img));
        Picasso.get().load("https://media.valorant-api.com/weaponskins/9e648b20-4ed5-1f34-87a9-979cbe9a958a/displayicon.png").into((ImageView) findViewById(R.id.offer1_img));
        Picasso.get().load("https://media.valorant-api.com/weaponskins/57523cf0-4574-968b-9f17-168e3bdb6d0d/displayicon.png").into((ImageView) findViewById(R.id.offer2_img));
        Picasso.get().load("https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png").into((ImageView) findViewById(R.id.offer3_img));
        //
        Picasso.get().load("https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png").into((ImageView) findViewById(R.id.offer4_img));
        //
        //https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png
        //
    }
}