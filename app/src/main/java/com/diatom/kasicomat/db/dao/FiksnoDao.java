package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.Fiksno;

import java.util.List;

@Dao
public interface FiksnoDao {
    @Insert
    long insert(Fiksno fiksno);

    @Query("SELECT * FROM tbl_fiksno WHERE plan_id=:planId")
    List<Fiksno> findKusursForPlanId(final long planId);
}
