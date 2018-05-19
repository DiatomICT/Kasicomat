package com.diatom.kasicomat.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.diatom.kasicomat.db.entities.Plan;

import java.util.List;

@Dao
public interface PlanDao {
    @Insert
    long insert(Plan plan);

    @Insert
    long[] insert(Plan... plans);

    @Query("SELECT * FROM tbl_plan")
    List<Plan> getAllPlans();
}
