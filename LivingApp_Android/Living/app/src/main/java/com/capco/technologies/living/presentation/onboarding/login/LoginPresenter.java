package com.capco.technologies.living.presentation.onboarding.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.capco.technologies.living.domain.repository.ApiClient;
import com.capco.technologies.living.domain.repository.LivingSharedPref;
import com.capco.technologies.living.presentation.onboarding.login.LoginContract.Presenter;
import com.capco.technologies.living.presentatoin.forgotpassword.ForgotPasswordActivity;
import com.capco.technologies.living.utils.AppValidator;
import com.capco.technologies.living.utils.NetworkErrorUtils;
import com.capco.technologies.living.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sachin on 06/01/18.
 */

public class LoginPresenter implements Presenter {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */


    final LoginContract.View view;

    final LivingSharedPref livingSharedPref = LivingSharedPref.getInstance();

    public LoginPresenter(LoginContract.View view) {
        this.view = view;

        this.view.setPresenter(this);
    }

    @Override
    public void start() {
        String host = livingSharedPref.getHostIP();
        int port = livingSharedPref.getHostPORT();
        if (host != null) {
            view.setHostIP(host);
        }
        if (port > 0) {
            view.setPort(String.valueOf(port));
        }
    }

    @Override
    public void forgotPassword(Context context) {
        Intent intent = new Intent(context, ForgotPasswordActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void login() {

        view.showProgress(true);
        String username = view.getUsername();
        String password = view.getPassword();

        String ip = view.getHostIP();
        String port = view.getHostPort();


        boolean isError = false;


        if (!AppValidator.isValidIP(ip)) {
            view.showIPError("Please enter valid IP");
        }


        livingSharedPref.setHostIP(ip);
        livingSharedPref.setHostPORT(port.equals("") ? 8080 : Integer.parseInt(port));


        view.initClient();


        if (TextUtils.isEmpty(username)) {
            view.showUsernameError("Username should not be empty");
            isError = true;
        } else if (!AppValidator.isValidEmail(username)) {
            view.showUsernameError("Please enter valid username");
            isError = true;
        }


        if (TextUtils.isEmpty(password)) {
            view.showPasswordError("Password should not be empty");
            isError = true;
        } else if (password.length() < 2) {
            view.showPasswordError("Password should not be less than 2 character");
            isError = true;
        }

        if (isError) {
            view.showProgress(false);
            return;
        }

//        MockData mockData = MockData.getInstance();
//        if (!mockData.getDummyCredentials().containsKey(username) || !mockData.getDummyCredentials().get(username).equals(password)) {
//            view.showProgress(false);
//            view.showScreenError("Invalid email or Password");
//            return;
//        }


        attemptLogin();


        // Call webservice to validate credentials
        // right now no webservice is available so just validate with dummy data


    }

    @Override
    public void loadLocalityData() {

    }

    private void attemptLogin() {


        if (!NetworkUtils.getInstance().isNetworkAvailable()) {
            view.showProgress(false);
            view.showScreenError("OOPs!!!. Network not available. Please check Internet Connection.");
            return;

        }


        JsonObject obj = new Gson().fromJson("{\"email\":\"" + view.getUsername() + "\", \"password\":\"" + view.getPassword() + "\"}", JsonObject.class);

        ApiClient.getInstance().getClient().login(obj).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                view.showProgress(false);
                if (response.code() == 200) {
                    view.navigateToDashboard();
                } else if (response.code() == 401) {
                    view.showScreenError("Invalid Username or Password.");
                } else {
                    view.showScreenError("Something went wrong. Please check network connection and try again.");
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                view.showProgress(false);
                view.showScreenError(NetworkErrorUtils.getError(call, t));
            }
        });


    }
}
