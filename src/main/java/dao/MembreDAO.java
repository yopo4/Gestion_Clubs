package dao;

import models.Membre;

import java.util.List;

public interface MembreDAO {
    List<Membre> getAllMembres();
    Membre getMembreById();
    boolean createMembre();
    Membre updateMembre();
}
