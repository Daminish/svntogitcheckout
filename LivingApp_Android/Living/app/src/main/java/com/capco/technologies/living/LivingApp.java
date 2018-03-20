package com.capco.technologies.living;

import android.app.Application;

import com.capco.technologies.living.core.ScreenLifeCycle;
import com.capco.technologies.living.domain.repository.ApiClient;
import com.capco.technologies.living.domain.repository.LivingSharedPref;
import com.capco.technologies.living.storage.MockData;
import com.capco.technologies.living.utils.NetworkUtils;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


/**
 * Created by test on 19/12/17.
 */

public class LivingApp extends Application {

    private final ScreenLifeCycle screenLifeCycle = new ScreenLifeCycle();

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        registerActivityLifecycleCallbacks(screenLifeCycle);
        MockData.init(this);
        LivingSharedPref.init(this);
        NetworkUtils.init(this);
        //initNetworkClient();
    }


    //TODO this implementation need to change once our dev / prod server stable.
    //Right now we are taking host details from user for Testing purpose
    public void initNetworkClient() {
        ApiClient.init(this);
    }

}
