package dao;

import models.Club;
import models.Membre;
import models.User;

import java.util.List;

public interface ClubDAO {
    List<Club> getAllClubs();
    Club getClubById(int clubId);
    boolean createClub(Club club);
    List<Club> getTopClubs(int topNumber);
    Club updateClub(Club club);
    String getGerantNameById(int idUser);
    int getMembersCountByClubId(int idClub);
    boolean addMembreToClub(Club club, Membre membre);
    List<Club> getClubsOfMember(int id_member);
    List<Club> getClubsOfManager(Membre membre);
    boolean userIsMemberOfClub(User user, Club club);
}
