package models;

public class Club {
    private int idClub;
    private int idUser; // Changed from Integer to int
    private String nom;

    // Default constructor
    public Club() {
    }

    // Parameterized constructor
    public Club(int idClub, int idUser, String nom) {
        this.idClub = idClub;
        this.idUser = idUser;
        this.nom = nom;
    }

    // Getters and Setters
    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // toString method
    @Override
    public String toString() {
        return "Club{" +
                "idClub=" + idClub +
                ", idUser=" + idUser +
                ", nom='" + nom + '\'' +
                '}';
    }

}

