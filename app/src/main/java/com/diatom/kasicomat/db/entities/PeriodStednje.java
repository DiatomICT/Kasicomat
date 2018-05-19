package com.diatom.kasicomat.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;

import com.diatom.kasicomat.R;

@Entity(tableName = "tbl_period_stednje")
public class PeriodStednje {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "trajanje_u_mesecima")
    private int trajanjeUMesecima;

    public PeriodStednje() {

    }

    public PeriodStednje(int trajanjeUMesecima) {
        this.trajanjeUMesecima = trajanjeUMesecima;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTrajanjeUMesecima() {
        return trajanjeUMesecima;
    }

    public void setTrajanjeUMesecima(int trajanjeUMesecima) {
        this.trajanjeUMesecima = trajanjeUMesecima;
    }

    public static PeriodStednje[] prepopulate(Context context) {
        String[] periodStednjeArray = context.getResources().getStringArray(R.array.period_stednje_arrays);

        return new PeriodStednje[]{
            new PeriodStednje(Integer.parseInt(periodStednjeArray[0])),
            new PeriodStednje(Integer.parseInt(periodStednjeArray[1])),
            new PeriodStednje(Integer.parseInt(periodStednjeArray[2])),
            new PeriodStednje(Integer.parseInt(periodStednjeArray[3])),
        };
    }
}
