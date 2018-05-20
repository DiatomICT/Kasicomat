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
import android.widget.TextView;
import android.widget.Toast;

import com.diatom.kasicomat.async.GetFiksnoByPlanIdAsyncTask;
import com.diatom.kasicomat.async.GetPlanAsyncTask;
import com.diatom.kasicomat.async.GetTransakcijaAsyncTask;
import com.diatom.kasicomat.db.entities.Fiksno;
import com.diatom.kasicomat.db.entities.Plan;
import com.diatom.kasicomat.db.entities.Transakcija;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanActivity extends AppCompatActivity {

    final Context context = this;
    private Button btnVidiPredlog;
    private Button btnIzmeniPlan;
    private long sakupljeno;
    private long cilj;
    private long ocekivanaUsteda;
    private long boljeFiksno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        GraphView graph = findViewById(R.id.graph);

        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setPadding(32);

        try {
            List<Transakcija> transakcije = new GetTransakcijaAsyncTask(PlanActivity.this).execute().get();
            int brojTransakcija = transakcije.size();

            int fiksno = new GetFiksnoByPlanIdAsyncTask(PlanActivity.this).execute(1L).get().get(0).getKolicina();

            Plan plan = new GetPlanAsyncTask(PlanActivity.this).execute().get().get(0);

            long d1 = plan.getDatumPocetka().getTime();
            long d2 = System.currentTimeMillis();
            long d3 = plan.getDatumKraja().getTime();

            Toast.makeText(context, "" + new Date(d1) + "\n" + new Date(d2) + "\n" + new Date(d3), Toast.LENGTH_LONG).show();

            double prosek = brojTransakcija / ((double) d2 - d1);

            ocekivanaUsteda = (int) ((d3 - d2) * prosek * fiksno);
            cilj = plan.getCena();

            sakupljeno = brojTransakcija * fiksno;
            boljeFiksno = (int) ((cilj - sakupljeno) / ((d3 - d2) * prosek)) + 1;

            long ukupnoDanaDoKraja = (long) ((d2 - d1) * cilj) / sakupljeno;

            long DAY_IN_MILLIS = 24 * 60 * 60 * 1000;
            long inX = ukupnoDanaDoKraja + d1;
            ((TextView) findViewById(R.id.textOcekivaniDatumZavrsetkaValue)).setText(new Date(inX).toString());


            Map<String, Integer> mapa = new HashMap<>();
            for (Transakcija t : transakcije) {
                if (mapa.containsKey(t.getDatum().toString())) {
                    mapa.put(t.getDatum().toString(), mapa.get(t.getDatum().toString()) + 10);
                } else {
                    mapa.put(t.getDatum().toString(), 10);
                }
            }

            DataPoint[] dataPoints = new DataPoint[mapa.size()];
            int i = 0;
            for (Map.Entry<String, Integer> entry : mapa.entrySet()) {
                dataPoints[i++] = new DataPoint(Date.valueOf(entry.getKey()), entry.getValue());
            }

            for (int j = 0; j < dataPoints.length - 1; j++) {
                for (int k = j + 1; k < dataPoints.length; k++) {
                    if (dataPoints[j].getX() > dataPoints[k].getX()) {
                        DataPoint tmp = dataPoints[j];
                        dataPoints[j] = dataPoints[k];
                        dataPoints[k] = tmp;
                    }
                }
            }

            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(dataPoints);

            graph.addSeries(series);

            // set date label formatter
            graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(PlanActivity.this));
            graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

            // set manual x bounds to have nice steps
            graph.getViewport().setMinX(d1);
            graph.getViewport().setMaxX(d3);
            graph.getViewport().setXAxisBoundsManual(true);

            // as we use dates as labels, the human rounding to nice readable numbers
            // is not necessary
            graph.getGridLabelRenderer().setHumanRounding(false);
        } catch (Exception e) {
            e.printStackTrace();
        }



        btnVidiPredlog = (Button) findViewById(R.id.btnVidiPredlog);
        btnVidiPredlog.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                StringBuffer sb = new StringBuffer();


//                    String poruka = "";
//                    poruka += "D1 :" + new Date(d1) + "\n";
//                    poruka += "D2 :" + new Date(d2) + "\n";
//                    poruka += "D3 :" + new Date(d3) + "\n";
//                    poruka += "Broj transakcija :" + brojTransakcija + "\n";
//                    poruka += "Broj transakcija po danu :" + prosek + "\n";
//                    poruka += "Fiksno :" + fiksno + "\n";
//                    poruka += "Usteda :" + sakupljeno + "\n";
//                    poruka += "Cilj :" + cilj + "\n";
//                    poruka += "Ocekivana usteda :" + ocekivanaUsteda + "\n";
//                    poruka += "Predlog :" + boljeFiksno + "\n";
//
//                    Toast.makeText(context, poruka, Toast.LENGTH_LONG).show();
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
                    Intent intent = new Intent(PlanActivity.this, NivoiStednjeFiksnoActivity.class);
                    intent.putExtra("planId", 1L);
                    startActivity(intent);
                }
            }

        });
    }
}
