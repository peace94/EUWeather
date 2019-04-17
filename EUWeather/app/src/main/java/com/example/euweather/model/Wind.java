package com.example.euweather.model;

public class Wind {
    private Float speed;
    private Integer deg;

    public Wind() { }

    public Float getSpeed() { return speed; }
    public void setSpeed(Float speed) { this.speed = speed; }

    public Integer getDeg() { return deg; }
    public void setDeg(Integer deg) { this.deg = deg; }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
