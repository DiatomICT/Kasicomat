package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.HomeActivity;
import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Korisnik;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class InsertKorisnikAsyncTask extends AsyncTask<Korisnik, Void, List<Long>>{
    private WeakReference<Activity> weakActivity;

    public InsertKorisnikAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Long> doInBackground(Korisnik... korisniks) {
        long[] ids = KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).korisnikDao().insert(korisniks);
        List<Long> lst = new ArrayList<>();
        for (Long id: ids) {
            lst.add(id);
        }
        return lst;
    }
}
