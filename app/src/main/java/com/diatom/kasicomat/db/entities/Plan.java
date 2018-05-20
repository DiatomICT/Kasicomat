package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_plan", foreignKeys = {
        @ForeignKey(entity = Rezim.class, parentColumns = "id", childColumns = "rezim_id"),
        @ForeignKey(entity = PeriodStednje.class, parentColumns = "id", childColumns = "period_stednje_id"),
        @ForeignKey(entity = Kategorija.class, parentColumns = "id", childColumns = "kategorija_id")
})
public class Plan {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "brend")
    private String brend;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "cena")
    private int cena;

    @ColumnInfo(name = "sakupljeno")
    private int sakupljeno;

    @ColumnInfo(name = "rezim_id")
    private int rezimId;

    @ColumnInfo(name = "period_stednje_id")
    private int periodStednjeId;

    @ColumnInfo(name = "kategorija_id")
    private int kategorijaId;

    @ColumnInfo(name = "korisnik_id")
    private long korisnikId;

    public Plan() {

    }

    public Plan(String brend, String model, int cena, int sakupljeno, int rezimId, int periodStednjeId, int kategorijaId, long korisnikId) {
        this.brend = brend;
        this.model = model;
        this.cena = cena;
        this.sakupljeno = sakupljeno;
        this.rezimId = rezimId;
        this.periodStednjeId = periodStednjeId;
        this.kategorijaId = kategorijaId;
        this.korisnikId = korisnikId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getSakupljeno() {
        return sakupljeno;
    }

    public void setSakupljeno(int sakupljeno) {
        this.sakupljeno = sakupljeno;
    }

    public int getRezimId() {
        return rezimId;
    }

    public void setRezimId(int rezimId) {
        this.rezimId = rezimId;
    }

    public int getPeriodStednjeId() {
        return periodStednjeId;
    }

    public void setPeriodStednjeId(int periodStednjeId) {
        this.periodStednjeId = periodStednjeId;
    }

    public int getKategorijaId() {
        return kategorijaId;
    }

    public void setKategorijaId(int kategorijaId) {
        this.kategorijaId = kategorijaId;
    }

    public long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(long korisnikId) {
        this.korisnikId = korisnikId;
    }
}
