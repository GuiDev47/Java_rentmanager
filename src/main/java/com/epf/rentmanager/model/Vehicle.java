package com.epf.rentmanager.model;
public class Vehicle {

    private long ID;
    private String constructeur;
    private int nb_places;
    public Vehicle(){
    }

    public Vehicle(long ID, String constructeur, int nb_places) {
        this.ID= ID;
        this.constructeur = constructeur;
        this.nb_places = nb_places;
    }

    public Vehicle(String constructeur, int nb_places) {
        this.constructeur = constructeur;
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
