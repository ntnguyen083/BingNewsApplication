package tnguyen.BingNews.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Category {
    private String ID;
    private String name;
    private List<Channel> listChanel;

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("listChanel")
    public List<Channel> getListChanel() { return listChanel; }
    @JsonProperty("listChanel")
    public void setListChanel(List<Channel> value) { this.listChanel = value; }
}
