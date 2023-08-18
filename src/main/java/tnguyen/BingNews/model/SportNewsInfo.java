package tnguyen.BingNews.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SportNewsInfo {
    String ID;
    String categoryID;
    String title;
    List<Match> listMatch;

    public SportNewsInfo(String categoryID, String title) {
        this.ID = UUID.randomUUID().toString();
        this.categoryID = categoryID;
        this.title = title;
        listMatch = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getTitle() {
        return title;
    }

    public List<Match> getListMatch() {
        return listMatch;
    }
}
