package com.scorp.loftmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class BalanceFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_balance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button rndButton = view.findViewById(R.id.rndButton);
        final BalanceView balanceView = view.findViewById(R.id.balanceView);
        rndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balanceView.update(new Random().nextFloat(), new Random().nextFloat());
            }
        });
    }

    public static BalanceFragment getInstance(){
        return new BalanceFragment();
    }
}
