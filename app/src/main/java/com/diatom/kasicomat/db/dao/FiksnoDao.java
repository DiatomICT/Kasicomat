package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.diatom.kasicomat.db.entities.Fiksno;

@Dao
public interface FiksnoDao {
    @Insert
    long insert(Fiksno fiksno);
}
