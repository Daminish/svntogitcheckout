package com.capco.technologies.living.presentation.budgeting.step1;

import com.capco.technologies.living.domain.model.MortgageRequirement;
import com.capco.technologies.living.presentation.base.BasePresenter;
import com.capco.technologies.living.presentation.base.BaseView;

/**
 * Created by sachin on 10/01/18.
 */

public interface EstimateBudgetStep1Contract {


    interface View extends BaseView<Presenter> {

        void setDefaultApplicationTypeAsSingle(boolean single);

        double getEstimatedMortgageAmount();

        int getTerm();

        void setEstimatedMortgageAmount(double amount);

        void setTerm(int term);

        String getNationalId();

        void setNationalId(String nationalId);


        void setMortgageAmountError(String s);

        void setTermError(String s);

        boolean isSingleApplicant();

        void setMortgageValuesToViews();
    }


    interface Presenter extends BasePresenter {

        MortgageRequirement validateInputs();
    }


}
