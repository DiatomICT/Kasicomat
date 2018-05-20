package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

@Entity(tableName = "tbl_korisnik")
public class Korisnik {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "ime")
    private String ime;

    public Korisnik() {

    }

    public Korisnik(String ime) {
        this.ime = ime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public static Korisnik[] prepopulate(Context context) {
        return new Korisnik[]{
                new Korisnik("DIATOM")
        };
    }

    @Override
    public String toString() {
        return "Korisnik(" + ime + ")";
    }
}
