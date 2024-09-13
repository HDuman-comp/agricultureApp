package com.tarim.backend.tarimApp.business.concretes;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tarim.backend.tarimApp.core.entities.Weather;
import com.tarim.backend.tarimApp.dataAccess.abstracts.IWeatherRepository;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    private IWeatherRepository weatherRepository;

    public void fetchAndSaveWeatherData(String city) {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://api.collectapi.com/weather/getWeather")
                    .header("content-type", "application/json")
                    .header("authorization", "apikey 3lBSgsAAZp6rXAlT5uPWvv:28bKuAmS29K67Dr6O6geHj")
                    .queryString("data.lang", "tr")
                    .queryString("data.city", city)
                    .asJson();

            if (response.getStatus() == 200) {
                JsonNode responseBody = response.getBody();
                saveWeatherData(responseBody);
            } else {
                System.out.println("Error: " + response.getStatus() + " - " + response.getStatusText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveWeatherData(JsonNode responseBody) {
        JSONObject jsonObject = responseBody.getObject();
        String city = jsonObject.getString("city");
        JSONArray results = jsonObject.getJSONArray("result");

        for (int i = 0; i < results.length(); i++) {
            JSONObject result = results.getJSONObject(i);

            Weather weather = new Weather();
            weather.setCity(city);
            weather.setDate(result.getString("date"));
            weather.setDay(result.getString("day"));
            weather.setIcon(result.getString("icon"));
            weather.setDescription(result.getString("description"));
            weather.setStatus(result.getString("status"));
            weather.setDegree(Float.parseFloat(result.getString("degree")));
            weather.setMinDegree(Float.parseFloat(result.getString("min")));
            weather.setMaxDegree(Float.parseFloat(result.getString("max")));
            weather.setNightDegree(Float.parseFloat(result.getString("night")));
            weather.setHumidity(result.getString("humidity"));
            weather.setRequestTime(new Timestamp(System.currentTimeMillis()));

            weatherRepository.save(weather);
        }
    }

    // Veritabanındaki belirli bir şehrin hava durumu verilerini döner
    public List<Weather> getWeatherByCity(String city) {
        return weatherRepository.findByCity(city);
    }
}
