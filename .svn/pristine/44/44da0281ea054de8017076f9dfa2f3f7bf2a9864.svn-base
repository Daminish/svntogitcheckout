package com.capco.technologies.living.presentatoin.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.capco.technologies.living.LivingApp;
import com.capco.technologies.living.R;
import com.capco.technologies.living.presentation.base.BaseActivity;
import com.capco.technologies.living.presentation.dashboard.DashboardActivity;
import com.capco.technologies.living.presentation.onboarding.login.LoginActivity;

/**
 * Created by Shreyas Bhondve on 1/16/2018.
 */

public class ForgotPasswordActivity extends BaseActivity implements ForgotPasswordContract.View{

    private ForgotPasswordContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        new ForgotPasswordPresenter(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void setPresenter(ForgotPasswordContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mPresenter.start();
    }

    public void onSendLinkClick(View view) {
        mPresenter.sendLink();
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
    public String getEmailId() {
        return ((EditText) findViewById(R.id.et_username)).getText().toString();
    }

    @Override
    public void showEmailError(String error) {
        ((EditText) findViewById(R.id.et_username)).setError(error);
    }

    @Override
    public void showProgress(boolean isShow) {
        findViewById(R.id.progress).setVisibility(isShow ? View.VISIBLE : View.GONE);
        showOverlayDialog(isShow);
    }

    @Override
    public void showScreenError(String error) {

    }

    @Override
    public void initClient() {
        ((LivingApp) getApplication()).initNetworkClient();
    }

    @Override
    public String getHostIP() {
        return null;
    }

    @Override
    public String getHostPort() {
        return null;
    }

    @Override
    public void setHostIP(String host) {

    }

    @Override
    public void setPort(String host) {

    }

    @Override
    public void navigateToLogin() {
        showOverlayDialog(false);
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showIPError(String error) {

    }
}
