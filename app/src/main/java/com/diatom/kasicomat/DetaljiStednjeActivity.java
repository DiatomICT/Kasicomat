package com.diatom.kasicomat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.diatom.kasicomat.async.InsertPlanAsyncTask;
import com.diatom.kasicomat.db.entities.Plan;

import java.util.concurrent.ExecutionException;

public class DetaljiStednjeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btnDalje = findViewById(R.id.btnDalje);
        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKusur = ((RadioButton) findViewById(R.id.radioBtnKusur)).isChecked();

                Intent intent = new Intent(DetaljiStednjeActivity.this.getApplicationContext(), isKusur ? NivoiStednjeKusurActivity.class : NivoiStednjeKusurActivity.class);

                Plan plan = new Plan();
                plan.setNazivProizvoda(((EditText)findViewById(R.id.editNaziv)).getText().toString());
                plan.setCena(Integer.parseInt(((EditText)findViewById(R.id.editCena)).getText().toString()));
                plan.setRezimId(isKusur ? 0 : 1);
                plan.setPeriodStednjeId((int) ((Spinner)findViewById(R.id.spinnerPeriodStednje)).getSelectedItemId());

                try {
                    long planId = new InsertPlanAsyncTask(DetaljiStednjeActivity.this).execute(plan).get();
                    intent.putExtra("planId", planId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });
    }
}
