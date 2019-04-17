package com.example.euweather;

import android.app.Application;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    public static final String APP_ID = "485c3a631193f85242f0279d7e337fe1";
    private static MyApplication myApplication;

    private MyAPI myApi;
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    @Override
    public void onCreate() {
        super.onCreate();

        myApplication = this;

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .addInterceptor(loggingInterceptor)
                .build();

        myApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build()
                .create(MyAPI.class);
    }

    public MyAPI getMyApi() { return myApi; }

    public static MyApplication getMyApplicationInstance() {
        if (myApplication == null)
            myApplication = new MyApplication();
        return myApplication;
    }
}
