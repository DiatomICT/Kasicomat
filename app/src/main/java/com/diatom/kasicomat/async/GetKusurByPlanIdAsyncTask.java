package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Kusur;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetKusurByPlanIdAsyncTask extends AsyncTask<Long, Void, List<Kusur>> {
    private WeakReference<Activity> weakActivity;

    public GetKusurByPlanIdAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Kusur> doInBackground(Long... ids) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).kusurDao().findKusursForPlanId(ids[0]);
    }
}
