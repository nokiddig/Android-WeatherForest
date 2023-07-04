package com.example.weatherforecast.model;
public class Weather {
    private String icon;
    private String main;
    private String temperature;
    private String humidity;
    private String windSpeed;
    private String cloudAll;
    private String date;

    public Weather(String icon, String main, String temperature, String humidity, String windSpeed, String cloudAll, String date) {
        this.icon = icon;
        this.main = main;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudAll = cloudAll;
        this.date = date;
    }

    public String getUrlIconWeather(){
        return String.format("https://raw.githubusercontent.com/nokiddig/android-revision/main/iconweather/%s%%402x.png", icon);
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getTemperature() {
        if (temperature == null){
            return "null";
        }
        else {
            return String.valueOf(Math.round(Double.parseDouble(temperature)));
        }

    }

    public String getHumidity() {
        if (humidity == null){
            return "null";
        }
        else {
            return humidity;
        }

    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getCloudAll() {
        return cloudAll;
    }

    public String getDate() {
        return date;
    }

    public boolean isDay() {
        return icon.charAt(2) == 'd';
    }
}
