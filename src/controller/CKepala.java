package controller;

import view.VLogin;
import view.VKepala;

public class CKepala {

    private VKepala view;

    public CKepala(VKepala view) {
        this.view = view;
    }

    public void logout() {
        new VLogin().setVisible(true);
        view.dispose();
    }
}