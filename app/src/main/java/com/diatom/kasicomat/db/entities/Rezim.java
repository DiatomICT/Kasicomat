package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_rezim")
public class Rezim {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "tip")
    private String tip;

    public Rezim() {

    }

    public Rezim(String tip) {
        this.tip = tip;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public static Rezim[] prepopulate() {
        return new Rezim[]{
            new Rezim("kusur"),
            new Rezim("fiksno")
        };
    }

    @Override
    public String toString() {
        return "Rezim(" + id + "," + tip + ")";
    }
}
