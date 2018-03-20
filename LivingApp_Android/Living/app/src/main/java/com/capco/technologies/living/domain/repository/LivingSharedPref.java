package com.capco.technologies.living.domain.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.capco.technologies.living.utils.AppConstants;

/**
 * Created by sachin on 10/01/18.
 */

public class LivingSharedPref {
    private static LivingSharedPref ourInstance = null;
    private Context context;
    private SharedPreferences preferences;


    public static LivingSharedPref getInstance() {

        if (ourInstance == null) {
            throw new NullPointerException("Please init Shared pref before accesing instance using LivingSharedPref.init(Context)");
        }
        return ourInstance;
    }


    public static void init(Context context) {
        if (ourInstance == null) {
            ourInstance = new LivingSharedPref(context);
        }
    }

    private LivingSharedPref(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(AppConstants.PREF_NAME, 0);
    }


    private String PREF_KEY_HOST = "pref_key_host";
    private String PREF_KEY_HOST_PORT = "pref_key_host_port";
    private String PREF_KEY_CALCULATE_EMI_RESPONSE = "pref_key_calculate_emi";

    public void setHostIP(String ip) {
        preferences.edit().putString(PREF_KEY_HOST, ip).commit();

    }

    public String getHostIP() {
        return preferences.getString(PREF_KEY_HOST, null);
    }

    public void setHostPORT(int port) {
        preferences.edit().putInt(PREF_KEY_HOST_PORT, port).commit();

    }

    public int getHostPORT() {
        return preferences.getInt(PREF_KEY_HOST_PORT, 80);
    }


    public void setCalculateEmi(String emi) {
        preferences.edit().putString(PREF_KEY_CALCULATE_EMI_RESPONSE, emi).commit();
    }

    public String getCalculateEmi() {
        return preferences.getString(PREF_KEY_CALCULATE_EMI_RESPONSE, null);
    }


}
