package tnguyen.BingNews.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WeatherInfo {
    private String ID;
    private String placeID;
    private List<HourTemperature> listHourTemperature;
    private Date date;

    public WeatherInfo(String placeID, Date date) {
        this.ID = UUID.randomUUID().toString();
        this.placeID = placeID;
        this.date = date;
        this.listHourTemperature = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public String getPlaceID() {
        return placeID;
    }

    public List<HourTemperature> getListHourTemperature() {
        return listHourTemperature;
    }

    public Date getDate() {
        return date;
    }
}
