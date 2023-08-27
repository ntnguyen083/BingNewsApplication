package tnguyen.BingNews.controller.parseData;

public interface DataFactory<T> {
    T createNews(String guid, String title, String description, String link, String pubDate, String image);
}
