package com.example.euweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherInfo {
    private Coord coord;
    private List<Weather> weather = null;
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Integer dt;
    private Sys sys;
    private Integer id;
    private String name;
    private Integer cod;

   /* public class WeatherDescription {
        String icon;
    }
    @SerializedName("weather")
    private List<WeatherDescription> iconName;*/

    public WeatherInfo() { }

    public Coord getCoord() { return coord; }
    public void setCoord(Coord coord) { this.coord = coord; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }

    public String getBase() { return base; }
    public void setBase(String base) { this.base = base; }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public Integer getVisibility() { return visibility; }
    public void setVisibility(Integer visibility) { this.visibility = visibility; }

    public Wind getWind() { return wind; }
    public void setWind(Wind wind) { this.wind = wind; }

    public Clouds getClouds() { return clouds; }
    public void setClouds(Clouds clouds) { this.clouds = clouds; }

    public Integer getDt() { return dt; }
    public void setDt(Integer dt) { this.dt = dt; }

    public Sys getSys() { return sys; }
    public void setSys(Sys sys) { this.sys = sys; }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getCod() { return cod; }
    public void setCod(Integer cod) { this.cod = cod; }

    public String getIconUrl() {
        return "http://openweathermap.org/img/w/" + weather.get(0).getIcon() + ".png";
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", base=" + base + '\'' +
                ", icon=" + weather.get(0).getIcon()+ '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }
}
