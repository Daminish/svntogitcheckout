package com.capco.technologies.living.presentation.budgeting.step2;

import com.capco.technologies.living.domain.model.Income;
import com.capco.technologies.living.presentation.base.BasePresenter;
import com.capco.technologies.living.presentation.base.BaseView;

/**
 * Created by sachin on 10/01/18.
 */

public interface EstimateBudgetStep2Contract {


    interface View extends BaseView<Presenter> {

        double getAnnualSalary();

        void setAnnualSalary(double annualSalary);

        double getOtherYearlyIncome();

        void setOtherYearlyIncome(double otherYearlyIncome);

        void setAnnualSalaryError(String error);

        double getMonthlyIncome();

        void setMonthlyIncome(double monthlyIncome);

        void setOtherYearlyIncomeError(String error);
    }


    interface Presenter extends BasePresenter {

        Income validateInputs();

        void calculateMonthlyIncome();
    }
}
