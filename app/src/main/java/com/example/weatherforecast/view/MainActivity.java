package com.example.weatherforecast.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherforecast.model.API;
import com.example.weatherforecast.R;

public class MainActivity extends AppCompatActivity {
    private final String CITY = "hanoi";

    private TextView textViewPosition, textViewDate, textViewCloud,
            textViewWind, textViewHumidity, textViewTemperature;
    LinearLayout layoutContainer;
    private ImageView imageViewIconWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        setupTodayWeather();
    }

    private void setupTodayWeather() {
        API api = new API();
        api.getTodayForecast(getBaseContext(), CITY).observe(this, weather -> {
            Glide.with(getBaseContext()).load(weather.getUrlIconWeather()).fitCenter()
                    .placeholder(R.drawable.icon_temp).into(imageViewIconWeather);
            textViewCloud.setText(weather.getCloudAll() + "%");
            textViewWind.setText(weather.getWindSpeed() + "km/h");
            textViewHumidity.setText(weather.getHumidity() + "%");
            textViewTemperature.setText(weather.getTemperature() + "°C");
            textViewDate.setText("Ngày: " + weather.getDate());
            if (weather.getIcon().charAt(2) == 'n'){
                layoutContainer.setBackgroundResource(R.drawable.background_light);
            }
            else {
                layoutContainer.setBackgroundResource(R.drawable.background_dark);
            }
        });
    }

    private void initWidgets() {
        textViewPosition = findViewById(R.id.textViewPosition);
        textViewDate = findViewById(R.id.textViewDate);
        imageViewIconWeather = findViewById(R.id.imageViewIconWeather);
        textViewCloud = findViewById(R.id.textViewCloud);
        textViewWind = findViewById(R.id.textViewWind);
        textViewHumidity = findViewById(R.id.textViewHibility);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        layoutContainer = findViewById(R.id.container);
    }

//    public void getJSON(String json){
//        String url = "https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=7862417df465ab994fda481d1e3f0169&units=metric";
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    JSONArray weatherJsonArray = response.getJSONArray(API.WEATHER);
//                    JSONObject weatherJSON = weatherJsonArray.getJSONObject(0);
//                    JSONObject mainJSON = response.getJSONObject(API.MAIN);
//                    JSONObject windJSON = response.getJSONObject(API.WIND);
//                    JSONObject cloudJSON = response.getJSONObject(API.CLOUDS);
//                    Weather weather = new Weather(weatherJSON, mainJSON, windJSON, cloudJSON);
//                    Glide.with(getBaseContext()).load(weather.getUrlIconWeather()).fitCenter()
//                            .placeholder(R.drawable.icon_temp).into(imageViewIconWeather);
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//                Toast.makeText(MainActivity.this, " " + response.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, " " + error.toString(), Toast.LENGTH_SHORT);
//
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }
}