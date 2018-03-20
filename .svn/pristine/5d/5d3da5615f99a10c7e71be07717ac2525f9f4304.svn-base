package com.capco.technologies.living.presentation.findhome;

import com.capco.technologies.living.domain.model.location.City;
import com.capco.technologies.living.domain.model.location.ICity;
import com.capco.technologies.living.domain.model.location.ILocality;
import com.capco.technologies.living.presentation.budgeting.BudgetingContract;
import com.capco.technologies.living.storage.MockData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shreyas Bhondve on 1/8/2018.
 */

public class AreaGuidePresenter implements AreaGuideContract.Presenter {

    private final AreaGuideContract.View view;

    public AreaGuidePresenter(AreaGuideContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {


        view.setPageBanner(view.getBannerText());
        view.showAreaDetails();
        view.hideAreaDetails();
        view.showAreaFragment();

    }


    @Override
    public void startCitySearch(String city) {

        boolean isEmptySearch = city == null || city.trim().isEmpty();

        view.showDefaultLocations(isEmptySearch);
        view.showFilteredLocations(!isEmptySearch);
        view.showLocalityField(!isEmptySearch);
        view.showCityCurrentLocationIcon(isEmptySearch);

        List<ICity> cities = MockData.getInstance().getCity();

        if (!isEmptySearch) {
            List<ICity> filteredCity = new ArrayList<>();
            for (ICity cityObj : cities) {
                if (cityObj.getName().toLowerCase().startsWith(city.toLowerCase())) {
                    filteredCity.add(cityObj);
                }
            }
            view.showCityListData(filteredCity);
        } else {
            //view.showCityListData(cities);
        }

        //to clear locality field when city field becomes empty
        if(isEmptySearch){
           view.getLocalityFieldView().getText().clear();
        }

    }

    @Override
    public void startLocalitySearch(String locality) {
        boolean isEmptySearch = locality == null || locality.trim().isEmpty();

        //view.showDefaultLocations(isEmptySearch);
        //view.showLocalityField(!isEmptySearch);
        //view.showCityCurrentLocationIcon(isEmptySearch);

        view.showFilteredLocations(!isEmptySearch);

        List<ILocality> localities = MockData.getInstance().getLocalities();

        if (!isEmptySearch) {
            List<ILocality> filteredLocality = new ArrayList<>();
            for (ILocality localityObj : localities) {
                if (localityObj.getName().toLowerCase().startsWith(locality.toLowerCase())) {
                    filteredLocality.add(localityObj);
                }
            }
            view.showLocalityListData(filteredLocality);
        } else {
            //view.showLocalityListData(localities);
        }

        if (!isEmptySearch) {
            //Start filtering city from data
        }
    }
}
