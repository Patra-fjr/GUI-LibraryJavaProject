package model;

import controller.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MDataAnggota {

    public DefaultTableModel loadData() {
        Connection kon = Koneksi.koneksiDb();
        String[] header = {"ID ANGGOTA", "NIS", "NAMA ANGGOTA", "JK", "TINGKAT", "JURUSAN", "KELAS", "NO HP", "STATUS"};
        DefaultTableModel data = new DefaultTableModel(null, header);
        String sql_data = "SELECT * FROM tb_anggota";

        try {
            Statement st = kon.createStatement();
            ResultSet rs = st.executeQuery(sql_data);
            while (rs.next()) {
                String d1 = rs.getString(1);
                String d2 = rs.getString(2);
                String d3 = rs.getString(3);
                String d4 = rs.getString(4);
                String d5 = rs.getString(5);
                String d6 = rs.getString(6);
                String d7 = rs.getString(7);
                String d8 = rs.getString(8);
                String d9 = rs.getString(9);

                String[] d = {d1, d2, d3, d4, d5, d6, d7, d8, d9}; 
                data.addRow(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public String generateId() {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_id = "SELECT * FROM tb_anggota ORDER BY id_anggota DESC";
            ResultSet rs = st.executeQuery(sql_id);
            if (rs.next()) {
                String id_anggota = rs.getString("id_anggota").substring(1);
                String AN = "" + (Integer.parseInt(id_anggota) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "0000";
                } else if (AN.length() == 2) {
                    Nol = "000";
                } else if (AN.length() == 3) {
                    Nol = "00";
                }
                return "A" + Nol + AN;
            } else {
                return "A00001";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadTingkat(javax.swing.JComboBox<String> KTINGKAT) {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_tingkat = "SELECT * FROM tb_tingkat";
            ResultSet rs = st.executeQuery(sql_tingkat);
            while (rs.next()) {
                KTINGKAT.addItem(rs.getString("id_tingkat"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadJurusan(javax.swing.JComboBox<String> KJURUSAN) {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_jurusan = "SELECT kd_jurusan FROM tb_jurusan";
            ResultSet rs = st.executeQuery(sql_jurusan);
            while (rs.next()) {
                KJURUSAN.addItem(rs.getString("kd_jurusan"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadKelas(javax.swing.JComboBox<String> KKELAS) {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_kelas = "SELECT id_kelas FROM tb_kelas";
            ResultSet rs = st.executeQuery(sql_kelas);
            while (rs.next()) {
                KKELAS.addItem(rs.getString("id_kelas"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String id, String nis, String nama, String jk, String tingkat, String jurusan, String kelas, String nope, String status) {
        try (Connection kon = Koneksi.koneksiDb();
        PreparedStatement pst = kon.prepareStatement("INSERT INTO tb_anggota VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

        pst.setString(1, id);
        pst.setString(2, nis);
        pst.setString(3, nama);
        pst.setString(4, jk);
        pst.setString(5, tingkat);
        pst.setString(6, jurusan);
        pst.setString(7, kelas);
        pst.setString(8, nope);
        pst.setString(9, status);

        pst.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public void updateData(String id, String nis, String nama, String jk, String tingkat, String jurusan, String kelas, String nope, String status) {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_update = "UPDATE tb_anggota SET NIS='" + nis
                    + "',Nama ='" + nama
                    + "',jk='" + jk
                    + "',id_tingkat='" + tingkat
                    + "',kd_jurusan='" + jurusan
                    + "',id_kelas='" + kelas
                    + "',nope='" + nope
                    + "',status='" + status
                    + "'WHERE id_anggota='" + id + "'";
            st.execute(sql_update);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(String id) {
        try {
            Connection kon = Koneksi.koneksiDb();
            Statement st = kon.createStatement();
            String sql_delete = "DELETE from tb_anggota WHERE id_anggota='" + id + "'";
            st.executeUpdate(sql_delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}