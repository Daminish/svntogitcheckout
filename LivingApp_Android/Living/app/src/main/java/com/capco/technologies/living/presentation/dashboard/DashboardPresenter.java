package com.capco.technologies.living.presentation.dashboard;

import android.view.View;

import com.capco.technologies.living.domain.model.DashboardMenu;
import com.capco.technologies.living.storage.MockData;

import java.util.List;

/**
 * Created by sachin on 06/01/18.
 */

public class DashboardPresenter implements DashboardContract.Presenter {

    private final DashboardContract.View view;

    public DashboardPresenter(DashboardContract.View view) {
        this.view = view;

        view.setPresenter(this);
    }


    @Override
    public void start() {
        view.setPageBanner(view.getBannerText());
        view.setScreenTitle("Home Move");
        List<DashboardMenu> menus = MockData.getInstance().getDashboardMenus();

        for (DashboardMenu menu : menus) {
            View menuView = view.getGridMenu(menu);
            view.addDashboardMenu(menuView);
        }
    }


    @Override
    public void onClickDashboardMenu(DashboardMenu menu) {
        switch (menu.id){
            case 1:
                break;
            case 2 :
                view.navigateToBudgetingScreen();
                break;
            case 3 :
                view.navigateToAreaGuide();
                break;

        }

    }
}
