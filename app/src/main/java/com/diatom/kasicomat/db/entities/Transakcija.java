package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.diatom.kasicomat.db.Converters;

import java.sql.Date;

@TypeConverters(Converters.class)
@Entity(tableName = "tbl_transakcija")
public class Transakcija {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "datum")
    private Date datum;

    public Transakcija() {

    }

    public Transakcija(Date datum) {
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public static Transakcija[] prepopulate() {
        Transakcija[] ret = new Transakcija[450];

        int SAT_U_MS = 60 * 60 * 1000;
        long millis = System.currentTimeMillis();

        for (int i = 0; i < 450; i ++) {
            int sati = (int) (Math.random() * 2 + 4);

            ret[i] = new Transakcija(new Date(millis - sati * SAT_U_MS));
            millis -= sati * SAT_U_MS;
        }

        return ret;
    }
}
