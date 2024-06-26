package dao;

import models.User;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {

    @Override
    public User getUserById(int id) {
        User user = null;
        String query = "SELECT * FROM users WHERE id_user = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId_user(resultSet.getInt("id_user"));
                    user.setNom(resultSet.getString("nom"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("mot_de_passe"));
                    user.setRole(resultSet.getBoolean("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        User user = null;
        String query = "SELECT * FROM users WHERE email = ? AND mot_de_passe = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId_user(resultSet.getInt("id_user"));
                    user.setNom(resultSet.getString("nom"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPassword(resultSet.getString("mot_de_passe"));
                    user.setRole(resultSet.getBoolean("role"));
                    user.setId_membre(resultSet.getInt("id_membre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public int getLastId() {
        int id = 0;
        String query = "SELECT id_user FROM users order by id_user desc limit 1";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id_user");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public boolean createUser(User newUser) {
        String query = "INSERT INTO users (nom, email, mot_de_passe, id_membre) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, newUser.getNom());
            statement.setString(2, newUser.getEmail());
            statement.setString(3, newUser.getPassword());
            statement.setInt(4, newUser.getId_membre());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
