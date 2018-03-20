package com.capco.technologies.living.presentation.budgeting;

/**
 * Created by sachin on 07/01/18.
 */

public class BudgetingPresenter implements BudgetingContract.Presenter {

    private final BudgetingContract.View view;

    public BudgetingPresenter(BudgetingContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void start() {
        view.setPageBanner(view.getBannerText());
        view.showBudgetingMenuFragment();
    }
}
