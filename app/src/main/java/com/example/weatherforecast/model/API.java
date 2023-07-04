package com.example.weatherforecast.model;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class API {
    public static final String ICON = "icon";
    public static final String MAIN = "main";
    public static final String DESCRIPTION = "description";
    public static final String TEMPERATURE = "temp";
    public static final String HUMIDITY = "humidity";
    public static final String WEATHER = "weather";
    public static final String WIND = "wind";
    public static final String WIND_SPEED = "speed";
    public static final String CLOUDS = "clouds";
    public static final String CLOUDS_ALL = "all";
    public static final String API_KEY = "7862417df465ab994fda481d1e3f0169";
    private static final String DATE = "dt";
    private final MutableLiveData<Weather> weather = new MutableLiveData<>();
    public LiveData<Weather> getTodayForecast(Context context, String city){
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, API_KEY);
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                weather.postValue(readData(response));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {

        });
        requestQueue.add(jsonObjectRequest);
        return weather;
    }

    private Weather readData(JSONObject response) throws JSONException {
        Weather weather = new Weather();
        JSONArray weatherJsonArray = response.getJSONArray(API.WEATHER);
        JSONObject weatherJSON = weatherJsonArray.getJSONObject(0);
        JSONObject mainJSON = response.getJSONObject(API.MAIN);
        JSONObject windJSON = response.getJSONObject(API.WIND);
        JSONObject cloudJSON = response.getJSONObject(API.CLOUDS);
        String icon = weatherJSON.getString(API.ICON);
        String main = weatherJSON.getString(API.MAIN);
        String description = weatherJSON.getString(API.DESCRIPTION);
        String temperature = mainJSON.getString(API.TEMPERATURE);
        String humidity = mainJSON.getString(API.HUMIDITY);
        String windSpeed = windJSON.getString(API.WIND_SPEED);
        String cloudAll = cloudJSON.getString(API.CLOUDS_ALL);
        String dateJson = response.getString(API.DATE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(Long.parseLong(dateJson)*1000);
        String currentTime = dateFormat.format(date);
        return new Weather(icon, main, description, temperature, humidity, windSpeed, cloudAll, currentTime);
    }

}
