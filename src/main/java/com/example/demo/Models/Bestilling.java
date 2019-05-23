package com.example.demo.Models;

public class Bestilling {

    private int bestillingsID;
    private int brugerID;
    private int itemID;

    public Bestilling(int brugerID, int itemID) {
        this.brugerID = brugerID;
        this.itemID = itemID;
    }

    public int getBrugerID() {
        return brugerID;
    }

    public void setBrugerID(int brugerID) {
        this.brugerID = brugerID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
