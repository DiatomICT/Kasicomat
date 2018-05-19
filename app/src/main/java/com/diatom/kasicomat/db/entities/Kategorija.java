package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.diatom.kasicomat.R;

@Entity(tableName = "tbl_kategorija")
public class Kategorija {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "kategorija")
    private String kategorija;

    public Kategorija() {

    }

    public Kategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public static Kategorija[] prepopulate(Context context) {
        String[] periodStednjeArray = context.getResources().getStringArray(R.array.kategorija_arrays);

        Kategorija[] res = new Kategorija[periodStednjeArray.length];
        for (int i = 0; i < periodStednjeArray.length; i++) {
            res[i] = new Kategorija(periodStednjeArray[i]);
        }
        return res;
    }
}
