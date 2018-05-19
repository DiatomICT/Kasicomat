package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.Kusur;

import java.util.List;

@Dao
public interface KusurDao {
    @Insert
    long[] insert(Kusur... kusurs);

    @Insert
    long insert(Kusur kusur);

    @Query("SELECT * FROM tbl_kusur WHERE plan_id=:planId")
    List<Kusur> findKusursForPlanId(final long planId);
}
