package com.example.myapplication.Login;

import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.JavaNetCookieJar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.example.myapplication.Login.utils.Store_item;

@SuppressWarnings("serial")
public class Auth extends AppCompatActivity implements Serializable {
    public static final MediaType JSON
            = MediaType.parse("application/json;charset=utf-8");
    private String AUTH_URL = "https://auth.riotgames.com/api/v1/authorization";
    private String USER_INFO_URL = "https://auth.riotgames.com/userinfo";
    private String ENT_TOKEN_URL = "https://entitlements.auth.riotgames.com/api/token/v1";
    private String user;
    private String password;
    private String token;
    private String ent_toekn;
    private ArrayList<Store_item> items;
    private String allskins;

    public String getMatches() {
        return matches;
    }

    public void setMatches(String matches) {
        this.matches = matches;
    }

    private String matches;
    public String getAll_contracts() {
        return all_contracts;
    }

    public String getMycontracts() {
        return mycontracts;
    }

    private String all_contracts;
    private String mycontracts;

    public String getMycollection() {
        return mycollection;
    }

    private String mycollection;

    public ArrayList<String> getMyagents() {
        return myagents;
    }

    public void setMyagents(ArrayList<String> myagents) {
        this.myagents = myagents;
    }

    private ArrayList<String> myagents = new ArrayList<String>();

    public int getAgentsLength() {
        return agentsLength;
    }

    public void setAgentsLength(int agentsLength) {
        this.agentsLength = agentsLength;
    }

    public ArrayList<String> getAgentsSplash() {
        return agentsSplash;
    }

    public void setAgentsSplash(ArrayList<String> agentsSplash) {
        this.agentsSplash = agentsSplash;
    }


    private int agentsLength;
    private  ArrayList<String>  agentsSplash  = new ArrayList<String>();
    public String getMatchInfo(String match){
        String res = "";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/match-details/v1/matches/"+match).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            res = response.body().string();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return  res;
    }
    public void GetContracts(){
        //https://pd.eu.a.pvp.net/contract-definitions/v2/definitions
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/contract-definitions/v2/definitions").addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            this.all_contracts = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request request1 = new Request.Builder().url("https://pd.eu.a.pvp.net/contracts/v1/contracts/"+this.uid).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response rep = client.newCall(request1).execute()){
            this.mycontracts = rep.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public String getPregame(String match){
            String res = "";
            OkHttpClient client = new OkHttpClient.Builder().build();
            Request request = new Request.Builder().url("https://glz-eu-1.eu.a.pvp.net/pregame/v1/matches/"+match).addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + this.token)
                    .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                    .addHeader("X-Riot-ClientVersion", this.version)
                    .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
            try (Response response = client.newCall(request).execute()) {
                res = response.body().string();
                if (res.contains("404")){
                    throw new Exception("Pregame Ended");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                return "404";
            }
            return res;

        }
    public String getPregameMatchId(){
        String res = "";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://glz-eu-1.eu.a.pvp.net/pregame/v1/players/"+this.uid).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            res = new JSONObject(response.body().string()).getString("MatchID");

        } catch (IOException | JSONException e) {
            return "404";
        }
        return res;
    }

    public void getMatchHistory(){
        //https://pd.ap.a.pvp.net/mmr/v1/players/{user_id}/competitiveupdates?startIndex=0&endIndex=15&queue=competitive
        String url = "https://pd.eu.a.pvp.net/mmr/v1/players/"+this.uid+"/competitiveupdates?startIndex=0&endIndex=20&queue=competitive";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            JSONArray matches = new JSONObject(response.body().string()).getJSONArray("Matches");
            this.matches = matches.toString();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    public void getAgents(){
        String url = "https://pd.eu.a.pvp.net/store/v1/entitlements/"+this.uid+"/01bb38e1-da47-4e6a-9b3d-945fe4655707";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            JSONArray agents = new JSONObject(response.body().string()).getJSONArray("Entitlements");
            agentsLength =  5 + agents.length();
            for (int i = 0; i<agents.length();i++){
                myagents.add(agents.getJSONObject(i).getString("ItemID"));

            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        Request request1 = new Request.Builder().url("https://valorant-api.com/v1/agents").build();
        try (Response rep = client.newCall(request1).execute()){
            JSONArray d = new JSONObject(rep.body().string()).getJSONArray("data");
            for(int i = 0 ; i < d.length();i++){
                if (!d.getJSONObject(i).getString("uuid").equals("ded3520f-4264-bfed-162d-b080e2abccf9")) {
                    agentsSplash.add(d.getJSONObject(i).toString());
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
    public Long getFeatured_timer() {
        return featured_timer;
    }

    private Long featured_timer;

    public long getItem_time_left() {
        return item_time_left;
    }

    private long item_time_left;


    public ArrayList<Store_item> getItems() {
        return items;
    }

    public Store_item getBundle() {
        return bundle;
    }

    private Store_item bundle;

    public String getVal_points() {
        return val_points;
    }

    public String getRadiant_points() {
        return radiant_points;
    }

    private String val_points;
    private String radiant_points;

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getLp() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public String getPlayer_title() {
        return player_title;
    }

    private String player_title;
    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    private String rank;
    private String lp = "0";
    private String rank_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private String name;
    private String version;
    private String tag;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    private String uid;
    private String card;
    private int n = 0;
    private String clientPV = "ew0KCSJwbGF0Zm9ybVR5cGUiOiAiUEMiLA0KCSJwbGF0Zm9ybU9TIjogIldpbmRvd3MiLA0KCSJwbGF0Zm9ybU9TVmVyc2lvbiI6ICIxMC4wLjE5MDQyLjEuMjU2LjY0Yml0IiwNCgkicGxhdGZvcm1DaGlwc2V0IjogIlVua25vd24iDQp9";
    private String store_content;

    public String _getSkinPrice(String ID) throws JSONException {
        String result = "";
        JSONArray data = new JSONObject(this.store_content).getJSONArray("Offers");
        for (int i = 0; i < data.length(); i++) {
            if (data.getJSONObject(i).getString("OfferID").equals(ID)) {

                result = data.getJSONObject(i).getJSONObject("Cost").getString("85ad13f7-3d1b-5128-9eb2-7cd8ee0b5741");
                break;
            }
        }
        return result;

    }

    public String _getColor(String ID) {
        String result = "";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/contenttiers/" + ID).build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject data = new JSONObject(response.body().string()).getJSONObject("data");
            result = data.getString("highlightColor");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void _getAllSkins() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/weapons/skins").build();
        try (Response response = client.newCall(request).execute()) {
            this.allskins = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void _getBundle(String ID) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/bundles/" + ID).build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject data = new JSONObject(response.body().string()).getJSONObject("data");
            Store_item item = new Store_item(data.getString("displayIcon"), data.getString("displayName"), "", "");
            this.bundle = item;


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void _getSotreContent() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/store/v1/offers/").addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            this.store_content = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String _getTierId(String name) throws JSONException {
        String result = "";
        JSONArray data = new JSONObject(this.allskins).getJSONArray("data");
        for (int i = 0; i < data.length(); i++) {
            if (data.getJSONObject(i).getString("displayName").equals(name)) {
                result = data.getJSONObject(i).getString("contentTierUuid");
                break;
            }
        }
        return result;

    }

    public void _addToStore(String ID) throws JSONException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/weapons/skinlevels/" + ID).build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject data = new JSONObject(response.body().string()).getJSONObject("data");
            Store_item item = new Store_item(data.getString("displayIcon"), data.getString("displayName"), this._getSkinPrice(ID), this._getColor(this._getTierId(data.getString("displayName"))));
            this.items.add(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void _getStore() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/store/v2/storefront/" + this.uid).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject data = new JSONObject(response.body().string());
            JSONObject bundle = data.getJSONObject("FeaturedBundle");
            this._getBundle(bundle.getJSONObject("Bundle").getString("DataAssetID"));
            JSONArray myskins = data.getJSONObject("SkinsPanelLayout").getJSONArray("SingleItemOffers");
            this.item_time_left = data.getJSONObject("SkinsPanelLayout").getLong("SingleItemOffersRemainingDurationInSeconds");
            this.featured_timer = bundle.getLong("BundleRemainingDurationInSeconds");
            Log.d("Skins", myskins.toString());
            for (int i = 0; i < myskins.length(); i++) {
                this._addToStore(myskins.getString(i));
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }

    public void _getBalance() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/store/v1/wallet/" + this.uid).addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject data = new JSONObject(response.body().string()).getJSONObject("Balances");
            this.val_points = data.getString("85ad13f7-3d1b-5128-9eb2-7cd8ee0b5741");
            this.radiant_points = data.getString("e59aa87c-4cbf-517a-5983-6e81511be9b7");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public String get_Card() {
        return this.card;
    }
    public void LeavePregame(String match){

        //https://glz-ap-1.ap.a.pvp.net/pregame/v1/matches/{match_id}/quit
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = "https://glz-eu-1.eu.a.pvp.net/pregame/v1/matches/"+match+"/quit";
        RequestBody body = RequestBody.create("", null);
        Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV)
                .url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            Log.d("Bye bye" , response.body().string());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void LockAgent(String match , String agent) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = "https://glz-eu-1.eu.a.pvp.net/pregame/v1/matches/"+match+"/lock/"+ agent;
        RequestBody body = RequestBody.create("", null);
        Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV)
                .url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            Log.d("Rep Locked" , response.body().string());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void SelectAgent(String match , String agent){
        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = "https://glz-eu-1.eu.a.pvp.net/pregame/v1/matches/"+match+"/select/"+ agent;
        RequestBody body = RequestBody.create("", null);
        Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV)
                .url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            Log.d("Rep selct" , response.body().string());



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getPlayerName(String uid){
        String res = "";
        OkHttpClient client = new OkHttpClient.Builder().build();

        RequestBody body = RequestBody.create("[\"" + uid + "\"]", JSON);
        Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV)
                .url("https://pd.eu.a.pvp.net/name-service/v2/players").put(body).build();
        try (Response response = client.newCall(request).execute()) {
            JSONArray data = new JSONArray(response.body().string());
            res = data.getJSONObject(0).getString("GameName");


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return res;
    }
    public void _getPlayerNameAndTag(String uid) throws JSONException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        RequestBody body = RequestBody.create("[\"" + uid + "\"]", JSON);
        Request request = new Request.Builder().addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV)
                .url("https://pd.eu.a.pvp.net/name-service/v2/players").put(body).build();
        try (Response response = client.newCall(request).execute()) {
            JSONArray data = new JSONArray(response.body().string());
            this.name = data.getJSONObject(0).getString("GameName");
            this.tag = data.getJSONObject(0).getString("TagLine");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void _setPlayerCard(String card_id) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/playercards/" + card_id).build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject data = new JSONObject(response.body().string());
            this.card = data.getJSONObject("data").getString("displayIcon");

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void _getLoadOut() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().addHeader("Authorization", "Bearer " + this.token).addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).url("https://pd.eu.a.pvp.net/personalization/v2/players/" + this.uid + "/playerloadout/").build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject data = new JSONObject(response.body().string());
            this.mycollection  = data.toString();
            Log.d("LOADOUT" , data.toString());
            this._setPlayerCard(data.getJSONObject("Identity").getString("PlayerCardID"));
            Request req = new Request.Builder().url("https://valorant-api.com/v1/playertitles/"+ data.getJSONObject("Identity").getString("PlayerTitleID")).build();
            try (Response rep  = client.newCall(req).execute()){
                this.player_title = new JSONObject(rep.body().string()).getJSONObject("data").getString("titleText");
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public Auth(String user, String pwd) {
        this.user = user;
        this.password = pwd;
        this.items = new ArrayList<Store_item>();

    }

    public void _getEntToken() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().addHeader("Authorization", "Bearer " + this.token).url(this.ENT_TOKEN_URL).post(RequestBody.create("{}", JSON)).build();
        try (Response response = client.newCall(request).execute()) {
            this.ent_toekn = new JSONObject(response.body().string()).getString("entitlements_token");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void _getUid() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().addHeader("Authorization", "Bearer " + this.token).addHeader("X-Riot-Entitlements-JWT", this.ent_toekn).url(this.USER_INFO_URL).post(RequestBody.create("", null)).build();
        try (Response response = client.newCall(request).execute()) {
            this.uid = new JSONObject(response.body().string()).getString("sub");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void _getCurrentClientVersion() {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/version").build();
        try (Response response = client.newCall(request).execute()) {
            String d = response.body().string();
            JSONObject data = new JSONObject(d).getJSONObject("data");
            this.version = MessageFormat.format("{0}-shipping-{1}-{2}", data.getString("branch"), data.getString("buildVersion"), data.getString("version").split("\\.")[3]);
            Log.d("Version", this.version);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStore() {
        this._getAllSkins();
        this._getSotreContent();
        this._getStore();
    }

    public String _getCurrentSeason() {
        String result = "";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/seasons").build();
        try (Response response = client.newCall(request).execute()) {
            JSONArray seasons = new JSONObject(response.body().string()).getJSONArray("data");
            for(int i =0;i<seasons.length();i++){
                JSONObject season = seasons.getJSONObject(i);
                if ((!new SimpleDateFormat("yyyy-MM-DD").parse(season.getString("endTime").replace("T00:00:00Z" , "")).before(new Date())) && (new SimpleDateFormat("yyyy-MM-DD").parse(season.getString("startTime").replace("T00:00:00Z" , "")).before(new Date()))){
                    if (season.getString("type").equals("EAresSeasonType::Act")) {
                        result = season.getString("uuid");
                        break;
                    }
                }
            }
            JSONObject last_season = seasons.getJSONObject(seasons.length() - 1);

        } catch (JSONException | IOException | ParseException e) {
            e.printStackTrace();
        }
        Log.d("Season" , result);
        return "2a27e5d2-4d30-c9e2-b15a-93b8909a442c";
    }

    public void _getRank() {
        HashMap<String, String> ranks = new HashMap<String, String>();
        ranks.put("0", "Unrated");
        ranks.put("1", "Unrated");
        ranks.put("2", "Unrated");
        ranks.put("3", "Iron 1");
        ranks.put("4", "Iron 2");
        ranks.put("5", "Iron 3");
        ranks.put("6", "Bronze 1");
        ranks.put("7", "Bronze 2");
        ranks.put("8", "Bronze 3");
        ranks.put("9", "Silver 1");
        ranks.put("10", "Silver 2");
        ranks.put("11", "Silver 3");
        ranks.put("12", "Gold 1");
        ranks.put("13", "Gold 2");
        ranks.put("14", "Gold 3");
        ranks.put("15", "Platinum 1");
        ranks.put("16", "Platinum 2");
        ranks.put("17", "Platinum 3");
        ranks.put("18", "Diamond 1");
        ranks.put("19", "Diamond 2");
        ranks.put("20", "Diamond 3");
        ranks.put("21", "Immortal");
        ranks.put("22", "None");
        ranks.put("23", "None");
        ranks.put("24", "Radiant");
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/mmr/v1/players/" + this.uid + "/competitiveupdates?startIndex=0&endIndex=15&queue=competitive").addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT", this.ent_toekn)
                .addHeader("X-Riot-ClientVersion", this.version)
                .addHeader("X-Riot-ClientPlatform", this.clientPV).build();
        try (Response response = client.newCall(request).execute()) {
            JSONArray data = new JSONObject(response.body().string()).getJSONArray("Matches");
            Log.d("Rank"  , data.toString());
            if (data.length() == 0) {
                this.rank_id = "0";
                this.lp = "0";
                this.rank = "Unranked";
                Log.d("Error" , "NIK OMEK CHFAMA2");
            } else {
                JSONObject lastmatch = data.getJSONObject(0);
                if (lastmatch.getString("SeasonID").equals(this._getCurrentSeason())) {
                    this.rank_id = lastmatch.getString("TierAfterUpdate");
                    this.lp = lastmatch.getString("RankedRatingAfterUpdate");
                    this.rank = ranks.get(this.rank_id);
                } else {
                    this.rank_id = "0";
                    this.lp = "0";
                    this.rank = "Unranked";

                    Log.d("Error" , "NIK OMEK CHFAMA1");
                }
            }


        } catch (JSONException | IOException e) {
            Log.d("Error" , "NIK OMEK CHFAMA");
        }
    }

    public boolean login() {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        OkHttpClient client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieManager)).build();


        JSONObject jsonob = new JSONObject();
        try {
            jsonob.put("client_id", "play-valorant-web-prod");
            jsonob.put("nonce", "1");
            jsonob.put("redirect_uri", "https://playvalorant.com/opt_in");
            jsonob.put("response_type", "token id_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonob.toString(), JSON);
        Request request = new Request.Builder().url(this.AUTH_URL).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject creds = new JSONObject();
            try {
                creds.put("type", "auth");
                creds.put("username", this.user);
                creds.put("password", this.password);
                Log.d("DATA", creds.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            body = RequestBody.create(creds.toString(), JSON);
            Request login_request = new Request.Builder().url(this.AUTH_URL).put(body).build();
            try {
                response = client.newCall(login_request).execute();
                String rep = response.body().string();
                if (rep.contains("failure")) {
                    return false;
                } else {
                    Pattern pat = Pattern.compile("access_token=(.*?)&scope", Pattern.DOTALL);
                    Matcher matcher = pat.matcher(rep);
                    matcher.find();
                    this.token = matcher.group(1);
                    Log.d("Token", this.token);



                    return true;
                }


            } catch (IOException e) {
                e.printStackTrace();

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

