package dao;

import models.Club;

import java.util.List;

public interface ClubDAO {
    List<Club> getAllClubs();
    Club getClubById(int clubId);
    boolean createClub(Club club);
    List<Club> getTopClubs(int topNumber);
    Club updateClub(Club club);
    String getGerantNameById(int idUser);
}
