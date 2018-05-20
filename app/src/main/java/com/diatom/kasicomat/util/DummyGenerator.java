package com.diatom.kasicomat.util;

import com.diatom.kasicomat.async.InsertPlanAsyncTask;
import com.diatom.kasicomat.db.entities.KorisnikPlan;
import com.diatom.kasicomat.db.entities.Plan;

import java.util.List;

public class DummyGenerator {
    public static Plan[] generisiDummyPlanove(int n) {
        return new Plan[]{
                new Plan("Samsung", "S8", 50000, 40000, 1, 2, 1),
                new Plan("Sony", "Playstation 4", 40000, 15000, 2, 4, 1),
                new Plan("Kingston", "USB 3.0", 3200, 1000, 1, 1, 1),
                new Plan("Gorenje", "Frizider", 24000, 22000, 2, 3, 1),
                new Plan("GeForce", "GTX 1050Ti", 22000, 5000, 1, 2, 1)
        };
    }
    public static KorisnikPlan[] generisiKorisnikPlanove(List<Long> planIds) {
        KorisnikPlan[] korisnikPlans = new KorisnikPlan[planIds.size()];

        for (int i = 0; i < planIds.size(); i++) {
            korisnikPlans[i] = new KorisnikPlan(5000 + i, planIds.get(i));
        }
        return korisnikPlans;
    }
}
