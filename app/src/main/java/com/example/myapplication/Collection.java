package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Login.Auth;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        Intent i = this.getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        String BUDDY_URL = "https://media.valorant-api.com/buddies/";
        String Chroma_URL  = "https://media.valorant-api.com/weaponskinchromas/";
        String CARD_URL = "https://media.valorant-api.com/playercards/";
        String TITLE_URL = "https://valorant-api.com/v1/playertitles/";
        String SPRAY_URL = "https://media.valorant-api.com/sprays/";
        String defaults = "2f93861d-4b2f-2175-af0c-3ba0c736e257\n" +
                "b33de820-4061-8b85-31ce-808f1a2c58f5\n" +
                "19629ae1-4996-ae98-7742-24a240d41f99\n" +
                "bf35f404-4a14-6953-ced2-5bafd21639a0\n" +
                "52221ba2-4e4c-ec76-8c81-3483506d5242\n" +
                "b71ae8d6-44bb-aa4c-0d2a-dc9ed9e66410\n" +
                "3d8ffcfe-4786-0180-42d7-e1be18dd1cab\n" +
                "dc99ed5a-4d75-87a0-c921-75963ea3c1e1\n" +
                "4b2d5b4f-4955-4208-286c-abadec250cdd\n" +
                "947a28b6-4e0f-61fb-e795-bc9a5e7b7129\n" +
                "5a59bd61-48a9-af61-c00f-4aa21deca9a8\n" +
                "95608504-4c8b-1408-1612-0f8200421c49\n" +
                "4914f50d-49f9-6424-ca80-9486c45a138d\n" +
                "0f934388-418a-a9e7-42a7-21b27402e46c\n" +
                "1afec971-4170-f29b-1c94-07a0eff270ab\n" +
                "a9aaccca-4cdc-02ea-1d7e-89bbacecc0e2\n" +
                "31bb2115-4c62-d37c-43c4-11b8fee7f212\n" +
                "cac83e5c-47a1-3519-5420-1db1fdbc4892";
        try {
            ((TextView)findViewById(R.id.username_collec)).setText(auth.getName());
            JSONObject data = new JSONObject(auth.getMycollection());
            Picasso.get().load(CARD_URL + data.getJSONObject("PlayerCard").getString("ID") + "/largeart.png" ).into((ImageView) findViewById(R.id.agent_card));
            Picasso.get().load(SPRAY_URL + data.getJSONArray("Sprays").getJSONObject(2).getString("SprayID")+"/displayicon.png").into((ImageView)findViewById(R.id.tag1));
            Picasso.get().load(SPRAY_URL + data.getJSONArray("Sprays").getJSONObject(0).getString("SprayID")+"/displayicon.png").into((ImageView)findViewById(R.id.tag2));
            Picasso.get().load(SPRAY_URL + data.getJSONArray("Sprays").getJSONObject(1).getString("SprayID")+"/displayicon.png").into((ImageView)findViewById(R.id.tag3));

            //CLASSIC
            JSONObject classic = data.getJSONArray("Guns").getJSONObject(8);
            if (classic.has("CharmID")){
                Picasso.get().load(BUDDY_URL+ classic.getString("CharmID")+"/displayicon.png").into((ImageView)findViewById(R.id.buddy_classic));
                ((ImageView)findViewById(R.id.buddy_classic)).setVisibility(View.VISIBLE);
            }
            if (defaults.contains(classic.getString("ChromaID"))){

                Picasso.get().load("https://media.valorant-api.com/weapons/"+classic.getString("ID")+"/displayicon.png").into((ImageView)findViewById(R.id.classic_img));
            }
            else{
                Picasso.get().load(Chroma_URL+ classic.getString("ChromaID") + "/displayicon.png").into((ImageView)findViewById(R.id.classic_img));
            }

            //SHORTY
            JSONObject shorty = data.getJSONArray("Guns").getJSONObject(11);
            if (shorty.has("CharmID")){
                Picasso.get().load(BUDDY_URL+ shorty.getString("CharmID")+"/displayicon.png").into((ImageView)findViewById(R.id.buddy_shorty));
                ((ImageView)findViewById(R.id.buddy_shorty)).setVisibility(View.VISIBLE);
            }
            if (defaults.contains(shorty.getString("ChromaID"))){
                Picasso.get().load("https://media.valorant-api.com/weapons/"+shorty.getString("ID")+"/displayicon.png").into((ImageView)findViewById(R.id.shorty_pic));
            }
            else{
                Picasso.get().load(Chroma_URL+ shorty.getString("ChromaID") + "/displayicon.png").into((ImageView)findViewById(R.id.shorty_pic));
            }
            //STRINGER
            JSONObject stinger = data.getJSONArray("Guns").getJSONObject(16);
            if (stinger.has("CharmID")){
                Picasso.get().load(BUDDY_URL+ stinger.getString("CharmID")+"/displayicon.png").into((ImageView)findViewById(R.id.buddy_stinger));
                ((ImageView)findViewById(R.id.buddy_stinger)).setVisibility(View.VISIBLE);
            }
            if (defaults.contains(stinger.getString("ChromaID"))){
                Picasso.get().load("https://media.valorant-api.com/weapons/"+stinger.getString("ID")+"/displayicon.png").into((ImageView)findViewById(R.id.stinger_pic));
            }
            else{
                Picasso.get().load(Chroma_URL+ stinger.getString("ChromaID") + "/displayicon.png").into((ImageView)findViewById(R.id.stinger_pic));
            }
            //SPECTRE
            JSONObject spectre = data.getJSONArray("Guns").getJSONObject(15);
            if (spectre.has("CharmID")){
                Picasso.get().load(BUDDY_URL+ spectre.getString("CharmID")+"/displayicon.png").into((ImageView)findViewById(R.id.buddy_spectre));
                ((ImageView)findViewById(R.id.buddy_spectre)).setVisibility(View.VISIBLE);
            }
            if (defaults.contains(spectre.getString("ChromaID"))){
                Picasso.get().load("https://media.valorant-api.com/weapons/"+spectre.getString("ID")+"/displayicon.png").into((ImageView)findViewById(R.id.spectre_pic));
            }
            else{
                Picasso.get().load(Chroma_URL+ spectre.getString("ChromaID") + "/displayicon.png").into((ImageView)findViewById(R.id.spectre_pic));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}