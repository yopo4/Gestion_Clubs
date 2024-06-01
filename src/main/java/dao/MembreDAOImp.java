package dao;

import models.Club;
import models.Membre;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembreDAOImp implements MembreDAO {

    @Override
    public List<Membre> getAllMembres() {
        List<Membre> membres = new ArrayList<>();
        String query = "SELECT * FROM MEMBRES";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Membre membre = new Membre();
                membre.setIdMembre(resultSet.getInt("ID_MEMBRE"));
                membre.setNom(resultSet.getString("NOM"));
                membres.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    @Override
    public Membre getMembreById(int idMembre) {
        Membre membre = null;
        String query = "SELECT * FROM MEMBRES WHERE ID_MEMBRE = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idMembre);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    membre = new Membre();
                    membre.setIdMembre(resultSet.getInt("ID_MEMBRE"));
                    membre.setNom(resultSet.getString("NOM"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public boolean createMembre(Membre membre) {
        String query = "INSERT INTO MEMBRES (NOM) VALUES (?)";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, membre.getNom());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Membre updateMembre(Membre membre) {
        String query = "UPDATE MEMBRES SET NOM = ? WHERE ID_MEMBRE = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, membre.getNom());
            statement.setInt(2, membre.getIdMembre());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return getMembreById(membre.getIdMembre());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Membre getMembreByUserId(int userId) {
        Membre membre = null;
        String query = "SELECT m.ID_MEMBRE, m.NOM " +
                "FROM MEMBRES m " +
                "JOIN USERS u ON m.ID_MEMBRE = u.ID_MEMBRE " +
                "WHERE u.ID_USER = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    membre = new Membre();
                    membre.setIdMembre(resultSet.getInt("ID_MEMBRE"));
                    membre.setNom(resultSet.getString("NOM"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membre;
    }

    @Override
    public boolean isGerant(Membre membre) {
        String query = "select * from integrer where id_membre = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, membre.getIdMembre());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                if(rs.getBoolean("role")){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public List<Membre> getMembersRequestingToJoinClub(Club club) {
        String query = "select m.* from membres m join integrer i on m.ID_MEMBRE = i.ID_MEMBRE join clubs c on c.ID_CLUB = i.ID_CLUB where c.ID_CLUB = ? and i.is_accepted = false";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, club.getIdClub());
            ResultSet rs = statement.executeQuery();
            List<Membre> membres = new ArrayList<>();
            while(rs.next()){
               Membre membre = new Membre();
               membre.setIdMembre(rs.getInt("id_membre"));
               membre.setNom(rs.getString("nom"));
               membres.add(membre);
            }
            return membres;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
