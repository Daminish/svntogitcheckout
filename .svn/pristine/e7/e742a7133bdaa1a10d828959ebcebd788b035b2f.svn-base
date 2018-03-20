package com.capco.technologies.living.presentation.budgeting.step3;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.Budget;
import com.capco.technologies.living.domain.model.Expense;
import com.capco.technologies.living.domain.model.ExpenseHeader;
import com.capco.technologies.living.domain.model.ExpenseItem;
import com.capco.technologies.living.domain.model.IExpenseHeader;
import com.capco.technologies.living.domain.model.IExpenseItem;
import com.capco.technologies.living.domain.repository.ApiClient;
import com.capco.technologies.living.presentation.base.BaseFragment;
import com.capco.technologies.living.presentation.budgeting.BudgetingActivity;
import com.capco.technologies.living.presentation.budgeting.EstimateBudgetSummary;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;
import com.capco.technologies.living.utils.NetworkUtils;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EstimateBudgetStep3Fragment extends BaseFragment implements EstimateBudgetStep3Contract.View, OnRecyclerViewItemClickListener<Expense> {


    RecyclerView recyclerView;

    private HashMap<IExpenseHeader, List<IExpenseItem>> payments = new HashMap<>();
    private BudgetExpenseAdapter adapter;
    private EstimateBudgetStep3Contract.Presenter presenter;
    List<Expense> expenseList = new ArrayList<>();

    @Override
    public void setPresenter(EstimateBudgetStep3Contract.Presenter presenter) {

        this.presenter = presenter;
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

    private enum PAYMENT_ACTION {
        ADD, EDIT
    }


    public EstimateBudgetStep3Fragment() {

    }

    private void addExpense(IExpenseHeader header, int headerIndex, String expenseName, double expenseAmount) {
        IExpenseItem expenseItem = new ExpenseItem();
        expenseItem.setName(expenseName);
        expenseItem.setAmount(expenseAmount);
        expenseItem.setHeader(header);

        List<IExpenseItem> iExpenseItemList = payments.get(header);
        iExpenseItemList.add(expenseItem);
        expenseList.add(headerIndex + iExpenseItemList.size(), expenseItem);
        adapter.setExpenses(expenseList);
    }

    private void deleteExpense(IExpenseHeader header, int headerIndex, String expenseName, double expenseAmount) {

        IExpenseItem expenseItem = new ExpenseItem();
        expenseItem.setName(expenseName);
        expenseItem.setAmount(expenseAmount);

        List<IExpenseItem> iExpenseItemList = payments.get(header);
        for (int i = 0; i < iExpenseItemList.size(); i++) {
            if (iExpenseItemList.get(i).getHeader() == header) {
                iExpenseItemList.remove(i);
            }
        }

        expenseList.remove(headerIndex);
        adapter.setExpenses(expenseList);
    }

    private void editExpense(IExpenseHeader header, int headerIndex, String expenseName, double expenseAmount) {

        IExpenseItem expenseItem = new ExpenseItem();
        expenseItem.setName(expenseName);
        expenseItem.setAmount(expenseAmount);
        expenseItem.setHeader(header);

        List<IExpenseItem> iExpenseItemList = payments.get(header);
        int pos = 0;
        for (int i = 0; i < iExpenseItemList.size(); i++) {
            if (iExpenseItemList.get(i).getHeader() == header) {
                iExpenseItemList.remove(i);
                pos = i;
            }
        }
        iExpenseItemList.add(pos, expenseItem);
        expenseList.remove(headerIndex);
        expenseList.add(headerIndex, expenseItem);
        adapter.setExpenses(expenseList);
    }


    public void addOrEditPayment(IExpenseHeader header, IExpenseItem item, int headerIndex, PAYMENT_ACTION payment_action, int position) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_payment_layout, null);
        dialogBuilder.setView(dialogView);

        final EditText expenseName = dialogView.findViewById(R.id.expense_name);
        final EditText expenseAmount = dialogView.findViewById(R.id.expense_amount);

        dialogBuilder.setTitle(header.getName());

        if (payment_action == PAYMENT_ACTION.ADD) {
            dialogBuilder.setPositiveButton("ADD", (dialog, whichButton) -> {
                String expenseNameStr = expenseName.getText().toString();
                String expenseAmountStr = expenseAmount.getText().toString();
                addExpense(header, headerIndex, expenseNameStr.equals("")?"":expenseNameStr, Double.parseDouble(expenseAmountStr.equals("")?"0":expenseAmountStr));
            });

            dialogBuilder.setNegativeButton("CANCEL", (dialog, whichButton) -> {
                expenseName.setText("");
                expenseAmount.setText("");
            });

        } else if (payment_action == PAYMENT_ACTION.EDIT) {
            expenseAmount.setText(String.valueOf(item.getAmount()));
            expenseName.setText(item.getName());

            dialogBuilder.setPositiveButton("EDIT", (dialog, whichButton) -> {
                String expenseNameStr = expenseName.getText().toString();
                String expenseAmountStr = expenseAmount.getText().toString();
                editExpense(header, headerIndex, expenseNameStr.equals("")?"":expenseNameStr, Double.parseDouble(expenseAmountStr.equals("")?"0":expenseAmountStr));
            });

            dialogBuilder.setNegativeButton("DELETE", (dialog, whichButton) -> {
                String expenseNameStr = expenseName.getText().toString();
                String expenseAmountStr = expenseAmount.getText().toString();
                deleteExpense(header, headerIndex, expenseNameStr.equals("")?"":expenseNameStr, Double.parseDouble(expenseAmountStr.equals("")?"0":expenseAmountStr));
            });
        }


        AlertDialog alert = dialogBuilder.create();

        alert.show();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_estimate_budget, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_next) {


            calculateMonthlyExpensesLocally();


            calculateMortgageAmountRemotely();


        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateMortgageAmountRemotely() {


        Budget budget = ((BudgetingActivity) getActivity()).budget;

        JsonObject object = new JsonObject();
        object.addProperty("total_income", budget.getIncome().getGrossSalary() + budget.getIncome().getOtherIncome());
        object.addProperty("mortgage_amount", budget.getRequirement().getEstimatedAmount());
        object.addProperty("mortgage_term", budget.getRequirement().getTerm());

        JsonObject expenses = new JsonObject();
        for (ExpenseHeader expense : budget.getExpenses()) {
            expenses.addProperty(expense.getAlternativeName(), expense.getAmount());
        }

        object.add("monthlyExpenses", expenses);


        Log.d("Living", object.toString());
        System.out.println("Living Budget data : " + object.toString());


        if (NetworkUtils.getInstance().isNetworkAvailable()) {


            showProgressDialog(true);


            ApiClient.getInstance().getClient().budgeting(object).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    showProgressDialog(false);
                    if (response.code() == 200) {
                        navigateToNextStep(response.body());
                    }

                    System.out.println("Response : " + response.body().toString());
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    showProgressDialog(false);
                    t.printStackTrace();
                }
            });
        }
    }


    private void showProgressDialog(boolean isShow) {
        getView().findViewById(R.id.progress).setVisibility(isShow ? View.VISIBLE : View.GONE);
        ((BudgetingActivity) getActivity()).showOverlayDialog(isShow);
    }

    private void calculateMonthlyExpensesLocally() {
        Iterator<IExpenseHeader> iterator = payments.keySet().iterator();

        List<ExpenseHeader> iExpenseItemList = new ArrayList<>();
        while (iterator.hasNext()) {
            IExpenseHeader header = iterator.next();
            List<IExpenseItem> expenseItemsList = payments.get(header);

            double amount = 0;
            for (IExpenseItem expenseItem : expenseItemsList) {
                amount += expenseItem.getAmount();
            }


            ExpenseHeader item = new ExpenseHeader();
            item.setAlternativeName(header.getAlternativeName());
            item.setAmount(amount);
            iExpenseItemList.add(item);
        }

        ((BudgetingActivity) getActivity()).budget.setExpenses(iExpenseItemList);

    }

    private void navigateToNextStep(JsonObject body) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, EstimateBudgetSummary.newInstance(body.toString()));
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onItemClick(View v, Expense clickedItem, int position) {
        if (clickedItem instanceof IExpenseHeader) {
            IExpenseHeader header = (IExpenseHeader) clickedItem;
            addOrEditPayment(header, null, (int) v.getTag(R.string.item_click_tag), PAYMENT_ACTION.ADD, position);

        } else {
            IExpenseItem item = (IExpenseItem) clickedItem;
            addOrEditPayment(item.getHeader(), item, (int) v.getTag(R.string.item_click_tag), PAYMENT_ACTION.EDIT, position);
        }
    }


    public static EstimateBudgetStep3Fragment newInstance() {
        EstimateBudgetStep3Fragment fragment = new EstimateBudgetStep3Fragment();
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
        View view = inflater.inflate(R.layout.fragment_estimate_budget_step_3, container, false);
        recyclerView = view.findViewById(R.id.rv_budget_expenses);
        getExpensesList();
        adapter = new BudgetExpenseAdapter(expenseList);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void getExpensesList() {

        List<String> expensesHeaders = Arrays.asList(getResources().getStringArray(R.array.expenses_header));
        List<String> expensesHeadersAlternative = Arrays.asList(getResources().getStringArray(R.array.expenses_header_alternative));
        for (int i = 0; i < expensesHeaders.size(); i++) {
            IExpenseHeader header = new ExpenseHeader();
            header.setName(expensesHeaders.get(i));
            header.setAlternativeName(expensesHeadersAlternative.get(i));
            payments.put(header, new ArrayList<>());
        }

        Iterator<IExpenseHeader> expenses = payments.keySet().iterator();
        while (expenses.hasNext()) {
            IExpenseHeader header = expenses.next();
            List<IExpenseItem> expenseItems = payments.get(header);
            expenseList.add(header);
            for (IExpenseItem item : expenseItems) {
                expenseItems.add(item);
            }
        }
    }
}