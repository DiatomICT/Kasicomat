package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_korisnik_plan")
public class KorisnikPlan {
    @PrimaryKey
    @ColumnInfo(name = "korisnik_id")
    private long korisnikId;

    @ColumnInfo(name = "plan_id")
    private long planId;



    public KorisnikPlan() {

    }

    public KorisnikPlan(long korisnikId, long planId) {
        this.korisnikId = korisnikId;
        this.planId = planId;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public long getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(long korisnikId) {
        this.korisnikId = korisnikId;
    }

    @Override
    public String toString() {
        return "KorisnikPlan(" + korisnikId + "," + planId + ")";
    }
}
