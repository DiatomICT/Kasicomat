package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Plan;

import java.lang.ref.WeakReference;

public class InsertPlanAsyncTask extends AsyncTask<Plan, Void, Long> {
    private WeakReference<Activity> weakActivity;

    public InsertPlanAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Long doInBackground(Plan... plans) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).planDao().insert(plans[0]);
    }
}
