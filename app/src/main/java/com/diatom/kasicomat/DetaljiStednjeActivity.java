package com.diatom.kasicomat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.diatom.kasicomat.async.InsertPlanAsyncTask;
import com.diatom.kasicomat.db.entities.Plan;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ExecutionException;

public class DetaljiStednjeActivity extends AppCompatActivity {

    ImageView mImageView;
    Button mBtnSlika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalji_stednje);

        mImageView = findViewById(R.id.imgSlikaProizvoda);

        mBtnSlika = findViewById(R.id.btnDodajSliku);
        mBtnSlika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(DetaljiStednjeActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetaljiStednjeActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                }
            }
        });

        Button btnDalje = findViewById(R.id.btnDalje);
        btnDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isKusur = ((RadioButton) findViewById(R.id.radioBtnKusur)).isChecked();

                Intent intent = new Intent(DetaljiStednjeActivity.this.getApplicationContext(), isKusur ? NivoiStednjeKusurActivity.class : NivoiStednjeFiksnoActivity.class);

                Plan plan = new Plan();
                plan.setBrend(((EditText)findViewById(R.id.editBrend)).getText().toString());
                plan.setModel(((EditText)findViewById(R.id.editModel)).getText().toString());
                plan.setCena(Integer.parseInt(((EditText)findViewById(R.id.editCena)).getText().toString()));
                plan.setRezimId(isKusur ? 1 : 2);
                plan.setPeriodStednjeId((int) ((Spinner)findViewById(R.id.spinnerPeriodStednje)).getSelectedItemId() + 1);
                plan.setKategorijaId((int) ((Spinner) findViewById(R.id.spinnerKategorija)).getSelectedItemId() + 1);
                plan.setKorisnikId(1);

                try {
                    long planId = new InsertPlanAsyncTask(DetaljiStednjeActivity.this).execute(plan).get().get(0);
                    intent.putExtra("planId", planId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                startActivity(intent);
            }
        });

        FloatingActionButton fabKusur = findViewById(R.id.fabKusur);
        fabKusur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetaljiStednjeActivity.this, "\"Kusur\" rezim stednje svaku transakciju zaokruzuje na definisani veci iznos", Toast.LENGTH_LONG).show();
            }
        });
        FloatingActionButton fabFiksno = findViewById(R.id.fabFiksno);
        fabFiksno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetaljiStednjeActivity.this, "\"Fiksno\" rezim stednje na svaku transakciju dodaje fiksan iznos koji prelazi na stedni racun", Toast.LENGTH_LONG).show();
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

                    Log.i("DetaljiStednjeActivity", filepath);

                    Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                    Log.i("DetaljiStednjeActivity", "Original: " + bitmap.getByteCount());
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 50, out);

                    byte[] bytes = out.toByteArray();
                    Bitmap compressedBitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

                    Log.i("DetaljiStednjeActivity", "Compressed: " + compressedBitmap.getByteCount());

                    Drawable drawable = new BitmapDrawable(getResources(), compressedBitmap);
                    mImageView.setImageDrawable(drawable);
                    mImageView.setVisibility(ImageView.VISIBLE);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 1);
                } else {
//                    mBtnSlika.setBackgroundColor(Color.GRAY);
//                    mBtnSlika.setClickable(false);
                }
            }
        }
    }
}
