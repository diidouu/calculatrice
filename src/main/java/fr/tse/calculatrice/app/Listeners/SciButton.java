package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Engine;

public class SciButton implements ActionListener {
    private JTextField display;
    private String operation;
    private Engine engine;

    public SciButton(JTextField display, String operation, Engine engine) {
        this.display = display;
        this.operation = operation;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String expression = display.getText();
            switch (operation) {
                case "log":
                    expression = "Math.log(" + expression + ")";
                    break;
                case "exp":
                    expression = "Math.exp(" + expression + ")";
                    break;
                case "sin":
                    expression = "Math.sin(" + expression + ")";
                    break;
                case "cos":
                    expression = "Math.cos(" + expression + ")";
                    break;
                case "tan":
                    expression = "Math.tan(" + expression + ")";
                    break;
                case "sqrt":
                    expression = "Math.sqrt(" + expression + ")";
                    break;
                case "x^2":
                    expression = "(" + expression + ")**2";
                    break;
                case "x^3":
                    expression = "(" + expression + ")**3";
                    break;
                case "1/x":
                    expression = "1/(" + expression + ")";
                    break;
            }
            String result = engine.evaluateExpression(expression);
            display.setText(result);
        } catch (Exception exception) {
            display.setText("Error");
        }
    }
}
