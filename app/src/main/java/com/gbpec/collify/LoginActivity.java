package com.gbpec.collify;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.sign_in)
    Button mSignIn;

    @BindView(R.id.sign_up)
            Button mSignUp;

    @BindView(R.id.user_mail)
    EditText mEmail;

    @BindView(R.id.password)
    EditText mPass;

    @BindView(R.id.forgot)
    TextView mForgotPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setStatusBarColor(Color.BLACK);
        ButterKnife.bind(this);

       mSignIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent  = new Intent(LoginActivity.this,HomeActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
               SharedPreferences.Editor editor = getSharedPreferences("USER",MODE_PRIVATE).edit();
               editor.putBoolean("IS_SIGNED_IN",true);
               editor.commit();
               finish();
           }
       });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });



    }
}
