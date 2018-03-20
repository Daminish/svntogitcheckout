package com.capco.technologies.living.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.capco.technologies.living.R;
import com.capco.technologies.living.domain.model.BudgetMenu;
import com.capco.technologies.living.presentation.base.BaseFragment;
import com.capco.technologies.living.presentation.budgeting.EstimateBudgetMenuAdapter;
import com.capco.technologies.living.presentation.budgeting.step1.EstimateBudgetStep1Fragment;
import com.capco.technologies.living.presentation.ui.listener.OnRecyclerViewItemClickListener;
import com.capco.technologies.living.storage.MockData;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class BudgetingHomeFragment extends BaseFragment implements OnRecyclerViewItemClickListener<BudgetMenu> {

    private RecyclerView recyclerView;
    EstimateBudgetMenuAdapter adapter = null;

    public BudgetingHomeFragment() {
    }


    public static BudgetingHomeFragment newInstance(String calculatedEmiAmount) {
        BudgetingHomeFragment fragment = new BudgetingHomeFragment();
        Bundle args = new Bundle();
        args.putString("calculatedEmiAmount", calculatedEmiAmount);
        fragment.setArguments(args);
        return fragment;
    }


    String calculatedEmi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            calculatedEmi = getArguments().getString("calculatedEmiAmount", null);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_budgeting_home, container, false);
        initializeData();

        return recyclerView;
    }


    private void initializeData() {


        List<BudgetMenu> budgetMenus = MockData.getInstance().getBudgetMenus();
        budgetMenus.get(0).value = calculatedEmi;

        adapter = new EstimateBudgetMenuAdapter(budgetMenus, this);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_budgeting_home, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            Toast.makeText(getActivity(), "Budget Info", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(View v, BudgetMenu clickedItem, int position) {

        switch (position) {
            case 0:
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.container, EstimateBudgetStep1Fragment.newInstance(), null);
                ft.addToBackStack(null);
                ft.commit();
                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }
}
