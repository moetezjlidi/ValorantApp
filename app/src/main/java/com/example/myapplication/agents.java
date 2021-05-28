package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemChangeListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.myapplication.Login.Auth;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class agents extends AppCompatActivity {
 public int current = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);
        Intent i = getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        auth.getAgents();
        ArrayList<String> data = auth.getAgentsSplash();
        ImageSlider slider = findViewById(R.id.slider);
        ArrayList<String> free_agents = new ArrayList<String>();
        free_agents.add("320b2a48-4d9b-a075-30f1-1f93a9b638fa");
        free_agents.add("eb93336a-449b-9c1b-0a54-a891f7921d69");
        free_agents.add("9f0d8ba9-4140-b941-57d3-a7ad57c6b417");
        free_agents.add("569fdd95-4d10-43ab-ca70-79becc718b46");
        free_agents.add("add6443a-41bd-e414-f6ad-e58d267f4e95");
        List<SlideModel>  slidemod = new ArrayList<>();
        for (int j = 0 ; j<data.size();j++){
            try {
                JSONObject d = new JSONObject(data.get(j));
                slidemod.add(new SlideModel(d.getString("displayIcon")   , ScaleTypes.CENTER_INSIDE ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList<String> myagents = auth.getMyagents();
        slider.setImageList(slidemod);
        slider.setItemChangeListener(new ItemChangeListener() {
            @Override
            public void onItemChanged(int i) {
                try {
                    current = i;
                    ((ImageButton) findViewById(R.id.info_agent)).performClick();
                    JSONObject d = new JSONObject(data.get(i));
                    ((TextView)findViewById(R.id.agent_name)).setText(d.getString("displayName"));
                    Picasso.get().load(d.getString("bustPortrait")).into((ImageView) findViewById(R.id.agent_portrait));

                    Picasso.get().load(d.getJSONObject("role").getString("displayIcon")).into((ImageView) findViewById(R.id.info_agent));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(0).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab1));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(1).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab2));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(2).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab3));
                    Picasso.get().load(d.getJSONArray("abilities").getJSONObject(3).getString("displayIcon")).into((ImageButton)findViewById(R.id.ulti));
                    ((TextView)findViewById(R.id.agent_role)).setText(d.getJSONObject("role").getString("displayName"));
                    if (myagents.contains(d.getString("uuid")) || free_agents.contains(d.getString("uuid"))){
                        ((TextView)findViewById(R.id.ownership)).setText("UNLOCKED");
                        ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#4CAF50"));
                    }
                    else{
                        ((TextView)findViewById(R.id.ownership)).setText("LOCKED");
                        ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#CC3232"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        ((ImageButton) findViewById(R.id.info_agent)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONObject("role").getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONObject("role").getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.VISIBLE);
                    ((TextView)findViewById(R.id.info_desc)).setText(d.getString("description"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ab1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(0).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(0).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ab2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(1).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(1).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ab3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(2).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(2).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        ((ImageButton)findViewById(R.id.ulti)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    JSONObject d = new JSONObject(data.get(current));
                    ((TextView)findViewById(R.id.ab_title)).setText(d.getJSONArray("abilities").getJSONObject(3).getString("displayName"));
                    ((TextView)findViewById(R.id.ab_desc)).setText(d.getJSONArray("abilities").getJSONObject(3).getString("description"));
                    ((TextView)findViewById(R.id.info_desc)).setVisibility(View.GONE);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        JSONObject d = null;
        try {
            d = new JSONObject(data.get(current));
            ((TextView)findViewById(R.id.agent_name)).setText(d.getString("displayName"));
            ((TextView)findViewById(R.id.agent_role)).setText(d.getJSONObject("role").getString("displayName"));
            Picasso.get().load(d.getString("bustPortrait")).into((ImageView) findViewById(R.id.agent_portrait));

            Picasso.get().load(d.getJSONObject("role").getString("displayIcon")).into((ImageView) findViewById(R.id.info_agent));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(0).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab1));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(1).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab2));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(2).getString("displayIcon")).into((ImageButton)findViewById(R.id.ab3));
            Picasso.get().load(d.getJSONArray("abilities").getJSONObject(3).getString("displayIcon")).into((ImageButton)findViewById(R.id.ulti));

            ((ImageButton) findViewById(R.id.info_agent)).performClick();
            if (myagents.contains(d.getString("uuid")) || free_agents.contains(d.getString("uuid"))){
                ((TextView)findViewById(R.id.ownership)).setText("UNLOCKED");
                ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#4CAF50"));
            }
            else{
                ((TextView)findViewById(R.id.ownership)).setText("LOCKED");
                ((TextView)findViewById(R.id.ownership)).setTextColor(Color.parseColor("#CC3232"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
        ((Button)findViewById(R.id.view_contract)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((ConstraintLayout)findViewById(R.id.contract)).setVisibility(View.VISIBLE);
                ((ConstraintLayout)findViewById(R.id.all_stuff)).setAlpha((float) 0.5);
                ((ConstraintLayout)findViewById(R.id.all_constraint)).setBackgroundColor(Color.parseColor("#6C6767"));
            }
        });
        ((ImageButton)findViewById(R.id.close_c)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ConstraintLayout)findViewById(R.id.contract)).setVisibility(View.GONE);
                ((ConstraintLayout)findViewById(R.id.all_stuff)).setAlpha((float) 1);
                ((ConstraintLayout)findViewById(R.id.all_constraint)).setBackgroundColor(Color.parseColor("#BD5555"));
            }
        });






    }
}