package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Plan;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetPlanAsyncTask extends AsyncTask<Void, Void, List<Plan>> {
    private WeakReference<Activity> weakActivity;

    public GetPlanAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Plan> doInBackground(Void... voids) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).planDao().getAllPlans();
    }
}
