package tnguyen.BingNews.repository;

import tnguyen.BingNews.model.AdArticle;

import java.util.List;

public interface AdArticleRepository {
    List<AdArticle> getAllAdArticle();
    AdArticle getAdArticleById(int adArticleId);
    void insertAdArticle(AdArticle adArticle);
    void updateAdArticle(AdArticle adArticle);
    void deleteAdArticle(int adArticleId);
}
