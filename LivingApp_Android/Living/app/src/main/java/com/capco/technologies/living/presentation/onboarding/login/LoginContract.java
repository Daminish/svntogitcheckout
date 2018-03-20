package com.capco.technologies.living.presentation.onboarding.login;

import android.content.Context;

import com.capco.technologies.living.presentation.base.BasePresenter;
import com.capco.technologies.living.presentation.base.BaseView;

/**
 * Created by sachin on 06/01/18.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {

        String getUsername();

        String getPassword();

        String getHostIP();

        String getHostPort();

        void showUsernameError(String error);

        void showPasswordError(String error);

        void showProgress(boolean isShow);

        void showScreenError(String error);

        void navigateToDashboard();
        void loggedInSuccessful();

        void showIPError(String error);

        void showPORTError(String error);

        void initClient();

        void setHostIP(String host);

        void setPort(String host);
    }


    interface Presenter extends BasePresenter {

        void forgotPassword(Context context);

        void login();

        void loadLocalityData();
    }


}
