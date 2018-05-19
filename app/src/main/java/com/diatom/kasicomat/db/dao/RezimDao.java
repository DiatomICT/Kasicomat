package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.Rezim;

import java.util.List;

@Dao
public interface RezimDao {
    @Insert
    long insert(Rezim rezim);

    @Insert
    long[] insert(Rezim... rezims);

    @Query("SELECT * FROM tbl_rezim")
    List<Rezim> getAllRezims();
}
