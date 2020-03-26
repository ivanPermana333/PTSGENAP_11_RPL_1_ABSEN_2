package com.example.ptsgenap11rpl1absen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity"; //untuk melihat log
    private EditText etKode, etNamaBarang, etJenis; //pembuatan variabel edittext
    private Button btnTambah, btnLihat; //pembuatan variabel button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN

        Log.d(TAG, "onCreate: inisialisasi"); //untuk log pada oncreate

        etKode = findViewById(R.id.editTextMainKode); //inisialisasi value etNim
        etNamaBarang = findViewById(R.id.editTextMainNamaBarang); //inisialisasi value etNama
        etJenis = findViewById(R.id.editTextMainJenis); //inisialisasi value etKelas
        btnTambah = findViewById(R.id.buttonMainTambah); //inisialisasi value btnTambah
        btnLihat = findViewById(R.id.buttonMainLihat); //inisialisasi value btnLihat

        aksiButton();//memanggil fungsi aksiButton()
    }
    public void aksiButton(){
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kode = etKode.getText().toString(); //mengambil Value etNim menjadi string
                String namabarang = etNamaBarang.getText().toString(); //mengambil Value etNim menjadi string
                String jenisbarang = etJenis.getText().toString(); //mengambil Value etNim menjadi string
                if (kode.equals("")||namabarang.equals("")||jenisbarang.equals("")){
                    Toast.makeText(getApplicationContext(),"Semua data harus diisi" , Toast.LENGTH_SHORT).show();
                    //memunculkan toast saat form masih ada yang kosong
                } else {
                    tambahData(kode,namabarang,jenisbarang); //memanggil fungsi tambahData()

                    etKode.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                    etNamaBarang.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                    etJenis.setText(""); //mengosongkan form setelah data berhasil ditambahkan
                }
            }
        });

        btnLihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //untuk pindah keActivity lain saat buttonlihat dipencet
                Intent intent = new Intent(MainActivity.this, ReadAllActivity.class);
                startActivity(intent);
            }
        });
    }


    public void tambahData(String kode, String namabarang, String jenisbarang){
        //koneksi ke file create.php, jika menggunakan localhost gunakan ip sesuai dengan ip kamu
        AndroidNetworking.post("http://192.168.43.92/crud_Android/create.php")
                .addBodyParameter("id", "") //id bersifat Auto_Increment tidak perlu diisi/(diisi NULL) cek create.php
                .addBodyParameter("kode",kode) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel nim
                .addBodyParameter("namabarang", namabarang) //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                .addBodyParameter("jenisbarang", jenisbarang) //mengirimkan data kelas_mahasiswa yang akan diisi dengan varibel kelas
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
                        Toast.makeText(getApplicationContext(),"Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toaalist saat data gagal ditambahkan
                    }
                });
        }
    }

