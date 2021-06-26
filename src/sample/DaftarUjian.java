package sample;

import java.time.LocalDate;

public class DaftarUjian {
    private int ID;
    private String Nama;
    private String NIM;
    private String Email;
    private String waktuUjian;
    private String Nilai;

    public DaftarUjian(int ID, String nama, String NIM, String email, String waktuUjian, String nilai) {
        this.ID = ID;
        this.Nama = nama;
        this.NIM = NIM;
        this.Email = email;
        this.waktuUjian = waktuUjian;
        this.Nilai = nilai;
    }

    public int getID() {
        return ID;
    }

    public String getNama() {
        return Nama;
    }

    public String getNIM() {
        return NIM;
    }

    public String getEmail() {
        return Email;
    }

    public String getWaktuUjian() {
        return waktuUjian;
    }

    public String getNilai() {
        return Nilai;
    }


}
