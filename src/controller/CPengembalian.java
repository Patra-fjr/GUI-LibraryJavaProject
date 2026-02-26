package controller;

import model.MPengembalian;
import view.VPengembalian;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import view.VPustakawan;

public class CPengembalian {

    private final VPengembalian view;
    private final MPengembalian model;
    private String idPeminjamanTerpilih;

    public CPengembalian(VPengembalian view) {
        this.view = view;
        this.model = new MPengembalian();
    }

    public void cariPeminjaman() {
        String keyword = view.getCariPeminjaman();
        DefaultTableModel tableModel = model.cariPeminjaman(keyword); 
        if (tableModel != null) {
            view.setTableModel(tableModel);
            view.getTabelPeminjaman().getSelectionModel().addListSelectionListener(event -> {
                if (!event.getValueIsAdjusting() && view.getTabelPeminjaman().getSelectedRow() != -1) {
                    idPeminjamanTerpilih = view.getTabelPeminjaman().getValueAt(view.getTabelPeminjaman().getSelectedRow(), 0).toString();
                }
            });
        } 
        else {
            JOptionPane.showMessageDialog(view, "Gagal memuat data peminjaman.");
        }
    }

    public void kembalikanBuku() {
        if (idPeminjamanTerpilih == null) {
            JOptionPane.showMessageDialog(view, "Pilih peminjaman yang akan dikembalikan!");
            return;
        }
        try {
            model.kembalikanBuku(idPeminjamanTerpilih);
            JOptionPane.showMessageDialog(view, "Buku berhasil dikembalikan!");
            view.clearFields();
            
            // Refresh data setelah pengembalian
            DefaultTableModel tableModel = (DefaultTableModel) view.getTabelPeminjaman().getModel();
            tableModel.setRowCount(0);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Terjadi kesalahan: " + e.getMessage());
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

}