package tnguyen.BingNews.controller;

import tnguyen.BingNews.model.HourTemperature;
import tnguyen.BingNews.model.WeatherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class APIReader {

    public static String readAPIRequest(String apiUrl) throws IOException {

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        String result = "";

        // Set request method
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            result = response.toString();
        } else {
            System.out.println("Request Failed");
        }
        return result;
    }

    public static WeatherInfo extractWeatherInfo(String apiUrl) throws Exception {
        String apiJson = APIReader.readAPIRequest(apiUrl);
        String locationName = extractApiValue(apiJson, "\"name\":\"", "\",");
        String localtime = extractApiValue(apiJson, "\"localtime\":\"", "\"}");
        String apiHoursUrl;
        List<HourTemperature> listHourTemperature = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            apiHoursUrl = apiUrl + "&hour=" + i;
            String apiHoursJson = APIReader.readAPIRequest(apiHoursUrl);
            String hourTag = extractApiValue(apiHoursJson, "\"hour\":[{", "}]");
            String time = extractApiValue(hourTag, "\"time\":\"", "\",");
            String icon = extractApiValue(hourTag, "\"icon\":\"", "\",");
            String tempC = extractApiValue(hourTag, "\"temp_c\":", ",");
            String humidity = extractApiValue(hourTag, "\"humidity\":", ",");
            HourTemperature hourTemperature = new HourTemperature(time, tempC, humidity, icon);
            listHourTemperature.add(hourTemperature);
        }
        return new WeatherInfo(locationName, localtime, listHourTemperature);
    }

    private static String extractApiValue(String json, String startTag, String endTag) {
        int startIndex = json.indexOf(startTag);
        int endIndex = json.indexOf(endTag, startIndex + startTag.length());
        if (startIndex != -1 && endIndex != -1) {
            return json.substring(startIndex + startTag.length(), endIndex);
        }
        return null;
    }
}
