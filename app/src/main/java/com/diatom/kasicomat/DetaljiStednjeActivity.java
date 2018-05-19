package com.diatom.kasicomat;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.diatom.kasicomat.async.InsertPlanAsyncTask;
import com.diatom.kasicomat.db.entities.Plan;

import java.util.concurrent.ExecutionException;

public class DetaljiStednjeActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_stednje);

        mImageView = findViewById(R.id.imgSlikaProizvoda);

        Button btnSlika = findViewById(R.id.btnDodajSliku);
        btnSlika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        Button btnDalje = findViewById(R.id.btnDalje);
        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKusur = ((RadioButton) findViewById(R.id.radioBtnKusur)).isChecked();

                Intent intent = new Intent(DetaljiStednjeActivity.this.getApplicationContext(), isKusur ? NivoiStednjeKusurActivity.class : NivoiStednjeFiksnoActivity.class);

                Plan plan = new Plan();
                plan.setNazivProizvoda(((EditText)findViewById(R.id.editBrend)).getText().toString());
                plan.setCena(Integer.parseInt(((EditText)findViewById(R.id.editCena)).getText().toString()));
                plan.setRezimId(isKusur ? 1 : 2);
                plan.setPeriodStednjeId((int) ((Spinner)findViewById(R.id.spinnerPeriodStednje)).getSelectedItemId() + 1);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                    Drawable drawable = new BitmapDrawable(bitmap);
                    mImageView.setImageDrawable(drawable);
                    mImageView.setVisibility(ImageView.VISIBLE);
                }
                break;
            default:
                break;
        }
    }


}
