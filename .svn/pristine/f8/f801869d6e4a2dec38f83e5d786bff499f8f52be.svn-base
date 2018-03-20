package com.capco.technologies.living.presentation.budgeting.step1;

import com.capco.technologies.living.domain.model.MortgageRequirement;

/**
 * Created by sachin on 10/01/18.
 */
public class EstimateBudgetStep1Presenter implements EstimateBudgetStep1Contract.Presenter {


    private EstimateBudgetStep1Contract.View view;


    public EstimateBudgetStep1Presenter(EstimateBudgetStep1Contract.View view) {
        this.view = view;

        this.view.setPresenter(this);

    }

    @Override
    public void start() {
        view.setMortgageValuesToViews();


    }

    @Override
    public MortgageRequirement validateInputs() {
        double mortgageAmount = view.getEstimatedMortgageAmount();
        int term = view.getTerm();
        String nationalId = view.getNationalId();

        boolean hasError = false;


        if (mortgageAmount < 100) {
            view.setMortgageAmountError("Please enter amount greater than $100");
            hasError = true;
        }

        if (term < 1) {
            view.setTermError("Please enter Term greater than 0");
            hasError = true;
        }


        if (hasError) {
            return null;
        }

        MortgageRequirement requirement = new MortgageRequirement();
        requirement.setEstimatedAmount(mortgageAmount);
        requirement.setTerm(term);
        requirement.setNationalId(nationalId);
        requirement.setJoinApplicant(!view.isSingleApplicant());


        return requirement;

    }
}
