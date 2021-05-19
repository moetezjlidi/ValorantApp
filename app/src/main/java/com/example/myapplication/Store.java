package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.Login.Auth;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

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
        if (auth.getItems().size() == 0){
            auth.updateStore();
        }


        ((ImageView) findViewById(R.id.offer1_img)).setRotation(35);
        ((ImageView) findViewById(R.id.offer2_img)).setRotation(35);
        ((ImageView) findViewById(R.id.offer3_img)).setRotation(35);
        ((ImageView) findViewById(R.id.offer4_img)).setRotation(35);

        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(auth.getBundle().getImg_url()).getContent());
            BitmapDrawable background = new BitmapDrawable(bitmap);
            ((ConstraintLayout)findViewById(R.id.bundle)).setBackground(background);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        ((ConstraintLayout) findViewById(R.id.offer_2)).setBackgroundColor(Color.parseColor("#"+auth.getItems().get(1).getSkin_type().substring(0 , 6)));
        ((ConstraintLayout) findViewById(R.id.offer_3)).setBackgroundColor(Color.parseColor("#"+auth.getItems().get(2).getSkin_type().substring(0 , 6)));
        ((ConstraintLayout) findViewById(R.id.offer_4)).setBackgroundColor(Color.parseColor("#"+auth.getItems().get(3).getSkin_type().substring(0 , 6)));
        CountDownTimer cdt = new CountDownTimer(auth.getItem_time_left() * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                String time = twoDigitString(days) + ":" + twoDigitString(hours % 24) + ":" + twoDigitString(minutes % 60) + ":" + twoDigitString(seconds % 60);
                ((TextView) findViewById(R.id.time_offer)).setText(time);

                //You can compute the millisUntilFinished on hours/minutes/seconds
            }

            @Override
            public void onFinish() {
                ((TextView) findViewById(R.id.time_offer)).setText("No longer available , please reload the page !");
            }
        };
        cdt.start();

        //
        //
        //https://media.valorant-api.com/weaponskins/65baa0cd-42ec-f99d-54a0-338d795b5824/displayicon.png
        //
    }
    private String twoDigitString(long number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }
}