package com.capco.technologies.living.presentation.budgeting.step1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.MortgageRequirement;
import com.capco.technologies.living.presentation.base.BaseFragment;
import com.capco.technologies.living.presentation.budgeting.BudgetingActivity;
import com.capco.technologies.living.presentation.budgeting.step2.EstimateBudgetStep2Fragment;
import com.capco.technologies.living.presentation.ui.custom.SegmentedGroup;
import com.capco.technologies.living.utils.DecimalDigitsInputFilter;


public class EstimateBudgetStep1Fragment extends BaseFragment implements EstimateBudgetStep1Contract.View {

    MortgageRequirement mortgageRequirement = new MortgageRequirement();
    EstimateBudgetStep1Contract.Presenter presenter;

    public EstimateBudgetStep1Fragment() {
    }

    public static EstimateBudgetStep1Fragment newInstance() {
        EstimateBudgetStep1Fragment fragment = new EstimateBudgetStep1Fragment();
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


    SegmentedGroup applicantType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_estimate_budget_step_1, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        new EstimateBudgetStep1Presenter(this);
    }

    private void initViews() {
        applicantType = getView().findViewById(R.id.srg_applicant_type);
        applicantType.setOnCheckedChangeListener(applicationTypeListener);
        mortgageRequirement = ((BudgetingActivity) getActivity()).budget.getRequirement();
        mortgageRequirement = mortgageRequirement == null ? new MortgageRequirement() : mortgageRequirement;
    }


    RadioGroup.OnCheckedChangeListener applicationTypeListener = (radioGroup, checkedId) -> {
        mortgageRequirement.setJoinApplicant(checkedId == R.id.rb_join_applicant);
    };


    private MenuItem nextMenu;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_estimate_budget, menu);
        super.onCreateOptionsMenu(menu, inflater);
        nextMenu = menu.findItem(R.id.action_next);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_next) {


            MortgageRequirement mortgageRequirement = presenter.validateInputs();
            if (mortgageRequirement == null) {
                return false;
            } else {
                this.mortgageRequirement = mortgageRequirement;
                ((BudgetingActivity) getActivity()).budget.setRequirement(mortgageRequirement);
                navigateToNextStep();
            }


            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


    public void navigateToNextStep() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, EstimateBudgetStep2Fragment.newInstance(), null);
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void setPresenter(EstimateBudgetStep1Contract.Presenter presenter) {
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
    public void setDefaultApplicationTypeAsSingle(boolean single) {
        if (single) {
            ((RadioButton) getView().findViewById(R.id.rb_single_application)).setChecked(true);
            //showOtherNationalIdView(false);
        } else {
            ((RadioButton) getView().findViewById(R.id.rb_join_applicant)).setChecked(true);
            //showOtherNationalIdView(true);
        }
    }

//    private void showOtherNationalIdView(boolean isShow){
//        if(isShow){
//            ((TextView)getView().findViewById(R.id.txt_second_national_id)).setVisibility(View.VISIBLE);
//            ((TextView)getView().findViewById(R.id.et_second_national_id)).setVisibility(View.VISIBLE);
//        }
//        else{
//            ((TextView)getView().findViewById(R.id.txt_second_national_id)).setVisibility(View.GONE);
//            ((TextView)getView().findViewById(R.id.et_second_national_id)).setVisibility(View.GONE);
//        }
//    }

    @Override
    public double getEstimatedMortgageAmount() {
        String amount = ((EditText) getView().findViewById(R.id.et_estimated_morgage_amount)).getText().toString();
        return amount.isEmpty() ? 0 : Double.parseDouble(amount);
    }

    @Override
    public void setEstimatedMortgageAmount(double amount) {
        ((EditText) getView().findViewById(R.id.et_estimated_morgage_amount)).setText(String.format("%.2f", amount));

    }


    @Override
    public int getTerm() {
        String term = ((EditText) getView().findViewById(R.id.et_estimated_morgage_term)).getText().toString();
        return term.isEmpty() ? 0 : Integer.parseInt(term);
    }

    @Override
    public void setTerm(int term) {
        ((EditText) getView().findViewById(R.id.et_estimated_morgage_term)).setText(String.format("%d", term));

    }


    @Override
    public String getNationalId() {
        String term = ((EditText) getView().findViewById(R.id.et_national_id)).getText().toString();
        return term;
    }

    @Override
    public void setNationalId(String nationalId) {
        ((EditText) getView().findViewById(R.id.et_national_id)).setText(nationalId);

    }


    @Override
    public void setMortgageAmountError(String s) {
        ((EditText) getView().findViewById(R.id.et_estimated_morgage_amount)).setError(s);
    }

    @Override
    public void setTermError(String s) {
        ((EditText) getView().findViewById(R.id.et_estimated_morgage_term)).setError(s);
    }

    @Override
    public boolean isSingleApplicant() {
        return applicantType.getCheckedRadioButtonId() == R.id.rb_single_application;

    }

    @Override
    public void setMortgageValuesToViews() {
        setEstimatedMortgageAmount(mortgageRequirement.getEstimatedAmount());
        setTerm(mortgageRequirement.getTerm());
        setNationalId(mortgageRequirement.getNationalId() == null ? "" : mortgageRequirement.getNationalId());
        setDefaultApplicationTypeAsSingle(!mortgageRequirement.isJoinApplicant());

    }


}
