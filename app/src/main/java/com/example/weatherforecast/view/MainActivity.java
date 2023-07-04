package com.example.weatherforecast.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherforecast.adapter.WeatherAdapter;
import com.example.weatherforecast.API.API;
import com.example.weatherforecast.R;
import com.example.weatherforecast.model.Weather;

public class MainActivity extends AppCompatActivity {
    private TextView textViewDate, textViewCloud,
            textViewWind, textViewHumidity, textViewTemperature;
    private LinearLayout layoutContainer;
    private ImageView imageViewIconWeather;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        readAPI();
    }

    private void readAPI() {
        API api = new API();
        api.getDailyForecast(getBaseContext()).observe(this, listWeather -> {
            Weather weather = listWeather.get(0);
            setupCurrentWeather(weather);
            WeatherAdapter adapter = new WeatherAdapter(this, listWeather.subList(1, 8));
            listView.setAdapter(adapter);
        });
    }

    public void setupCurrentWeather(Weather weather) {
        Glide.with(getBaseContext()).load(weather.getUrlIconWeather()).fitCenter()
                .placeholder(R.drawable.icon_temp).into(imageViewIconWeather);
        textViewCloud.setText(weather.getCloudAll() + "%");
        textViewWind.setText(weather.getWindSpeed() + "km/h");
        textViewHumidity.setText(weather.getHumidity() + "%");
        textViewTemperature.setText(weather.getTemperature() + "°C");
        textViewDate.setText("Ngày: " + weather.getDate());
        if (weather.isDay()){
            layoutContainer.setBackgroundResource(R.drawable.background_light);
        }
        else {
            layoutContainer.setBackgroundResource(R.drawable.background_dark);
        }
    }

    private void initWidgets() {
        textViewDate = findViewById(R.id.textViewDate);
        imageViewIconWeather = findViewById(R.id.imageViewIconWeather);
        textViewCloud = findViewById(R.id.textViewCloud);
        textViewWind = findViewById(R.id.textViewWind);
        textViewHumidity = findViewById(R.id.textViewHibility);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        layoutContainer = findViewById(R.id.container);
        listView = findViewById(R.id.listView);
    }
}