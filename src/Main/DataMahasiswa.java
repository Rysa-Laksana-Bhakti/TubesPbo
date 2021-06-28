package Main;

public class DataMahasiswa {
    private int idKel;
    private String anggotaKel;
    private String alamatKel;
    private String waktuAwal;
    private String waktuAkhir;
    private String Persetujuan;

    public DataMahasiswa(int idKel, String anggotaKel, String alamatKel, String waktuAwal, String waktuAkhir, String Persetujuan) {
        this.idKel = idKel;
        this.anggotaKel = anggotaKel;
        this.alamatKel = alamatKel;
        this.waktuAwal = waktuAwal;
        this.waktuAkhir = waktuAkhir;
        this.Persetujuan = Persetujuan;
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

    public String getPersetujuan(){return Persetujuan;}







}
