package models;

public class Membre {
    private int idMembre;

    //added equals for List.remove to work by idMembre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membre membre = (Membre) o;
        return idMembre == membre.idMembre;
    }

    private String nom;

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Membre{" +
                "idMembre=" + idMembre +
                ", nom='" + nom + '\'' +
                '}';
    }
}

