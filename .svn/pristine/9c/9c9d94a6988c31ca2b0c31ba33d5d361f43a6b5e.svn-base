package com.capco.technologies.living.presentation.budgeting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.Budget;
import com.capco.technologies.living.domain.model.ExpenseHeader;
import com.capco.technologies.living.domain.repository.LivingSharedPref;
import com.capco.technologies.living.fragments.BudgetingHomeFragment;
import com.capco.technologies.living.presentation.base.BaseFragment;
import com.capco.technologies.living.presentation.budgeting.step1.EstimateBudgetStep1Fragment;
import com.capco.technologies.living.presentation.budgeting.step2.EstimateBudgetStep2Fragment;
import com.capco.technologies.living.presentation.budgeting.step3.EstimateBudgetStep3Fragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Locale;

/**
 * Created by Shreyas on 1/8/2018.
 */

public class EstimateBudgetSummary extends BaseFragment implements View.OnClickListener {


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_estimate_budget, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {

            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//            getActivity().getSupportFragmentManager().
            // Replace the contents of the container with the new fragment
            LivingSharedPref.getInstance().setCalculateEmi(getCalculatedMortgageEmi());
            ft.replace(R.id.container, BudgetingHomeFragment.newInstance(getCalculatedMortgageEmi()));
            ft.addToBackStack(null);
            ft.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    JsonObject response;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            response = new Gson().fromJson(getArguments().getString("response", null), JsonObject.class);
        }

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_next).setTitle("Done");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_estimate__budget__summary, container, false);
        view.findViewById(R.id.whats_your_income_edit_text).setOnClickListener(this);
        view.findViewById(R.id.whats_your_plan_edit_text).setOnClickListener(this);
        view.findViewById(R.id.monthly_income_edit_text).setOnClickListener(this);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setValues();
    }

    private void setValues() {

        String value = response.get("emi").getAsString();
        setCalculatedMortgageEmi(Double.parseDouble(value));
        value = response.get("accountBalance").getAsString();
        setAccountBalance(Double.parseDouble(value));

        Budget budget = ((BudgetingActivity) getActivity()).budget;
        setMortgageAmount(budget.getRequirement().getEstimatedAmount());
        setMortgageTerm(budget.getRequirement().getTerm());
        setMortgateMonthlyIncome(budget.getIncome().getMonthlyIncome());
        setTotalIncome(budget.getIncome().getGrossSalary() + budget.getIncome().getOtherIncome());


        double monthlyExpenses = 0;
        for (ExpenseHeader header : budget.getExpenses()) {
            monthlyExpenses += header.getAmount();
        }

        setMonthlyExpenses(monthlyExpenses);
    }

    private void setMortgageAmount(double value) {

        ((TextView) getView().findViewById(R.id.estimated_mortgage_amount_text)).setText(String.format(Locale.getDefault(), "$%.2f", value));
    }

    private void setAccountBalance(double value) {


        ((TextView) getView().findViewById(R.id.available_account_text)).setText(String.format(Locale.getDefault(), "$%.2f", value));
    }


    private void setMortgageTerm(int term) {

        ((TextView) getView().findViewById(R.id.mortgage_term_text)).setText(String.format(Locale.getDefault(), "$%d", term));

    }


    private void setMortgateMonthlyIncome(double monthlyIncome) {

        ((TextView) getView().findViewById(R.id.mortgage_monthly_income)).setText(String.format(Locale.getDefault(), "$%.2f", monthlyIncome));
    }


    private void setTotalIncome(double totalIncome) {

        ((TextView) getView().findViewById(R.id.your_total_income_text)).setText(String.format(Locale.getDefault(), "$%.2f", totalIncome));
    }


    private void setMonthlyExpenses(double monthlyExpenses) {
        ((TextView) getView().findViewById(R.id.your_total_income_text_1)).setText(String.format(Locale.getDefault(), "$%.2f", monthlyExpenses));
    }


    //TODO This needs to be changed
    public static EstimateBudgetSummary newInstance(String body) {
        EstimateBudgetSummary fragment = new EstimateBudgetSummary();
        Bundle args = new Bundle();
        args.putString("response", body);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.whats_your_plan_edit_text:
                ft.replace(R.id.container, new EstimateBudgetStep1Fragment());
                break;

            case R.id.whats_your_income_edit_text:
                ft.replace(R.id.container, new EstimateBudgetStep2Fragment());
                break;

            case R.id.monthly_income_edit_text:
                ft.replace(R.id.container, new EstimateBudgetStep3Fragment());
                break;
        }

        ft.addToBackStack(null);
        ft.commit();
    }


    void setCalculatedMortgageEmi(double value) {
        String valueToSet = value < 1 ? "N/A" : String.format(Locale.getDefault(), "$%.2f", value);
        ((TextView) getView().findViewById(R.id.loan_mortgage_text)).setText(valueToSet);
    }

    String getCalculatedMortgageEmi() {

        return ((TextView) getView().findViewById(R.id.loan_mortgage_text)).getText().toString();
    }


}
