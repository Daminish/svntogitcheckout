package com.capco.technologies.living.presentation.findhome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.capco.technologies.living.R;
import com.capco.technologies.living.datamodel.FindHomeLandingPageData;
import com.capco.technologies.living.domain.model.AreaDetailMenu;
import com.capco.technologies.living.domain.model.FindHomeMenu;
import com.capco.technologies.living.presentation.base.BaseActivity;
import com.capco.technologies.living.presentation.budgeting.BudgetingActivity;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;
import com.capco.technologies.living.storage.MockData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class FindHomeActivity extends BaseActivity implements FindHomeContract.View,OnRecyclerViewItemClickListener<FindHomeMenu> {

    private RecyclerView find_home_landing_recycler;
    private List<FindHomeLandingPageData> findHomeLandingitems=new ArrayList<>();

    private FindHomeContract.Presenter mPresenter;

    RecyclerView recyclerView;
    FindHomeAdapter findHomeAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new FindHomePresenter(this);
        setContentView(R.layout.activity_find_home_landing_page);

        recyclerView = findViewById(R.id.find_home_landing_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initializeData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void initializeData() {


        findHomeAdapter = new FindHomeAdapter(MockData.getInstance().getFindHomeMenus(), this);
        recyclerView.setAdapter(findHomeAdapter);
    }

    @Override
    public void setPresenter(FindHomeContract.Presenter presenter) {

    }

    @Override
    public void setScreenTitle(String title) {

    }

    @Override
    public void setPageBanner(String bannerString) {

    }

    @Override
    public String getBannerText() {
        return null;
    }

    @Override
    public void onItemClick(View v, FindHomeMenu clickedItem, int position) {
        Intent intent = null;
        switch (position){
            case 0:
                intent = new Intent(this, AreaGuideActivity.class);

                break;
            case 1:
                intent = new Intent(this, FindingPropertyActivity.class);

                break;
        }

        startActivity(intent);


    }
}
