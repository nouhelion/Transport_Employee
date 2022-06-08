package com.example.templo;

public class Station {
    String nom;
    Boolean status;

    public Station() {
    }

    public Station(String nom, Boolean status) {
        this.nom = nom;
        this.status = status;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
