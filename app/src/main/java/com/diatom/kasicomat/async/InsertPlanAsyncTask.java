package com.diatom.kasicomat.async;

import android.app.Activity;
import android.os.AsyncTask;

import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Plan;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertPlanAsyncTask extends AsyncTask<Plan, Void, List<Long>> {
    private WeakReference<Activity> weakActivity;

    public InsertPlanAsyncTask(Activity activity) {
        weakActivity = new WeakReference<>(activity);
    }

    @Override
    protected List<Long> doInBackground(Plan... plans) {
        long[] ids = KasicomatDatabase.getInstance(weakActivity.get().getApplicationContext()).planDao().insert(plans);
        List<Long> lst = new ArrayList<>();
        for (Long id: ids) {
            lst.add(id);
        }
        return lst;
    }
}
