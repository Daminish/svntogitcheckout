package com.capco.technologies.living.presentation.budgeting.step2;

import com.capco.technologies.living.domain.model.Income;

/**
 * Created by sachin on 10/01/18.
 */

public class EstimateBudgetStep2Presenter implements EstimateBudgetStep2Contract.Presenter {
    private EstimateBudgetStep2Contract.View view;

    public EstimateBudgetStep2Presenter(EstimateBudgetStep2Contract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public Income validateInputs() {
        double salary = view.getAnnualSalary();
        double otherIncome = view.getOtherYearlyIncome();
        double monthlyIncome = view.getMonthlyIncome();

        boolean hasError = false;


        if (monthlyIncome < 100) {
            view.setAnnualSalaryError("Mothly income should be greater than $100");
            hasError = true;
        }


        if (salary < 100) {
            view.setOtherYearlyIncomeError("Mothly income should be greater than $100");
            hasError = true;
        }


        if (hasError) {
            return null;
        }

        Income income = new Income();
        income.setGrossSalary(salary);
        income.setMonthlyIncome(view.getMonthlyIncome());

        income.setOtherIncome(otherIncome);

        return income;
    }

    @Override
    public void calculateMonthlyIncome() {
        double salary = view.getAnnualSalary();
        double otherIncome = view.getOtherYearlyIncome();
        view.setMonthlyIncome((salary + otherIncome) / 12);

    }
}
