package com.diatom.kasicomat.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.diatom.kasicomat.db.dao.FiksnoDao;
import com.diatom.kasicomat.db.dao.KategorijaDao;
import com.diatom.kasicomat.db.dao.KorisnikDao;
import com.diatom.kasicomat.db.dao.KorisnikPlanDao;
import com.diatom.kasicomat.db.dao.KusurDao;
import com.diatom.kasicomat.db.dao.PeriodStednjeDao;
import com.diatom.kasicomat.db.dao.PlanDao;
import com.diatom.kasicomat.db.dao.RezimDao;
import com.diatom.kasicomat.db.entities.Fiksno;
import com.diatom.kasicomat.db.entities.Kategorija;
import com.diatom.kasicomat.db.entities.Korisnik;
import com.diatom.kasicomat.db.entities.KorisnikPlan;
import com.diatom.kasicomat.db.entities.Kusur;
import com.diatom.kasicomat.db.entities.PeriodStednje;
import com.diatom.kasicomat.db.entities.Plan;
import com.diatom.kasicomat.db.entities.Rezim;

import java.util.concurrent.Executors;

@Database(entities = {Plan.class, Rezim.class, PeriodStednje.class, Kusur.class, Fiksno.class, Kategorija.class, Korisnik.class}, version = 1)
public abstract class KasicomatDatabase extends RoomDatabase {
    private static KasicomatDatabase instance;

    public static KasicomatDatabase getInstance(final Context context) {
        if (instance == null) {
            synchronized (KasicomatDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), KasicomatDatabase.class, "kasicomat_database")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            getInstance(context).rezimDao().insert(Rezim.prepopulate());
                                            getInstance(context).periodStednjeDao().insert(PeriodStednje.prepopulate(context));
                                            getInstance(context).kategorijaDao().insert(Kategorija.prepopulate(context));
                                        }
                                    });
                                }
                            })
                            .build();

                }
            }
        }
        return instance;
    }

    public abstract RezimDao rezimDao();

    public abstract PeriodStednjeDao periodStednjeDao();

    public abstract KusurDao kusurDao();

    public abstract FiksnoDao fiksnoDao();

    public abstract PlanDao planDao();

    public abstract KategorijaDao kategorijaDao();

    public abstract KorisnikDao korisnikDao();

    public abstract KorisnikPlanDao korisnikPlanDao();
}
