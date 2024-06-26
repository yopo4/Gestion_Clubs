package dao;

import models.Club;
import models.Membre;

import java.util.List;

public interface MembreDAO {
    List<Membre> getAllMembres();
    Membre getMembreById(int idMembre);
    boolean createMembre(Membre membre);
    Membre updateMembre(Membre membre);
    Membre getMembreByUserId(int idUser);
    boolean isGerant(Membre membre);
    List<Membre> getMembersOfClub(Club club,boolean accepted);

    List<Membre> findUsersWithoutRoleGerant();
    boolean updateUserRole(Membre membre, Club club, boolean role);
    boolean createMembreAndInsertItInIntegrer(Membre membre, Club club);
    boolean updateAcceptation(Membre membre, Club club, boolean isAccepted);
    Membre getClubManager(Club club);
    void deleteMemberFromClub(Membre membre,Club club);
    int getLastId();
}
