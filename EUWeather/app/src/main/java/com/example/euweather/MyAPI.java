package com.example.euweather;



import com.example.euweather.model.WeatherInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyAPI {
    @GET("weather")
    Call<WeatherInfo> getWeather(@Query("id") String id, @Query("appid") String appid);
}
