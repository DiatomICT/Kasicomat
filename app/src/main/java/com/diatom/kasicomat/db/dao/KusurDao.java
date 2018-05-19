package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.diatom.kasicomat.db.entities.Kusur;

@Dao
public interface KusurDao {
    @Insert
    long[] insert(Kusur... kusurs);

    @Insert
    long insert(Kusur kusur);
}
