package com.example.ptsgenap11rpl1absen2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity"; //untuk melihat log\

    EditText etKode, etNamaBarang, etJenis; //pembuatan variabel edittext
    Button btnUpdate, btnDelete; //pembuatan variabel button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etKode = findViewById(R.id.editTextKode1);
        etNamaBarang = findViewById(R.id.editTextNama1);
        etJenis = findViewById(R.id.editTextJenis1);
        btnUpdate = findViewById(R.id.buttonMainUpdate);
        btnDelete = findViewById(R.id.buttonMaindelete);

        etKode.setText(getIntent().getStringExtra("events2"));
        etNamaBarang.setText(getIntent().getStringExtra("events3"));
        etJenis.setText(getIntent().getStringExtra("events4"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN
                AndroidNetworking.post("http://192.168.43.92/crud_Android/update.php")
                        .addBodyParameter("id", getIntent().getStringExtra("events")) //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                        .addBodyParameter("kode",etKode.getText().toString().trim().toUpperCase()) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel nim
                        .addBodyParameter("namabarang", etNamaBarang.getText().toString().trim().toUpperCase()) //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                        .addBodyParameter("jenisbarang", etJenis.getText().toString().trim().toUpperCase()) //mengirimkan data kelas_mahasiswa yang akan diisi dengan varibel kelas
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Handle Response
                                Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                                Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data berhasil ditambahkan

                            }
                            @Override
                            public void onError(ANError error) {
                                //Handle Error
                                Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                                Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data gagal ditambahkan
                            }
                        });


            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN
                AndroidNetworking.post("http://192.168.43.92/crud_Android/delete.php")
                        .addBodyParameter("id", getIntent().getStringExtra("events")) //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Handle Response
                                Log.d(TAG, "onResponse: " + response); //untuk log pada onresponse
                                Toast.makeText(getApplicationContext(),"Data berhasil di Delete" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data berhasil di Delete

                            }
                            @Override
                            public void onError(ANError error) {
                                //Handle Error
                                Log.d(TAG, "onError: Failed" + error); //untuk log pada onerror
                                Toast.makeText(getApplicationContext(),"Data berhasil di Delete" , Toast.LENGTH_SHORT).show();
                                //memunculkan Toast saat data gagal di Delete
                            }
                        });

            }
        });

    }
}
