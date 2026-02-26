package controller;

import view.VDataAnggota;
import view.VLogin;
import view.VPustakawan;
import javax.swing.JOptionPane;
import view.VDataBuku;
import view.VPeminjaman;
import view.VPengembalian;

public class CPustakawan {

    protected VPustakawan view;

    public CPustakawan(VPustakawan view) {
        this.view = view;
    }

    public void exit() {
        int keluar = JOptionPane.showOptionDialog(
                view,
                "Keluar dari Aplikasi",
                "Exit",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, null, null
        );
        if (keluar == JOptionPane.YES_OPTION) {
            new VLogin().setVisible(true);
            view.dispose();
        }
    }

    public void showDataAnggota() {
        new VDataAnggota().setVisible(true);
        view.dispose();
    }
    
    public void showDataBuku() {
        new VDataBuku().setVisible(true);
        view.dispose();
    }
    
    public void showPeminjaman() {
        new VPeminjaman().setVisible(true);
        view.dispose();
    }
    
    public void showPengembalian(){
        new VPengembalian().setVisible(true);
        view.dispose();
    }
}