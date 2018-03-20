package com.capco.technologies.living.presentation.dashboard;

import com.capco.technologies.living.domain.model.DashboardMenu;
import com.capco.technologies.living.presentation.base.BasePresenter;
import com.capco.technologies.living.presentation.base.BaseView;

/**
 * Created by sachin on 06/01/18.
 */

public interface DashboardContract {


    interface View extends BaseView<Presenter> {



        android.view.View getGridMenu(DashboardMenu menu);

        void addDashboardMenu(android.view.View menuView);

        void navigateToBudgetingScreen();

        void navigateToAreaGuide();


    }


    interface Presenter extends BasePresenter {

        void onClickDashboardMenu(DashboardMenu menu);
    }
}
