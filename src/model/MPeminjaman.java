package model;

import controller.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class MPeminjaman {

    public DefaultTableModel loadDataForPeminjaman() {
    try (Connection kon = Koneksi.koneksiDb();
         Statement stmt = kon.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT id_buku, judul, penulis, stok FROM tb_buku")) { 

        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Buku", "Judul", "Penulis", "Stok"}, 0);
        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getString("id_buku"),
                    rs.getString("judul"),
                    rs.getString("penulis"),
                    rs.getInt("stok")
            });
        }
        return model;
    } catch (SQLException e) {
        return null;
    }
}
    
    public void pinjamBuku(String idAnggota, String idBuku) throws SQLException {
    try (Connection kon = Koneksi.koneksiDb()) {

        // Memeriksa stok buku
        PreparedStatement cekStokStmt = kon.prepareStatement("SELECT stok FROM tb_buku WHERE id_buku = ?");
        cekStokStmt.setString(1, idBuku);
        ResultSet rsStok = cekStokStmt.executeQuery();

        if (rsStok.next()) {
            int stokBuku = rsStok.getInt("stok");

            if (stokBuku > 0) {
                // Insert ke tabel peminjaman
                PreparedStatement stmt = kon.prepareStatement(
                        "INSERT INTO tb_peminjaman (id_anggota, id_buku, tanggal_pinjam, status) " +
                                "VALUES (?, ?, NOW(), 'Dipinjam')");
                stmt.setString(1, idAnggota);
                stmt.setString(2, idBuku);
                stmt.executeUpdate();

                // Update stok buku
                PreparedStatement updateStok = kon.prepareStatement("UPDATE tb_buku SET stok = stok - 1 WHERE id_buku = ?");
                updateStok.setString(1, idBuku);
                updateStok.executeUpdate();

            } else {
                throw new SQLException("Buku ini sedang tidak ada.");
            }
            
            } 
            else {
            throw new SQLException("Buku tidak ditemukan.");
            }
        
        }
    }
}