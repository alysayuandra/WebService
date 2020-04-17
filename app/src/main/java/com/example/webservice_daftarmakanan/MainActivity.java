package com.example.webservice_daftarmakanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button inputMenu, tampilMenu, hapusMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMenu = findViewById(R.id.btn_input_menu);
        tampilMenu = findViewById(R.id.btn_tampil_menu);
        hapusMenu = findViewById(R.id.btn_hapus);

       inputMenu.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent iInput = new Intent(MainActivity.this, InputDaftarMenu.class);
               startActivity(iInput);
           }
       });

       tampilMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent iTampil = new Intent(MainActivity.this, TampilDaftarMenu.class);
               startActivity(iTampil);
           }
       });

       hapusMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent iHapus = new Intent(MainActivity.this, HapusMenu.class);
               startActivity(iHapus);
           }
       });
    }
}
