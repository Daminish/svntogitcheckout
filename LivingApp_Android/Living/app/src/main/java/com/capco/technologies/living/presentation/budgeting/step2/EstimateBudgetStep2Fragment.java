package com.capco.technologies.living.presentation.budgeting.step2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.Income;
import com.capco.technologies.living.presentation.base.BaseFragment;
import com.capco.technologies.living.presentation.budgeting.BudgetingActivity;
import com.capco.technologies.living.presentation.budgeting.step3.EstimateBudgetStep3Fragment;
import com.capco.technologies.living.utils.DecimalDigitsInputFilter;

import java.util.Locale;


public class EstimateBudgetStep2Fragment extends BaseFragment implements EstimateBudgetStep2Contract.View {


    private EstimateBudgetStep2Contract.Presenter presenter;
    private Income income;

    public EstimateBudgetStep2Fragment() {
        // Required empty public constructor
    }


    public static EstimateBudgetStep2Fragment newInstance() {
        EstimateBudgetStep2Fragment fragment = new EstimateBudgetStep2Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estimate_budget_step_2, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        new EstimateBudgetStep2Presenter(this);
    }

    private void initViews() {
        income = ((BudgetingActivity) getActivity()).budget.getIncome();
        income = income == null ? new Income() : income;
        ((EditText) getView().findViewById(R.id.et_yearly_income)).addTextChangedListener(yearlyIncomeWatcher);
        ((EditText) getView().findViewById(R.id.et_annual_salary)).addTextChangedListener(annualSalaryWatcher);
    }


    private TextWatcher yearlyIncomeWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            presenter.calculateMonthlyIncome();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private TextWatcher annualSalaryWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            presenter.calculateMonthlyIncome();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_estimate_budget, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_next) {


            Income income = presenter.validateInputs();
            if (income == null) {
                return false;
            } else {
                this.income = income;
                ((BudgetingActivity) getActivity()).budget.setIncome(income);
                navigateToNextStep();
            }


            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }


    }

    private void navigateToNextStep() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, EstimateBudgetStep3Fragment.newInstance());
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void setPresenter(EstimateBudgetStep2Contract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.start();
    }

    @Override
    public void setScreenTitle(String title) {

    }

    @Override
    public void setPageBanner(String bannerString) {

    }

    @Override
    public String getBannerText() {
        return null;
    }

    @Override
    public double getAnnualSalary() {

        try{
            String annualSalary = ((EditText) getView().findViewById(R.id.et_annual_salary)).getText().toString();

            return annualSalary.isEmpty() ? 0.0 : Double.parseDouble(annualSalary);
        }
        catch (NumberFormatException exception){
            return 0;
        }

    }


    @Override
    public void setAnnualSalary(double annualSalary) {
        ((EditText) getView().findViewById(R.id.et_annual_salary)).setText(annualSalary == 0 ? "" : String.format(Locale.getDefault(), "%.2f", annualSalary));
//        ((EditText) getView().findViewById(R.id.et_annual_salary)).setText(String.format("%.2f", annualSalary));
    }


    @Override
    public double getOtherYearlyIncome() {

        try {

            String otherIncome = ((EditText) getView().findViewById(R.id.et_yearly_income)).getText().toString();

            return otherIncome.isEmpty() ? 0.0 : Double.parseDouble(otherIncome);
        }
        catch (NumberFormatException exception){
            return 0;
        }
    }


    @Override
    public void setOtherYearlyIncome(double otherYearlyIncome) {
        ((EditText) getView().findViewById(R.id.et_yearly_income)).setText(otherYearlyIncome == 0 ? "" : String.format(Locale.getDefault(), "%.2f", otherYearlyIncome));
    }

    @Override
    public void setAnnualSalaryError(String error) {
        ((EditText) getView().findViewById(R.id.et_annual_salary)).setError(error);
    }

    @Override
    public double getMonthlyIncome() {
        String monthlyIncome = ((EditText) getView().findViewById(R.id.et_monthly_income)).getText().toString();

        return monthlyIncome.isEmpty() ? 0.0 : Double.parseDouble(monthlyIncome);

    }

    @Override
    public void setMonthlyIncome(double monthlyIncome) {
        ((EditText) getView().findViewById(R.id.et_monthly_income)).setText(monthlyIncome == 0 ? "" : String.format(Locale.getDefault(), "%.2f", monthlyIncome));


    }

    @Override
    public void setOtherYearlyIncomeError(String error) {
        ((EditText) getView().findViewById(R.id.et_yearly_income)).setError(error);
    }

}
