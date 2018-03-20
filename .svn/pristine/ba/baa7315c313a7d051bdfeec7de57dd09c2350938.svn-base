package com.capco.technologies.living.presentation.dashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.capco.technologies.living.domain.model.Offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sachin on 06/01/18.
 */

public class DashboardOfferAdapter extends FragmentPagerAdapter {


    private List<Offer> offers = new ArrayList<>();

    public DashboardOfferAdapter(FragmentManager fm, List<Offer> offers) {
        super(fm);
        this.offers = offers == null ? new ArrayList<>() : offers;
    }

    @Override
    public Fragment getItem(int position) {
        return OfferPageFragment.newInstance(offers.get(position % offers.size()));
    }

    @Override
    public int getCount() {
        return offers.size();
    }
}
