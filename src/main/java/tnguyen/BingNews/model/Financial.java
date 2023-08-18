package tnguyen.BingNews.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Financial {
    private String ID;
    private String title;
    private String description;
    private String financeImg;
    private float rateRising;
    private String status;
    private float totalRising;
    private Date date;
    private Timestamp time;

    public Financial(String title, String description, String financeImg, float rateRising, String status, float totalRising, Date date, Timestamp time) {
        this.ID = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.financeImg = financeImg;
        this.rateRising = rateRising;
        this.status = status;
        this.totalRising = totalRising;
        this.date = date;
        this.time = time;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFinanceImg() {
        return financeImg;
    }

    public float getRateRising() {
        return rateRising;
    }

    public String getStatus() {
        return status;
    }

    public float getTotalRising() {
        return totalRising;
    }

    public Date getDate() {
        return date;
    }

    public Timestamp getTime() {
        return time;
    }
}
