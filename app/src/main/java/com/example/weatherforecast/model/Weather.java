package com.example.weatherforecast.model;
import org.json.JSONException;
import org.json.JSONObject;
public class Weather {
    private String icon;
    private String main;
    private String description;
    private String temperature = "";
    private String humidity;
    private String windSpeed;
    private String cloudAll;
    private String date;

    public Weather(String icon, String main, String description, String temperature, String humidity, String windSpeed, String cloudAll, String date) {
        this.icon = icon;
        this.main = main;
        this.description = description;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudAll = cloudAll;
        this.date = date;
    }

    public Weather(JSONObject weather, JSONObject mainJO, JSONObject windJO, JSONObject cloudJO) {
        try {
            icon = weather.getString(API.ICON);
            main = weather.getString(API.MAIN);
            description = weather.getString(API.DESCRIPTION);
            temperature = mainJO.getString(API.TEMPERATURE);
            humidity = mainJO.getString(API.HUMIDITY);
            windSpeed = windJO.getString(API.WIND_SPEED);
            cloudAll = cloudJO.getString(API.CLOUDS_ALL);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public Weather() {

    }

    public String getUrlIconWeather(){
        return String.format("https://raw.githubusercontent.com/nokiddig/android-revision/main/iconweather/%s%%402x.png", icon);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getCloudAll() {
        return cloudAll;
    }

    public void setCloudAll(String cloudAll) {
        this.cloudAll = cloudAll;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
