package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.diatom.kasicomat.db.entities.Korisnik;

@Dao
public interface KorisnikDao {
    @Insert
    void insert(Korisnik korisnik);

    @Insert
    long[] insert(Korisnik... korisniks);
}
