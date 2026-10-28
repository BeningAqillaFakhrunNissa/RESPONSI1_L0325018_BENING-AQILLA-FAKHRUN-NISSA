package Responsi1;
// 1. Kebutuhan Entitas
public class BarangBantuan {
    // Atribut
    private String kodeBarang;
    private String namaBarang;
    private String kategori;
    private double beratKg;
    
   // Constructor : Inisialisasi seluruh atribut
   public BarangBantuan(String kodeBarang, String namaBarang, String kategori, double beratKg) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategori = kategori;
        this.beratKg = beratKg;
    } 
   
    public String getKodeBarang() { return kodeBarang; }
    public String getNamaBarang() { return namaBarang; }
    public String getKategori()   { return kategori; }
    public double getBeratKg()    { return beratKg; }
    
    public void tampilkan() {
    System.out.println(kodeBarang + " | " + namaBarang + " | " + kategori + " | " + beratKg + " kg");
    }
}
