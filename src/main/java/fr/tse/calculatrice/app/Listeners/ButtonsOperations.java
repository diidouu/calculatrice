package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Operation;

public class ButtonsOperations implements ActionListener {
    private JTextField display;
    String operation;

    public ButtonsOperations(JTextField display, String operation) {
        this.display = display;
        this.operation = operation;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String currentText = display.getText();
            
            // Vérifier si le dernier caractère est un opérateur
            if (!currentText.isEmpty()) {
                char lastChar = currentText.trim().charAt(currentText.trim().length() - 1);
                if (isOperator(lastChar)) {
                    // Si le dernier caractère est un opérateur, remplacer par le nouveau
                    display.setText(currentText.substring(0, currentText.length() - 2) + " " + operation + " ");
                    return;
                }
            }

            // Si ce n'est pas un opérateur scientifique, ajouter l'opérateur normalement
            if (!isScientificOperation(operation)) {
                display.setText(currentText + " " + operation + " ");
                return;
            }

            // Traitement des opérations scientifiques
            double result = 0;
            switch (operation) {
                case "log":
                    result = Operation.log(Double.parseDouble(currentText));
                    break;
                case "exp":
                    result = Operation.exp(Double.parseDouble(currentText));
                    break;
                case "sin":
                    result = Operation.sin(Double.parseDouble(currentText));
                    break;
                case "cos":
                    result = Operation.cos(Double.parseDouble(currentText));
                    break;
                case "tan":
                    result = Operation.tan(Double.parseDouble(currentText));
                    break;
                case "sqrt":
                    result = Operation.sqrt(Double.parseDouble(currentText));
                    break;
                case "x^2":
                    result = Operation.pow(Double.parseDouble(currentText), 2);
                    break;
                case "x^3":
                    result = Operation.pow(Double.parseDouble(currentText), 3);
                    break;
            }
            display.setText(String.valueOf(result));
        } catch (Exception exception) {
            display.setText("Error");
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    private boolean isScientificOperation(String op) {
        return op.equals("log") || op.equals("exp") || op.equals("sin") || 
               op.equals("cos") || op.equals("tan") || op.equals("sqrt") || 
               op.equals("x^2") || op.equals("x^3");
    }
}