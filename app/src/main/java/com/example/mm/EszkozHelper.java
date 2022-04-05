package com.example.mm;

public class EszkozHelper {
    String kategoria, nev, helyszin, utolso, instrukcio;

    public EszkozHelper() {
    }

    public EszkozHelper(String helyszin, String kategoria, String nev, String utolso, String instrukcio) {
        this.helyszin = helyszin;
        this.kategoria = kategoria;
        this.nev = nev;
        this.utolso = utolso;
        this.instrukcio = instrukcio;
    }

    public String getInstrukcio() {
        return instrukcio;
    }

    public void setInstrukcio(String instrukcio) {
        this.instrukcio = instrukcio;
    }

    public String getUtolso() {
        return utolso;
    }

    public void setUtolso(String utolso) {
        this.utolso = utolso;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getHelyszin() {
        return helyszin;
    }

    public void setHelyszin(String helyszin) {
        this.helyszin = helyszin;
    }
}
