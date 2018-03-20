package com.capco.technologies.living.presentation.findhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.capco.technologies.living.R;
import com.capco.technologies.living.presentation.base.BaseActivity;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarFinalValueListener;
import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;

/**
 * Created by Shreyas Bhondve on 1/9/2018.
 */

public class FindingPropertyActivity extends BaseActivity implements FindingPropertyContract.View {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        new FindingPropertyPresenter(this);
        setContentView(R.layout.activity_find_property);

        // get seekbar from view
        final CrystalRangeSeekbar rangeSeekbar = (CrystalRangeSeekbar) findViewById(R.id.rangeSeekbar1);

// get min and max text view
        final TextView tvMin = (TextView) findViewById(R.id.minVal);
        final TextView tvMax = (TextView) findViewById(R.id.maxVal);

// set listener
        rangeSeekbar.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                tvMin.setText(String.valueOf(minValue));
                tvMax.setText(String.valueOf(maxValue));
            }
        });

// set final value listener
        rangeSeekbar.setOnRangeSeekbarFinalValueListener(new OnRangeSeekbarFinalValueListener() {
            @Override
            public void finalValue(Number minValue, Number maxValue) {
                Log.d("CRS=>", String.valueOf(minValue) + " : " + String.valueOf(maxValue));
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void setPresenter(FindingPropertyContract.Presenter presenter) {

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
}
