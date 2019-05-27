package com.example.demo.Models;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
@EntityScan
public class Bestilling {

    private int bestillingsID;
    private int brugerID;
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

    public Bestilling() {
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
