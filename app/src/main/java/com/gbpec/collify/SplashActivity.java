package com.gbpec.collify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setStatusBarColor(Color.BLACK);

        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("IS_SIGNED_IN",false);

        isLoggedIn = false;
        CountDownTimer timer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

               if(false)
               {
                   Intent intent  = new Intent(SplashActivity.this,HomeActivity.class);
                   FirebaseFirestore.getInstance()
                           .collection("users")
                           .document(preferences.getString("main_user","user"))
                           .get()
                           .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                               @Override
                               public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                  if(task.isComplete())
                                  {
                                      DocumentSnapshot snapshot = task.getResult();
                                      String name = (String) snapshot.get("name");
                                      String email = (String) snapshot.get("email");
                                      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                      intent.putExtra("NAME",name);
                                      intent.putExtra("EMAIL",email);
                                      startActivity(intent);
                                      finish();
                                  }
                               }
                           });

               }
               else
               {
                   Intent intent  = new Intent(SplashActivity.this,LoginActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(intent);
                   finish();
               }
            }
        };
        timer.start();

    }
}
