package com.example.mm;

import android.app.Application;

public class Feladat extends Application {

    String feladatID;
    String allapot;
    String datum;
    String eszkozID;
    String idotartam;
    String indoklas;
    String instrukcio;
    String prioritas;
    String problema;
    String szolgaID;
    String tipus;

    public Feladat() {
    }

    public Feladat(String feladatID, String allapot, String datum, String eszkozID, String idotartam, String indoklas, String instrukcio, String prioritas, String problema, String szolgaID, String tipus) {
        this.feladatID = feladatID;
        this.allapot = allapot;
        this.datum = datum;
        this.eszkozID = eszkozID;
        this.idotartam = idotartam;
        this.indoklas = indoklas;
        this.instrukcio = instrukcio;
        this.prioritas = prioritas;
        this.problema = problema;
        this.szolgaID = szolgaID;
        this.tipus = tipus;
    }

    public Feladat(String feladatID, String szolgaID, String allapot) {
        this.feladatID = feladatID;
        this.szolgaID = szolgaID;
        this.allapot = allapot;
    }

    public String getFeladatID() { return feladatID; }

    public void setFeladatID(String feladatID) { this.feladatID = feladatID; }

    public String getAllapot() { return allapot; }

    public void setAllapot(String allapot) {
        this.allapot = allapot;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getEszkozID() {
        return eszkozID;
    }

    public void setEszkozID(String eszkozID) {
        this.eszkozID = eszkozID;
    }

    public String getIdotartam() {
        return idotartam;
    }

    public void setIdotartam(String idotartam) {
        this.idotartam = idotartam;
    }

    public String getIndoklas() {
        return indoklas;
    }

    public void setIndoklas(String indoklas) {
        this.indoklas = indoklas;
    }

    public String getInstrukcio() {
        return instrukcio;
    }

    public void setInstrukcio(String instrukcio) {
        this.instrukcio = instrukcio;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getSzolgaID() {
        return szolgaID;
    }

    public void setSzolgaID(String szolgaID) {
        this.szolgaID = szolgaID;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        return "Feladat: "+feladatID+", Szolga: "+szolgaID+", Állapot: "+allapot;
    }
}
