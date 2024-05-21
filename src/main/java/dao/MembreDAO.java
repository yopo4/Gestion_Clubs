package dao;

import models.Membre;

import java.util.List;

public interface MembreDAO {
    List<Membre> getAllMembres();
    Membre getMembreById(int idMembre);
    boolean createMembre(Membre membre);
    Membre updateMembre(Membre membre);
    Membre getMembreByUserId(int idUser);
}
