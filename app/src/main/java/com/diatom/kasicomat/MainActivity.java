package com.diatom.kasicomat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.diatom.kasicomat.async.GetPlanAsyncTask;
import com.diatom.kasicomat.db.entities.Plan;

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

    }

    private void handleHomeBezPlana() {
        setContentView(R.layout.activity_home_bez_plana);

        Button btnNapraviPlan = findViewById(R.id.btnNapraviPlan);
        btnNapraviPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this.getApplicationContext(), DetaljiStednjeActivity.class);
                startActivity(intent);
            }
        });
    }
}
