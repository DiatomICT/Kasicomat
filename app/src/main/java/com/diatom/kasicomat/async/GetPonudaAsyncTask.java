package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Ponuda;

import java.lang.ref.WeakReference;

public class GetPonudaAsyncTask extends AsyncTask<String, Void, Ponuda> {
    private WeakReference<Activity> weakActivity;

    public GetPonudaAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected Ponuda doInBackground(String... names) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).ponudaDao().findPonudaByKorisnikId(names[0]).get(0);
    }
}
