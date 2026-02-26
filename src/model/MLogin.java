package model;

import controller.Koneksi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MLogin {

    public boolean checkLogin(String username, String password, String level) {
        Connection kon = Koneksi.koneksiDb();
        try {
            Statement st = kon.createStatement();
            String sql = "SELECT * FROM tb_user WHERE username='" + username + "' and password='" + password + "' and level='" + level + "'";
            ResultSet rs = st.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getLevel(String username, String password) {
        Connection kon = Koneksi.koneksiDb();
        try {
            Statement st = kon.createStatement();
            String sql = "SELECT level FROM tb_user WHERE username='" + username + "' and password='" + password + "'";
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("level");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}