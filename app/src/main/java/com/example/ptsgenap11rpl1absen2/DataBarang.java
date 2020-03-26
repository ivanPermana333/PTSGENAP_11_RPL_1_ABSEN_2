package com.example.ptsgenap11rpl1absen2;

public class DataBarang {
    //inisialisasi variabel
    String id; //idMahasiswa didatabase merupakan int (Auto Increment)
    String kode; //namaMahasiswa didatabase merupakan string

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNamabarang() {
        return namabarang;
    }

    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }

    public String getJenisbarang() {
        return jenisbarang;
    }

    public void setJenisbarang(String jenisbarang) {
        this.jenisbarang = jenisbarang;
    }

    String namabarang; //nimMahasiswa didatabase merupakan string
    String jenisbarang; //kelasMahasiswa didatabase merupakan string

    //construktor datamahasiswa


    //getter dan setter

}
