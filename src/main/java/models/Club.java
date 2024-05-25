package models;

public class Club {
    private int idClub;
    private int idUser;
    private String nom;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Club() {
    }

    public Club(int idClub, int idUser, String nom) {
        this.idClub = idClub;
        this.idUser = idUser;
        this.nom = nom;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Club{" + "idClub=" + idClub + ", idUser=" + idUser + ", nom='" + nom + '\'' + '}';
    }
}

