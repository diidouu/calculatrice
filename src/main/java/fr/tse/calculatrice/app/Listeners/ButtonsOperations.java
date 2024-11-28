package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Engine;

public class ButtonsOperations implements ActionListener {
    private JTextField display;
    String operation;
    private Engine engine;

    public ButtonsOperations(JTextField display, String operation, Engine engine) {
        this.display = display;
        this.operation = operation;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        display.setText(display.getText() + " " + operation + " ");
    }
}
