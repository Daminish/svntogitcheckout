package com.capco.technologies.living.presentation.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.DashboardMenu;
import com.capco.technologies.living.presentation.base.BaseActivity;
import com.capco.technologies.living.presentation.budgeting.BudgetingActivity;
import com.capco.technologies.living.presentation.findhome.FindHomeActivity;
import com.capco.technologies.living.presentation.onboarding.login.LoginActivity;
import com.capco.technologies.living.presentation.ui.custom.CirclePageIndicator;
import com.capco.technologies.living.presentation.userprofile.UserProfileActivity;
import com.capco.technologies.living.storage.MockData;

public class DashboardActivity extends BaseActivity implements DashboardContract.View, OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private DashboardContract.Presenter mPresenter;
    CirclePageIndicator circlePageIndicator;
    private GridLayout gridLayout;

    private DashboardOfferAdapter dashBoardAdapter;


    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        onCreate(savedInstanceState, R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //find ViewPager
        setViewPager();


        gridLayout = findViewById(R.id.grid_dashboard_menu);


        new DashboardPresenter(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;
        switch (id){
            case R.id.nav_logout:
                intent = new Intent(this, LoginActivity.class);

                break;
            case R.id.nav_profile:
                intent = new Intent(this, UserProfileActivity.class);
                break;

            case R.id.nav_home:
                intent = new Intent(this, DashboardActivity.class);
                break;
        }

        startActivity(intent);
        finish();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        gridLayout.removeAllViews();
        mPresenter.start();
    }

    private void setViewPager() {

        viewPager = findViewById(R.id.viewPager);
        circlePageIndicator = findViewById(R.id.circule_page_indicator);

        dashBoardAdapter = new DashboardOfferAdapter(this.getSupportFragmentManager(), MockData.getInstance().getOFFERS());

        viewPager.setAdapter(dashBoardAdapter);
        circlePageIndicator.setViewPager(viewPager);
        viewPager.setClipToPadding(false);
        viewPager.setPadding(50, 0, 50, 0);
        viewPager.setPageMargin(20);
        viewPager.setCurrentItem(1);
    }


    private OnClickListener onClickListener = view -> {

        Intent intent = null;

        if (intent != null)
            startActivity(intent);
    };

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setScreenTitle(String title) {
        setTitle(title);
    }

    @Override
    public void setPageBanner(String bannerString) {
        super.setBannerText(bannerString);
    }


    @Override
    public View getGridMenu(DashboardMenu menu) {

        View view = LayoutInflater.from(this).inflate(R.layout.dashboard_menu_item, gridLayout, false);

        ((ImageView) view.findViewById(R.id.iv_dashboard_menu_icon)).setImageResource(menu.icon);
        ((TextView) view.findViewById(R.id.tv_dashboard_menu_title)).setText(menu.title);
        ((TextView) view.findViewById(R.id.tv_dashboard_menu_desc)).setText(menu.desc);

        view.setTag(R.string.dashboard_menu_tag, menu);
        view.setOnClickListener(this);

        return view;
    }

    @Override
    public void addDashboardMenu(View menuView) {
        gridLayout.addView(menuView);
        GridLayout.LayoutParams param = new GridLayout.LayoutParams();
        param.setGravity(Gravity.CENTER);
        param.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
        menuView.setLayoutParams(param);
        gridLayout.requestLayout();
    }

    @Override
    public void navigateToBudgetingScreen() {
        Intent intent = new Intent(this, BudgetingActivity.class);
        startActivity(intent);


    }

    @Override
    public void navigateToAreaGuide() {
//        Intent intent = new Intent(this, AreaGuideActivity.class);
//        startActivity(intent);

        Intent intent = new Intent(this, FindHomeActivity.class);
        startActivity(intent);
    }

    @Override
    public String getBannerText() {
        return "Hi Steve, Good Morning";
    }


    @Override
    public void onClick(View view) {
        if (view.getTag(R.string.dashboard_menu_tag) != null) {
            DashboardMenu menu = (DashboardMenu) view.getTag(R.string.dashboard_menu_tag);
            mPresenter.onClickDashboardMenu(menu);

        }
    }
}

