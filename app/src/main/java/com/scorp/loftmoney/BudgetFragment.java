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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.scorp.loftmoney.cells.item.ItemCellModel;
import com.scorp.loftmoney.cells.item.ItemsAdapter;
import com.scorp.loftmoney.remote.LoftMoneyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BudgetFragment extends Fragment {

    private static final int REQUEST_CODE = 100;

    private RecyclerView recyclerView;
    private ItemsAdapter itemsAdapter;
    private FloatingActionButton fabAdd;
    private SwipeRefreshLayout srl;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

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
        super.onViewCreated(view, savedInstanceState);

        //configureAddButton(view);
        configureItemsDisplay(view);

        if (getArguments() != null) {
            if((BudgetFragmentTags) getArguments().getSerializable("positionTag") == BudgetFragmentTags.INCOMES){
                loadItems("income");
            }
            else {
                loadItems("expense");
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if (getArguments() != null) {
                if((BudgetFragmentTags) getArguments().getSerializable("positionTag") == BudgetFragmentTags.INCOMES){
                    itemsAdapter.addItem( new ItemCellModel(data.getStringExtra("name"),
                                                            data.getStringExtra("cost"),
                                                            R.string.currency, R.color.incomeColor)
                    );
                    addItem(data.getStringExtra("cost"), data.getStringExtra("name"),"income");
                }
                else {
                    itemsAdapter.addItem( new ItemCellModel(data.getStringExtra("name"),
                                                            data.getStringExtra("cost"),
                                                             R.string.currency, R.color.expenseColor)
                    );
                    addItem(data.getStringExtra("cost"), data.getStringExtra("name"),"expense");
                }
            }
        }
    }

    private void configureItemsDisplay(@NonNull View view){
        srl = view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = view.findViewById(R.id.costsRecyclerView);
        itemsAdapter = new ItemsAdapter();

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (getArguments() != null) {
                    if((BudgetFragmentTags) getArguments().getSerializable("positionTag") == BudgetFragmentTags.INCOMES){
                        loadItems("income");
                    }
                    else {
                        loadItems("expense");
                    }
                }
            }
        });

        recyclerView.setAdapter(itemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void loadItems(String typeItems){
        final List<ItemCellModel> itemCellModels = new ArrayList<>();
        String token = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.app_name), 0).getString(LoftApp.TOKEN_KEY, "");

        Disposable disposable = ((LoftApp) getActivity().getApplication()).getLoftMoneyApi().getItem(token, typeItems)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<LoftMoneyItem>>() {
                    @Override
                    public void accept(List<LoftMoneyItem> loftMoneyItems) throws Exception {
                        Log.e("TAG", "Success " + loftMoneyItems);

                        srl.setRefreshing(false);
                        for(LoftMoneyItem lmi : loftMoneyItems){
                            if(lmi.getName() != null && lmi.getPrice() != null && lmi.getType() != null){
                                itemCellModels.add(ItemCellModel.getInstance(lmi));
                            }
                        }

                        itemsAdapter.setData(itemCellModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TAG", "Error " + throwable);
                        srl.setRefreshing(false);
                    }
                });

        compositeDisposable.add(disposable);
    }

    private void addItem(String price, String name, String type){
        String token = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.app_name), 0).getString(LoftApp.TOKEN_KEY, "");

        compositeDisposable.add(((LoftApp) getActivity().getApplication())
                .getLoftMoneyApi()
                .addItem(token, price, name, type)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {

                    @Override
                    public void run() throws Exception {
                        Log.e("TAG", "Completed");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TAG", "Error " + throwable.getLocalizedMessage());
                    }
                }));
    }
}