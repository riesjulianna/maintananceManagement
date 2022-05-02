package com.example.mm;

import android.app.Application;

public class User extends Application {

    String id,felhnev,jelszo,beosztas,nev,szakma;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFelhnev() {
        return felhnev;
    }

    public void setFelhnev(String felhnev) {
        this.felhnev = felhnev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getBeosztas() {
        return beosztas;
    }

    public void setBeosztas(String beosztas) {
        this.beosztas = beosztas;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getSzakma() {
        return szakma;
    }

    public void setSzakma(String szakma) {
        this.szakma = szakma;
    }

    public User(String id, String felhnev, String jelszo, String beosztas, String nev, String szakma) {
        this.id = id;
        this.felhnev = felhnev;
        this.jelszo = jelszo;
        this.beosztas = beosztas;
        this.nev = nev;
        this.szakma = szakma;
    }
}
