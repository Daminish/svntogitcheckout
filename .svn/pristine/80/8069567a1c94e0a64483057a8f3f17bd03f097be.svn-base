package com.capco.technologies.living.presentation.budgeting;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.Budget;
import com.capco.technologies.living.fragments.BudgetingHomeFragment;
import com.capco.technologies.living.presentation.base.BaseActivity;

public class BudgetingActivity extends BaseActivity implements BudgetingContract.View {

    FragmentTransaction ft = null;
    private BudgetingContract.Presenter mPresenter;

    public Budget budget = new Budget();


    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_budgeting_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new BudgetingPresenter(this);
    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void setPresenter(BudgetingContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mPresenter.start();
    }


    @Override
    public void showMortgageAmountRequirementFragment() {

    }


    @Override
    public void showBudgetingMenuFragment() {
        ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.container, BudgetingHomeFragment.newInstance(null), null);
        ft.addToBackStack(null);

        // Complete the changes added above
        ft.commit();
    }

    @Override
    public void setScreenTitle(String title) {

    }

    @Override
    public void setPageBanner(String bannerString) {
        setBannerText(bannerString);
    }

    @Override
    public String getBannerText() {
        return getString(R.string.subtitle_activity_main);
    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {

            super.onBackPressed();
        }
    }
}
