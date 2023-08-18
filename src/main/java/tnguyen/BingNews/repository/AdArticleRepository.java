package tnguyen.BingNews.repository;

import tnguyen.BingNews.model.AdArticle;

import java.util.List;

public interface AdArticleRepository {
    List<AdArticle> getAllAdArticle();
    AdArticle getAdArticleById(String adArticleId);
    void insertAdArticle(AdArticle adArticle);
    void updateAdArticle(String imgURL, String title, String sourceURL, String adArticleId);
    void deleteAdArticle(String adArticleId);
}
