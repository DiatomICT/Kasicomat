package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Fiksno;

import java.lang.ref.WeakReference;

public class InsertFiksnoAsyncTask extends AsyncTask<Fiksno, Void, Void> {
    private WeakReference<Activity> weakActivity;

    public InsertFiksnoAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Void doInBackground(Fiksno... fiksnos) {
        KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).fiksnoDao().insert(fiksnos[0]);
        return null;
    }
}
