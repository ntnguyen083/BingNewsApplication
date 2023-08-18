package tnguyen.BingNews.model;

import javax.xml.crypto.Data;
import java.sql.Timestamp;

public class News extends AdArticle{
    private String categoryID;
    private Data publishDate;
    private Timestamp publishAt;
    private int like;
    private int dislike;

    public News(String imgURL, String title, String sourceURL, String categoryID, Data publishDate, Timestamp publishAt, int like, int dislike) {
        super(imgURL, title, sourceURL);
        this.categoryID = categoryID;
        this.publishDate = publishDate;
        this.publishAt = publishAt;
        this.like = like;
        this.dislike = dislike;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public Data getPublishDate() {
        return publishDate;
    }

    public Timestamp getPublishAt() {
        return publishAt;
    }

    public int getLike() {
        return like;
    }

    public int getDislike() {
        return dislike;
    }
}
