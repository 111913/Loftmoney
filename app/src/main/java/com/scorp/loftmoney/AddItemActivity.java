package com.scorp.loftmoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddItemActivity extends AppCompatActivity {
    private final int EXPENCES_FRAGMENT_INDEX = 0;
    //private final int INCOMES_FRAGMENT_INDEX = 1;
    private final int DEFAULT_INTENT_TAG = 404;

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
            boolean etNameIsEmpty;
            boolean etExpenseIsEmpty;

            if(etName.getText() != null && etName.getText().toString().trim().isEmpty()){
                tilName.setError(getString(R.string.input_text_is_empty));
                etNameIsEmpty = true;
            }
            else{
                tilName.setError("");
                etNameIsEmpty = false;
            }

            if(etExpense.getText() != null && etExpense.getText().toString().trim().isEmpty()){
                tilExpense.setError(getString(R.string.input_text_is_empty));
                etExpenseIsEmpty = true;
            }
            else{
                tilExpense.setError("");
                etExpenseIsEmpty = false;
            }

            boolean isEnabled = !etNameIsEmpty && !etExpenseIsEmpty;

            btnAdd.setEnabled(isEnabled);
        }
    };

    private View.OnClickListener btnAddClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            intent.putExtra("name", Objects.requireNonNull(etName.getText()).toString());
            intent.putExtra("cost", Objects.requireNonNull(etExpense.getText()).toString());
            setResult(RESULT_OK, intent);
            finish();
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

        btnAdd.setOnClickListener(btnAddClickListener);
        setColorEditText();
    }

    private void setColorEditText(){
        if(getIntent().getIntExtra("Index", DEFAULT_INTENT_TAG) == EXPENCES_FRAGMENT_INDEX){
            etName.setTextColor(getResources().getColor(R.color.expenseColor));
            etExpense.setTextColor(getResources().getColor(R.color.expenseColor));
        }
        else{
            etName.setTextColor(getResources().getColor(R.color.incomeColor));
            etExpense.setTextColor(getResources().getColor(R.color.incomeColor));
        }
    }

}