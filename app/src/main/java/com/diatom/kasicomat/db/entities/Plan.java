package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_plan", foreignKeys = {
        @ForeignKey(entity = Rezim.class, parentColumns = "id", childColumns = "rezim_id"),
        @ForeignKey(entity = PeriodStednje.class, parentColumns = "id", childColumns = "period_stednje_id")
})
public class Plan {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "naziv_proizvoda")
    private String nazivProizvoda;

    @ColumnInfo(name = "cena")
    private int cena;

    @ColumnInfo(name = "sakupljeno")
    private int sakupljeno;

    @ColumnInfo(name = "rezim_id")
    private int rezimId;

    @ColumnInfo(name = "period_stednje_id")
    private int periodStednjeId;

    public Plan() {

    }

    public Plan(String nazivProizvoda, int cena, int sakupljeno, int rezimId, int periodStednjeId) {
        this.nazivProizvoda = nazivProizvoda;
        this.cena = cena;
        this.sakupljeno = sakupljeno;
        this.rezimId = rezimId;
        this.periodStednjeId = periodStednjeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazivProizvoda() {
        return nazivProizvoda;
    }

    public void setNazivProizvoda(String nazivProizvoda) {
        this.nazivProizvoda = nazivProizvoda;
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
}
