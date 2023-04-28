package com.epf.rentmanager.model;
public class Vehicle {

    private long ID;
    private String constructeur, modele;
    private int nb_places;
    public Vehicle(){
    }

    public Vehicle(long ID, String constructeur, String modele,  int nb_places) {
        this.ID= ID;
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_places = nb_places;
    }

    public Vehicle(String constructeur, String modele, int nb_places) {
        this.constructeur = constructeur;
        this.modele = modele;
        this.nb_places = nb_places;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public String getModele() {
        return modele;
    }
    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public int getNb_places() {
        return nb_places;
    }

    public void setNb_places(int nb_places) {
        this.nb_places = nb_places;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "ID=" + ID +
                ", constructeur='" + constructeur + '\'' +
                ", nb_places=" + nb_places +
                '}';
    }
}
