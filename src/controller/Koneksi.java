package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Koneksi {
    public static Connection koneksiDb() {
        Connection connection = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
//            String url = "jdbc:mysql://localhost:3306/db_library?serverTimezone=UTC"; //?serverTimezone=UTC
//            String user = "root";
//            String password = "";
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_library", "root", "");
            System.out.println("Koneksi Berhasil!");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver tidak ditemukan: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
            e.addSuppressed(e);
        }
        return connection;
    }


public static void main(String[] args) {
        koneksiDb(); 
    }
}
