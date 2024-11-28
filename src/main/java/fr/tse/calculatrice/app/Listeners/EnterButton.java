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
            String expression = display.getText();
            expression = expression.replaceAll("log", "Math.log10");
            expression = expression.replaceAll("exp", "Math.exp");
            expression = expression.replaceAll("sin", "Math.sin");
            expression = expression.replaceAll("cos", "Math.cos");
            expression = expression.replaceAll("tan", "Math.tan");
            expression = expression.replaceAll("sqrt", "Math.sqrt");
            expression = expression.replaceAll("x\\^2", "(x)**2");
            expression = expression.replaceAll("x\\^3", "(x)**3");
            expression = expression.replaceAll("1/x", "1/(x)");

            String result = engine.evaluateExpression(expression);
            display.setText(result);
        } catch (Exception exception) {
            display.setText("Error");
        }
    }
}
