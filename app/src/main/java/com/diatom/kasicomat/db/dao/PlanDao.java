package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.diatom.kasicomat.db.entities.Plan;

import java.util.List;

@Dao
public interface PlanDao {
    @Insert
    long insert(Plan plan);

    @Insert
    long[] insert(Plan... plans);

    @Update
    void update(Plan plan);

    @Query("SELECT * FROM tbl_plan")
    List<Plan> getAllPlans();

    @Query("SELECT * FROM tbl_plan WHERE kategorija_id=:kategorijaId ORDER BY sakupljeno DESC")
    List<Plan> findPlansByCategory(long kategorijaId);

    @Query("SELECT * FROM tbl_plan WHERE id=:id")
    List<Plan> getPlanById(Long id);
}
