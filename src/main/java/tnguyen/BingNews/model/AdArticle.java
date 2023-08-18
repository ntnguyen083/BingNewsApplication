package tnguyen.BingNews.model;

import java.util.UUID;

public class AdArticle {
    private String ID;
    private String imgURL;
    private String title;
    private String sourceURL;

    public AdArticle(String imgURL, String title, String sourceURL) {
        this.ID = UUID.randomUUID().toString();
        this.imgURL = imgURL;
        this.title = title;
        this.sourceURL = sourceURL;
    }

    public AdArticle() { }

    public String getID() {
        return ID;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getTitle() {
        return title;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }
}
