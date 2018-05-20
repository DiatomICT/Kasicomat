package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.Transakcija;

import java.util.List;

@Dao
public interface TransakcijaDao {
    @Insert
    void insert(Transakcija transakcija);

    @Insert
    void insert(Transakcija... transakcije);

    @Query("SELECT * FROM tbl_transakcija")
    List<Transakcija> getAllTransakcijas();
}
