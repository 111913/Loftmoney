package com.scorp.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddItemActivity extends AppCompatActivity {
    private Button btnAdd;
    private TextInputLayout tilName;
    private TextInputLayout tilExpense;
    private TextInputEditText etName;
    private TextInputEditText etExpense;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(etName.getText() != null && etName.getText().toString().trim().isEmpty()){
                tilName.setError(getString(R.string.input_text_is_empty));
            }
            else
                tilName.setError("");

            if(etExpense.getText() != null && etExpense.getText().toString().trim().isEmpty()){
                tilExpense.setError(getString(R.string.input_text_is_empty));
            }
            else
                tilExpense.setError("");

            stateBtnAdd();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        btnAdd = findViewById(R.id.btnAddItem);
        tilName = findViewById(R.id.tilName);
        tilExpense = findViewById(R.id.tilExpense);
        etName = findViewById(R.id.etName);
        etExpense = findViewById(R.id.etExpense);

        etExpense.addTextChangedListener(textWatcher);
        etName.addTextChangedListener(textWatcher);
    }

    private void stateBtnAdd(){
        if(etName.getText() != null && !etName.getText().toString().trim().isEmpty()
                && etExpense.getText() != null && !etExpense.getText().toString().isEmpty()){
            btnAdd.setEnabled(true);
            btnAdd.setTextColor(getApplicationContext().getResources().getColor(R.color.colorActiveView));
            Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_arrow_enable);
            btnAdd.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        }
        else{
            btnAdd.setEnabled(false);
            btnAdd.setTextColor(getApplicationContext().getResources().getColor(R.color.colorUnActiveView));
            Drawable img = getApplicationContext().getResources().getDrawable(R.drawable.ic_arrow_disable);
            btnAdd.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        }
    }
}