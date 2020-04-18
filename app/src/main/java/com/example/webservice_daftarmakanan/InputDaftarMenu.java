package com.example.webservice_daftarmakanan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InputDaftarMenu extends AppCompatActivity {
    EditText editId, editNama, editHarga, editDesk;
    Button btnSimpan, btnKembali;

    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_menu);

        editId = findViewById(R.id.editId);
        editNama = findViewById(R.id.edit_nama);
        editHarga = findViewById(R.id.edit_harga);
        editDesk = findViewById(R.id.edit_desk);
        btnSimpan = findViewById(R.id.btnSimpanMenu);
        btnKembali = findViewById(R.id.btnKembali);

        requestQueue = Volley.newRequestQueue(this);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iKembali = new Intent(InputDaftarMenu.this, MainActivity.class);
                startActivity(iKembali);
            }
        });
    }


    void simpan() {
        final String Id = editId.getText().toString().trim();
        final String Nama = editNama.getText().toString().trim();
        final String Harga = editHarga.getText().toString().trim();
        final String Deskripsi = editDesk.getText().toString().trim();

        stringRequest = new StringRequest(Request.Method.POST, Input.SIMPAN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains(Input.SIMPAN_SUCCESS)) {
                            Toast.makeText(InputDaftarMenu.this, "Data Berhasil disimpan", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(InputDaftarMenu.this, "Daftar Gagal disimpan", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InputDaftarMenu.this, "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(Input.KEY_ID, Id);
                params.put(Input.KEY_NAMA, Nama);
                params.put(Input.KEY_HARGA, Harga);
                params.put(Input.KEY_DESK, Deskripsi);

                return params;
            }
        };
        requestQueue.add(stringRequest);
        requestQueue.start();

    }

}
