package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityLoginPageBinding;
import com.example.myapplication.Login.Auth;

import java.io.Serializable;

public class Login_Page extends AppCompatActivity implements Serializable {

    private AppBarConfiguration appBarConfiguration;
private ActivityLoginPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        binding = ActivityLoginPageBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
     Button b_login = (Button)  findViewById(R.id.login);
     b_login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             TextView result = (TextView) findViewById(R.id.result);
             TextView user =(TextView)  findViewById(R.id.username);
             TextView pwd = (TextView) findViewById(R.id.password);
             if (user.getText().length() == 0 || pwd.getText().length() == 0 ){
                    result.setText("Please enter a valid username/password !");
             }
             else
             {

                 Auth auth = new Auth(user.getText().toString() , pwd.getText().toString());

                             if(auth.login()){
                                 ((TextView) findViewById(R.id.result)).setText("logged in!");
                                 Intent  i = new Intent(Login_Page.this , User.class);
                                 i.putExtra("auth" , auth);
                                 Login_Page.this.startActivity(i);

                             }
                             else{
                                 ((TextView) findViewById(R.id.result)).setText("Invalid Username/Password ! ");
                             }



             }

         }
     });





    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.username);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}