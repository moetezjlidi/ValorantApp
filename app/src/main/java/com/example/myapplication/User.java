package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;


import com.example.myapplication.Login.Auth;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityUserBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.M)

public class User extends AppCompatActivity  {
    private Boolean isChecking = false;
    private ScheduledExecutorService exec ;
    private ActivityUserBinding binding;
    protected MyApp mMyApp ;
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
        mMyApp = (MyApp) this .getApplicationContext() ;
setTheme(R.style.NoActionBar);
        Intent i = getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        Intent ir=new Intent(this, NotificationService.class);
        ir.putExtra("auth", auth);
        this.startService(ir);
        FirebaseMessaging.getInstance().subscribeToTopic(auth.getUid())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg ="Done";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d("FIREBASE", msg);
                    }
                });
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
        ((Button) findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(User.this , agents.class);
                i.putExtra("auth"  , auth);
                User.this.startActivity(i);
            }
        });

        ((Button)findViewById(R.id.button_collec)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(User.this , Collection.class);
                i.putExtra("auth" , auth);
                User.this.startActivity(i);

            }
        });
        ((Button)findViewById(R.id.view_contract)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(User.this , matches.class);
                i.putExtra("auth" , auth);
                User.this.startActivity(i);
            }
        });

        ((Switch)findViewById(R.id.check)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChecking){
                    isChecking = false;
                    exec.shutdownNow();
                    ((Switch)findViewById(R.id.check)).setText("Check : OFF");
                }
                else{
                    isChecking = true;
                    ((Switch)findViewById(R.id.check)).setText("Check : ON");
                    exec =  Executors.newSingleThreadScheduledExecutor();
                    exec.scheduleAtFixedRate(() -> {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                try {
                                    ListenToQueue(auth);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }, 1, 1, TimeUnit.SECONDS);
                }
            }
        });





    }
    private void ListenToQueue(Auth auth) throws JSONException {
        String Match = auth.getPregameMatchId();
        if (!Match.contains("404")){
            exec.shutdownNow();
            String d = auth.getPregame(Match);
            auth.getAgents();
            Intent i = new Intent(User.this , agent_select.class);
            i.putExtra("auth" , auth);
            ((Switch)findViewById(R.id.check)).performClick();
            User.this.startActivity(i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));



        }
    }
    protected void onResume () {
        super .onResume() ;
        mMyApp .setCurrentActivity( this ) ;
    }
    protected void onPause () {
        clearReferences() ;
        super .onPause() ;
    }
    protected void onDestroy () {
        clearReferences() ;
        super .onDestroy() ;
    }
    private void clearReferences () {
        Activity currActivity = mMyApp .getCurrentActivity() ;
        if ( this .equals(currActivity))
            mMyApp .setCurrentActivity( null ) ;
    }

}