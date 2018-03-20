package com.capco.technologies.living.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by sachin on 10/01/18.
 */

public class NetworkUtils {


    private static NetworkUtils mInstance;
    private Context context;

    private NetworkUtils(Context context) {

        this.context = context;
    }


    public static NetworkUtils init(Context context) {
        if (mInstance == null) {
            mInstance = new NetworkUtils(context);
        }
        return mInstance;
    }


    public static NetworkUtils getInstance() {
        if (mInstance == null) {
            throw new NullPointerException("Please init NetworkUtil before accessing instance using NetworkUtils.init(Context)");
        }
        return mInstance;
    }


    public Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public Boolean isOnline() {
        try {
            Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            boolean reachable = (returnVal == 0);
            return reachable;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
