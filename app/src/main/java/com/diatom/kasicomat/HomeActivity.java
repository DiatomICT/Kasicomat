package com.diatom.kasicomat;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diatom.kasicomat.async.GetPlanAsyncTask;
import com.diatom.kasicomat.async.GetPonudaAsyncTask;
import com.diatom.kasicomat.async.InsertKorisnikAsyncTask;
import com.diatom.kasicomat.async.InsertKorisnikPlanAsyncTask;
import com.diatom.kasicomat.async.InsertPlanAsyncTask;
import com.diatom.kasicomat.db.entities.KorisnikPlan;
import com.diatom.kasicomat.db.entities.Plan;
import com.diatom.kasicomat.db.entities.Ponuda;
import com.diatom.kasicomat.dto.PonudaDTO;
import com.diatom.kasicomat.util.DummyGenerator;
import com.diatom.kasicomat.util.StringUtils;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mRezimId;
//    private Plan mPlan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sa_planom);

        try {
            List<Plan> plans = new GetPlanAsyncTask(HomeActivity.this).execute().get();
            Plan mPlan = plans.get(0);
            mRezimId = mPlan.getRezimId();

            ((TextView)findViewById(R.id.textNazivValue)).setText(mPlan.getBrend() + " " + mPlan.getModel());
            ((TextView)findViewById(R.id.textCenaValue)).setText(String.valueOf(mPlan.getCena()));
            ((TextView)findViewById(R.id.textSakupljenoValue)).setText(String.valueOf(mPlan.getSakupljeno())); // TODO dodaj ovo kao polje u bazu

            mRecyclerView = findViewById(R.id.my_recycler_view);
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            // vidi da li smo dosli do ovde as FakeRetailerActivity
            PonudaDTO[] ponude;
            if (getIntent().hasExtra("fake_ponuda")) {
                Bundle extras = getIntent().getExtras();
                Ponuda ponuda = new GetPonudaAsyncTask(HomeActivity.this).execute("DIATOM").get();
                int slikaId = R.drawable.societe_generale;
                switch (ponuda.getRetailer()) {
                    case "Gigatron": slikaId = R.drawable.logo_gigatron; break;
                    case "win-win": slikaId = R.drawable.logo_winwin; break;
                    case "Emmi": slikaId = R.drawable.logo_emmi; break;
                }
                ponude = new PonudaDTO[]{
                    new PonudaDTO(
                            slikaId,
                            mPlan.getBrend() + " " + mPlan.getModel(),
                            mPlan.getCena(), //extras.getInt("cena_proizvoda"),
                            mPlan.getSakupljeno()
                    )
                };
            } else {
                ponude = null;
            }

            mAdapter = new MyAdapter(this, ponude);
            mRecyclerView.setAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button btnFakeRetailer = findViewById(R.id.btnFakeRetailer);
        btnFakeRetailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // dodati lazne podatke
                try {
                    Globals g = (Globals) getApplication();
                    if (!g.isDummyInserted()) {
                        List<Long> korisnici = new InsertKorisnikAsyncTask(HomeActivity.this).execute(DummyGenerator.generisiDummyKorisnike(5)).get();
                        List<Long> planovi = new InsertPlanAsyncTask(HomeActivity.this).execute(DummyGenerator.generisiDummyPlanove(5, korisnici)).get();
                        g.setDummyInserted(true);
                    }
//                    new InsertKorisnikPlanAsyncTask(HomeActivity.this).execute(DummyGenerator.generisiKorisnikPlanove(ids));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(HomeActivity.this.getApplicationContext(), FakeRetailerActivity.class));
            }
        });

        Button btnAnalizaPlana = findViewById(R.id.btnAnalizaPlana);
        btnAnalizaPlana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this.getApplicationContext(), PlanActivity.class);
                intent.putExtra("rezimId", mRezimId);
                startActivity(intent);
            }
        });
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private PonudaDTO[] mDataset;
        private Activity activity;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView mImageRetailer;
            public TextView mTextPonudaNaziv;
            public TextView mTextPonudaCena;

            public ViewHolder(View v) {
                super(v);
                itemView.setOnClickListener(this);
                mImageRetailer = (ImageView) v.findViewById(R.id.imageRetailer);
                mTextPonudaNaziv = (TextView) v.findViewById(R.id.textPonudaNaziv);
                mTextPonudaCena = (TextView) v.findViewById(R.id.textPonudaCena);
            }

            @Override
            public void onClick(View v) {

            }
        }

        public MyAdapter(Activity activity, PonudaDTO[] myDataset) {
            this.activity = activity;
            mDataset = myDataset;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ponuda_row_item, parent, false);
            ViewHolder vh = new ViewHolder(view);

            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            if (mDataset == null || mDataset.length == 0) {
                Toast.makeText(HomeActivity.this, "Nema ponuda od retailer-a.", Toast.LENGTH_LONG).show();
                mRecyclerView.setVisibility(View.INVISIBLE);
            } else {
                final PonudaDTO ponudaDTO = mDataset[position];
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity.getApplicationContext(), PonudaActivity.class);
                        intent.putExtra("ponuda", ponudaDTO);
                        startActivity(intent);
                    }
                });
                holder.mImageRetailer.setImageDrawable(getResources().getDrawable(ponudaDTO.getSlikaId()));
                holder.mTextPonudaNaziv.setText(ponudaDTO.getNaziv());
                holder.mTextPonudaCena.setText(String.valueOf(ponudaDTO.getCena()));
            }
        }



        @Override
        public int getItemCount() {
            return mDataset == null ? 0 : mDataset.length;
        }
    }
}
