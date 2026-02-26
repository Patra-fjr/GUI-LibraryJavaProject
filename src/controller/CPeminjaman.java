package controller;

import model.MPeminjaman;
import view.VPeminjaman;
import view.VPustakawan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

public class CPeminjaman {

    private final VPeminjaman view;
    private final MPeminjaman model;
    private String idBukuTerpilih;

    public CPeminjaman(VPeminjaman view) {
        this.view = view;
        this.model = new MPeminjaman();
    }

    public void loadData() {
        DefaultTableModel tableModel = model.loadDataForPeminjaman();
        if (tableModel != null) {
            view.setTableModel(tableModel);
        } else {
            JOptionPane.showMessageDialog(view, "Gagal memuat data buku.");
        }
    }

    public void cariBuku() {
        String keyword = view.getCariBuku();
        DefaultTableModel tableModel = model.loadDataForPeminjaman();
        if (tableModel != null) {
            DefaultTableModel filteredModel = new DefaultTableModel(new String[]{"ID Buku", "Nama Buku", "Penulis", "Stok"}, 0);
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String idBuku = tableModel.getValueAt(i, 0).toString();
                String judulBuku = tableModel.getValueAt(i, 1).toString();
                String penulis = tableModel.getValueAt(i, 2).toString();
                if (idBuku.toLowerCase().contains(keyword.toLowerCase()) || judulBuku.toLowerCase().contains(keyword.toLowerCase()) || penulis.toLowerCase().contains(keyword.toLowerCase())) {
                    filteredModel.addRow(new Object[]{idBuku, judulBuku, penulis, tableModel.getValueAt(i, 3)});
                }
            }
            view.setTableModel(filteredModel);

            // Listener untuk mendapatkan idBukuTerpilih (sama seperti sebelumnya)
            view.getTabelBuku().getSelectionModel().addListSelectionListener(event -> {
                if (!event.getValueIsAdjusting() && view.getTabelBuku().getSelectedRow() != -1) {
                    idBukuTerpilih = view.getTabelBuku().getValueAt(view.getTabelBuku().getSelectedRow(), 0).toString();
                }
            });
        } else {
            JOptionPane.showMessageDialog(view, "Gagal memuat data buku.");
        }
    }

    // ... (kode lainnya di CPeminjaman) ...

public void pinjamBuku() {
    String idAnggota = view.getIdAnggota();
    if (idBukuTerpilih == null || idAnggota.isEmpty()) {
        JOptionPane.showMessageDialog(view, "Pilih buku dan masukkan ID Anggota!");
        return;
    }
    try {
        model.pinjamBuku(idAnggota, idBukuTerpilih);
        JOptionPane.showMessageDialog(view, "Buku berhasil dipinjam!");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(view,e.getMessage());
    }
}
    
    public void exit() {
    int keluar = JOptionPane.showOptionDialog(
                view,
                "Keluar dari Kelola Peminjaman?",
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null
        );
        if (keluar == JOptionPane.YES_OPTION) {
            new VPustakawan().setVisible(true);
            view.dispose();
        }
    }
    
    public void clear() {
        view.clear();
    }
}