package com.example.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.weatherforecast.R;
import com.example.weatherforecast.model.Weather;

import java.util.List;

public class WeatherAdapter  extends ArrayAdapter<Weather> {
    public WeatherAdapter(Context context, List<Weather> weathers) {
        super(context, 0, weathers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Weather weather = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_daily_forecast, parent, false);
        }

        TextView textViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
        TextView textViewTemp = (TextView) convertView.findViewById(R.id.textViewTemp);
        TextView textViewHumidity = (TextView) convertView.findViewById(R.id.textViewHumidity);
        ImageView imageViewIcon = (ImageView) convertView.findViewById(R.id.imageViewIconWeather);

        textViewDate.setText(weather.getDate());
        textViewTemp.setText(weather.getTemperature() + "°C");
        textViewHumidity.setText(weather.getHumidity() + "%");
        Glide.with(convertView).load(weather.getUrlIconWeather()).fitCenter()
                .placeholder(R.drawable.icon_temp).into(imageViewIcon);

        return convertView;
    }
}
