package models;

public class Club {
    private int idClub;
    private String nom;



    public Club() {
    }

    public Club(int idClub, String nom) {
        this.idClub = idClub;
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

    @Override
    public String toString() {
        return "Club{" +
                "idClub=" + idClub +
                ", nom='" + nom + '\'' +
                '}';
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}

