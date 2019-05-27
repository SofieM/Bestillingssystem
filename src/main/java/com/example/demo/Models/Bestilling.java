package com.example.demo.Models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
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
//    private int itemID;
//    private int amount;

//    public Bestilling(int brugerID, int itemID) {
//        this.brugerID = brugerID;
//        this.itemID = itemID;
//    }


    public Bestilling(int brugerID, String bestilling, String dato) {

        this.brugerID = brugerID;
        this.bestilling = bestilling;
        this.dato = dato;
    }

    public Bestilling (String bestilling, String dato){
        this.bestilling=bestilling;
        this.dato=dato;
    }

    public Bestilling() {
    }

    public Bestilling(int bestillingsID, String bestilling, String dato, String brugerFornavn, String brugerEfternavn, int brugerTelefon, String brugerEmail) {
        this.bestillingsID = bestillingsID;
        this.bestilling = bestilling;
        this.dato = dato;
        this.brugerFornavn = brugerFornavn;
        this.brugerEfternavn = brugerEfternavn;
        this.brugerTelefon = brugerTelefon;
        this.brugerEmail = brugerEmail;
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

//    public Bestilling(int itemID, int amount) {
//        this.itemID = itemID;
//        this.amount = amount;
//    }

//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

//    public int getItemID() {
//        return itemID;
//    }
//
//    public void setItemID(int itemID) {
//        this.itemID = itemID;
//    }
}
