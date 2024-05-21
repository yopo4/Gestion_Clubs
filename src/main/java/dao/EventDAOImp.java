package dao;

import models.Event;
import utils.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImp implements EventDAO {

    @Override
    public List<Event> selectAllEvents() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM evenements";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Event event = new Event();
                event.setId_evenement(resultSet.getInt("id_evenement"));
                event.setId_club(resultSet.getInt("id_club"));
                event.setTitre(resultSet.getString("titre"));
                event.setDescription(resultSet.getString("description"));
                event.setDateDebut(resultSet.getString("date_debut"));
                event.setDateFin(resultSet.getString("date_fin"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public Event selectEventById(int idEvent) {
        Event event = null;
        String query = "SELECT * FROM evenements WHERE id_evenement = ?";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idEvent);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    event = new Event();
                    event.setId_evenement(resultSet.getInt("id_evenement"));
                    event.setId_club(resultSet.getInt("id_club"));
                    event.setTitre(resultSet.getString("titre"));
                    event.setDescription(resultSet.getString("description"));
                    event.setDateDebut(resultSet.getString("date_debut"));
                    event.setDateFin(resultSet.getString("date_fin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<Event> selectTopEventsByMembersCount(int top) {
        List<Event> events = new ArrayList<>();
        String query = "SELECT evenements.*, COUNT(integrer_evenement.ID_EVENEMENT) AS inscrits \n" +
                "FROM evenements \n" +
                "INNER JOIN integrer_evenement \n" +
                "ON evenements.ID_EVENEMENT = integrer_evenement.ID_EVENEMENT \n" +
                "GROUP BY evenements.ID_EVENEMENT \n" +
                "ORDER BY inscrits DESC \n" +
                "LIMIT ?;";

        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, top);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Event event = new Event();
                    event.setId_evenement(resultSet.getInt("id_evenement"));
                    event.setId_club(resultSet.getInt("id_club"));
                    event.setTitre(resultSet.getString("titre"));
                    event.setDescription(resultSet.getString("description"));
                    event.setDateDebut(resultSet.getString("date_debut"));
                    event.setDateFin(resultSet.getString("date_fin"));
                    events.add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public boolean createEvent(Event event) {
        String query = "INSERT INTO evenements (id_club, titre, description, date_debut, date_fin) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, event.getId_club());
            statement.setString(2, event.getTitre());
            statement.setString(3, event.getDescription());
            statement.setString(4, event.getDateDebut());
            statement.setString(5, event.getDateFin());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Event updateEvent(Event event) {
        Event updatedEvent = null;
        String query = "UPDATE evenements SET id_club = ?, titre = ?, description = ?, date_debut = ?, date_fin = ? WHERE id_evenement = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, event.getId_club());
            statement.setString(2, event.getTitre());
            statement.setString(3, event.getDescription());
            statement.setString(4, event.getDateDebut());
            statement.setString(5, event.getDateFin());
            statement.setInt(6, event.getId_evenement());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                updatedEvent = selectEventById(event.getId_evenement());
                return updatedEvent;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedEvent;
    }
}
