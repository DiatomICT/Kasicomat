package com.diatom.kasicomat;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.materialrangebar.RangeBar;
import com.diatom.kasicomat.async.GetKorisnikPlanAsyncTask;
import com.diatom.kasicomat.async.InsertKorisnikPlanAsyncTask;
import com.diatom.kasicomat.async.InsertPonudaAsyncTask;
import com.diatom.kasicomat.db.entities.KorisnikPlan;
import com.diatom.kasicomat.db.entities.Ponuda;
import com.diatom.kasicomat.dto.PonudaDTO;
import com.diatom.kasicomat.dto.RetailerPregledDTO;
import com.diatom.kasicomat.util.StringUtils;

import org.w3c.dom.Text;

import java.util.List;

public class FakeRetailerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RangeBar mRangeCena;
    private RangeBar mRangeProcenat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer);

        mRangeCena = findViewById(R.id.rangeCena);
        mRangeProcenat = findViewById(R.id.rangeProcenat);

        // dohvati se plan sa najvise ustedjenog novca po kategoriji
        Spinner spinnerKategorija = findViewById(R.id.spinnerKategorijaRetailer);
        spinnerKategorija.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mRangeCena.setTickEnd(5);
                    mRangeCena.setRangePinsByIndices(0, 5);
                } else {
                    mRangeCena.setTickEnd(500);
                    mRangeCena.setRangePinsByIndices(0, 500);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<KorisnikPlan> fakeData = null;
        try {
            fakeData = new GetKorisnikPlanAsyncTask(FakeRetailerActivity.this).execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RetailerPregledDTO[] prikazData;
        if (fakeData == null || fakeData.size() == 0) {
            prikazData = new RetailerPregledDTO[]{new RetailerPregledDTO("JEDAN", "JEDAN", 30)};
        } else {
            prikazData = new RetailerPregledDTO[fakeData.size()];
            for (int i = 0; i < fakeData.size(); i++) {
                prikazData[i] = new RetailerPregledDTO(
                        fakeData.get(i).getKorisnik().getIme(),
                        fakeData.get(i).getPlan().get(0).getBrend() + " " + fakeData.get(i).getPlan().get(0).getModel(),
                        fakeData.get(i).getPlan().get(0).getSakupljeno());
            }
        }
        mAdapter = new FakeRetailerActivity.MyAdapter(prikazData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter<FakeRetailerActivity.MyAdapter.ViewHolder> {

        private RetailerPregledDTO[] mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView mTextKorisnik;
            public TextView mTextProizvod;
            public TextView mTextProcenatSakupljenog;
            public Button mPonudaButton;

            public ViewHolder(View v) {
                super(v);
                itemView.setOnClickListener(this);
                mTextKorisnik = (TextView) v.findViewById(R.id.textKorisnik);
                mTextProizvod = (TextView) v.findViewById(R.id.textNazivPredmeta);
                mTextProcenatSakupljenog = (TextView) v.findViewById(R.id.textProcenatSakupljenogRetailer);
                mPonudaButton = v.findViewById(R.id.btnPosaljiPonudu);
            }

            @Override
            public void onClick(View v) {

            }
        }

        public MyAdapter(RetailerPregledDTO[] myDataset) {
            mDataset = myDataset;
        }

        @NonNull
        @Override
        public FakeRetailerActivity.MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pregled_row_item, parent, false);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            FakeRetailerActivity.MyAdapter.ViewHolder vh = new FakeRetailerActivity.MyAdapter.ViewHolder(view);

            return vh;
        }

        @Override
        public void onBindViewHolder(final FakeRetailerActivity.MyAdapter.ViewHolder holder, int position) {

            final RetailerPregledDTO retailerPregledDTO = mDataset[position];

            holder.mTextKorisnik.setText(retailerPregledDTO.getKorisnik());
            holder.mTextProizvod.setText(retailerPregledDTO.getProizvod());
            holder.mTextProcenatSakupljenog.setText(String.valueOf(retailerPregledDTO.getProcenatSkupljenog()));
            holder.mPonudaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FakeRetailerActivity.this.getApplicationContext(), HomeActivity.class);
                    intent.putExtra("fake_ponuda", true);
                    intent.putExtra("nazivProizvoda", retailerPregledDTO.getProizvod());
                    intent.putExtra("procenatSakupljenog", retailerPregledDTO.getProcenatSkupljenog());

                    new InsertPonudaAsyncTask(FakeRetailerActivity.this).execute(new Ponuda("Gigatron", "DIATOM"));

                    startActivity(intent);
                }
            });

        }



        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
