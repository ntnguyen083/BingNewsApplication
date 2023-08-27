package tnguyen.BingNews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertiesConfig {
    private String propertyName;
    private String path;

    @JsonProperty("propertyName")
    public String getPropertyName() { return propertyName; }
    @JsonProperty("propertyName")
    public void setPropertyName(String value) { this.propertyName = value; }

    @JsonProperty("Path")
    public String getPath() { return path; }
    @JsonProperty("Path")
    public void setPath(String value) { this.path = value; }
}
