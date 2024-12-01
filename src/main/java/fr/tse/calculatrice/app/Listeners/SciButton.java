package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Operation;

public class SciButton implements ActionListener {
    private JTextField display;
    private String operation;

    public SciButton(JTextField display, String operation) {
        this.display = display;
        this.operation = operation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String expression = display.getText();
            double result = 0;
            switch (operation) {
                case "log":
                    result = Operation.log(Double.parseDouble(expression));
                    break;
                case "exp":
                    result = Operation.exp(Double.parseDouble(expression));
                    break;
                case "sin":
                    result = Operation.sin(Double.parseDouble(expression));
                    break;
                case "cos":
                    result = Operation.cos(Double.parseDouble(expression));
                    break;
                case "tan":
                    result = Operation.tan(Double.parseDouble(expression));
                    break;
                case "sqrt":
                    result = Operation.sqrt(Double.parseDouble(expression));
                    break;
                case "x^2":
                    result = Operation.pow(Double.parseDouble(expression), 2);
                    break;
                case "x^3":
                    result = Operation.pow(Double.parseDouble(expression), 3);
                    break;
                case "1/x":
                    result = 1 / Double.parseDouble(expression);
                    break;
                default:
                    display.setText(display.getText() + " " + operation + " ");
                    return;
            }
            display.setText(String.valueOf(result));
        } catch (Exception exception) {
            display.setText("Error");
        }
    }
}
