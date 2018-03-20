package com.capco.technologies.living.presentatoin.forgotpassword;

import android.text.TextUtils;


import com.capco.technologies.living.domain.repository.ApiClient;
import com.capco.technologies.living.domain.repository.LivingSharedPref;
import com.capco.technologies.living.utils.AppValidator;
import com.capco.technologies.living.utils.NetworkErrorUtils;
import com.capco.technologies.living.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;


/**
 * Created by Shreyas Bhondve on 1/16/2018.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter{

    final ForgotPasswordContract.View view;
    final LivingSharedPref livingSharedPref = LivingSharedPref.getInstance();

    public ForgotPasswordPresenter(ForgotPasswordContract.View view) {
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
    public void sendLink() {
        view.showProgress(true);
        String emailId = view.getEmailId();

//        String ip = view.getHostIP();
//        String port = view.getHostPort();

        boolean isError = false;


//        if (!AppValidator.isValidIP(ip)) {
//            view.showIPError("Please enter valid IP");
//        }
//
//
//        livingSharedPref.setHostIP(ip);
//        livingSharedPref.setHostPORT(port.equals("") ? 8080 : Integer.parseInt(port));
//
//
//        view.initClient();

        if (TextUtils.isEmpty(emailId)) {
            view.showEmailError("Email ID should not be empty");
            isError = true;
        } else if (!AppValidator.isValidEmail(emailId)) {
            view.showEmailError("Please enter valid username");
            isError = true;
        }

        if (isError) {
            view.showProgress(false);
            return;
        }

        attemSendLink();
    }

    private void attemSendLink() {

        if (!NetworkUtils.getInstance().isNetworkAvailable()) {
            view.showProgress(false);
            view.showScreenError("OOPs!!!. Network not available. Please check Internet Connection.");
            return;

        }

//        JsonObject obj = new Gson().fromJson("{\"userId\":\"" + view.getEmailId() + "\"}", JsonObject.class);
//
//        ApiClient.getInstance().getClient().forgotpwd(obj).enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                view.showProgress(false);
//                if (response.code() == 200) {
//                    view.navigateToLogin();
//                } else if (response.code() == 401) {
//                    view.showScreenError("Invalid Username.");
//                } else {
//                    view.showScreenError("Something went wrong. Please check network connection and try again.");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                view.showProgress(false);
//                view.showScreenError(NetworkErrorUtils.getError(call, t));
//            }
//
//        });

        view.navigateToLogin();

    }
}
