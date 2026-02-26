package controller;

import model.MLogin;
import view.VLogin;
import view.VKepala;
import view.VPustakawan ;
import javax.swing.JOptionPane;

public class CLogin {

    protected VLogin view;
    protected MLogin model;

    public CLogin(VLogin view) {
        this.view = view;
        this.model = new MLogin();
    }

    public void cekLogin() {
        String username = view.getUserText();
        String password = view.getPassText();
        String level = view.getLevel();

        if (model.checkLogin(username, password, level)) {
            String userLevel = model.getLevel(username, password);
            if (userLevel != null) {
                if (userLevel.equals("Kepala")) {
                    new VKepala().setVisible(true);
                    view.dispose();
                } else if (userLevel.equals("Pustakawan")) {
                    new VPustakawan().setVisible(true);
                    view.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(view, "Sorry Username or Password Incorrect");
            view.clearFields();
        }
    }
}