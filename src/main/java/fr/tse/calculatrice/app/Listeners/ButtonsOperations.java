package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Operation;

/**
 * Gestionnaire d'événements pour les boutons d'opérations standard et scientifiques
 * Gère l'affichage et l'exécution des opérations
 */
public class ButtonsOperations implements ActionListener {
    private JTextField display;
    String operation;

    /**
     * Constructeur
     * @param display Le champ d'affichage de la calculatrice
     * @param operation L'opération à effectuer
     */
    public ButtonsOperations(JTextField display, String operation) {
        this.display = display;
        this.operation = operation;
    }

    /**
     * Gère le clic sur un bouton d'opération
     * Vérifie si le dernier caractère est un opérateur et le remplace si nécessaire
     * @param e L'événement de clic
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Récupère le texte actuel de l'affichage
            String currentText = display.getText();
            
            // Vérifie si le dernier caractère est un opérateur pour éviter les doublons
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

    /**
     * Vérifie si un caractère est un opérateur mathématique
     * @param c Le caractère à vérifier
     * @return true si c'est un opérateur, false sinon
     */
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    /**
     * Vérifie si une opération est une opération scientifique
     * @param op L'opération à vérifier
     * @return true si c'est une opération scientifique, false sinon
     */
    private boolean isScientificOperation(String op) {
        return op.equals("log") || op.equals("exp") || op.equals("sin") || 
               op.equals("cos") || op.equals("tan") || op.equals("sqrt") || 
               op.equals("x^2") || op.equals("x^3");
    }
}