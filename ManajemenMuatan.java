package Responsi1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ManajemenMuatan {
    
    // untuk menyimpan barang-barang yang didata
    private ArrayList<BarangBantuan> daftarBarang = new ArrayList<>();

    public void tambahBarang(BarangBantuan barangBaru) {
        daftarBarang.add(barangBaru);
    }

    public void tampilkanSemuaBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang yang didata.");
            return;
        }
        System.out.println("--- Daftar Muatan ---");
        for (BarangBantuan b : daftarBarang) {
            b.tampilkan();
        }
    }

    public boolean hapusBarang(String kodeBarang) {
        Iterator<BarangBantuan> it = daftarBarang.iterator();
        while (it.hasNext()) {
            BarangBantuan b = it.next();
            if (b.getKodeBarang().equals(kodeBarang)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean isKosong() {
        return daftarBarang.isEmpty();
    }

    public int hitungTotalBarang() {
        return daftarBarang.size();
    }

    public double hitungTotalBerat() {
        double total = 0;
        for (BarangBantuan b : daftarBarang) {
            total += b.getBeratKg();
        }
        return total;
    }
    
    // memberikan label armada
    public String tentukanArmada() {
        if (hitungTotalBerat() < 50) {
            return "Mobil Pickup";
        } else {
            return "Truk Ekspedisi";
        }
    }

    public void cetakManifes(String namaFile) {
        int totalBarang = hitungTotalBarang();
        double totalBerat = hitungTotalBerat();
        String armada = tentukanArmada();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(namaFile))) {
            bw.write("==============================================================");
            bw.newLine();
            bw.write("        MANIFEST PENGIRIMAN - LOGISTIK PENDIDIKAN DAERAH 3T");
            bw.newLine();
            bw.write("==============================================================");
            bw.newLine();
            bw.write(String.format("%-4s %-12s %-22s %-12s %10s",
                    "No", "Kode", "Nama Barang", "Kategori", "Berat(kg)"));
            bw.newLine();
            bw.write("--------------------------------------------------------------");
            bw.newLine();

            int no = 1;
            for (BarangBantuan b : daftarBarang) {
                bw.write(String.format("%-4d %-12s %-22s %-12s %10.2f",
                        no++, b.getKodeBarang(), b.getNamaBarang(),
                        b.getKategori(), b.getBeratKg()));
                bw.newLine();
            }

            bw.write("--------------------------------------------------------------");
            bw.newLine();
            bw.write("Total Barang : " + totalBarang + " item");
            bw.newLine();
            bw.write(String.format("Total Berat  : %.2f kg", totalBerat));
            bw.newLine();
            bw.write("Armada       : " + armada);
            bw.newLine();
            bw.write("==============================================================");
            bw.newLine();

            System.out.println("Data manifes berhasil diekspor ke " + namaFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menulis file: " + e.getMessage());
        }
    }
}