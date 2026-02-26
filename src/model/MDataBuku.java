package model;

import controller.Koneksi;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class MDataBuku {

public DefaultTableModel loadData() {
    try (Connection kon = Koneksi.koneksiDb();
        Statement stmt = kon.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tb_buku")) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Buku", "Judul", "Penulis", "Penerbit", "Tahun", "Stok"},0);
        while (rs.next()) {
                model.addRow(new Object[]{
                rs.getString("id_buku"),
                rs.getString("judul"),
                rs.getString("penulis"),
                rs.getString("penerbit"),
                rs.getInt("tahun_terbit"),
                rs.getInt("stok")
                });
            }
            return model;
        } catch (SQLException e) {
            e.printStackTrace(); // Atau logging yang lebih baik
            return null;
        }
    }

    public String generateId() {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_id = "SELECT * FROM tb_buku ORDER BY id_buku DESC";
            ResultSet rs = st.executeQuery(sql_id);
            if (rs.next()) {
                String id_buku = rs.getString("id_buku").substring(1);
                String BN = "" + (Integer.parseInt(id_buku) + 1);
                String Nol = "";

                if (BN.length() == 1) {
                    Nol = "0000";
                } else if (BN.length() == 2) {
                    Nol = "000";
                } else if (BN.length() == 3) {
                    Nol = "00";
                }
                return "B" + Nol + BN;
            } else {
                return "B00001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void tambahBuku(String idBuku, String judul, String penulis, String penerbit, int tahun, int stok) {
        try (Connection kon = Koneksi.koneksiDb();
             PreparedStatement stmt = kon.prepareStatement("INSERT INTO tb_buku VALUES (?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, idBuku);
            stmt.setString(2, judul);
            stmt.setString(3, penulis);
            stmt.setString(4, penerbit);
            stmt.setInt(5, tahun);
            stmt.setInt(6, stok);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Atau logging yang lebih baik
        }
    }

    public void editBuku(String idBuku, String judul, String penulis, String penerbit, int tahun, int stok) {
        try (Connection kon = Koneksi.koneksiDb();
             PreparedStatement stmt = kon.prepareStatement("UPDATE tb_buku SET judul=?, penulis=?, penerbit=?, tahun_terbit=?, stok=? WHERE id_buku=?")) {
            stmt.setString(1, judul);
            stmt.setString(2, penulis);
            stmt.setString(3, penerbit);
            stmt.setInt(4, tahun);
            stmt.setInt(5, stok);
            stmt.setString(6, idBuku);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Atau logging yang lebih baik
        }
    }

    public void hapusBuku(String idBuku) {
        try (Connection kon = Koneksi.koneksiDb();
             PreparedStatement stmt = kon.prepareStatement("DELETE FROM tb_buku WHERE id_buku=?")) {
            stmt.setString(1, idBuku);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Atau logging yang lebih baik
        }
    }
}