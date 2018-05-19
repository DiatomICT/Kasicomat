package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.diatom.kasicomat.db.entities.PeriodStednje;

@Dao
public interface PeriodStednjeDao {
    @Insert
    long insert(PeriodStednje periodStednje);

    @Insert
    long insert(PeriodStednje... periodStednjes);
}
