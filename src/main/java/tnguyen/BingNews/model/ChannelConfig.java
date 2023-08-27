package tnguyen.BingNews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChannelConfig {
    private String channelConfigName;
    private String titleXPath;
    private String descriptionXPath;
    private String linkXPath;
    private String imageXPath;
    private String pubDateXPath;
    private String guidXPath;
    @JsonProperty("channelConfigName")
    public String getChannelConfigName() { return channelConfigName; }
    @JsonProperty("channelConfigName")
    public void setChannelConfigName(String value) { this.channelConfigName = value; }

    @JsonProperty("titleXPath")
    public String getTitleXPath() { return titleXPath; }
    @JsonProperty("titleXPath")
    public void setTitleXPath(String value) { this.titleXPath = value; }

    @JsonProperty("descriptionXPath")
    public String getDescriptionXPath() { return descriptionXPath; }
    @JsonProperty("descriptionXPath")
    public void setDescriptionXPath(String value) { this.descriptionXPath = value; }

    @JsonProperty("linkXPath")
    public String getLinkXPath() { return linkXPath; }
    @JsonProperty("linkXPath")
    public void setLinkXPath(String value) { this.linkXPath = value; }

    @JsonProperty("imageXPath")
    public String getImageXPath() { return imageXPath; }
    @JsonProperty("imageXPath")
    public void setImageXPath(String value) { this.imageXPath = value; }

    @JsonProperty("pubDateXPath")
    public String getPubDateXPath() { return pubDateXPath; }
    @JsonProperty("pubDateXPath")
    public void setPubDateXPath(String value) { this.pubDateXPath = value; }

    @JsonProperty("guidXPath")
    public String getGUIDXPath() { return guidXPath; }
    @JsonProperty("guidXPath")
    public void setGUIDXPath(String value) { this.guidXPath = value; }
}
