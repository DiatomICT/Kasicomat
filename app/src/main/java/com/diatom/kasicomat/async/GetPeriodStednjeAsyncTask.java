package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.PeriodStednje;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetPeriodStednjeAsyncTask extends AsyncTask<Void, Void, List<PeriodStednje>> {
    private WeakReference<Activity> weakActivity;

    public GetPeriodStednjeAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<PeriodStednje> doInBackground(Void... voids) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).periodStednjeDao().getAllPeriodStednjes();
    }
}
