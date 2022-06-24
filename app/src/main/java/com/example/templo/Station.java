package com.example.templo;

public class Station {
    String nom;
    String status;

    public Station() {
    }

    public Station(String nom, String status) {
        this.nom = nom;
        this.status = status;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
