package com.scorp.loftmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.scorp.loftmoney.remote.BalanceResponse;

import java.util.Objects;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BalanceFragment extends Fragment {

    private BalanceView balanceView;
    private TextView expenseValueView;
    private TextView incomeValueView;
    private TextView balanceValueView;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static BalanceFragment getInstance(){
        return new BalanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_balance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUI(view);
        getBalance();
    }

    private void setUI(View view){
        balanceView = view.findViewById(R.id.balanceView);
        expenseValueView = view.findViewById(R.id.expenseTextViewValue);
        incomeValueView = view.findViewById(R.id.incomeTextViewValue);
        balanceValueView = view.findViewById(R.id.txtBalanceFinanceValue);
    }

    private void getBalance(){
        String token = Objects.requireNonNull(getActivity()).getSharedPreferences(getString(R.string.app_name), 0).getString(LoftApp.TOKEN_KEY, "");
        compositeDisposable.add(((LoftApp) getActivity().getApplication()).getLoftMoneyApi().getBalance(token)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BalanceResponse>() {
                    @Override
                    public void accept(BalanceResponse balanceResponse) throws Exception {
                        updateUI(balanceResponse);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    private void updateUI(BalanceResponse balanceResponse){
        expenseValueView.setText(balanceResponse.getTotalExpenses());
        incomeValueView.setText(balanceResponse.getTotalIncome());

        float expense = Float.parseFloat(balanceResponse.getTotalExpenses());
        float income = Float.parseFloat(balanceResponse.getTotalIncome());
        balanceView.update(expense, income);

        balanceValueView.setText(String.valueOf(income - expense));
    }
}
