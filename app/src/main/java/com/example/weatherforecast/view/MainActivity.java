package com.example.weatherforecast.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.API;
import com.example.weatherforecast.R;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getJSON("");
        setContentView(R.layout.activity_main);
    }

    public void getJSON(String json){
        String url = "https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=7862417df465ab994fda481d1e3f0169&units=metric";
        Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, " " + response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, " " + error.toString(), Toast.LENGTH_SHORT);

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}