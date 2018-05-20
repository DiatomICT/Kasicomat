package com.diatom.kasicomat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diatom.kasicomat.dto.PonudaDTO;

public class PonudaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponuda_detalji);

        Bundle extras = getIntent().getExtras();
        PonudaDTO ponuda = extras.getParcelable("ponuda");

        int sakupljenIznos = ponuda.getSakupljenIznos();
        int iznosKredita = ponuda.getCena() - sakupljenIznos;

        ((ImageView) findViewById(R.id.imageDetalji)).setImageDrawable(getResources().getDrawable(ponuda.getSlikaId()));
        ((TextView) findViewById(R.id.textDetaljiNaziv)).setText(ponuda.getNaziv());
        ((TextView) findViewById(R.id.textDetaljiCena)).setText(String.valueOf(ponuda.getCena()));

        ((TextView) findViewById(R.id.textSakupljenIznosValue)).setText(String.valueOf(sakupljenIznos));
        ((TextView) findViewById(R.id.textIznosKreditaValue)).setText(String.valueOf(iznosKredita));
    }
}
