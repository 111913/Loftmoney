package com.scorp.loftmoney.screens.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scorp.loftmoney.LoftApp;
import com.scorp.loftmoney.MainActivity;
import com.scorp.loftmoney.R;


public class LoginActivity extends AppCompatActivity implements LoginView {

    private Button loginButton;
    private LoginPresenter loginPresenter = new LoginPresenterImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButtonView);
        loginPresenter.attachViewState(this);

        configureButton();
    }

    @Override
    protected void onPause() {
        loginPresenter.disposeRequest();
        super.onPause();
    }

    private void configureButton(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.performLogin(((LoftApp) getApplication()).getAuthApi());
            }
        });
    }

    @Override
    public void toggleSending(boolean isActive) {
        loginButton.setVisibility(isActive ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showMessage(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String token) {
        Toast.makeText(getApplicationContext(), "User was logged successfully", Toast.LENGTH_SHORT).show();
        ((LoftApp) getApplication()).getSharedPreferences().edit().putString(LoftApp.TOKEN_KEY, token).apply();

        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}