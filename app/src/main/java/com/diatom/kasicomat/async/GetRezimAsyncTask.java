package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Rezim;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetRezimAsyncTask extends AsyncTask<Void, Void, List<Rezim>> {
    private WeakReference<Activity> weakActivity;

    public GetRezimAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Rezim> doInBackground(Void... voids) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).rezimDao().getAllRezims();
    }
}
