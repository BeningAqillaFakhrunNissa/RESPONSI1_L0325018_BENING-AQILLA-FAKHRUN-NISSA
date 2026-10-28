package Responsi1;

import java.util.Scanner;

public class MainLogistik {

    private static Scanner input = new Scanner(System.in);

    public static void validasiBerat(double berat) throws BeratInvalidException {
        if (berat <= 0) {
            throw new BeratInvalidException("Berat barang harus lebih dari 0 kg!");
        }
    }

    public static void validasiKode(String kode) {
        if (!kode.startsWith("EDX-")) {
            throw new IllegalArgumentException("Kode barang harus diawali EDX-");
        }
    }

    private static String inputKode() {
        while (true) {
            try {
                System.out.print("Kode barang (awali EDX-) : ");
                String kode = input.nextLine().trim();
                validasiKode(kode);
                return kode;
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            } finally {
                System.out.println("(Pemeriksaan kode selesai)");
            }
        }
    }

    private static double inputBerat() {
        while (true) {
            try {
                System.out.print("Berat (kg)               : ");
                double berat = Double.parseDouble(input.nextLine().trim());
                validasiBerat(berat);
                return berat;
            } catch (BeratInvalidException e) {
                System.out.println("[ERROR] " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Berat harus berupa angka (contoh: 2.5)!");
            } finally {
                System.out.println("(Pemeriksaan berat selesai)");
            }
        }
    }

    public static void main(String[] args) {
        ManajemenMuatan manajer = new ManajemenMuatan();
        boolean selesai = false;
        
        // Membuat menu interaktif di konsol, sehingga user bisa terus 
        // menambahkan barang hingga memilih menu "Selesai dan Cetak Manifes"
        do {
            System.out.println("\n=== SISTEM LOGISTIK PENDIDIKAN DAERAH 3T ===");
            System.out.println("1. Tambah barang");
            System.out.println("2. Tampilkan daftar barang");
            System.out.println("3. Hapus barang berdasarkan kode");
            System.out.println("4. Selesai dan Cetak Manifes");
            System.out.print("Pilih menu: ");

            int pilihan;
            try {
                pilihan = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Menu harus berupa angka 1-4!");
                continue;
            }

            switch (pilihan) {
                case 1:
                    String kode = inputKode();
                    System.out.print("Nama barang              : ");
                    String nama = input.nextLine().trim();
                    System.out.print("Kategori                 : ");
                    String kategori = input.nextLine().trim();
                    double berat = inputBerat();

                    manajer.tambahBarang(new BarangBantuan(kode, nama, kategori, berat));
                    System.out.println("Barang berhasil ditambahkan. Total: "
                            + manajer.hitungTotalBarang() + " item");
                    break;

                case 2:
                    manajer.tampilkanSemuaBarang();
                    break;

                case 3:
                    System.out.print("Masukkan kode barang yang dihapus: ");
                    String kodeHapus = input.nextLine().trim();
                    if (manajer.hapusBarang(kodeHapus)) {
                        System.out.println("Barang " + kodeHapus + " berhasil dihapus.");
                    } else {
                        System.out.println("[PERINGATAN] Kode " + kodeHapus + " tidak ditemukan.");
                    }
                    break;

                case 4:
                    if (manajer.isKosong()) {
                        System.out.println("[PERINGATAN] Belum ada barang, manifes tidak dapat dicetak.");
                    } else {
                        manajer.cetakManifes("manifest_pengiriman.txt");
                        selesai = true;
                    }
                    break;

                default:
                    System.out.println("[ERROR] Pilihan tidak tersedia!");
                    break;
            }
        } while (!selesai);

        System.out.println("Program selesai. Terima kasih!");
        input.close();
    }
}