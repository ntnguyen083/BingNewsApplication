package tnguyen.BingNews.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class Match {
    private String ID;
    private String title;
    private String description;
    private String groupID;
    private String home;
    private String away;
    private String score;
    private Date date;
    private Timestamp time;

    public Match(String title, String description, String groupID, String home, String away, String score, Date date, Timestamp time) {
        this.ID = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.groupID = groupID;
        this.home = home;
        this.away = away;
        this.score = score;
        this.date = date;
        this.time = time;
    }
}
