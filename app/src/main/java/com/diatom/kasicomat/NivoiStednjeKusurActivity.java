package com.diatom.kasicomat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.diatom.kasicomat.async.InsertKusurAsyncTask;
import com.diatom.kasicomat.db.KasicomatDatabase;
import com.diatom.kasicomat.db.entities.Kusur;
import com.diatom.kasicomat.db.entities.Plan;

public class NivoiStednjeKusurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivoi_stednje_kusur);

        final Bundle extras = getIntent().getExtras();

        Button btnSacuvajPlan = findViewById(R.id.btnSacuvajPlanKusur); // TODO
        btnSacuvajPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NivoiStednjeKusurActivity.this.getApplicationContext(), HomeActivity.class);

                int planId;
                if (extras.keySet().contains("planId")) {
                    planId = (int) extras.getLong("planId");
                } else {
                    planId = -1;
                }

                int editOdL1 = Integer.parseInt(((EditText) findViewById(R.id.editOdL1)).getText().toString());
                int editDoL1 = Integer.parseInt(((EditText) findViewById(R.id.editDoL1)).getText().toString());
                int editCifraL1 = Integer.parseInt(((EditText) findViewById(R.id.editCifraL1)).getText().toString());

                int editOdL2 = Integer.parseInt(((EditText) findViewById(R.id.editOdL2)).getText().toString());
                int editDoL2 = Integer.parseInt(((EditText) findViewById(R.id.editDoL2)).getText().toString());
                int editCifraL2 = Integer.parseInt(((EditText) findViewById(R.id.editCifraL2)).getText().toString());

                int editOdL3 = Integer.parseInt(((EditText) findViewById(R.id.editOdL3)).getText().toString());
                int editDoL3 = Integer.parseInt(((EditText) findViewById(R.id.editDoL3)).getText().toString());
                int editCifraL3 = Integer.parseInt(((EditText) findViewById(R.id.editCifraL3)).getText().toString());

                new InsertKusurAsyncTask(NivoiStednjeKusurActivity.this).execute(
                        new Kusur(editOdL1, editDoL1, editCifraL1, planId),
                        new Kusur(editOdL2, editDoL2, editCifraL2, planId),
                        new Kusur(editOdL3, editDoL3, editCifraL3, planId)
                );
                
                startActivity(intent);
            }
        });
    }
}
