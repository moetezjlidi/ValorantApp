package com.example.myapplication;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myapplication.databinding.ActivityLoginPageBinding;
import com.example.myapplication.Login.Auth;

import org.w3c.dom.Text;

public class Login_Page extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
private ActivityLoginPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                 Thread thread = new Thread(new Runnable() {

                     @Override
                     public void run() {

                         try  {
                             if(auth.login()){
                                 ((TextView) findViewById(R.id.result)).setText("Logged in");
                                 findViewById(R.id.loadingPanel).setVisibility(View.GONE);
                             }
                             else{
                                 ((TextView) findViewById(R.id.result)).setText("Invalid Username/Password ! ");
                             }

                         } catch (Exception e) {
                             e.printStackTrace();
                         }
                     }
                 });

                 thread.start();
                 findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
             }

         }
     });





    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_login_page);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}