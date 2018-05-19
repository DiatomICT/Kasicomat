package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_fiksno")
public class Fiksno {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "kolicina")
    private int kolicina;

    public Fiksno() {

    }

    public Fiksno(int kolicina) {
        this.kolicina = kolicina;
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
}
