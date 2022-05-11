package model;

import java.util.List;

public class Matiere {
    private int id;
    private String libelle;
    private List<Formateur> formateurs;

    public Matiere(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Matiere() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<Formateur> getFormateurs() {
        return formateurs;
    }

    public void setFormateurs(List<Formateur> formateurs) {
        this.formateurs = formateurs;
    }

    @Override
    public String toString() {
        return libelle;
    }
}
