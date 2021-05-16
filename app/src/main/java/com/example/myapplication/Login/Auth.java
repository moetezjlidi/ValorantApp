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
    private static String AUTH_URL = "https://auth.riotgames.com/api/v1/authorization";
    private String user ;
    private String password;
    private String token;
    public Auth(String user , String pwd){
        this.user = user;
        this.password = pwd;
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
                    Pattern pat = Pattern.compile("access_token=(.*?)&token" , Pattern.DOTALL);
                    Matcher matcher = pat.matcher(rep);
                    matcher.find();
                    this.token  = matcher.group(1);
                    Log.d("Token" , this.token);
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

