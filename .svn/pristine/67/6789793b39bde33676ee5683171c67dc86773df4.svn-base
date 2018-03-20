package com.capco.technologies.living.presentation.onboarding.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.capco.technologies.living.LivingApp;
import com.capco.technologies.living.R;
import com.capco.technologies.living.presentation.base.BaseActivity;
import com.capco.technologies.living.presentation.dashboard.DashboardActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    private LoginContract.Presenter mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        new LoginPresenter(this);


        ((EditText) findViewById(R.id.et_password)).setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                hideKeyboard(textView);
                mPresenter.login();
                return true;
            }
            return false;
        });

    }


    @Override
    public String getUsername() {
        return ((EditText) findViewById(R.id.et_username)).getText().toString();
    }

    @Override
    public String getPassword() {
        return ((EditText) findViewById(R.id.et_password)).getText().toString();
    }

    @Override
    public String getHostIP() {
        return ((EditText) findViewById(R.id.et_host)).getText().toString();
    }

    @Override
    public String getHostPort() {
        return ((EditText) findViewById(R.id.et_port)).getText().toString();
    }


    @Override
    public void showUsernameError(String error) {
        ((EditText) findViewById(R.id.et_username)).setError(error);
    }

    @Override
    public void showPasswordError(String error) {
        ((EditText) findViewById(R.id.et_password)).setError(error);
    }


    public void onForgotPasswordClick(View view) {
        mPresenter.forgotPassword(this);
    }


    public void onLoginClick(View view) {
        mPresenter.login();
//        navigateToDashboard();
    }


    @Override
    public void showProgress(boolean isShow) {
        findViewById(R.id.progress).setVisibility(isShow ? View.VISIBLE : View.GONE);
        findViewById(R.id.login_btn).setVisibility(isShow ? View.GONE : View.VISIBLE);
        showOverlayDialog(isShow);
    }

    @Override
    public void showScreenError(String error) {
        ((TextView) findViewById(R.id.txt_screen_error)).setText(error);
        new Handler().postDelayed(() -> {
            ((TextView) findViewById(R.id.txt_screen_error)).setText("");
        }, 3000);
    }

    @Override
    public void navigateToDashboard() {
        showOverlayDialog(false);
        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loggedInSuccessful() {
        mPresenter.loadLocalityData();
    }

    @Override
    public void showIPError(String error) {
        ((EditText) findViewById(R.id.et_host)).setError(error);
    }

    @Override
    public void showPORTError(String error) {
        ((EditText) findViewById(R.id.et_port)).setError(error);
    }

    @Override
    public void initClient() {
        ((LivingApp) getApplication()).initNetworkClient();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mPresenter.start();
    }

    @Override
    public void setScreenTitle(String title) {
        setTitle(title);
    }

    @Override
    public void setPageBanner(String bannerString) {
        super.setBannerText(null);
    }

    @Override
    public String getBannerText() {
        return null;
    }

    @Override
    public void setHostIP(String host) {
        ((EditText) findViewById(R.id.et_host)).setText(host);
    }

    @Override
    public void setPort(String host) {
        ((EditText) findViewById(R.id.et_port)).setText(host);
    }


}

