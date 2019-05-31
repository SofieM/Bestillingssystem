package com.example.demo.Models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;

//lavet af Sofie og Christine
@EntityScan
public class Bestilling {

    private int bestillingsID;
    private int brugerID;
    private String brugerFornavn;
    private String brugerEfternavn;
    private int brugerTelefon;
    private String brugerEmail;
    private String bestilling;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dato;
    @DateTimeFormat (pattern = "hh:mm")
    private String klokkeslet;
    private String status;


    public Bestilling(int brugerID, String bestilling, String dato, String klokkeslet) {

        this.brugerID = brugerID;
        this.bestilling = bestilling;
        this.dato = dato;
        this.klokkeslet=klokkeslet;
    }

    public Bestilling(int bestillingsID, int brugerID, String bestilling, String dato, String klokkeslet) {
        this.bestillingsID = bestillingsID;
        this.brugerID = brugerID;
        this.bestilling = bestilling;
        this.dato = dato;
        this.klokkeslet = klokkeslet;
    }

    public Bestilling (String bestilling, String dato, String klokkeslet){
        this.bestilling=bestilling;
        this.dato=dato;
        this.klokkeslet = klokkeslet;
    }

    public Bestilling() {
    }

    public Bestilling(int bestillingsID, String bestilling, String dato, String klokkeslet, String status, String brugerFornavn, String brugerEfternavn, int brugerTelefon, String brugerEmail) {
        this.bestillingsID = bestillingsID;
        this.bestilling = bestilling;
        this.dato = dato;
        this.klokkeslet = klokkeslet;
        this.status = status;
        this.brugerFornavn = brugerFornavn;
        this.brugerEfternavn = brugerEfternavn;
        this.brugerTelefon = brugerTelefon;
        this.brugerEmail = brugerEmail;

    }

    public String getKlokkeslet() {
        return klokkeslet;
    }

    public void setKlokkeslet(String klokkeslet) {
        this.klokkeslet = klokkeslet;
    }

    public int getBestillingsID() {
        return bestillingsID;
    }

    public void setBestillingsID(int bestillingsID) {
        this.bestillingsID = bestillingsID;
    }

    public String getBrugerFornavn() {
        return brugerFornavn;
    }

    public void setBrugerFornavn(String brugerFornavn) {
        this.brugerFornavn = brugerFornavn;
    }

    public String getBrugerEfternavn() {
        return brugerEfternavn;
    }

    public void setBrugerEfternavn(String brugerEfternavn) {
        this.brugerEfternavn = brugerEfternavn;
    }

    public int getBrugerTelefon() {
        return brugerTelefon;
    }

    public void setBrugerTelefon(int brugerTelefon) {
        this.brugerTelefon = brugerTelefon;
    }

    public String getBrugerEmail() {
        return brugerEmail;
    }

    public void setBrugerEmail(String brugerEmail) {
        this.brugerEmail = brugerEmail;
    }

    public String getBestilling() {
        return bestilling;
    }

    public void setBestilling(String bestilling) {
        this.bestilling = bestilling;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
