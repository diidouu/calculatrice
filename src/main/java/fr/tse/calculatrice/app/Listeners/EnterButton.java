package main.java.fr.tse.calculatrice.app.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.fr.tse.calculatrice.app.Operation;

/**
 * Gestionnaire d'événements pour le bouton Entrée
 * Évalue l'expression complète et affiche le résultat
 */
public class EnterButton implements ActionListener {
    private JTextField display;

    /**
     * Constructeur
     * @param display Le champ d'affichage de la calculatrice
     */
    public EnterButton(JTextField display) {
        this.display = display;
    }

    /**
     * Évalue l'expression mathématique présente dans l'affichage
     * et affiche le résultat
     * @param e L'événement de clic
     */
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
