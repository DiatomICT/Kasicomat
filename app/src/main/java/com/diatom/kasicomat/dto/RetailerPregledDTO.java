package com.diatom.kasicomat.dto;

public class RetailerPregledDTO {
    private String korisnik;
    private String proizvod;
    private int procenatSkupljenog;

    public RetailerPregledDTO() {

    }

    public RetailerPregledDTO(String korisnik, String proizvod, int procenatSkupljenog) {
        this.korisnik = korisnik;
        this.proizvod = proizvod;
        this.procenatSkupljenog = procenatSkupljenog;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    public String getProizvod() {
        return proizvod;
    }

    public void setProizvod(String proizvod) {
        this.proizvod = proizvod;
    }

    public int getProcenatSkupljenog() {
        return procenatSkupljenog;
    }

    public void setProcenatSkupljenog(int procenatSkupljenog) {
        this.procenatSkupljenog = procenatSkupljenog;
    }
}
