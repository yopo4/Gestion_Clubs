//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package models;

public class Membre {
    private int idMembre;
    private String nom;

    public Membre() {
    }

    public int getIdMembre() {
        return this.idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String toString() {
        return "Membre{idMembre=" + this.idMembre + ", nom='" + this.nom + "'}";
    }
}
