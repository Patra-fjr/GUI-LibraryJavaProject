package controller;

import model.MDataAnggota;
import view.VDataAnggota;
import view.VPustakawan;
import javax.swing.JOptionPane;

public class CDataAnggota {

    protected VDataAnggota view;
    protected MDataAnggota model;

    public CDataAnggota(VDataAnggota view) {
        this.view = view;
        this.model = new MDataAnggota();
    }

    public void loadData() {
        view.setTableData(model.loadData());
    }

    public void generateId() {
        String id = model.generateId();
        if (id != null) {
            view.setID(id);
        } else {
            JOptionPane.showMessageDialog(view, "Gagal generate ID Anggota");
        }
    }

    public void loadTingkat() {
    model.loadTingkat(view.getKTINGKAT());
}

public void loadJurusan() {
    model.loadJurusan(view.getKJURUSAN());
}

public void loadKelas() {
    model.loadKelas(view.getKKELAS());
}

    public void insertData() {
    String jk = view.getJK();
    model.insertData(
            view.getID(),
            view.getNIS(),
            view.getNAMA(),
            jk,
            view.getKTINGKAT().getSelectedItem().toString(), 
            view.getKJURUSAN().getSelectedItem().toString(), 
            view.getKKELAS().getSelectedItem().toString(), 
            view.getNOPE(),
            view.getSTATUS()
    );
    JOptionPane.showMessageDialog(view, "Data Anggota Berhasil Diinput");
    
}

public void updateData() {
    String jk = view.getJK();
    model.updateData(
            view.getID(),
            view.getNIS(),
            view.getNAMA(),
            jk,
            view.getKTINGKAT().getSelectedItem().toString(), // Ambil nilai String
            view.getKJURUSAN().getSelectedItem().toString(), // Ambil nilai String
            view.getKKELAS().getSelectedItem().toString(), // Ambil nilai String
            view.getNOPE(),
            view.getSTATUS()
    );
    JOptionPane.showMessageDialog(view, "Data Berhasil Di Update");
}

    public void deleteData() {
        model.deleteData(view.getID());
        JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus");
    }

    public void exit() {
        int keluar = JOptionPane.showOptionDialog(
                view,
                "Keluar dari Kelola Data Anggota?",
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