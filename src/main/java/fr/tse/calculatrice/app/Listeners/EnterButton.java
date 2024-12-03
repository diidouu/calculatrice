package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Operation;

public class EnterButton implements ActionListener {
    private JTextField display;

    public EnterButton(JTextField display) {
        this.display = display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String expression = display.getText();
            double result = Operation.evaluate(expression);
            display.setText(String.valueOf(result));
        } catch (Exception exception) {
            display.setText("Error");
        }
    }
}
