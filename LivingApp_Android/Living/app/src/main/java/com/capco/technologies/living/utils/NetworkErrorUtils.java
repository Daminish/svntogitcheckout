package com.capco.technologies.living.utils;

import java.net.SocketTimeoutException;

import retrofit2.Call;

/**
 * Created by sachin on 10/01/18.
 */

public class NetworkErrorUtils {


    public static String getError(Call<Object> call, Throwable e) {

        String host = call.request().url().host();
        int port = call.request().url().port();


        if (e instanceof SocketTimeoutException) {

            return "Host " + host + (port == 80 ? "" : ":" + port) + " not reachable. Please contact administrator";
        }
        return "Something Went Wrong";
    }

}
