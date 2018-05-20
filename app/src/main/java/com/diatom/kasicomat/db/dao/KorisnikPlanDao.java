package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.KorisnikPlan;

import java.util.List;

@Dao
public interface KorisnikPlanDao {
//    @Query("SELECT tbl_korisnik.*, tbl_plan.* FROM tbl_korisnik INNER JOIN tbl_plan ON tbl_korisnik.plan_id=tbl_plan.id")
//    List<KorisnikPlan> getAllKorisnikPlans();

    @Query("SELECT * FROM tbl_korisnik")
    List<KorisnikPlan> getAllKorisnikPlans();
}
