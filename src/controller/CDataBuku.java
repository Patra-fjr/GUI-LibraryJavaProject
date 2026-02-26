package controller;

import model.MDataBuku;
import view.VDataBuku;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.VPustakawan;

public class CDataBuku {

    protected VDataBuku view;
    protected MDataBuku model;

    public CDataBuku(VDataBuku view) {
        this.view = view;
        this.model = new MDataBuku();
    }

    public void loadData() {
        DefaultTableModel tableModel = model.loadData();
        if (tableModel != null) {
            view.setTableModel(tableModel);
        } else {
            JOptionPane.showMessageDialog(view, "Gagal memuat data buku.");
        }
    }
    
    public void generateId() {
        String id = model.generateId();
        if (id != null) {
            view.setID(id);
        } else {
            JOptionPane.showMessageDialog(view, "Gagal generate ID Anggota");
        }
    }

    public void tambahBuku() {
        try {
            String idBuku = view.getIdBuku();
            String judul = view.getJudul();
            String penulis = view.getPenulis();
            String penerbit = view.getPenerbit();
            int tahun = Integer.parseInt(view.getTahunTerbit());
            int stok = Integer.parseInt(view.getStok());

            model.tambahBuku(idBuku, judul, penulis, penerbit, tahun, stok);
            JOptionPane.showMessageDialog(view, "Buku berhasil ditambahkan!");
            loadData(); // Refresh data setelah tambah
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Tahun dan stok harus berupa angka.");
        }
    }

    public void editBuku() {
        try {
            String idBuku = view.getIdBuku();
            String judul = view.getJudul();
            String penulis = view.getPenulis();
            String penerbit = view.getPenerbit();
            int tahun = Integer.parseInt(view.getTahunTerbit());
            int stok = Integer.parseInt(view.getStok());

            model.editBuku(idBuku, judul, penulis, penerbit, tahun, stok);
            JOptionPane.showMessageDialog(view, "Buku berhasil diperbarui!");
            loadData(); // Refresh data setelah edit
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Tahun dan stok harus berupa angka.");
        }
    }

    public void hapusBuku() {
        String idBuku = view.getIdBuku();
        model.hapusBuku(idBuku);
        JOptionPane.showMessageDialog(view, "Buku berhasil dihapus!");
        loadData(); // Refresh data setelah hapus
    }
    
    public void exit() {
    int keluar = JOptionPane.showOptionDialog(
                view,
                "Keluar dari Kelola Data Buku?",
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