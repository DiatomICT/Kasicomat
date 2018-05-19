package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.PeriodStednje;

import java.util.List;

@Dao
public interface PeriodStednjeDao {
    @Insert
    long insert(PeriodStednje periodStednje);

    @Insert
    long[] insert(PeriodStednje... periodStednjes);

    @Query("SELECT * FROM tbl_period_stednje")
    List<PeriodStednje> getAllPeriodStednjes();
}
