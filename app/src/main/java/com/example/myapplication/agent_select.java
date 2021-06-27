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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class agent_select extends AppCompatActivity {
    private String selectedAgent;
    private HashMap<String , Integer> AgentsImages = new HashMap<String, Integer>();
    private String state = "picking";
    private ArrayList<String> AvailableAgents = new ArrayList<String>();
    private int index;
    private ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AvailableAgents.add("5f8d3a7f-467b-97f3-062c-13acf203c006");
        AvailableAgents.add("f94c3b30-42be-e959-889c-5aa313dba261");
        AvailableAgents.add("601dbbe7-43ce-be57-2a40-4abd24953621");
        AvailableAgents.add("6f2a04ca-43e0-be17-7f36-b3908627744d");
        AvailableAgents.add("117ed9e3-49f3-6512-3ccf-0cada7e3823b");
        AvailableAgents.add("ded3520f-4264-bfed-162d-b080e2abccf9");
        AvailableAgents.add("320b2a48-4d9b-a075-30f1-1f93a9b638fa");
        AvailableAgents.add("1e58de9c-4950-5125-93e9-a0aee9f98746");
        AvailableAgents.add("707eab51-4836-f488-046a-cda6bf494859");
        AvailableAgents.add("707eab51-4836-f488-046a-cda6bf494859");
        AvailableAgents.add("41fb69c1-4189-7b37-f117-bcaf1e96f1bf");
        AvailableAgents.add("9f0d8ba9-4140-b941-57d3-a7ad57c6b417");
        AvailableAgents.add("7f94d92c-4234-0a36-9646-3a87eb8b5c89");
        AvailableAgents.add("569fdd95-4d10-43ab-ca70-79becc718b46");
        AvailableAgents.add("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc");
        AvailableAgents.add("8e253930-4c05-31dd-1b6c-968525494517");
        AvailableAgents.add("add6443a-41bd-e414-f6ad-e58d267f4e95");
        //
        AgentsImages.put("5f8d3a7f-467b-97f3-062c-13acf203c006" , R.id.breach);
        AgentsImages.put("f94c3b30-42be-e959-889c-5aa313dba261" , R.id.raze);
        AgentsImages.put("601dbbe7-43ce-be57-2a40-4abd24953621" , R.id.kayo);
        AgentsImages.put("6f2a04ca-43e0-be17-7f36-b3908627744d" , R.id.skye);
        AgentsImages.put("117ed9e3-49f3-6512-3ccf-0cada7e3823b" , R.id.cypher);
        AgentsImages.put("ded3520f-4264-bfed-162d-b080e2abccf9" , R.id.sova);
        AgentsImages.put("320b2a48-4d9b-a075-30f1-1f93a9b638fa" , R.id.sova);
        AgentsImages.put("1e58de9c-4950-5125-93e9-a0aee9f98746" , R.id.kj);
        AgentsImages.put("707eab51-4836-f488-046a-cda6bf494859" , R.id.viper);
        AgentsImages.put("eb93336a-449b-9c1b-0a54-a891f7921d69" , R.id.phoenix);
        AgentsImages.put("41fb69c1-4189-7b37-f117-bcaf1e96f1bf" , R.id.astra);
        AgentsImages.put("9f0d8ba9-4140-b941-57d3-a7ad57c6b417" , R.id.brimstone);
        AgentsImages.put("7f94d92c-4234-0a36-9646-3a87eb8b5c89" ,R.id.yoru);
        AgentsImages.put("569fdd95-4d10-43ab-ca70-79becc718b46" , R.id.sage);
        AgentsImages.put("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc" ,R.id.reyna);
        AgentsImages.put("8e253930-4c05-31dd-1b6c-968525494517" , R.id.omen);
        AgentsImages.put("add6443a-41bd-e414-f6ad-e58d267f4e95" , R.id.jett);




        //

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
                if (AvailableAgents.contains("41fb69c1-4189-7b37-f117-bcaf1e96f1bf") && state.equals("picking")) {
                    selectedAgent = "41fb69c1-4189-7b37-f117-bcaf1e96f1bf";
                    Log.d("Agent selected", selectedAgent);
                    auth.SelectAgent(match, selectedAgent);

                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));

                }
            }
        });
        ((ImageView)findViewById(R.id.breach)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AvailableAgents.contains("5f8d3a7f-467b-97f3-062c-13acf203c006") && state.equals("picking")){
                    selectedAgent = "5f8d3a7f-467b-97f3-062c-13acf203c006";
                    auth.SelectAgent(match , selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }

            }
        });
        ((ImageView)findViewById(R.id.brimstone)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("9f0d8ba9-4140-b941-57d3-a7ad57c6b417") && state.equals("picking")) {

                    selectedAgent = "9f0d8ba9-4140-b941-57d3-a7ad57c6b417";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.cypher)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("117ed9e3-49f3-6512-3ccf-0cada7e3823b") && state.equals("picking")) {

                    selectedAgent = "117ed9e3-49f3-6512-3ccf-0cada7e3823b";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.jett)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("add6443a-41bd-e414-f6ad-e58d267f4e95") && state.equals("picking")) {
                    selectedAgent = "add6443a-41bd-e414-f6ad-e58d267f4e95";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.kj)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("1e58de9c-4950-5125-93e9-a0aee9f98746") && state.equals("picking")) {
                    selectedAgent = "1e58de9c-4950-5125-93e9-a0aee9f98746";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.omen)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("8e253930-4c05-31dd-1b6c-968525494517") && state.equals("picking")) {
                    selectedAgent = "8e253930-4c05-31dd-1b6c-968525494517";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.phoenix)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("eb93336a-449b-9c1b-0a54-a891f7921d69") && state.equals("picking")) {
                    selectedAgent = "eb93336a-449b-9c1b-0a54-a891f7921d69";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.raze)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("f94c3b30-42be-e959-889c-5aa313dba261") && state.equals("picking")) {
                    selectedAgent = "f94c3b30-42be-e959-889c-5aa313dba261";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.reyna)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc") && state.equals("picking")) {
                    selectedAgent = "a3bfb853-43b2-7238-a4f1-ad90e9e46bcc";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.sage)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("569fdd95-4d10-43ab-ca70-79becc718b46") && state.equals("picking")) {
                    selectedAgent = "569fdd95-4d10-43ab-ca70-79becc718b46";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.skye)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("6f2a04ca-43e0-be17-7f36-b3908627744d") && state.equals("picking")) {
                    selectedAgent = "6f2a04ca-43e0-be17-7f36-b3908627744d";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.sova)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("ded3520f-4264-bfed-162d-b080e2abccf9") && state.equals("picking")) {
                    selectedAgent = "ded3520f-4264-bfed-162d-b080e2abccf9";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.viper)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("707eab51-4836-f488-046a-cda6bf494859") && state.equals("picking")) {
                    selectedAgent = "707eab51-4836-f488-046a-cda6bf494859";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.yoru)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("7f94d92c-4234-0a36-9646-3a87eb8b5c89") && state.equals("picking")) {
                    selectedAgent = "7f94d92c-4234-0a36-9646-3a87eb8b5c89";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((ImageView)findViewById(R.id.kayo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AvailableAgents.contains("601dbbe7-43ce-be57-2a40-4abd24953621") && state.equals("picking")) {
                    selectedAgent = "601dbbe7-43ce-be57-2a40-4abd24953621";
                    auth.SelectAgent(match, selectedAgent);
                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/displayicon.png").into((CircleImageView) findViewById(R.id.me));

                    Picasso.get().load("https://media.valorant-api.com/agents/" + selectedAgent + "/fullportrait.png").into((ImageView)findViewById(R.id.agent_selected));
                }
            }
        });
        ((Button)findViewById(R.id.lock_in)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AvailableAgents.contains(selectedAgent)){
                    auth.LockAgent(match , selectedAgent);
                    ((TextView)findViewById(R.id.mystate)).setText("PICKED");
                    Picasso.get().load("https://media.valorant-api.com/agents/"+selectedAgent+"/displayicon.png").into((CircleImageView) findViewById(R.id.me));
                    ((Button)findViewById(R.id.lock_in)).setEnabled(false);
                    state = "locked";
                    ((ImageView)findViewById(AgentsImages.get(selectedAgent))).getBackground().setAlpha(51);
                }

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
        ((TextView)findViewById(R.id.player_name1)).setText(p1name.toUpperCase());
        ((TextView)findViewById(R.id.player_name2)).setText(p2name.toUpperCase());
        ((TextView)findViewById(R.id.player_name3)).setText(p3name.toUpperCase());
        ((TextView)findViewById(R.id.player_name4)).setText(p4name.toUpperCase());
        this.index = index;
        ArrayList<String> myagents = auth.getMyagents();
        Log.d("Agents" , myagents.toString());
        //((ImageView)findViewById(R.id.yoru)).setImageResource(R.drawable.lock_icon_614x460);
        if (!(myagents.contains("5f8d3a7f-467b-97f3-062c-13acf203c006"))){
            ((ImageView)findViewById(R.id.breach)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.breach)).getBackground().setAlpha(51);
            AvailableAgents.remove("5f8d3a7f-467b-97f3-062c-13acf203c006");
        }
        if (!(myagents.contains("f94c3b30-42be-e959-889c-5aa313dba261"))){
            ((ImageView)findViewById(R.id.raze)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.raze)).getBackground().setAlpha(51);
            AvailableAgents.remove("f94c3b30-42be-e959-889c-5aa313dba261");
        }
        if (!(myagents.contains("601dbbe7-43ce-be57-2a40-4abd24953621"))){

            ((ImageView)findViewById(R.id.kayo)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.kayo)).getBackground().setAlpha(51);
            AvailableAgents.remove("601dbbe7-43ce-be57-2a40-4abd24953621");
        }
        if (!(myagents.contains("6f2a04ca-43e0-be17-7f36-b3908627744d"))){

            ((ImageView)findViewById(R.id.skye)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.skye)).getBackground().setAlpha(51);
            AvailableAgents.remove("6f2a04ca-43e0-be17-7f36-b3908627744d");
        }
        if (!(myagents.contains("117ed9e3-49f3-6512-3ccf-0cada7e3823b"))){

            ((ImageView)findViewById(R.id.cypher)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.cypher)).getBackground().setAlpha(51);
            AvailableAgents.remove("117ed9e3-49f3-6512-3ccf-0cada7e3823b");
        }
        if (!(myagents.contains("1e58de9c-4950-5125-93e9-a0aee9f98746"))){

            ((ImageView)findViewById(R.id.kj)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.kj)).getBackground().setAlpha(51);
            AvailableAgents.remove("1e58de9c-4950-5125-93e9-a0aee9f98746");
        }
        if (!(myagents.contains("707eab51-4836-f488-046a-cda6bf494859"))){

            ((ImageView)findViewById(R.id.viper)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.viper)).getBackground().setAlpha(51);
            AvailableAgents.remove("707eab51-4836-f488-046a-cda6bf494859");
        }
        if (!(myagents.contains("41fb69c1-4189-7b37-f117-bcaf1e96f1bf"))){

            ((ImageView)findViewById(R.id.astra)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.astra)).getBackground().setAlpha(51);
            AvailableAgents.remove("41fb69c1-4189-7b37-f117-bcaf1e96f1bf");
        }
        if (!(myagents.contains("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc"))){

            ((ImageView)findViewById(R.id.reyna)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.reyna)).getBackground().setAlpha(51);
            AvailableAgents.remove("a3bfb853-43b2-7238-a4f1-ad90e9e46bcc");
        }
        if (!(myagents.contains("8e253930-4c05-31dd-1b6c-968525494517"))){

            ((ImageView)findViewById(R.id.omen)).setImageResource(R.drawable.lock_icon_614x460);
            ((ImageView)findViewById(R.id.omen)).getBackground().setAlpha(51);
            AvailableAgents.remove("8e253930-4c05-31dd-1b6c-968525494517");
        }










    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void updateUI(String match , Auth auth) throws JSONException {
        String d = auth.getPregame(match);
        if (d.equals("404")){
            exec.shutdownNow();
            finish();
        }
        else{

        JSONObject data =new JSONObject(d) ;

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
            try{
                AvailableAgents.remove(player1c);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            ((ImageView)findViewById(AgentsImages.get(player1c))).getBackground().setAlpha(51);
        }
        if (state2.equals("locked")){
            ((TextView)findViewById(R.id.state2)).setText("PICKED");
            try{
                AvailableAgents.remove(player2c);
            }
            catch (Exception e){
                e.printStackTrace();
            }

            ((ImageView)findViewById(AgentsImages.get(player2c))).getBackground().setAlpha(51);
        }
        if (state3.equals("locked")){
            ((TextView)findViewById(R.id.state3)).setText("PICKED");
            try{
                AvailableAgents.remove(player3c);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            ((ImageView)findViewById(AgentsImages.get(player3c))).getBackground().setAlpha(51);
        }
        if (state4.equals("locked")){
            ((TextView)findViewById(R.id.state4)).setText("PICKED");
            try{
                AvailableAgents.remove(player4c);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            ((ImageView)findViewById(AgentsImages.get(player4c))).getBackground().setAlpha(51);
        }
        }





    }
}