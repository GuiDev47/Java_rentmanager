package com.epf.rentmanager.model;


import java.time.LocalDate;

public class Client {

    private long ID;
    private String nom, prenom, email;
    private LocalDate dateNaissance;

    public Client(){
    }

    public Client(long ID, String nom, String prenom, String email, LocalDate dateNaissance) {
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }
    public Client(String nom, String prenom, String email, LocalDate dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public long getID(){
        return this.ID;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    @Override
    public String toString() {
        return "\nClient{" +
                "ID=" + ID +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", dateNaissance=" + dateNaissance +
                "}";
    }
}
