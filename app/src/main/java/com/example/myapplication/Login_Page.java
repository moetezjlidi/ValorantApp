package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityLoginPageBinding;
import com.example.myapplication.Login.Auth;

import org.json.JSONException;

import java.io.IOException;
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
        (findViewById(R.id.loadingPanel)).setVisibility(View.GONE);
     Button b_login = (Button)  findViewById(R.id.login);
     b_login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             TextView result = (TextView) findViewById(R.id.result);
             TextView result2 = (TextView) findViewById(R.id.result2);
             TextView user =(TextView)  findViewById(R.id.username);
             TextView pwd = (TextView) findViewById(R.id.password);
             if (user.getText().length() == 0 || pwd.getText().length() == 0 ){
                    result.setText("Please enter a valid username/password !");
             }
             else
             {

                 Auth auth = new Auth(user.getText().toString() , pwd.getText().toString());
                Thread t =  new Thread(new Runnable() {
                    @Override
                     public void run() {
                         runOnUiThread(new Runnable() {

                             @Override
                             public void run() {
                                 (findViewById(R.id.loadingPanel)).setVisibility(View.VISIBLE);

                                 findViewById(R.id.login).setVisibility(View.GONE);
                                 // Stuff that updates the UI

                             }
                         });

                         if (auth.login()) {
                             result2.setText(result2.getText() + "\n" + "Logged in !");
                             try {
                                 auth._getEntToken();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                             result2.setText(result2.getText() + "\n" + "Generated Entiltement Token");
                             try {
                                 auth._getUid();
                             } catch (IOException e) {
                                 e.printStackTrace();
                             }
                             result2.setText(result2.getText() + "\n" + "Received UUID");
                             auth._getCurrentClientVersion();
                             result2.setText(result2.getText() + "\n" + "Version is Up To Date");
                             auth._getLoadOut();
                             result2.setText(result2.getText() + "\n" + "Downloaded Player's loadout");
                             try {
                                 auth._getPlayerNameAndTag();
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                             result2.setText(result2.getText() + "\n" + "Got in-game name and tag");
                             auth._getRank();
                             result2.setText(result2.getText() + "\n" + "Got player's rank");
                             auth._getBalance();
                             result2.setText(result2.getText() + "\n" + "Got player's wallet");
                             ((TextView) findViewById(R.id.result)).setText("logged in!");
                             Intent i = new Intent(Login_Page.this, User.class);
                             i.putExtra("auth", auth);
                             Login_Page.this.startActivity(i);


                         } else {
                             ((TextView) findViewById(R.id.result)).setText("Invalid Username/Password ! ");
                         }
                         runOnUiThread(new Runnable() {

                             @Override
                             public void run() {
                                 (findViewById(R.id.loadingPanel)).setVisibility(View.GONE);

                                 findViewById(R.id.login).setVisibility(View.VISIBLE);
                                 // Stuff that updates the UI

                             }
                         });
                     }
                 });
                t.start();



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