package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.Login.Auth;

public class agent_select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_select);
        Intent i = getIntent();
        Auth auth = (Auth) i.getExtras().get("auth");
        Log.d("agentselc" , auth.getUid());
    }
}