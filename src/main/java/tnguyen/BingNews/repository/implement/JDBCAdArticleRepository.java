package tnguyen.BingNews.repository.implement;

import tnguyen.BingNews.model.AdArticle;
import tnguyen.BingNews.repository.AdArticleRepository;
import tnguyen.BingNews.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCAdArticleRepository implements AdArticleRepository {
    private static final String SELECT_ALL_ADARTICLE = "SELECT * FROM AdArticle";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM AdArticle WHERE id = ?";
    private static final String INSERT_USER = "INSERT INTO AdArticle (id, imgURL, title, sourceURL) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE AdArticle SET imgURL = ?, title = ?, sourceURL = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM AdArticle WHERE id = ?";

    @Override
    public List<AdArticle> getAllAdArticle() {
        List<AdArticle> adArticleList = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADARTICLE);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                AdArticle adArticle = new AdArticle();
                adArticle.setID(resultSet.getString("id"));
                adArticle.setImgURL(resultSet.getString("imgURL"));
                adArticle.setTitle(resultSet.getString("title"));
                adArticle.setSourceURL(resultSet.getString("sourceURL"));
                adArticleList.add(adArticle);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adArticleList;
    }

    @Override
    public AdArticle getAdArticleById(int adArticleId) {
        return null;
    }

    @Override
    public void insertAdArticle(AdArticle adArticle) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, adArticle.getID());
            preparedStatement.setString(2, adArticle.getImgURL());
            preparedStatement.setString(3, adArticle.getTitle());
            preparedStatement.setString(4, adArticle.getSourceURL());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAdArticle(AdArticle adArticle) {

    }

    @Override
    public void deleteAdArticle(int adArticleId) {

    }

    // Implement other methods...
}
