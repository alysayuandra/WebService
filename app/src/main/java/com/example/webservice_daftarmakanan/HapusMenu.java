package com.example.webservice_daftarmakanan;

import android.app.Activity;
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

public class HapusMenu extends AppCompatActivity {
    EditText editId;
    Button btnHapus;
    ProgressDialog pd;
    RequestQueue requestQueue;
    StringRequest stringRequest;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hapus_menu);

        editId = findViewById(R.id.editId);
        btnHapus = findViewById(R.id.btn_hapus);
        pd = new ProgressDialog(HapusMenu.this);

        requestQueue = Volley.newRequestQueue(this);
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusData();
            }
        });
    }

    private void hapusData(){
        pd.setMessage("Hapus data ...");
        pd.setCancelable(false);
        pd.show();
//        final String Id = editId.getText().toString().trim();

        stringRequest= new StringRequest(Request.Method.POST,Hapus.HAPUS_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(HapusMenu.this,"pesan : " +res.getString("message"), Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity(new Intent(HapusMenu.this,MainActivity.class));
                        if (response.contains(Hapus.HAPUS_SUCCESS)){
                            Toast.makeText(HapusMenu.this, "Data berhasil dihapus", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(HapusMenu.this, "Data Gagal dihapus", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                        Toast.makeText(HapusMenu.this, "Error" + error, Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
//                map.put(Hapus.KEY_ID, Id);
                map.put("id",editId.getText().toString());
                return map;
            }
        };

        requestQueue.add(stringRequest);
        requestQueue.start();
    }
}
