package com.capco.technologies.living.presentation.findhome;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class FindingPropertyPresenter implements FindingPropertyContract.Presenter{
    private final FindingPropertyContract.View view;

    public FindingPropertyPresenter(FindingPropertyContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        view.setPageBanner(view.getBannerText());
    }
}
