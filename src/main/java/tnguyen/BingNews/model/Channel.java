package tnguyen.BingNews.model;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

public class Channel {
    private String title;
    private String description;
    private ChannelImage image;
    private String pubDate;
    private String link;
    private List<News> newsList;
    private String channelName;
    private String rssURL;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.title = value;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.description = value;
    }

    @JsonProperty("image")
    public ChannelImage getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(ChannelImage value) {
        this.image = value;
    }

    @JsonProperty("pubDate")
    public String getPubDate() {
        return pubDate;
    }

    @JsonProperty("pubDate")
    public void setPubDate(String value) {
        this.pubDate = value;
    }

    @JsonProperty("link")
    public String getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(String value) {
        this.link = value;
    }

    @JsonProperty("news")
    public List<News> getNews() {
        return newsList;
    }

    @JsonProperty("news")
    public void setNews(List<News> value) {
        this.newsList = value;
    }

    @JsonProperty("channelName")
    public String getChannelName() {
        return channelName;
    }

    @JsonProperty("channelName")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @JsonProperty("rssURL")
    public String getRssURL() {
        return rssURL;
    }

    @JsonProperty("rssURL")
    public void setRssURL(String rssURL) {
        this.rssURL = rssURL;
    }
}
