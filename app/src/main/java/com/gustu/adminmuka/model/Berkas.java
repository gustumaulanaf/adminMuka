package com.gustu.adminmuka.model;

import com.google.gson.annotations.SerializedName;

public class Berkas {

    @SerializedName("hari")
    private String hari;

    @SerializedName("desa")
    private String desa;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("no_berkas")
    private String noBerkas;

    @SerializedName("pemohon")
    private String pemohon;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("no_hak")
    private String noHak;

    @SerializedName("tanggal")
    private String tanggal;

    @SerializedName("petugas")
    private String petugas;
    @SerializedName("permasalahan")
    private String permasalahan;

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getHari() {
        return hari;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getDesa() {
        return desa;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoBerkas(String noBerkas) {
        this.noBerkas = noBerkas;
    }

    public String getNoBerkas() {
        return noBerkas;
    }

    public void setPemohon(String pemohon) {
        this.pemohon = pemohon;
    }

    public String getPemohon() {
        return pemohon;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setNoHak(String noHak) {
        this.noHak = noHak;
    }

    public String getNoHak() {
        return noHak;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setPetugas(String petugas) {
        this.petugas = petugas;
    }

    public String getPetugas() {
        return petugas;
    }

    public String getPermasalahan() {
        return permasalahan;
    }

    public void setPermasalahan(String permasalahan) {
        this.permasalahan = permasalahan;
    }

    @Override
    public String toString() {
        return "Berkas{" +
                "hari='" + hari + '\'' +
                ", desa='" + desa + '\'' +
                ", noHp='" + noHp + '\'' +
                ", noBerkas='" + noBerkas + '\'' +
                ", pemohon='" + pemohon + '\'' +
                ", kecamatan='" + kecamatan + '\'' +
                ", noHak='" + noHak + '\'' +
                ", tanggal='" + tanggal + '\'' +
                ", petugas='" + petugas + '\'' +
                ", permasalahan='" + permasalahan + '\'' +
                '}';
    }
}