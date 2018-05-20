package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Plan;

import java.lang.ref.WeakReference;

public class GetPlanByIdAsyncTask extends AsyncTask<Long, Void, Plan> {
    private WeakReference<Activity> weakActivity;

    public GetPlanByIdAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Plan doInBackground(Long... longs) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).planDao().getPlanById(longs[0]).get(0);
    }
}
