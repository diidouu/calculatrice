package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Engine;

public class EnterButton implements ActionListener {
    private JTextField display;
    private Engine engine;

    public EnterButton(JTextField display, Engine engine) {
        this.display = display;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String resultat = engine.evaluateExpression(display.getText());
            display.setText(resultat);
        } catch (Exception exception) {
            display.setText("Error");
        }
    }
}
