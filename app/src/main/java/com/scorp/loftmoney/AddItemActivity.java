package com.scorp.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AddItemActivity extends AppCompatActivity {
    private TextView addExpense;
    private ImageView checkView;
    private EditText titleExpense;
    private EditText priceExpense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addExpense = findViewById(R.id.addNewExpense);
        checkView = findViewById(R.id.checkView);
        titleExpense = findViewById(R.id.titleExpense);
        priceExpense = findViewById(R.id.priceExpense);

        addItem();
    }

    private void addItem(){
        titleExpense.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(titleExpense.getText().length() > 0 && priceExpense.getText().length() > 0)
                    showAddItem();
                else
                    hideAddItem();
            }
        });

        priceExpense.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(titleExpense.getText().length() > 0 && priceExpense.getText().length() > 0)
                    showAddItem();
                else
                    hideAddItem();
            }
        });
    }

    private void showAddItem(){
        addExpense.setVisibility(View.VISIBLE);
        addExpense.setClickable(true);
        checkView.setVisibility(View.VISIBLE);
    }

    private void hideAddItem(){
        addExpense.setVisibility(View.INVISIBLE);
        addExpense.setClickable(false);
        checkView.setVisibility(View.INVISIBLE);
    }
}