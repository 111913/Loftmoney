package com.scorp.loftmoney;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class BudgetPagerAdapter extends FragmentPagerAdapter {

    public BudgetPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        BudgetFragmentTags tag;
        switch (position){
            case 0:
                return BudgetFragment.newInstance(BudgetFragmentTags.EXPENCES);
            case 1:
                return BudgetFragment.newInstance(BudgetFragmentTags.INCOMES);
            default:
                return BalanceFragment.getInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
