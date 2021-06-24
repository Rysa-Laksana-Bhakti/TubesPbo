package sample;

public class DataMahasiswa {
    private int idKel;
    private String anggotaKel;
    private String alamatKel;
    private String waktuAwal;
    private String waktuAkhir;

    public DataMahasiswa(int idKel, String anggotaKel, String alamatKel, String waktuAwal, String waktuAkhir) {
        this.idKel = idKel;
        this.anggotaKel = anggotaKel;
        this.alamatKel = alamatKel;
        this.waktuAwal = waktuAwal;
        this.waktuAkhir = waktuAkhir;
    }

    public int getIdKel() {
        return idKel;
    }

    public String getAnggotaKel() {
        return anggotaKel;
    }

    public String getAlamatKel() {
        return alamatKel;
    }

    public String getWaktuAwal() {
        return waktuAwal;
    }

    public String getWaktuAkhir() {
        return waktuAkhir;
    }







}
