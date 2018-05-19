package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.diatom.kasicomat.db.entities.Kategorija;

@Dao
public interface KategorijaDao {
    @Insert
    long insert(Kategorija kategorija);

    @Insert
    long[] insert(Kategorija... kategorijas);
}
