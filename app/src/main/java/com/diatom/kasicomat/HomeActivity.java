package com.diatom.kasicomat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;
import android.widget.Toast;

import com.diatom.kasicomat.async.GetFiksnoByPlanIdAsyncTask;
import com.diatom.kasicomat.async.GetKusurByPlanIdAsyncTask;
import com.diatom.kasicomat.async.GetPlanAsyncTask;
import com.diatom.kasicomat.db.entities.Fiksno;
import com.diatom.kasicomat.db.entities.Kusur;
import com.diatom.kasicomat.db.entities.Plan;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sa_planom);

        try {
            List<Plan> plans = new GetPlanAsyncTask(HomeActivity.this).execute().get();
            Plan plan = plans.get(0);

            ((TextView)findViewById(R.id.textNazivValue)).setText(plan.getNazivProizvoda());
            ((TextView)findViewById(R.id.textCenaValue)).setText(String.valueOf(plan.getCena()));
            ((TextView)findViewById(R.id.textSakupljenoValue)).setText("0"); // TODO dodaj ovo kao polje u bazu

            StringBuffer sb = new StringBuffer();
            sb.append(plan.getNazivProizvoda()); sb.append("\n");
            sb.append(plan.getCena()); sb.append("\n");
            if (plan.getRezimId() == 1) {
                // kusur
                List<Kusur> kusurs = new GetKusurByPlanIdAsyncTask(HomeActivity.this).execute(plan.getId()).get();

                for (Kusur k : kusurs) {
                    sb.append(k); sb.append("\n");
                }
            } else {
                // fiksno
                List<Fiksno> fiksnos = new GetFiksnoByPlanIdAsyncTask(HomeActivity.this).execute(plan.getId()).get();

                for (Fiksno f : fiksnos) {
                    sb.append(f); sb.append("\n");
                }
            }
            Toast.makeText(HomeActivity.this.getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
