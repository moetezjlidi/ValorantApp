package com.example.myapplication;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication.Login.Auth;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class matches extends AppCompatActivity{
    private AppBarConfiguration appBarConfiguration;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_history);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            LoadMatches();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void LoadMatches() throws JSONException {
        ExecutorService service = Executors.newCachedThreadPool();

        Intent i = getIntent();
        Auth auth = (Auth) i.getSerializableExtra("auth");
        auth.getMatchHistory();
        String matches = auth.getMatches();
        try {
            JSONArray jmatches = new JSONArray(matches);
            ArrayList<JSONObject> ls = new ArrayList<JSONObject>();
            for(int j = 0;j<jmatches.length();j++) {
                ls.add(jmatches.getJSONObject(j));
            }

            ls.forEach(o -> {

                service.execute( ()->

                {
                try {

                    String mapID = o.getString("MapID");
                    String tierb = o.getString("TierBeforeUpdate");
                    String tiera = o.getString("TierAfterUpdate");
                    String rating = o.getString("RankedRatingEarned");
                    String matchID = o.getString("MatchID");
                    JSONObject info = new JSONObject(auth.getMatchInfo(matchID));
                    JSONArray players = info.getJSONArray("players");
                    String score = "";
                    String kda = "";
                    String agent = "";
                    String result = "";
                    String myteam = "";
                    String enemyteam = "";
                    String team = "";
                    for (int k = 0; k < players.length(); k++) {
                        if (players.getJSONObject(k).getString("subject").equals(auth.getUid())) {
                            JSONObject stats = players.getJSONObject(k).getJSONObject("stats");
                            score = stats.getString("score");
                            kda = stats.getString("kills") + "/" + stats.getString("deaths") + "/" + stats.getString("assists");
                            agent = players.getJSONObject(k).getString("characterId");
                            team = players.getJSONObject(k).getString("teamId");
                            break;
                        }
                    }
                    // check if player won the game or no
                    JSONArray teams = info.getJSONArray("teams");
                    if (teams.getJSONObject(0).getString("teamId").equals(team)) {
                        myteam = teams.getJSONObject(0).getString("roundsWon");
                        enemyteam = teams.getJSONObject(1).getString("roundsWon");
                        if (teams.getJSONObject(0).getBoolean("won") && !(teams.getJSONObject(1).getBoolean("won"))) {
                            result = "win";
                        } else if (!teams.getJSONObject(0).getBoolean("won") && (teams.getJSONObject(1).getBoolean("won"))) {
                            result = "lose";
                        } else {
                            result = "draw";
                        }
                    } else {
                        myteam = teams.getJSONObject(1).getString("roundsWon");
                        enemyteam = teams.getJSONObject(0).getString("roundsWon");
                        if (teams.getJSONObject(1).getBoolean("won") && !(teams.getJSONObject(0).getBoolean("won"))) {
                            result = "win";
                        } else if (!teams.getJSONObject(1).getBoolean("won") && (teams.getJSONObject(0).getBoolean("won"))) {
                            result = "lose";
                        } else {
                            result = "draw";
                        }
                    }
                    View v;

                    //parse data
                    LayoutInflater mlf = getLayoutInflater();
                    //check if won
                    if (result.equals("win")) {
                        v = mlf.inflate(R.layout.win_match_layout, null);
                    } else if (result.equals("lose")) {
                        v = mlf.inflate(R.layout.lose_match_layout, null);
                    } else {
                        v = mlf.inflate(R.layout.draw_match_layout, null);
                    }
                    String finalKda = kda;
                    String finalEnemyteam = enemyteam;
                    String finalAgent = agent;
                    String finalMyteam = myteam;
                    String finalScore = score;
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            Picasso.get().load("https://raw.githubusercontent.com/RumbleMike/ValorantStreamOverlay/main/Resources/TX_CompetitiveTier_Large_" + tierb + ".png").into((ImageView) v.findViewById(R.id.rank));
                            ((TextView) v.findViewById(R.id.kda)).setText("KDA : " + finalKda);
                            ((TextView) v.findViewById(R.id.game_score)).setText(finalMyteam + " - " + finalEnemyteam);
                            Picasso.get().load("https://media.valorant-api.com/agents/" + finalAgent + "/displayicon.png").into((ImageView) v.findViewById(R.id.agent));
                            ((TextView) v.findViewById(R.id.rating)).setText(rating);
                            ((TextView)v.findViewById(R.id.score)).setText(finalScore);
                            LinearLayout l = findViewById(R.id.matchesz);
                            if (mapID.contains("Bonsai")) {
                                ((ImageView) v.findViewById(R.id.match)).setBackgroundResource(R.drawable.bonsai);
                            }
                            if (mapID.contains("Ascent")) {
                                ((ImageView) v.findViewById(R.id.match)).setBackgroundResource(R.drawable.listviewicon);
                            }
                            if (mapID.contains("Port")) {
                                ((ImageView) v.findViewById(R.id.match)).setBackgroundResource(R.drawable.port);
                            }
                            if (mapID.contains("Duality")) {
                                ((ImageView) v.findViewById(R.id.match)).setBackgroundResource(R.drawable.duality);
                            }
                            if (mapID.contains("Triad")) {
                                ((ImageView) v.findViewById(R.id.match)).setBackgroundResource(R.drawable.triad);
                            }
                            if (mapID.contains("Foxtrot")) {
                                ((ImageView) v.findViewById(R.id.match)).setBackgroundResource(R.drawable.foxtrot);
                            }
                            l.addView(v);

                        }
                    });




                } catch (JSONException e) {
                    e.printStackTrace();
                }

            });

            });
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
