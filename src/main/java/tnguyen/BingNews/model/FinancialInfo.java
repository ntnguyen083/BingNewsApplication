package tnguyen.BingNews.model;

import java.util.ArrayList;
import java.util.List;

public class FinancialInfo {
    private String ID;
    private String categoryID;
    private String title;
    private List<Financial> listFinancial;

    public FinancialInfo(String categoryID, String title) {
        this.categoryID = categoryID;
        this.title = title;
        this.listFinancial = new ArrayList<>();
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

    public List<Financial> getListFinancial() {
        return listFinancial;
    }
}
