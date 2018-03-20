package com.capco.technologies.living.presentatoin.forgotpassword;

import com.capco.technologies.living.presentation.base.BasePresenter;
import com.capco.technologies.living.presentation.base.BaseView;

/**
 * Created by Shreyas Bhondve on 1/16/2018.
 */

public interface ForgotPasswordContract {

    interface View extends BaseView<Presenter> {
        String getEmailId();

        void showEmailError(String error);

        void showProgress(boolean isShow);

        void showScreenError(String error);

        void initClient();

        String getHostIP();

        String getHostPort();

        void setHostIP(String host);

        void setPort(String host);

        void navigateToLogin();

        void showIPError(String error);

    }

    interface Presenter extends BasePresenter {
        void sendLink();
    }
}
