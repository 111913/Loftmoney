package com.scorp.loftmoney;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scorp.loftmoney.cells.item.ItemsAdapter;
import com.scorp.loftmoney.cells.item.ItemAdapterClick;
import com.scorp.loftmoney.cells.item.ItemCellModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.costsRecyclerView);
        itemsAdapter = new ItemsAdapter();
//        itemsAdapter.setItemAdapterClick(new ItemAdapterClick() {
//            @Override
//            public void onMoneyClick(ItemCellModel itemCellModel) {
//
//            }
//        });

        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        itemsAdapter.setData(generateExpenses());
        itemsAdapter.addData(generateIncomes());

        findViewById(R.id.fab_add_expense).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent addItemAct = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivityForResult(addItemAct, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        ItemCellModel itemCellModel = new ItemCellModel(data.getStringExtra("name"),
                data.getStringExtra("expense"), R.string.currency, R.color.expenseColor);
        itemsAdapter.addItem(itemCellModel);
    }

    private List<ItemCellModel> generateExpenses(){
        List<ItemCellModel> itemCellModels = new ArrayList<>();
        itemCellModels.add(new ItemCellModel("Молоко", "70", R.string.currency, R.color.expenseColor));
        itemCellModels.add(new ItemCellModel("Зубная щетка", "70", R.string.currency, R.color.expenseColor));
        itemCellModels.add(new ItemCellModel("Сковородка с антипригарным покрытием",
                "1670", R.string.currency, R.color.expenseColor));
        return itemCellModels;
    }

    private List<ItemCellModel> generateIncomes(){
        List<ItemCellModel> itemCellModels = new ArrayList<>();
        itemCellModels.add(new ItemCellModel("Зарплата.Июнь", "70000", R.string.currency, R.color.incomeColor));
        itemCellModels.add(new ItemCellModel("Премия", "7000", R.string.currency, R.color.incomeColor));
        itemCellModels.add(new ItemCellModel("Олег наконец-то вернул долг",
                "300000", R.string.currency, R.color.incomeColor));
        return itemCellModels;
    }
}