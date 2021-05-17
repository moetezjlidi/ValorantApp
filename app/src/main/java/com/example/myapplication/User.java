package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import com.example.myapplication.Login.Auth;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.ui.main.SectionsPagerAdapter;
import com.example.myapplication.databinding.ActivityUserBinding;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.Serializable;

public class User extends AppCompatActivity implements Serializable {

    private ActivityUserBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
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







    }
}