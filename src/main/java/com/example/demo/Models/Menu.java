package com.example.demo.Models;

public class Menu {

    public int vareID;
    public String vareNavn;
    public int varePris;

    public Menu(int vareID, String vareNavn, int itemPris) {
        this.vareID = vareID;
        this.vareNavn = vareNavn;
        this.varePris = itemPris;
    }

    public int getVareID() {
        return vareID;
    }

    public void setVareID(int vareID) {
        this.vareID = vareID;
    }

    public String getVareNavn() {
        return vareNavn;
    }

    public void setVareNavn(String vareNavn) {
        this.vareNavn = vareNavn;
    }

    public int getVarePris() {
        return varePris;
    }

    public void setVarePris(int varePris) {
        this.varePris = varePris;
    }
}
