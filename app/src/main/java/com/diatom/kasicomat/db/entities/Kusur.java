package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_kusur")
@ForeignKey(entity = Plan.class, parentColumns = "id", childColumns = "plan_id")
public class Kusur {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "min_iznos")
    private int minIznos;

    @ColumnInfo(name = "max_iznos")
    private int maxIznos;

    @ColumnInfo(name = "zaokruzi_na")
    private int zaokruziNa;

    @ColumnInfo(name = "plan_id")
    private int planId;

    public Kusur() {

    }

    public Kusur(int minIznos, int maxIznos, int zaokruziNa, int planId) {
        this.minIznos = minIznos;
        this.maxIznos = maxIznos;
        this.zaokruziNa = zaokruziNa;
        this.planId = planId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMinIznos() {
        return minIznos;
    }

    public void setMinIznos(int minIznos) {
        this.minIznos = minIznos;
    }

    public int getMaxIznos() {
        return maxIznos;
    }

    public void setMaxIznos(int maxIznos) {
        this.maxIznos = maxIznos;
    }

    public int getZaokruziNa() {
        return zaokruziNa;
    }

    public void setZaokruziNa(int zaokruziNa) {
        this.zaokruziNa = zaokruziNa;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    @Override
    public String toString() {
        return "Kusur(" + minIznos + "," + maxIznos + "," + zaokruziNa + ")";
    }
}
