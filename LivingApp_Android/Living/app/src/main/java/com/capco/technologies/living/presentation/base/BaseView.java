package com.capco.technologies.living.presentation.base;

/**
 * Created by sachin on 06/01/18.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void setScreenTitle(String title);

    void setPageBanner(String bannerString);

    String getBannerText();
}
