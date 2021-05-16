package com.example.myapplication.Login;

import android.util.JsonReader;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.text.MessageFormat;
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

public class Auth  {
    public static final MediaType JSON
            = MediaType.parse("application/json;charset=utf-8");
    private  String AUTH_URL = "https://auth.riotgames.com/api/v1/authorization";
    private String USER_INFO_URL  = "https://auth.riotgames.com/userinfo";
    private String ENT_TOKEN_URL = "https://entitlements.auth.riotgames.com/api/token/v1";
    private String user ;
    private String password;
    private String token;
    private String ent_toekn;
    private String rank;
    private String lp;
    private String name;
    private String version;
    private String tag;
    private String uid;
    private CookieManager cookieManager = new CookieManager();

    private OkHttpClient client = new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieManager)).build();

    public Auth(String user , String pwd){
        this.user = user;
        this.password = pwd;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
    }
    private void _getEntToken() throws IOException {
        Request request =  new Request.Builder().addHeader("Authorization" , "Bearer " + this.token).url(this.ENT_TOKEN_URL).post(RequestBody.create("{}" , JSON) ).build();
        try (Response response = client.newCall(request).execute()) {
            this.ent_toekn = new JSONObject(response.body().string()).getString("entitlements_token");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void _getUid() throws IOException {
        Request request =  new Request.Builder().addHeader("Authorization" , "Bearer " + this.token).addHeader("X-Riot-Entitlements-JWT" , this.ent_toekn).url(this.USER_INFO_URL).post(RequestBody.create("" , null) ).build();
        try(Response response = client.newCall(request).execute()){
            this.uid = new JSONObject(response.body().string()).getString("sub");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void _getCurrentClientVersion(){
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
    private void _getRank() {
        return;
    }
    public boolean login() {



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
                    return true;
                }



            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        catch (IOException e){
            Log.d("OPPS" , e.getMessage());

        }


            return false;
    }

    }

