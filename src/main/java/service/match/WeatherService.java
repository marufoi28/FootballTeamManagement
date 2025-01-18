package service.match;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.weather.Weather;

public class WeatherService {
	public Weather getWeather(String locationCode, LocalDate targetDate) throws Exception {
		
		LocalDate today = LocalDate.now();
		
		long daysBetween = ChronoUnit.DAYS.between(today, targetDate);
		
		if(daysBetween < 0 || daysBetween > 5) {
			Weather weather = new Weather("取得不可", null, null);
			return weather;
		}
		
        String apiKey = "143d6b5bcdb90fabcf2ea12b9fbdf3d6";
        String urlString = "http://api.openweathermap.org/data/2.5/forecast?q=" + locationCode + "&appid=" + apiKey + "&units=metric&lang=ja";
        
		URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        
        InputStreamReader reader = new InputStreamReader(connection.getInputStream());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode response = objectMapper.readTree(reader);
        
        String targetDateString = targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        boolean dataFound = false;
        
        String weatherCondition = null;
        double temperature = 0;
        int humidity = 0;

        for (JsonNode forecast : response.get("list")) {
            String dateTime = forecast.get("dt_txt").asText();

            if (dateTime.startsWith(targetDateString)) {
                weatherCondition = forecast.get("weather").get(0).get("description").asText();
                temperature = forecast.get("main").get("temp").asDouble();
                humidity = forecast.get("main").get("humidity").asInt();
                dataFound = true;
            }
        }
        
        if (!dataFound) {
			Weather weather = new Weather("取得失敗", null, null);
			return weather;
        } 
        
    	Weather weather = new Weather(weatherCondition, temperature, humidity);
    	return weather;
    }
}
