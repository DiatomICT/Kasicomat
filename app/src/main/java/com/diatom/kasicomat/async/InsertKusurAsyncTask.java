package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Kusur;

import java.lang.ref.WeakReference;

public class InsertKusurAsyncTask extends AsyncTask<Kusur, Void, Void> {
    private WeakReference<Activity> weakActivity;

    public InsertKusurAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Void doInBackground(Kusur... kusurs) {
        KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).kusurDao().insert(kusurs);
        return null;
    }
}
