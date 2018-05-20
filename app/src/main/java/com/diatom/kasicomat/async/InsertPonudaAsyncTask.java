package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Ponuda;

import java.lang.ref.WeakReference;

public class InsertPonudaAsyncTask extends AsyncTask<Ponuda, Void, Void> {
    private WeakReference<Activity> weakActivity;

    public InsertPonudaAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }
    @Override
    protected Void doInBackground(Ponuda... ponudas) {
        KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).ponudaDao().insert(ponudas[0]);
        return null;
    }
}
