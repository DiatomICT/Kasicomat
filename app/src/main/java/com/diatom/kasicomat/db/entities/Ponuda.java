package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tbl_ponuda")
public class Ponuda {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "retailer")
    private String retailer;

    @ColumnInfo(name = "korisnik_name")
    private String korisnikName;

    public Ponuda() {

    }

    public Ponuda(String retailer, String korisnikName) {
        this.retailer = retailer;
        this.korisnikName = korisnikName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getKorisnikName() {
        return korisnikName;
    }

    public void setKorisnikName(String korisnikName) {
        this.korisnikName = korisnikName;
    }
}
