package main.java.fr.tse.calculatrice.app;

import javax.swing.*;
import java.awt.*;
import main.java.fr.tse.calculatrice.app.listeners.*;

/**
 * Classe principale de l'interface utilisateur de la calculatrice
 * Gère l'affichage et l'organisation des composants graphiques
 */
public class UI extends JFrame {
    private JTextField display;
    private JPanel panel;
    private JPanel sciPanel;

    /**
     * Constructeur de l'interface utilisateur
     * Configure la fenêtre principale et initialise tous les composants
     */
    public UI() {
        // Configuration du look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Configuration de base de la fenêtre
        setTitle("Calculatrice TD7");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Ajouter des marges
        getContentPane().setBackground(new Color(240, 240, 240)); // Fond gris clair

        // Utiliser une police standard
        Font standardFont = new Font("SansSerif", Font.BOLD, 24);

        /**
         * Configuration de l'écran d'affichage
         * Création d'un champ de texte noir avec texte vert pour l'affichage des calculs
         */
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(standardFont);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(350, 60));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /**
         * Configuration du panneau d'affichage
         * Ajout de marges et placement dans la partie supérieure
         */
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(display);
        add(displayPanel, BorderLayout.NORTH);

        /**
         * Configuration du panneau principal
         * Contient les boutons numériques et opérateurs de base
         */
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 8, 8));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addButtons(standardFont);
        add(panel, BorderLayout.CENTER);

        /**
         * Configuration du panneau scientifique
         * Contient les boutons des fonctions scientifiques (masqué par défaut)
         */
        sciPanel = new JPanel();
        sciPanel.setLayout(new GridLayout(2, 4, 8, 8));
        sciPanel.setBackground(new Color(240, 240, 240));
        sciPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addScientificButtons(standardFont);
        sciPanel.setVisible(false);
        add(sciPanel, BorderLayout.SOUTH);
    }

    /**
     * Ajoute les boutons standards à l'interface
     * Inclut les chiffres, les opérations de base et le bouton de mode scientifique
     * @param font La police à utiliser pour les boutons
     */
    private void addButtons(Font font) {
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+",
            "C", "(", ")", "SCI"
        };

        // Configuration et ajout de chaque bouton
        for (String text : buttons) {
            JButton button = new JButton(text);
            // Configuration visuelle du bouton
            button.setFont(font);
            button.setOpaque(true);
            button.setBackground(new Color(128, 128, 128)); // Gris plus foncé
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            button.setContentAreaFilled(true);
            button.setBorderPainted(false);

            // Attribution des écouteurs d'événements selon le type de bouton
            if (text.matches("[0-9]|[.]")) {
                // Boutons numériques
                button.addActionListener(new AddListenersToButton(display, text));
            } else if (text.equals("C")) {
                // Bouton d'effacement
                button.addActionListener(new CButton(display));
            } else if (text.equals("=")) {
                // Bouton égal
                button.addActionListener(new EnterButton(display));
            } else if (text.equals("SCI")) {
                // Bouton mode scientifique
                button.addActionListener(e -> sciPanel.setVisible(!sciPanel.isVisible()));
            } else {
                // Autres opérateurs
                button.addActionListener(new ButtonsOperations(display, text));
            }
            panel.add(button);
        }
    }

    /**
     * Ajoute les boutons scientifiques à l'interface
     * Inclut les fonctions trigonométriques et autres opérations avancées
     * @param font La police à utiliser pour les boutons
     */
    private void addScientificButtons(Font font) {
        String[] scientificButtons = {
            "log", "exp", "sin", "cos",
            "tan", "sqrt", "x^2", "x^3"
        };

        // Configuration et ajout de chaque bouton scientifique
        for (String text : scientificButtons) {
            JButton button = new JButton(text);
            button.setFont(font);
            button.setOpaque(true);
            button.setBackground(new Color(128, 128, 128)); // Gris plus foncé
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(100, 50)); // Agrandir les boutons scientifiques
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            button.setContentAreaFilled(true);
            button.setBorderPainted(false);

            button.addActionListener(new SciButton(display, text));
            sciPanel.add(button);
        }
    }

    /**
     * Point d'entrée de l'application
     * Crée et affiche l'interface graphique dans le thread EDT
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UI calculator = new UI();
            calculator.setVisible(true);
        });
    }
}