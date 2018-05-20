package com.diatom.kasicomat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

import com.diatom.kasicomat.async.GetFiksnoByPlanIdAsyncTask;
import com.diatom.kasicomat.async.GetPlanAsyncTask;
import com.diatom.kasicomat.async.GetTransakcijaAsyncTask;
import com.diatom.kasicomat.db.entities.Fiksno;
import com.diatom.kasicomat.db.entities.Plan;
import com.diatom.kasicomat.db.entities.Transakcija;
import com.jjoe64.graphview.GraphView;

import java.sql.Date;
import java.util.List;

public class PlanActivity extends AppCompatActivity {

    final Context context = this;
    private Button btnVidiPredlog;
    private Button btnIzmeniPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        GraphView graph = (GraphView) findViewById(R.id.graph);

        btnVidiPredlog = (Button) findViewById(R.id.btnVidiPredlog);
        btnVidiPredlog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                StringBuffer sb = new StringBuffer();
                try {
                    List<Transakcija> transakcije = new GetTransakcijaAsyncTask(PlanActivity.this).execute().get();
                    int brojTransakcija = transakcije.size();

                    int fiksno = new GetFiksnoByPlanIdAsyncTask(PlanActivity.this).execute(1L).get().get(0).getKolicina();

                    Plan plan = new GetPlanAsyncTask(PlanActivity.this).execute().get().get(0);

                    long d1 = plan.getDatumPocetka().getTime();
                    long d2 = System.currentTimeMillis();
                    long d3 = plan.getDatumKraja().getTime();

                    double prosek = brojTransakcija / ((double) d2 - d1);

                    int ocekivanaUsteda = (int) ((d3 - d2) * prosek * fiksno);
                    int cilj = plan.getCena();

                    int sakupljeno = brojTransakcija * fiksno;
                    int boljeFiksno = (int) ((cilj - sakupljeno) / ((d3 - d2) * prosek)) + 1;

                    String poruka = "";
                    poruka += "D1 :" + new Date(d1) + "\n";
                    poruka += "D2 :" + new Date(d2) + "\n";
                    poruka += "D3 :" + new Date(d3) + "\n";
                    poruka += "Broj transakcija :" + brojTransakcija + "\n";
                    poruka += "Broj transakcija po danu :" + prosek + "\n";
                    poruka += "Fiksno :" + fiksno + "\n";
                    poruka += "Usteda :" + sakupljeno + "\n";
                    poruka += "Cilj :" + cilj + "\n";
                    poruka += "Ocekivana usteda :" + ocekivanaUsteda + "\n";
                    poruka += "Predlog :" + boljeFiksno + "\n";

                    Toast.makeText(context, poruka, Toast.LENGTH_LONG).show();
                    sb.append("Vasa dosadasnja usteda je: ");
                    sb.append(sakupljeno); sb.append(" RSD.\n");
                    if (cilj - sakupljeno > ocekivanaUsteda) {
                        sb.append("Ovim tempom necete stici da u zeljenom roku ustedite novac.\n");

                        sb.append("Predlazemo Vam da izmenite plan i povecate fiksnu ustedu od svake transakcije na: ");
                        sb.append(boljeFiksno);
                        sb.append(" RSD.\n");
                    } else {
                        sb.append("Na sjajnom ste putu!");
                    }
                } catch (Exception e) {
                    sb.append("Doslo je do greske");
                    sb.append(e.getMessage());

                }
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                alertDialogBuilder.setTitle("Predlog izmene plana za štednju");
                alertDialogBuilder
                        .setMessage(sb.toString())
                        .setCancelable(false)
                        .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                PlanActivity.this.finish();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        btnIzmeniPlan = (Button) findViewById(R.id.btnIzmeniPlan);
        btnIzmeniPlan.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                int rezimId = getIntent().getIntExtra("rezimId", 1);
                if (rezimId == 1) {
                    startActivity(new Intent(PlanActivity.this, NivoiStednjeKusurActivity.class));
                } else {
                    startActivity(new Intent(PlanActivity.this, NivoiStednjeFiksnoActivity.class));
                }
            }

        });
    }
}
