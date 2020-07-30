package com.scorp.loftmoney;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scorp.loftmoney.cells.item.ItemCellModel;
import com.scorp.loftmoney.cells.item.ItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class BudgetFragment extends Fragment {

    private static final int REQUEST_CODE = 100;
    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private FloatingActionButton fabAdd;

    public static BudgetFragment newInstance(BudgetFragmentTags tag){
        BudgetFragment budgetFragment = new BudgetFragment();
        Bundle args = new Bundle();

        args.putSerializable("positionTag", tag);
        budgetFragment.setArguments(args);

        return budgetFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_budget, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        //super.onViewCreated(view, savedInstanceState);
        fabAdd = view.findViewById(R.id.fab_add_expense);
        fabAdd.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent addItemAct = new Intent(getActivity(), AddItemActivity.class);
                startActivityForResult(addItemAct, REQUEST_CODE);
            }
        });

        recyclerView = view.findViewById(R.id.costsRecyclerView);
        itemsAdapter = new ItemsAdapter();

        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        //itemsAdapter.setData(generateExpenses());
        //itemsAdapter.addData(generateIncomes());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if (getArguments() != null) {
                if((BudgetFragmentTags) getArguments().getSerializable("positionTag") == BudgetFragmentTags.INCOMES){
                    itemsAdapter.addItem(
                            new ItemCellModel(data.getStringExtra("name"), data.getStringExtra("cost"),
                                    R.string.currency, R.color.incomeColor)
                    );
                }
                else {
                    itemsAdapter.addItem(
                            new ItemCellModel(data.getStringExtra("name"), data.getStringExtra("cost"),
                                    R.string.currency, R.color.expenseColor)
                    );
                }
            }
        }
    }

//    private List<ItemCellModel> generateExpenses(){
//        List<ItemCellModel> itemCellModels = new ArrayList<>();
//        itemCellModels.add(new ItemCellModel("Молоко", "70", R.string.currency, R.color.expenseColor));
//        itemCellModels.add(new ItemCellModel("Зубная щетка", "70", R.string.currency, R.color.expenseColor));
//        itemCellModels.add(new ItemCellModel("Сковородка с антипригарным покрытием",
//                "1670", R.string.currency, R.color.expenseColor));
//        return itemCellModels;
//    }
//
//    private List<ItemCellModel> generateIncomes(){
//        List<ItemCellModel> itemCellModels = new ArrayList<>();
//        itemCellModels.add(new ItemCellModel("Зарплата.Июнь", "70000", R.string.currency, R.color.incomeColor));
//        itemCellModels.add(new ItemCellModel("Премия", "7000", R.string.currency, R.color.incomeColor));
//        itemCellModels.add(new ItemCellModel("Олег наконец-то вернул долг",
//                "300000", R.string.currency, R.color.incomeColor));
//        return itemCellModels;
//    }
}