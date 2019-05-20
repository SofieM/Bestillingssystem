package com.example.demo.Models;

import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan
public class Bruger {

    private String brugernavn;
    private String password;
    private int brugerID;
    private String fornavn;
    private String efternavn;
    private String adresse;
    private int telefon;
    private String email;

    public Bruger(String brugernavn, String password, String fornavn, String efternavn, String adresse, int telefon, String email) {
        this.brugernavn = brugernavn;
        this.password = password;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.telefon = telefon;
        this.email = email;
    }

    public Bruger(String brugernavn, String password) {
        this.brugernavn = brugernavn;
        this.password = password;
    }

    public Bruger(String fornavn, String efternavn, String adresse, int telefon, String email) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.adresse = adresse;
        this.telefon = telefon;
        this.email = email;
    }

    public Bruger(){

    }
    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
