package com.example.templo;

import java.io.Serializable;

public class Employe implements Serializable {
    private String nom;
    private String prenom;
    private String cin;
    private String adresse;
    private String datenaissance;
    private String telephone;
    private String station;
    private Boolean status;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Employe() {
    }

    public Employe(String nom, String prenom, String cin, String adresse, String datenaissance, String telephone, String station, Boolean status) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.adresse = adresse;
        this.datenaissance = datenaissance;
        this.telephone = telephone;
        this.station = station;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public  String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public  String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public  String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public  String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(String datenaissance) {
        this.datenaissance = datenaissance;
    }

    public  String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
