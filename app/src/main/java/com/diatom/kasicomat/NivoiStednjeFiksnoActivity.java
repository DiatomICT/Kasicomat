package com.diatom.kasicomat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.diatom.kasicomat.async.InsertFiksnoAsyncTask;
import com.diatom.kasicomat.db.entities.Fiksno;

public class NivoiStednjeFiksnoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivoi_stednje_fiksno);

        final Bundle extras = getIntent().getExtras();

        Button btnSacuvajPlan = findViewById(R.id.btnSacuvajPlanFiksno);
        btnSacuvajPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fiksno;
                switch (((RadioGroup) findViewById(R.id.rgFiksniIznos)).getCheckedRadioButtonId()) {
                    case R.id.rbI1: fiksno = 5; break;
                    case R.id.rbI2: fiksno = 10; break;
                    case R.id.rbI3: fiksno = 20; break;
                    case R.id.rbI4: {
                        fiksno = Integer.parseInt(((EditText) findViewById(R.id.editI4)).getText().toString());
                        break;
                    }
                    default: fiksno = 0; break;
                }

                Intent intent = new Intent(NivoiStednjeFiksnoActivity.this.getApplicationContext(), HomeActivity.class);

                int planId;
                if (extras.keySet().contains("planId")) {
                    planId = (int) extras.getLong("planId");
                } else {
                    planId = -1;
                }

                new InsertFiksnoAsyncTask(NivoiStednjeFiksnoActivity.this).execute(new Fiksno(fiksno, planId));
                startActivity(intent);
            }
        });
    }
}
