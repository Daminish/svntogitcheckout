package com.capco.technologies.living.presentation.findhome;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.capco.technologies.living.domain.model.location.City;
import com.capco.technologies.living.domain.model.location.ICity;
import com.capco.technologies.living.domain.model.location.ILocality;
import com.capco.technologies.living.presentation.base.BasePresenter;
import com.capco.technologies.living.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Shreyas Bhondve on 1/8/2018.
 */

public interface AreaGuideContract {

    interface View extends BaseView<Presenter> {

        void showAreaFragment();

        void showCityField(boolean isShow);

        void showLocalityField(boolean isShow);

        void showDefaultLocations(boolean isShow);

        void showFilteredLocations(boolean isShow);


        EditText getCityFieldView();

        EditText getLocalityFieldView();

        RecyclerView getCityLocalityRecycler();

        void updateLocationDetails();

        void showAreaDetails();

        void hideAreaDetails();

        void showCityCurrentLocationIcon(boolean isShow);

        void showCityListData(List<ICity> filteredCity);

        void showLocalityListData(List<ILocality> filteredCity);
    }

    interface Presenter extends BasePresenter {

        void startCitySearch(String city);

        void startLocalitySearch(String locality);


    }
}
