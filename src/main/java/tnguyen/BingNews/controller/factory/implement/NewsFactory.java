package tnguyen.BingNews.controller.factory.implement;

import tnguyen.BingNews.controller.factory.DataFactory;
import tnguyen.BingNews.model.News;

public class NewsFactory implements DataFactory<News> {
    @Override
    public News createNews() {
        return new News();
    }
}
