package tnguyen.BingNews.model;

import java.util.UUID;

public class HourTemperature {

    private String ID;
    private String hour;
    private float temperature;
    private float humidity;
    private String temperatureImg;

    public HourTemperature(String hour, float temperature, float humidity, String temperatureImg) {
        this.ID = UUID.randomUUID().toString();
        this.hour = hour;
        this.temperature = temperature;
        this.humidity = humidity;
        this.temperatureImg = temperatureImg;
    }

    public String getID() {
        return ID;
    }

    public String getHour() {
        return hour;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public String getTemperatureImg() {
        return temperatureImg;
    }
}
