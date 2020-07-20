package com.scorp.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.fab_add_expense).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent addItemAct = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(addItemAct);
            }
        });
    }
}