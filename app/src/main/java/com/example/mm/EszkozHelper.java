package com.example.mm;

public class EszkozHelper {
    String kategoria, nev, helyszin, idotartam, instrukcio, problema, utolso;

    public EszkozHelper() {
    }

    public EszkozHelper(String kategoria, String nev, String helyszin) {
        this.kategoria = kategoria;
        this.nev = nev;
        this.helyszin = helyszin;
    }

    public EszkozHelper(String idotartam, String instrukcio, String problema, String utolso) {
        this.idotartam = idotartam;
        this.instrukcio = instrukcio;
        this.problema = problema;
        this.utolso = utolso;
    }

    public String getIdotartam() {
        return idotartam;
    }

    public void setIdotartam(String idotartam) {
        this.idotartam = idotartam;
    }

    public String getInstrukcio() {
        return instrukcio;
    }

    public void setInstrukcio(String instrukcio) {
        this.instrukcio = instrukcio;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
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
