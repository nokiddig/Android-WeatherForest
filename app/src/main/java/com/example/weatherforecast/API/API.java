package com.example.weatherforecast.API;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class API {
    public static final String ICON = "icon";
    public static final String MAIN = "main";
    public static final String TEMPERATURE = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String WEATHER = "weather";
    public static final String CURRENT = "current";
    public static final String WIND_SPEED = "wind_speed";
    public static final String CLOUDS = "clouds";
    private static final String DATE = "dt";
    private static final String DAILY = "daily";
    public static final String API_KEY = "ca8c2c7970a09dc296d9b3cfc4d06940";
    private final MutableLiveData<List<Weather>> listWeather = new MutableLiveData<>();

    public LiveData<List<Weather>> getDailyForecast(Context context){
        String url = String.format("https://api.openweathermap.org/data/2.5/onecall?lat=21.0278&lon=105.8342&appid=%s&units=metric&lang=vi", API_KEY);
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                List<Weather> list = new ArrayList<>();
                list.add(readCurrentData(response.getJSONObject(API.CURRENT)));
                JSONArray jsonArray = response.getJSONArray(API.DAILY);
                for (int i = 0; i < 7; i++) {
                    list.add(readDailyData(jsonArray.getJSONObject(i)));
                }
                listWeather.postValue(list);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {

        });
        requestQueue.add(jsonObjectRequest);
        return listWeather;
    }

    private Weather readCurrentData(JSONObject response) throws JSONException {
        JSONArray weatherJsonArray = response.getJSONArray(API.WEATHER);
        JSONObject weatherJSON = weatherJsonArray.getJSONObject(0);
        String icon = weatherJSON.getString(API.ICON);
        String main = weatherJSON.getString(API.MAIN);
        String temperature = response.getString(API.TEMPERATURE);
        String humidity = response.getString(API.HUMIDITY);
        String windSpeed = response.getString(API.WIND_SPEED);
        String cloudAll = response.getString(API.CLOUDS);
        String dateJson = response.getString(API.DATE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(Long.parseLong(dateJson)*1000);
        String currentTime = dateFormat.format(date);
        return new Weather(icon, main, temperature, humidity, windSpeed, cloudAll, currentTime);
    }

    private Weather readDailyData(JSONObject response) throws JSONException {
        JSONArray weatherJsonArray = response.getJSONArray(API.WEATHER);
        JSONObject weatherJSON = weatherJsonArray.getJSONObject(0);
        JSONObject tempJSON = response.getJSONObject(API.TEMPERATURE);
        String icon = weatherJSON.getString(API.ICON);
        String temperature = tempJSON.getString("day");
        String humidity = response.getString(API.HUMIDITY);
        String dateJson = response.getString(API.DATE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(Long.parseLong(dateJson)*1000);
        String currentTime = dateFormat.format(date);
        return new Weather(icon, "main", temperature, humidity, "windSpeed", "cloudAll", currentTime);
    }

}
