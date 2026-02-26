package model;

import controller.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MPengembalian {

    
    public DefaultTableModel cariPeminjaman(String keyword) {
        try (Connection conn = Koneksi.koneksiDb();
            Statement stmt = conn.createStatement()) {

            String sql = "SELECT p.id_peminjaman, p.id_anggota, b.judul AS nama_buku, p.tanggal_pinjam " +
                         "FROM tb_peminjaman p JOIN tb_buku b ON p.id_buku = b.id_buku " +
                         "WHERE p.status = 'Dipinjam' AND (p.id_peminjaman LIKE '%" + keyword + "%' OR p.id_anggota LIKE '%" + keyword + "%')";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel(new String[]{"ID Peminjaman", "ID Anggota", "Nama Buku", "Tanggal Pinjam"}, 0);
            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("id_peminjaman"), rs.getString("id_anggota"), rs.getString("nama_buku"), rs.getString("tanggal_pinjam")});
            }
            return model;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public void kembalikanBuku(String idPeminjamanTerpilih) throws SQLException {
        try (Connection kon = Koneksi.koneksiDb();
                PreparedStatement stmt = kon.prepareStatement("UPDATE tb_peminjaman SET status = 'Dikembalikan', tanggal_kembali = NOW() WHERE id_peminjaman = ?");
                PreparedStatement updateStok = kon.prepareStatement("UPDATE tb_buku SET stok = stok + 1 WHERE id_buku = (SELECT id_buku FROM tb_peminjaman WHERE id_peminjaman = ?)");
                PreparedStatement getTanggalPinjam = kon.prepareStatement("SELECT tanggal_pinjam FROM tb_peminjaman WHERE id_peminjaman = ?");) {

            getTanggalPinjam.setString(1, idPeminjamanTerpilih);
            ResultSet rs = getTanggalPinjam.executeQuery();
            if (rs.next()) {
                LocalDate tanggalPinjam = rs.getDate(1).toLocalDate();
                LocalDate tanggalKembali = LocalDate.now();
                long selisihHari = ChronoUnit.DAYS.between(tanggalPinjam, tanggalKembali);

                if (selisihHari > 7) { // Misalnya batas peminjaman adalah 7 hari
                    long denda = (selisihHari - 7) * 1000; //Memberi denda keterlambatan, misal Rp.1000
                    JOptionPane.showMessageDialog(null, "Buku terlambat dikembalikan! Denda: Rp " + denda);
                }
            }

            stmt.setString(1, idPeminjamanTerpilih);
            stmt.executeUpdate();

            updateStok.setString(1, idPeminjamanTerpilih);
            updateStok.executeUpdate();

        }
    }
}