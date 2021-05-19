package com.example.myapplication.Login;

import android.util.JsonReader;
import android.util.Log;
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
import java.util.ArrayList;
import java.util.HashMap;
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
public class Auth  implements Serializable {
    public static final MediaType JSON
            = MediaType.parse("application/json;charset=utf-8");
    private  String AUTH_URL = "https://auth.riotgames.com/api/v1/authorization";
    private String USER_INFO_URL  = "https://auth.riotgames.com/userinfo";
    private String ENT_TOKEN_URL = "https://entitlements.auth.riotgames.com/api/token/v1";
    private String user ;
    private String password;
    private String token;
    private String ent_toekn;
    private ArrayList<Store_item> items;
    private String allskins;

    public ArrayList<Store_item> getItems() {
        return items;
    }

    public Store_item getBundle() {
        return bundle;
    }

    private  Store_item bundle;
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

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    private String rank;
    private String lp;
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
    private String uid;
    private String card;
    private int n = 0;
    private String clientPV = "ew0KCSJwbGF0Zm9ybVR5cGUiOiAiUEMiLA0KCSJwbGF0Zm9ybU9TIjogIldpbmRvd3MiLA0KCSJwbGF0Zm9ybU9TVmVyc2lvbiI6ICIxMC4wLjE5MDQyLjEuMjU2LjY0Yml0IiwNCgkicGxhdGZvcm1DaGlwc2V0IjogIlVua25vd24iDQp9";
    private String store_content;
    private String _getSkinPrice(String ID) throws JSONException {
        String result = "";
    JSONArray data = new JSONObject(this.store_content).getJSONArray("Offers");
    for (int i =0;i<data.length();i++){
        if (data.getJSONObject(i).getString("OfferID").equals(ID)){

            result = data.getJSONObject(i).getJSONObject("Cost").getString("85ad13f7-3d1b-5128-9eb2-7cd8ee0b5741");
            break;
        }
    }
    return result;

    }
    private String _getColor(String ID){
        String result = "";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/contenttiers/"+ ID).build();
        try(Response response = client.newCall(request).execute()){
            JSONObject data = new JSONObject(response.body().string()).getJSONObject("data");
            result = data.getString("highlightColor");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    private void _getAllSkins(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/weapons/skins").build();
        try(Response response = client.newCall(request).execute()){
            this.allskins = response.body().string();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }
    private void _getBundle(String ID){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/bundles/"+ID).build();
        try(Response response = client.newCall(request).execute()){
            JSONObject data = new JSONObject(response.body().string()).getJSONObject("data");
            Store_item item = new Store_item(data.getString("displayIcon") , data.getString("displayName")  , "" , "");
            this.bundle = item;




        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    private void _getSotreContent(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/store/v1/offers/").addHeader("Content-Type"  ,"application/json")
                .addHeader("Authorization" , "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn)
                .addHeader("X-Riot-ClientVersion" , this.version)
                .addHeader("X-Riot-ClientPlatform" , this.clientPV).build();
        try(Response response = client.newCall(request).execute()){
            this.store_content = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String _getTierId(String name) throws JSONException {
        String result = "";
        JSONArray data = new JSONObject(this.allskins).getJSONArray("data");
        for (int i=0;i<data.length();i++){
            if(data.getJSONObject(i).getString("displayName").equals(name)){
                result = data.getJSONObject(i).getString("contentTierUuid");
                break;
            }
        }
        return  result;

    }
    private void _addToStore(String ID) throws JSONException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/weapons/skinlevels/"+ ID).build();
        try(Response response = client.newCall(request).execute()){
            JSONObject data =  new JSONObject(response.body().string()).getJSONObject("data");
            Store_item item = new Store_item(data.getString("displayIcon")  , data.getString("displayName")  , this._getSkinPrice(ID)  , this._getColor(this._getTierId(data.getString("displayName"))) );
            this.items.add(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void _getStore(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/store/v2/storefront/"+ this.uid).addHeader("Content-Type"  ,"application/json")
                .addHeader("Authorization" , "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn)
                .addHeader("X-Riot-ClientVersion" , this.version)
                .addHeader("X-Riot-ClientPlatform" , this.clientPV).build();
        try(Response response = client.newCall(request).execute()){
            JSONObject data = new JSONObject(response.body().string());
            JSONObject bundle = data.getJSONObject("FeaturedBundle");
            this._getBundle(bundle.getJSONObject("Bundle").getString("DataAssetID"));
            JSONArray myskins = data.getJSONObject("SkinsPanelLayout").getJSONArray("SingleItemOffers");
            Log.d("Skins"  , myskins.toString());
            for(int i = 0 ; i<myskins.length();i++){
                this._addToStore(myskins.getString(i));
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }


    }
    private void _getBalance(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/store/v1/wallet/"+this.uid).addHeader("Content-Type"  ,"application/json")
                .addHeader("Authorization" , "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn)
                .addHeader("X-Riot-ClientVersion" , this.version)
                .addHeader("X-Riot-ClientPlatform" , this.clientPV).build();
        try(Response response = client.newCall(request).execute()){
            JSONObject data = new JSONObject(response.body().string()).getJSONObject("Balances");
            this.val_points = data.getString("85ad13f7-3d1b-5128-9eb2-7cd8ee0b5741");
            this.radiant_points = data.getString("e59aa87c-4cbf-517a-5983-6e81511be9b7");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
    public String get_Card(){
        return this.card;
    }

    private void _getPlayerNameAndTag() throws JSONException {
        OkHttpClient client = new OkHttpClient.Builder().build();

        RequestBody body = RequestBody.create("[\""+this.uid+"\"]" ,JSON);
        Request request = new Request.Builder().addHeader("Content-Type"  ,"application/json")
                .addHeader("Authorization" , "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn)
                .addHeader("X-Riot-ClientVersion" , this.version)
                .addHeader("X-Riot-ClientPlatform" , this.clientPV)
                .url("https://pd.eu.a.pvp.net/name-service/v2/players").put(body).build();
        try(Response response = client.newCall(request).execute()){
            JSONArray data = new JSONArray(response.body().string());
            this.name = data.getJSONObject(0).getString("GameName");
            this.tag = data.getJSONObject(0).getString("TagLine");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void _setPlayerCard(String card_id){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request  request = new Request.Builder().url("https://valorant-api.com/v1/playercards/"+card_id).build();
        try(Response response = client.newCall(request).execute()){
            JSONObject data = new JSONObject(response.body().string());
            this.card = data.getJSONObject("data").getString("displayIcon");

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void _getLoadOut(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request =  new Request.Builder().addHeader("Authorization" , "Bearer " + this.token).addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn)
        .addHeader("X-Riot-ClientVersion" , this.version)
                .addHeader("X-Riot-ClientPlatform" , this.clientPV).url("https://pd.eu.a.pvp.net/personalization/v2/players/"+this.uid+"/playerloadout/").build();
        try(Response response = client.newCall(request).execute()){
            JSONObject data = new JSONObject(response.body().string());
            this._setPlayerCard(data.getJSONObject("PlayerCard").getString("ID"));
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
    public Auth(String user , String pwd){
        this.user = user;
        this.password = pwd;
        this.items = new ArrayList<Store_item>();

    }
    private void _getEntToken() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request =  new Request.Builder().addHeader("Authorization" , "Bearer " + this.token).url(this.ENT_TOKEN_URL).post(RequestBody.create("{}" , JSON) ).build();
        try (Response response = client.newCall(request).execute()) {
            this.ent_toekn = new JSONObject(response.body().string()).getString("entitlements_token");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void _getUid() throws IOException {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request =  new Request.Builder().addHeader("Authorization" , "Bearer " + this.token).addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn).url(this.USER_INFO_URL).post(RequestBody.create("" , null) ).build();
        try(Response response = client.newCall(request).execute()){
            this.uid = new JSONObject(response.body().string()).getString("sub");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void _getCurrentClientVersion(){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/version").build();
        try(Response response = client.newCall(request).execute()){
            String d = response.body().string();
            JSONObject data = new JSONObject(d).getJSONObject("data");
            this.version = MessageFormat.format("{0}-shipping-{1}-{2}", data.getString("branch") , data.getString("buildVersion") , data.getString("version").split("\\.")[3]);
            Log.d("Version" , this.version);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }
    public void updateStore(){
        this._getAllSkins();
        this._getSotreContent();
        this._getStore();
    }
    private String _getCurrentSeason(){
        String result = "";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://valorant-api.com/v1/seasons").build();
        try(Response response = client.newCall(request).execute()){
            JSONArray seasons = new JSONObject(response.body().string()).getJSONArray("data");
            JSONObject last_season = seasons.getJSONObject(seasons.length() - 1);
            result =   last_season.getString("uuid");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private void _getRank() {
        HashMap<String, String> ranks = new HashMap<String, String>();
        ranks.put("0" , "Unrated");
        ranks.put("1" , "Unrated");
        ranks.put("2" , "Unrated");
        ranks.put("3" , "Iron 1");
        ranks.put("4" , "Iron 2");
        ranks.put("5" , "Iron 3");
        ranks.put("6" , "Bronze 1");
        ranks.put("7" , "Bronze 2");
        ranks.put("8" , "Bronze 3");
        ranks.put("9" , "Silver 1");
        ranks.put("10" , "Silver 2");
        ranks.put("11" , "Silver 3");
        ranks.put("12" , "Gold 1");
        ranks.put("13" , "Gold 2");
        ranks.put("14" , "Gold 3");
        ranks.put("15" , "Platinum 1");
        ranks.put("16" , "Platinum 2");
        ranks.put("17" , "Platinum 3");
        ranks.put("18" , "Diamond 1");
        ranks.put("19" , "Diamond 2");
        ranks.put("20" , "Diamond 3");
        ranks.put("21" , "Immortal");
        ranks.put("22" , "None");
        ranks.put("23" , "None");
        ranks.put("24" , "Radiant");
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url("https://pd.eu.a.pvp.net/mmr/v1/players/"+this.uid+"/competitiveupdates?startIndex=0&endIndex=15&queue=competitive").addHeader("Content-Type"  ,"application/json")
                .addHeader("Authorization" , "Bearer " + this.token)
                .addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn)
                .addHeader("X-Riot-ClientVersion" , this.version)
                .addHeader("X-Riot-ClientPlatform" , this.clientPV).build();
        try(Response response = client.newCall(request).execute()){
            JSONArray data = new JSONObject(response.body().string()).getJSONArray("Matches");
            if (data.length() == 0){
                this.rank_id = "0";
                this.lp = "0";
                this.rank = "Unrakned";
            }
            else{
                JSONObject lastmatch = data.getJSONObject(0);
                if(lastmatch.getString("SeasonID").equals(this._getCurrentSeason())){
                    this.rank_id = lastmatch.getString("TierAfterUpdate");
                    this.lp = lastmatch.getString("RankedRatingAfterUpdate");
                    this.rank = ranks.get(this.rank_id);
                }
                else {
                    this.rank_id = "0";
                    this.lp = "0";
                    this.rank = "Unrakned";
                }
            }


        } catch (JSONException | IOException e) {
            e.printStackTrace();
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
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(jsonob.toString() ,JSON);
        Request request = new Request.Builder().url(this.AUTH_URL).post(body).build();
        try{
            Response response = client.newCall(request).execute();
            JSONObject creds  = new JSONObject();
            try{
                creds.put("type" , "auth");
                creds.put("username" , this.user);
                creds.put("password" , this.password);
                Log.d("DATA" , creds.toString());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            body = RequestBody.create(creds.toString() , JSON);
            Request login_request = new Request.Builder().url(this.AUTH_URL).put(body).build();
            try
            {
                response = client.newCall(login_request).execute();
                String rep = response.body().string();
                if(rep.contains("failure")){
                    return false;
                }
                else{
                    Pattern pat = Pattern.compile("access_token=(.*?)&scope" , Pattern.DOTALL);
                    Matcher matcher = pat.matcher(rep);
                    matcher.find();
                    this.token  = matcher.group(1);
                    Log.d("Token" , this.token);
                    this._getEntToken();
                    this._getUid();
                    this._getCurrentClientVersion();
                    this._getLoadOut();
                    this._getPlayerNameAndTag();
                    this._getRank();
                    this._getBalance();

                    return true;
                }



            }
            catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e){
            Log.d("OPPS" , e.getMessage());

        }


            return false;
    }

    }

