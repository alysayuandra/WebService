package com.example.webservice_daftarmakanan;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TampilDaftarMenu extends AppCompatActivity {
    ListView listView;
    StringBuffer stringBuffer;
    StringRequest stringRequest;
    RequestQueue requestQueue;
    ArrayList<Tampil> list;
    ArrayList<String> list2;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_menu);

        listView = findViewById(R.id.listMenu);
        requestQueue = Volley.newRequestQueue(this);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        stringBuffer = new StringBuffer();

        tampilDaftarMenu();
    }

    void tampilDaftarMenu(){
        String URL_TAMPIL  = "http://10.0.2.2/menuMakanan/tampil_menu.php";
        stringRequest = new StringRequest(Request.Method.GET, URL_TAMPIL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                stringBuffer.append(response);
                aturJson();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TampilDaftarMenu.this, "Error" + error, Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(stringRequest);
        }

    void aturJson(){
        try {
            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("menu");
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                Tampil tampil = new Tampil();

                tampil.Id = jsonObj.getString("Id");
                tampil.Nama = jsonObj.getString("Nama");
                tampil.Harga = jsonObj.getString("Harga");
                tampil.Deskripsi = jsonObj.getString("Deskripsi");

                list.add(tampil);
                list2.add(tampil.toString());
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list2);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
