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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection extends AppCompatActivity {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        Intent i = this.getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        //04af080a-4071-487b-61c0-5b9c0cfaac74:3
        //5863985e-43ac-b05d-cb2d-139e72970014:1
        //0814b2fe-4512-60a4-5288-1fbdcec6ca48:2
        try {
            ((TextView)findViewById(R.id.username_collec)).setText(auth.getName());
            ((TextView)findViewById(R.id.Title)).setText(auth.getPlayer_title());
            JSONObject data = new JSONObject(auth.getMycollection());
            JSONArray sprays = data.getJSONArray("Sprays");
            for (int j = 0;j<sprays.length();j++){
                String slot = sprays.getJSONObject(j).getString("EquipSlotID");
                if (slot.equals("5863985e-43ac-b05d-cb2d-139e72970014")){
                    Picasso.get().load(SPRAY_URL + sprays.getJSONObject(j).getString("SprayID")+"/displayicon.png").into((ImageView)findViewById(R.id.tag1));

                }
                else if (slot.equals("0814b2fe-4512-60a4-5288-1fbdcec6ca48")){
                    Picasso.get().load(SPRAY_URL + sprays.getJSONObject(j).getString("SprayID")+"/displayicon.png").into((ImageView)findViewById(R.id.tag2));
                }
                else{
                    Picasso.get().load(SPRAY_URL + sprays.getJSONObject(j).getString("SprayID")+"/displayicon.png").into((ImageView)findViewById(R.id.tag3));
                }
            }
            Picasso.get().load(CARD_URL + data.getJSONObject("Identity").getString("PlayerCardID") + "/largeart.png" ).into((ImageView) findViewById(R.id.agent_card));
            //CLASSIC
            this.UpdateWeapon(8 , data , R.id.classic_img  , R.id.buddy_classic);

            //SHORTY
            this.UpdateWeapon(11 , data , R.id.shorty_pic , R.id.buddy_shorty);

            //FRENZY
            this.UpdateWeapon(7 , data , R.id.frenzy , R.id.frenzy_buddy);
            //GHOST
            this.UpdateWeapon(9 , data , R.id.ghost , R.id.ghost_buddy);
            //SHERIF
            this.UpdateWeapon(10 , data , R.id.sheriff_pic , R.id.sheriff_buddy);
            //STRINGER
            this.UpdateWeapon(16 , data , R.id.stinger_pic , R.id.buddy_stinger);
            //SPECTRE
            this.UpdateWeapon(15 , data , R.id.spectre_pic , R.id.buddy_spectre);
            //BUCKY
            this.UpdateWeapon(6 , data , R.id.buckyy , R.id.buddy_bucky);
            //JUDGE
            this.UpdateWeapon(5 , data , R.id.judge_pic , R.id.buddy_judge);
            //BULLDOG
            this.UpdateWeapon(3 , data , R.id.bulldog_pic , R.id.bulldog_buddy);
            //GUARDIAN
            this.UpdateWeapon(13 , data , R.id.guardian_pic , R.id.guardian_buddy);
            //PHANTOM
            this.UpdateWeapon(4 , data , R.id.phantom_img , R.id.phantom_buddy);
            //VANDAL
            this.UpdateWeapon(2 , data , R.id.vandal_pic , R.id.vandal_buddy);
            //MARSHALL
            this.UpdateWeapon(14 , data , R.id.marshal , R.id.marshal_buddy);
            //OP
            this.UpdateWeapon(12 , data , R.id.operator , R.id.operator_buddy);
            //ARES
            this.UpdateWeapon(1 , data , R.id.ares , R.id.ares_buddy);
            //ODIN
            this.UpdateWeapon(0 , data , R.id.odin_pic , R.id.odin_buddy);
            //KNIFE
            this.UpdateWeapon(17 , data , R.id.knife , R.id.knife_buddy);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public void UpdateWeapon(int index , JSONObject data , int wepimage , int wepbody ) throws JSONException {
        JSONObject weapon  = data.getJSONArray("Guns").getJSONObject(index);
        if (weapon.has("CharmID")){
            Picasso.get().load(BUDDY_URL+ weapon.getString("CharmID")+"/displayicon.png").into((ImageView)findViewById(wepbody));
            ((ImageView)findViewById(wepbody)).setVisibility(View.VISIBLE);
        }
        if (defaults.contains(weapon.getString("ChromaID"))){
            Picasso.get().load("https://media.valorant-api.com/weapons/"+weapon.getString("ID")+"/displayicon.png").into((ImageView)findViewById(wepimage));
        }
        else{
            Picasso.get().load(Chroma_URL+ weapon.getString("ChromaID") + "/fullrender.png").into((ImageView)findViewById(wepimage));
        }


    }
}