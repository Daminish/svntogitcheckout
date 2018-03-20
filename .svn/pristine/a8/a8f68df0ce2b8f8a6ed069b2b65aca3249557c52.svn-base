package com.capco.technologies.living.domain.repository;

import com.capco.technologies.living.utils.AppConstants;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by sachin on 08/01/18.
 */

public interface LivingAppApiService {


    @POST(AppConstants.API_BASE_ENDPOINT + "login")
    Call<Object> login(@Body JsonObject user);


    @POST(AppConstants.API_BASE_ENDPOINT + "budgeting/")
    Call<JsonObject> budgeting(@Body JsonObject budgeting);

    @POST(AppConstants.API_BASE_ENDPOINT + "forgot/password?")
    Call<Object> forgotpwd(@Body JsonObject forgotpwd);
}
