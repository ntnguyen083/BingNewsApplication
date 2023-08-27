package tnguyen.BingNews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ChannelConfig {
    private String channelConfigName;
    private String type;
    private List<PropertiesConfig> propertiesConfig;

    @JsonProperty("channelConfigName")
    public String getChannelConfigName() { return channelConfigName; }
    @JsonProperty("channelConfigName")
    public void setChannelConfigName(String value) { this.channelConfigName = value; }

    @JsonProperty("type")
    public String getType() { return type; }
    @JsonProperty("type")
    public void setType(String value) { this.type = value; }

    @JsonProperty("propertiesConfig")
    public List<PropertiesConfig> getPropertiesConfig() { return propertiesConfig; }
    @JsonProperty("propertiesConfig")
    public void setPropertiesConfig(List<PropertiesConfig> value) { this.propertiesConfig = value; }
}
