package com.scorp.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.scorp.loftmoney.screens.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        checkToken();
    }

    private void checkToken(){
        String token = ((LoftApp) getApplication()).getSharedPreferences().getString(LoftApp.TOKEN_KEY, "");

        if(TextUtils.isEmpty(token)){
            routeToLogin();
        }
        else{
            routeToMain();
        }
    }

    private void routeToMain(){
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
        finish();
        overridePendingTransition(R.anim.appreance, R.anim.fade);
    }

    private void routeToLogin(){
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
        finish();
        overridePendingTransition(R.anim.appreance, R.anim.fade);
    }
}