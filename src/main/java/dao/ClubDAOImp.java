package dao;

import models.Club;
import models.Event;
import models.Membre;
import utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClubDAOImp implements ClubDAO {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    public ClubDAOImp(){
        con = ConnectionDB.getConnection();
    }
    @Override
    public List<Club> getAllClubs() {
        String sql = "SELECT * FROM CLUBS";
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            int idClub;
            int idUser;
            String nom;
            List<Club> clubs = new ArrayList<Club>();
            while (rs.next()){
                Club club = new Club();
                idClub = rs.getInt("ID_CLUB");
                idUser = rs.getInt("ID_USER");
                nom = rs.getString("NOM");
                club.setIdClub(idClub);
                club.setIdUser(idUser);
                club.setNom(nom);
                clubs.add(club);
            }
            return clubs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Club getClubById(int clubId) {
        String sql = "SELECT * FROM CLUBS WHERE ID_CLUB = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, clubId);
            rs = pst.executeQuery();
            if (rs.next()) {
                int idClub = rs.getInt("ID_CLUB");
                int idUser = rs.getInt("ID_USER");
                String nom = rs.getString("NOM");
                return new Club(idClub, idUser, nom);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Club getClubByName(String clubName) {
        String sql = "SELECT * FROM CLUBS WHERE nom = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, clubName);
            rs = pst.executeQuery();
            if (rs.next()) {
                int idClub = rs.getInt("ID_CLUB");
                int idUser = rs.getInt("ID_USER");
                String nom = rs.getString("NOM");
                return new Club(idClub, idUser, nom);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean createClub(Club club) {
        String sql = "INSERT INTO CLUBS (ID_USER, NOM) VALUES (?, ?)";
        try {
            pst = con.prepareStatement(sql);
            if (club.getIdUser() == 0) {
                pst.setNull(1, java.sql.Types.INTEGER);
            } else {
                pst.setInt(1, club.getIdUser());
            }
            pst.setString(2, club.getNom());
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Club> getTopClubs(int topNumber) {
        String sql = "SELECT c.ID_CLUB, c.ID_USER, c.NOM, COUNT(i.ID_MEMBRE) AS memberCount " +
                "FROM CLUBS c " +
                "LEFT JOIN INTEGRER i ON c.ID_CLUB = i.ID_CLUB " +
                "GROUP BY c.ID_CLUB " +
                "ORDER BY memberCount DESC " +
                "LIMIT ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, topNumber);
            rs = pst.executeQuery();
            ArrayList<Club> clubs = new ArrayList<>();
            while (rs.next()) {
                int idClub = rs.getInt("ID_CLUB");
                int idUser = rs.getInt("ID_USER");
                String nom = rs.getString("NOM");
                Club club = new Club(idClub, idUser, nom);
                clubs.add(club);
            }
            return clubs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getGerantNameById(int idUser) {
        String gerantName = "";
        String query = "SELECT users.nom FROM users WHERE users.id_user = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idUser);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gerantName = resultSet.getString("nom");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gerantName;
    }

    @Override
    public boolean addMembreToClub(Club club, Membre membre) {
        String query = "INSERT INTO INTEGRER (ID_CLUB, ID_MEMBRE) VALUES (?, ?)";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameters for the prepared statement
            statement.setInt(1, club.getIdClub());
            statement.setInt(2, membre.getIdMembre());

            // Execute the update and check if a row was inserted
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Club> getClubsOfMember(int id_member) {
        String sql = "SELECT C.* FROM CLUBS C INNER JOIN INTEGRER I ON C.ID_CLUB=I.ID_CLUB WHERE I.ID_MEMBRE=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,id_member);
            rs = pst.executeQuery();
            int idClub;
            int idUser;
            String nom;
            List<Club> clubs = new ArrayList<Club>();
            while (rs.next()){
                Club club = new Club();
                idClub = rs.getInt("ID_CLUB");
                idUser = rs.getInt("ID_USER");
                nom = rs.getString("NOM");
                club.setIdClub(idClub);
                club.setIdUser(idUser);
                club.setNom(nom);
                clubs.add(club);
            }
            return clubs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getMembersCountByClubId(int idClub) {
        int membersCount = 0;
        String query = "SELECT count(ID_MEMBRE) as membersCount FROM integrer WHERE ID_CLUB = ?";
        try (Connection connection = ConnectionDB.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, idClub);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    membersCount = resultSet.getInt("membersCount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membersCount;
    }

    @Override
    public Club updateClub(Club club) {
        String sql = "UPDATE CLUBS SET ID_USER = ?, NOM = ? WHERE ID_CLUB = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, club.getIdUser());
            pst.setString(2, club.getNom());
            pst.setInt(3, club.getIdClub());
            int affectedRows = pst.executeUpdate();
            if (affectedRows > 0) {
                return club;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
