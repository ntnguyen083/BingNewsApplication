package tnguyen.BingNews.controller.parseData.factory;

import tnguyen.BingNews.controller.parseData.DataFactory;
import tnguyen.BingNews.model.News;

public class NewsFactory implements DataFactory<News> {
    @Override
    public News createNews(String guid, String title, String description, String link, String pubDate, String image) {
        return new News(guid, title, description, link, pubDate, image);
    }
}
