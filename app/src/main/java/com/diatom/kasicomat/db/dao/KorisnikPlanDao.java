package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.KorisnikPlan;

import java.util.List;

@Dao
public interface KorisnikPlanDao {
    @Insert
    void insert(KorisnikPlan korisnikPlan);

    @Insert
    void insert(KorisnikPlan... korisnikPlans);

    @Query("SELECT * FROM tbl_korisnik_plan")
    List<KorisnikPlan> getAllKorisnikPlans();
}
