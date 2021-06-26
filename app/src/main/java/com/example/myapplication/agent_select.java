package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Login.Auth;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class agent_select extends AppCompatActivity {
    private String selectedAgent;
    private String state;
    private int index;
    private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_select);
        Intent i = getIntent();
        Auth auth = (Auth) i.getExtras().get("auth");
        Log.d("agentselc" , auth.getUid());
        String match = auth.getPregameMatchId();
        //Inital step
        try {
            InitalUpdateUI(match  , auth);
        } catch (JSONException e) {
            e.printStackTrace();
        }



            exec.scheduleAtFixedRate(() -> {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            updateUI(match, auth);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }, 1, 1, TimeUnit.SECONDS);



        ((ImageView)findViewById(R.id.astra)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "41fb69c1-4189-7b37-f117-bcaf1e96f1bf";
                Log.d("Agent selected" , selectedAgent);
                auth.SelectAgent(match , selectedAgent);

                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.breach)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "5f8d3a7f-467b-97f3-062c-13acf203c006";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.brimstone)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "9f0d8ba9-4140-b941-57d3-a7ad57c6b417";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.cypher)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "117ed9e3-49f3-6512-3ccf-0cada7e3823b";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.jett)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "add6443a-41bd-e414-f6ad-e58d267f4e95";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.kj)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "1e58de9c-4950-5125-93e9-a0aee9f98746";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.omen)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "8e253930-4c05-31dd-1b6c-968525494517";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.phoenix)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "eb93336a-449b-9c1b-0a54-a891f7921d69";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.raze)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "f94c3b30-42be-e959-889c-5aa313dba261";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.reyna)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "a3bfb853-43b2-7238-a4f1-ad90e9e46bcc";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.sage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "569fdd95-4d10-43ab-ca70-79becc718b46";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.skye)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "6f2a04ca-43e0-be17-7f36-b3908627744d";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.sova)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "ded3520f-4264-bfed-162d-b080e2abccf9";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.viper)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "707eab51-4836-f488-046a-cda6bf494859";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.yoru)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "7f94d92c-4234-0a36-9646-3a87eb8b5c89";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
            }
        });
        ((ImageView)findViewById(R.id.kayo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAgent = "601dbbe7-43ce-be57-2a40-4abd24953621";
                auth.SelectAgent(match , selectedAgent);
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));

            }
        });
        ((Button)findViewById(R.id.lock_in)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.LockAgent(match , selectedAgent);
                ((TextView)findViewById(R.id.mystate)).setText("PICKED");
                Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                ((Button)findViewById(R.id.lock_in)).setEnabled(false);
            }
        });
        ((Button)findViewById(R.id.leave)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.LeavePregame(match);
                exec.shutdownNow();
                finish();
            }
        });


    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void InitalUpdateUI(String match  , Auth auth) throws JSONException {
        JSONObject data =new JSONObject(auth.getPregame(match)) ;
        JSONArray players = data.getJSONArray("Teams").getJSONObject(0).getJSONArray("Players");
        int index = 0;
        for (int i = 0;i<players.length();i++){
            JSONObject player = players.getJSONObject(i);
            if (player.getString("Subject").equals(auth.getUid())){
                index = i;
                break;

            }
        }
        JSONObject me = players.getJSONObject(index);
        players.remove(index);
        ((TextView)findViewById(R.id.myname)).setText(auth.getName());
        String p1name = auth.getPlayerName(players.getJSONObject(0).getString("Subject"));
        String p2name = auth.getPlayerName(players.getJSONObject(1).getString("Subject"));
        String p3name = auth.getPlayerName(players.getJSONObject(2).getString("Subject"));
        String p4name = auth.getPlayerName(players.getJSONObject(3).getString("Subject"));
        //
        ((TextView)findViewById(R.id.player_name1)).setText(p1name);
        ((TextView)findViewById(R.id.player_name2)).setText(p2name);
        ((TextView)findViewById(R.id.player_name3)).setText(p3name);
        ((TextView)findViewById(R.id.player_name4)).setText(p4name);
        this.index = index;






    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void updateUI(String match , Auth auth) throws JSONException {
        JSONObject data =new JSONObject(auth.getPregame(match)) ;
        long secs = data.getLong("PhaseTimeRemainingNS");
        long seconds = TimeUnit.SECONDS.
                convert(secs,
                        TimeUnit.NANOSECONDS);
        ((TextView)findViewById(R.id.timer)).setText(""+seconds);
        JSONArray players = data.getJSONArray("Teams").getJSONObject(0).getJSONArray("Players");
        players.remove(this.index);
        String player1c = players.getJSONObject(0).getString("CharacterID");
        String player2c = players.getJSONObject(1).getString("CharacterID");
        String player3c = players.getJSONObject(2).getString("CharacterID");
        String player4c = players.getJSONObject(3).getString("CharacterID");
        if (player1c.length() > 0){
            //https://media.valorant-api.com/agents//displayicon.png
            Picasso.get().load("https://media.valorant-api.com/agents/"+player1c+"/displayicon.png").into((CircleImageView) findViewById(R.id.player1));

        }
        if (player2c.length() > 0){
            //https://media.valorant-api.com/agents//displayicon.png
            Picasso.get().load("https://media.valorant-api.com/agents/"+player2c+"/displayicon.png").into((CircleImageView) findViewById(R.id.player2));

        }
        if (player3c.length() > 0){
            //https://media.valorant-api.com/agents//displayicon.png
            Picasso.get().load("https://media.valorant-api.com/agents/"+player3c+"/displayicon.png").into((CircleImageView) findViewById(R.id.player3));

        }
        if (player4c.length() > 0){
            //https://media.valorant-api.com/agents//displayicon.png
            Picasso.get().load("https://media.valorant-api.com/agents/"+player4c+"/displayicon.png").into((CircleImageView) findViewById(R.id.player4));

        }
        String state1 = players.getJSONObject(0).getString("CharacterSelectionState");
        String state2 = players.getJSONObject(1).getString("CharacterSelectionState");
        String state3 = players.getJSONObject(2).getString("CharacterSelectionState");
        String state4 = players.getJSONObject(3).getString("CharacterSelectionState");
        if (state1.equals("locked")){
            ((TextView)findViewById(R.id.state1)).setText("PICKED");
        }
        if (state2.equals("locked")){
            ((TextView)findViewById(R.id.state2)).setText("PICKED");
        }
        if (state3.equals("locked")){
            ((TextView)findViewById(R.id.state3)).setText("PICKED");
        }
        if (state4.equals("locked")){
            ((TextView)findViewById(R.id.state4)).setText("PICKED");
        }





    }
}