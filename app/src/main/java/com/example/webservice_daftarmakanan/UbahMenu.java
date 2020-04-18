package com.example.webservice_daftarmakanan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

public class UbahMenu extends AppCompatActivity {
    EditText editId, editNama, editHarga, editDesk;
    Button btnEdit;
    ProgressDialog pd;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_menu);

        editId = findViewById(R.id.editId);
        editNama = findViewById(R.id.edit_nama);
        editHarga = findViewById(R.id.edit_harga);
        editDesk = findViewById(R.id.edit_desk);
        btnEdit = findViewById(R.id.btn_edit);
        pd = new ProgressDialog(UbahMenu.this);


        requestQueue = Volley.newRequestQueue(this);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });
    }

    void updateData(){
        final String id = editId.getText().toString().trim();
        final String nama_mkn = editNama.getText().toString().trim();
        final String harga_mkn = editHarga.getText().toString().trim();
        final String desk_mkn = editDesk.getText().toString().trim();

        pd.setMessage("Ubah data ...");
        pd.setCancelable(false);
        pd.show();

        stringRequest = new StringRequest(Request.Method.POST, Ubah.UBAH_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(UbahMenu.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(new Intent(UbahMenu.this, MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(UbahMenu.this, "Gagal menambahkan data",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> map = new HashMap<>();
                map.put(Ubah.KEY_ID, id);
                map.put(Ubah.KEY_NAMA, nama_mkn);
                map.put(Ubah.KEY_HARGA, harga_mkn);
                map.put(Ubah.KEY_DESK, desk_mkn);

                return map;
            }
        };
        requestQueue.add(stringRequest);
        requestQueue.start();
    }



}

