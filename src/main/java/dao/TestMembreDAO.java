package dao;

import java.util.List;

import models.Membre;

public class TestMembreDAO {
    public static void main(String[] args) {
//        testGetAllMembres();
//        testGetMembreById();
//        testCreateMembre();
//        testUpdateMembre();
//        testGetMembreByUserId();
        testIsGerant();
    }
    public static void testIsGerant(){
        MembreDAO membreDAO = new MembreDAOImp();
        Membre membre = new Membre();
        membre.setIdMembre(1);
        if(membreDAO.isGerant(membre)){
            System.out.println("Le membre est un gerant");
        }else{
            System.out.println("Le membre n'est pas un gerant");
        }
    }

    public static void testGetAllMembres() {
        MembreDAO membreDAO = new MembreDAOImp();
        List<Membre> membres = membreDAO.getAllMembres();
        System.out.println("All Membres:");
        for (Membre membre : membres) {
            System.out.println(membre);
        }
    }

    public static void testGetMembreById() {
        MembreDAO membreDAO = new MembreDAOImp();
        int id = 1; // Replace with an existing member ID
        Membre membre = membreDAO.getMembreById(id);
        System.out.println("Membre with ID " + id + ": " + membre);
    }

    public static void testCreateMembre() {
        MembreDAO membreDAO = new MembreDAOImp();
        Membre newMembre = new Membre();
        newMembre.setNom("New Membre");
        boolean success = membreDAO.createMembre(newMembre);
        System.out.println("Membre created successfully: " + success);
    }

    public static void testUpdateMembre() {
        MembreDAO membreDAO = new MembreDAOImp();
        Membre existingMembre = membreDAO.getMembreById(1); // Replace with an existing member ID
        existingMembre.setNom("Updated Membre Name");
        Membre updatedMembre = membreDAO.updateMembre(existingMembre);
        System.out.println("Membre updated: " + updatedMembre);
    }


    public static void testGetMembreByUserId() {
        MembreDAO membreDAO = new MembreDAOImp();
        int userId = 2; // Replace with an existing user ID
        Membre membre = membreDAO.getMembreByUserId(userId);
        System.out.println("Membre with User ID " + userId + ": " + membre);
    }
}
