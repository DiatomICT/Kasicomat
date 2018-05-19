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
import android.widget.ImageView;
import android.widget.TextView;

import com.diatom.kasicomat.dto.PonudaDTO;
import com.diatom.kasicomat.dto.RetailerPregledDTO;

public class FakeRetailerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer);

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

        mAdapter = new FakeRetailerActivity.MyAdapter(new RetailerPregledDTO[]{
                new RetailerPregledDTO("user1", "sat1", 79),
                new RetailerPregledDTO("user2", "sat2", 51),
                new RetailerPregledDTO("user3", "sat3", 88)
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private class MyAdapter extends RecyclerView.Adapter<FakeRetailerActivity.MyAdapter.ViewHolder> {

        private RetailerPregledDTO[] mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView mTextKorisnik;
            public TextView mTextProcenatSakupljenog;

            public ViewHolder(View v) {
                super(v);
                itemView.setOnClickListener(this);
                mTextKorisnik = (TextView) v.findViewById(R.id.textKorisnik);
                mTextProcenatSakupljenog = (TextView) v.findViewById(R.id.textProcenatSakupljenogRetailer);
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
            holder.mTextProcenatSakupljenog.setText(String.valueOf(retailerPregledDTO.getProcenatSkupljenog()));
        }



        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
