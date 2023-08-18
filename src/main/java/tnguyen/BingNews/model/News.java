package tnguyen.BingNews.model;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Timestamp;

public class News extends AdArticle{
    private String categoryID;
    private Date publishDate;
    private Timestamp publishAt;
    private int like;
    private int dislike;

    private Chanel chanel;

    public News(String imgURL, String title, String sourceURL, String categoryID, Date publishDate, Timestamp publishAt, int like, int dislike, Chanel chanel) {
        super(imgURL, title, sourceURL);
        this.categoryID = categoryID;
        this.publishDate = publishDate;
        this.publishAt = publishAt;
        this.like = like;
        this.dislike = dislike;
        this.chanel = chanel;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public Date getPublishDate() {
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
