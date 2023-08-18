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
    public AdArticle getAdArticleById(String adArticleId) {
        AdArticle adArticle = new AdArticle();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            // Set the parameter for the prepared statement
            preparedStatement.setString(1, adArticleId);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    adArticle.setID(resultSet.getString("id"));
                    adArticle.setImgURL(resultSet.getString("imgURL"));
                    adArticle.setTitle(resultSet.getString("title"));
                    adArticle.setSourceURL(resultSet.getString("sourceURL"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adArticle;
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
    public void updateAdArticle(String imgURL, String title, String sourceURL, String ID) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, imgURL);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, sourceURL);
            preparedStatement.setString(4, ID);

            // Execute the update statement
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("AdArticle with ID " + ID + " updated successfully.");
            } else {
                System.out.println("No AdArticle with ID " + ID + " found for update.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdArticle(String adArticleId) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {

            // Set the parameter for the prepared statement
            preparedStatement.setString(1, adArticleId);

            // Execute the delete statement
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User with ID " + adArticleId + " deleted successfully.");
            } else {
                System.out.println("No user with ID " + adArticleId + " found for deletion.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
