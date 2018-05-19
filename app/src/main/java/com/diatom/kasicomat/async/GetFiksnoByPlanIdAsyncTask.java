package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Fiksno;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetFiksnoByPlanIdAsyncTask extends AsyncTask<Long, Void, List<Fiksno>> {
    private WeakReference<Activity> weakActivity;

    public GetFiksnoByPlanIdAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Fiksno> doInBackground(Long... ids) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).fiksnoDao().findKusursForPlanId(ids[0]);
    }
}
