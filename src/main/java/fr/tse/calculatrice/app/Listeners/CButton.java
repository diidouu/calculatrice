package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CButton implements ActionListener {
    private JTextField display;

    public CButton(JTextField display) {
        this.display = display;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        display.setText("0");
    }
}
