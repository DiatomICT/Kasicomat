package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.NivoiStednjeFiksnoActivity;
import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Plan;

import java.lang.ref.WeakReference;

public class UpdatePlanAsyncTask extends AsyncTask<Plan, Void, Void> {
    private WeakReference<Activity> weakActivity;

    public UpdatePlanAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Void doInBackground(Plan... plans) {
        KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).planDao().update(plans[0]);
        return null;
    }
}
