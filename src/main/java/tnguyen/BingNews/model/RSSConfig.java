package tnguyen.BingNews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RSSConfig {
    private List<ChannelConfig> listChanelConfig;

    @JsonProperty("listChanelConfig")
    public List<ChannelConfig> getListChanelConfig() {
        return listChanelConfig;
    }

    @JsonProperty("listChanelConfig")
    public void setListChanelConfig(List<ChannelConfig> value) {
        this.listChanelConfig = value;
    }
}
