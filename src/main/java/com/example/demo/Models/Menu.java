package com.example.demo.Models;

public class Menu {

    public int itemID;
    public String itemNavn;
    public int itemPris;

    public Menu(String itemNavn, int itemPris) {
        this.itemNavn = itemNavn;
        this.itemPris = itemPris;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemNavn() {
        return itemNavn;
    }

    public void setItemNavn(String itemNavn) {
        this.itemNavn = itemNavn;
    }

    public int getItemPris() {
        return itemPris;
    }

    public void setItemPris(int itemPris) {
        this.itemPris = itemPris;
    }
}
