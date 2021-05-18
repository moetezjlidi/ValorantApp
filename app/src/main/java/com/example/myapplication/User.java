package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import com.example.myapplication.Login.Auth;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityUserBinding;
import com.squareup.picasso.Picasso;

public class User extends AppCompatActivity  {

    private ActivityUserBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        super.onCreate(savedInstanceState);

        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((Button) findViewById(R.id.btn_store)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i = new Intent(User.this ,Store.class);
                i.putExtra("auth" , auth);
                User.this.startActivity(i);
            }
        });







    }

}