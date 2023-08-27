package tnguyen.BingNews.repository;

import tnguyen.BingNews.model.AdArticle;
import tnguyen.BingNews.model.News;

import java.util.List;

public interface NewsRepository {
    List<News> getAllNews();
    News getNewsById(String news);
    void insertNews(News news);
    void updateNews(String imgURL, String title, String sourceURL, String newsId);
    void deleteNews(String newsId);
}
