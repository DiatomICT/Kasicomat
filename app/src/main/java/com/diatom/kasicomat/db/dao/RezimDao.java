package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.diatom.kasicomat.db.entities.Rezim;

@Dao
public interface RezimDao {
    @Insert
    long insert(Rezim rezim);

    @Insert
    long[] insert(Rezim... rezims);
}
