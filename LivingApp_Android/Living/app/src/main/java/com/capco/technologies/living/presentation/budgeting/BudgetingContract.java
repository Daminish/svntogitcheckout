package com.capco.technologies.living.presentation.budgeting;

import com.capco.technologies.living.presentation.base.BasePresenter;
import com.capco.technologies.living.presentation.base.BaseView;

/**
 * Created by sachin on 07/01/18.
 */

public interface BudgetingContract {


    interface View extends BaseView<Presenter> {

        void showMortgageAmountRequirementFragment();

        void showBudgetingMenuFragment();

        
    }

    interface Presenter extends BasePresenter {

    }
}
