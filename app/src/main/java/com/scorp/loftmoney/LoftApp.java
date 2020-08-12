package com.scorp.loftmoney;

import android.app.Application;
import android.content.SharedPreferences;

import com.scorp.loftmoney.remote.AuthApi;
import com.scorp.loftmoney.remote.LoftMoneyApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoftApp extends Application {

    private LoftMoneyApi loftMoneyApi;
    private AuthApi authApi;

    public static String TOKEN_KEY = "token";

    public AuthApi getAuthApi() {
        return authApi;
    }

    public LoftMoneyApi getLoftMoneyApi() {
        return loftMoneyApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        configureNetwork();
    }

    private void configureNetwork(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://loftschool.com/android-api/basic/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        loftMoneyApi = retrofit.create(LoftMoneyApi.class);
        authApi = retrofit.create(AuthApi.class);
    }

    public SharedPreferences getSharedPreferences(){
        return getSharedPreferences(getString(R.string.app_name), 0);
    }
}
