package com.diatom.kasicomat;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.diatom.kasicomat.async.GetPeriodStednjeAsyncTask;
import com.diatom.kasicomat.async.GetPlanAsyncTask;
import com.diatom.kasicomat.async.GetRezimAsyncTask;
import com.diatom.kasicomat.db.entities.PeriodStednje;
import com.diatom.kasicomat.db.entities.Plan;
import com.diatom.kasicomat.db.entities.Rezim;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Plan> plans = null;
        try {
            plans = new GetPlanAsyncTask(MainActivity.this).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (plans == null || plans.size() == 0) {
            handleHomeBezPlana();
        } else {
            handleHomeSaPlanom();
        }


    }

    private void handleHomeSaPlanom() {
        setContentView(R.layout.activity_home_sa_planom);
        startActivity(new Intent(MainActivity.this.getApplicationContext(), HomeActivity.class));
    }

    private void handleHomeBezPlana() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_bez_plana);

        Button btnNapraviPlan = findViewById(R.id.btnNapraviPlan);
        btnNapraviPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), DetaljiStednjeActivity.class);

                // DEBUG informacije sta se nalazi u bazi
//                try {
//                    List<Rezim> rezims = new GetRezimAsyncTask(MainActivity.this).execute().get();
//                    List<PeriodStednje> periodStednjes = new GetPeriodStednjeAsyncTask(MainActivity.this).execute().get();
//
//                    StringBuffer sb = new StringBuffer();
//                    for (Rezim r : rezims) {
//                        sb.append(r);
//                        sb.append("\n");
//                    }
//                    for (PeriodStednje p : periodStednjes) {
//                        sb.append(p);
//                        sb.append("\n");
//                    }
//                    Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                startActivity(intent);
            }
        });
    }
}
