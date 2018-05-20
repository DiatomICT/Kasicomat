package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;

import java.util.List;

public class KorisnikPlan {
    @Embedded
    public Korisnik korisnik;

    @Relation(parentColumn = "id", entityColumn = "korisnik_id")
    public List<Plan> plan;

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public List<Plan> getPlan() {
        return plan;
    }

    public void setPlan(List<Plan> plan) {
        this.plan = plan;
    }

    @Override
    public String toString() {
        return "KorisnikPlan(" + korisnik.toString() + "," + plan.toString() + ")";
    }
}
