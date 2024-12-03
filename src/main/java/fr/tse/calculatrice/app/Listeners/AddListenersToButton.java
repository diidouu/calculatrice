package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddListenersToButton implements ActionListener {
    private JTextField display;
    private String valeur;
    
    public AddListenersToButton(JTextField display, String valeur) {
        this.display = display;
        this.valeur = valeur;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (display.getText().equals("0")) {
            display.setText(valeur);
        } else {
            display.setText(display.getText() + valeur);
        }    
    }
}
