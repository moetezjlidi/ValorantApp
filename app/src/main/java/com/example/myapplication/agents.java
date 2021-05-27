package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agents);
        Intent i = getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        auth.getAgents();
        ArrayList<String> data = auth.getAgentsSplash();
        ImageSlider slider = findViewById(R.id.slider);
        List<SlideModel>  slidemod = new ArrayList<>();
        for (int j = 0 ; j<data.size();j++){
            try {
                JSONObject d = new JSONObject(data.get(j)).getJSONObject("data");
                slidemod.add(new SlideModel(d.getString("displayIcon")   , ScaleTypes.CENTER_INSIDE ));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        slider.setImageList(slidemod);
        slider.setItemChangeListener(new ItemChangeListener() {
            @Override
            public void onItemChanged(int i) {
                try {
                    JSONObject d = new JSONObject(data.get(i)).getJSONObject("data");
                    ((TextView)findViewById(R.id.textView3)).setText(d.getString("displayName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        slider.st





    }
}