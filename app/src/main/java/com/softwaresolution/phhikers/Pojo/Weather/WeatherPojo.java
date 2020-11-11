package com.softwaresolution.phhikers.Pojo.Weather; 
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherPojo {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current")
    @Expose
    private Current current;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;
    @SerializedName("alert")
    @Expose
    private Alert alert;

    /**
     * No args constructor for use in serialization
     *
     */
    public WeatherPojo() {
    }

    /**
     *
     * @param current
     * @param alert
     * @param location
     * @param forecast
     */
    public WeatherPojo(Location location, Current current, Forecast forecast, Alert alert) {
        super();
        this.location = location;
        this.current = current;
        this.forecast = forecast;
        this.alert = alert;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

}