package com.capco.technologies.living.presentation.findhome;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class FindHomePresenter implements FindHomeContract.Presenter{

    private final FindHomeContract.View view;

    public FindHomePresenter(FindHomeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.setPageBanner(view.getBannerText());
    }
}
