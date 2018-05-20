package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.KorisnikPlan;
import com.diatom.kasicomat.util.DummyGenerator;

import java.lang.ref.WeakReference;

public class InsertKorisnikPlanAsyncTask extends AsyncTask<KorisnikPlan, Void, Void> {
    private WeakReference<Activity> weakActivity;

    public InsertKorisnikPlanAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Void doInBackground(KorisnikPlan... korisnikPlans) {
//        KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).korisnikPlanDao().insert(korisnikPlans);
        return null;
    }
}
