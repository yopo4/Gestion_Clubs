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
    //waiting for manager to accept as an official member
    boolean addMembreToClubTemp(Club club, Membre membre);
    List<Club> getClubsOfMember(int id_member);
    List<Club> getClubsOfManager(Membre membre);
    boolean userIsMemberOfClub(User user, Club club);
    void acceptMemberInClub(Membre membre,Club club);
    Club getClubByName(String clubName);
    int getNumberOfRequests(Club club);
    //check if the member is the manager of the club
    boolean isGerantOfClub(Membre membre, Club club);
}
