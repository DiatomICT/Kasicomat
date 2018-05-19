package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_fiksno")
@ForeignKey(entity = Plan.class, parentColumns = "id", childColumns = "plan_id")
public class Fiksno {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "kolicina")
    private int kolicina;

    @ColumnInfo(name = "plan_id")
    private int planId;

    public Fiksno() {

    }

    public Fiksno(int kolicina, int planId) {
        this.kolicina = kolicina;
        this.planId = planId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "Fiksno(" + kolicina + ")";
    }
}
