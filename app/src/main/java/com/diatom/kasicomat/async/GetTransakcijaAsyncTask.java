package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Transakcija;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetTransakcijaAsyncTask extends AsyncTask<Void, Void, List<Transakcija>> {
    private WeakReference<Activity> weakActivity;

    public GetTransakcijaAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Transakcija> doInBackground(Void... voids) {
        return KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).transakcijaDao().getAllTransakcijas();
    }
}
