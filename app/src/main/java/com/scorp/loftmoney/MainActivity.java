package com.scorp.loftmoney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fabAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI();
    }

    private void setUI(){
        configureAddButton();
        toolbar = findViewById(R.id.toolbar);
        tabLayout = findViewById(R.id.tabs);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new BudgetPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        tabLayout.setupWithViewPager(viewPager);
        Objects.requireNonNull(tabLayout.getTabAt(0)).setText(R.string.expenses);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setText(R.string.incomes);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setText(R.string.balance);
    }

    private void configureAddButton(){
        fabAddItem = findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final int activeFragmentIndex = viewPager.getCurrentItem();
                Fragment activeFragment = getSupportFragmentManager().getFragments().get(activeFragmentIndex);

                Intent addItemAct = new Intent(MainActivity.this, AddItemActivity.class);
                addItemAct.putExtra("Index", activeFragmentIndex);
                activeFragment.startActivityForResult(addItemAct, 100);
                overridePendingTransition(R.anim.diagonal_show_translate, R.anim.fade);
            }
        });
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorActionMode));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorActionMode));
        fabAddItem.hide();
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        tabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        fabAddItem.show();
    }

}