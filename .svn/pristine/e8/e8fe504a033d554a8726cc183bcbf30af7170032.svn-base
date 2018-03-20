package com.capco.technologies.living.domain.repository;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static ApiClient instance;
    private Context context;


    private Retrofit retrofit;

    private LivingAppApiService apiService;


    private ApiClient(Context context) {
        this.context = context;

        LivingSharedPref pref = LivingSharedPref.getInstance();
        String host = pref.getHostIP();
        int port = pref.getHostPORT();

        String hostUrl = String.format("http://%s:%d", host, port);
        initClient(hostUrl);
    }


    public LivingAppApiService getClient() {
        if (apiService == null || instance == null) {
            throw new RuntimeException("Please call ApiClient.init(Context) to access functionality");
        }
        return apiService;
    }

    public void initClient(String host) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


//        String


        retrofit = new Retrofit.Builder()
//                .baseUrl(BuildConfig.DOMAIN_URL)
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofit.create(LivingAppApiService.class);

    }


    public static ApiClient getInstance() {
        if (instance == null) {
            throw new RuntimeException("Please call ApiClient.init(Context) to access functionality");
        }

        return instance;
    }


    public synchronized static void init(Context context) {
        if (instance == null) {
            instance = new ApiClient(context);
        }
    }

}