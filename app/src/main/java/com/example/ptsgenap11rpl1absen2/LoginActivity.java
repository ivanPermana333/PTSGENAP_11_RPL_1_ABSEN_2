package com.example.ptsgenap11rpl1absen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        password = findViewById(R.id.password);
        username = findViewById(R.id.username);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.initialize(getApplicationContext()); //inisialisasi library FAN
                AndroidNetworking.post("http://192.168.43.92/crud_Android/Login.php")
                        .addBodyParameter("username",username.getText().toString().trim().toUpperCase()) //mengirimkan data nim_mahasiswa yang akan diisi dengan varibel nim
                        .addBodyParameter("password", password.getText().toString().trim().toUpperCase()) //mengirimkan data nama_mahasiswa yang akan diisi dengan varibel nama
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Handle Response
                                Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                //memunculkan Toast saat data berhasil ditambahkan

                            }
                            @Override
                            public void onError(ANError error) {
                                //Handle Error
                                Toast.makeText(getApplicationContext(),"Data berhasil ditambahkan" , Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                //memunculkan Toast saat data gagal ditambahkan
                            }
                        });
            }
        });
    }
}
