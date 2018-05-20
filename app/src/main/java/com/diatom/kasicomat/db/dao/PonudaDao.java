package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.Ponuda;

import java.util.List;

@Dao
public interface PonudaDao {
    @Insert
    void insert(Ponuda ponuda);

    @Query("SELECT * FROM tbl_ponuda WHERE korisnik_name=:korisnikName")
    List<Ponuda> findPonudaByKorisnikId(String korisnikName);
}
