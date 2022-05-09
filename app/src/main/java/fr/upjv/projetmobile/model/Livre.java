package fr.upjv.projetmobile.model;

public class Livre {
    private String auteur;
    private String titre;

    public Livre(String auteur, String titre) {
        this.auteur = auteur;
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "auteur='" + auteur + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }
}
