package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.KorisnikPlan;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetKorisnikPlanAsyncTask extends AsyncTask<Void, Void, List<KorisnikPlan>> {
    private WeakReference<Activity> weakActivity;

    public GetKorisnikPlanAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<KorisnikPlan> doInBackground(Void... voids) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).korisnikPlanDao().getAllKorisnikPlans();
    }
}
