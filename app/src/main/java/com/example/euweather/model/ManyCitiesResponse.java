package com.example.euweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ManyCitiesResponse {

    private int cnt;
    @SerializedName("list")
    private List<WeatherInfo> weatherInfoList;

    public int getCnt() {
        return cnt;
    }

    public List<WeatherInfo> getWeatherInfoList() {
        return weatherInfoList;
    }
}
