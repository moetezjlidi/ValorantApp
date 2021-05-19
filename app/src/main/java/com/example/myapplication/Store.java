package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Login.Auth;
import com.squareup.picasso.Picasso;

public class Store extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_store);
        Intent i = getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        ImageView card_img = (ImageView) findViewById(R.id.card_img);
        ImageView rank_img = (ImageView) findViewById(R.id.rank_img);
        Picasso.get().load("https://raw.githubusercontent.com/RumbleMike/ValorantStreamOverlay/main/Resources/TX_CompetitiveTier_Large_"+auth.getRank_id()+".png").into(rank_img);
        Picasso.get().load(auth.get_Card()).into(card_img);
        TextView name = (TextView) findViewById(R.id.name_id);
        name.setText(Html.fromHtml("<b>" +auth.getName() + "</b>" +  "#" + auth.getTag()) );
        ((TextView) findViewById(R.id.rank_name)).setText(auth.getRank());
        ProgressBar pbar =  ((ProgressBar) findViewById(R.id.rank_bar));
        pbar.setProgress(Integer.parseInt(auth.getLp()));
        ((TextView) findViewById(R.id.lp_txt)).setText(auth.getLp() + "/100");
        ((TextView) findViewById(R.id.valo_pt)).setText(auth.getVal_points());
        ((TextView) findViewById(R.id.radiant_pt_logo)).setText(auth.getRadiant_points());
        auth.updateStore();
        ImageView featured = findViewById(R.id.bundleimg);
        Picasso.get().load(auth.getBundle().getImg_url()).into(featured);
        Picasso.get().load(auth.getItems().get(0).getImg_url()).into((ImageView) findViewById(R.id.offer1_img));
        Picasso.get().load(auth.getItems().get(1).getImg_url()).into((ImageView) findViewById(R.id.offer2_img));
        Picasso.get().load(auth.getItems().get(2).getImg_url()).into((ImageView) findViewById(R.id.offer3_img));
        Picasso.get().load(auth.getItems().get(3).getImg_url()).into((ImageView) findViewById(R.id.offer4_img));
        ((TextView) findViewById(R.id.bundlename)).setText(auth.getBundle().getSkin_name());
        ((TextView) findViewById(R.id.offer1_name)).setText(auth.getItems().get(0).getSkin_name());
        ((TextView) findViewById(R.id.offer2_name)).setText(auth.getItems().get(1).getSkin_name());
        ((TextView) findViewById(R.id.offer3_name)).setText(auth.getItems().get(2).getSkin_name());
        ((TextView) findViewById(R.id.offer4_name)).setText(auth.getItems().get(3).getSkin_name());

        ((TextView) findViewById(R.id.offer1_price)).setText(auth.getItems().get(0).getSkin_price());
        ((TextView) findViewById(R.id.offer2_price)).setText(auth.getItems().get(1).getSkin_price());
        ((TextView) findViewById(R.id.offer3_price)).setText(auth.getItems().get(2).getSkin_price());
        ((TextView) findViewById(R.id.offer4_price)).setText(auth.getItems().get(3).getSkin_price());
        ((ConstraintLayout) findViewById(R.id.offer_1)).setBackgroundColor(Color.parseColor("#"+ auth.getItems().get(0).getSkin_type().substring(0 , 6)));
        ((ConstraintLayout) findViewById(R.id.offer2)).setBackgroundColor(Color.parseColor("#"+auth.getItems().get(1).getSkin_type().substring(0 , 6)));
        ((ConstraintLayout) findViewById(R.id.offer3)).setBackgroundColor(Color.parseColor("#"+auth.getItems().get(2).getSkin_type().substring(0 , 6)));
        ((ConstraintLayout) findViewById(R.id.offer4)).setBackgroundColor(Color.parseColor("#"+auth.getItems().get(3).getSkin_type().substring(0 , 6)));


        //
        //
        //https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png
        //
    }
}