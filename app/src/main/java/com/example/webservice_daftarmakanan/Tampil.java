package com.example.webservice_daftarmakanan;

public class Tampil {
    String Id, Nama, Harga, Deskripsi;

    public Tampil(){}

        public Tampil(String id, String nama, String harga, String deskripsi){
            this.Id = id;
            this.Nama = nama;
            this.Harga = harga;
            this.Deskripsi = deskripsi;
        }

        @Override
                public String toString(){
            return "Id : " + Id + "" + "\n" + "Nama : " + Nama + "" + "\n" + "Harga : " + Harga + "" + "\n" + "Deskripsi : " + Deskripsi + "" ;

        }
    }

