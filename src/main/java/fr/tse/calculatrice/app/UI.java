package main.java.fr.tse.calculatrice.app;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import main.java.fr.tse.calculatrice.app.listeners.*;

public class UI extends JFrame {
    private JTextField display;
    private JPanel panel;
    private JPanel sciPanel;
    private Font digitalFont;

    public UI() {
        setTitle("Calculatrice TD7");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Charger la police Digital-7
        try {
            digitalFont = Font.createFont(Font.TRUETYPE_FONT, new File("path/to/digital-7.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(digitalFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            digitalFont = new Font("Monospaced", Font.BOLD, 24); // Police de secours
        }

        // Configuration de l'écran de la calculatrice
        display = new JTextField("0");
        display.setEditable(false);
        display.setFont(digitalFont);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setPreferredSize(new Dimension(400, 50));
        add(display, BorderLayout.NORTH);

        // Configuration du panneau principal
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 4, 5, 5)); // Ajuster la grille pour inclure tous les boutons
        panel.setBackground(Color.DARK_GRAY);
        addButtons();
        add(panel, BorderLayout.CENTER);

        // Configuration du panneau scientifique
        sciPanel = new JPanel();
        sciPanel.setLayout(new GridLayout(2, 4, 5, 5)); // Ajuster la grille pour les boutons scientifiques
        sciPanel.setBackground(Color.DARK_GRAY);
        addScientificButtons();
        sciPanel.setVisible(false);
        add(sciPanel, BorderLayout.SOUTH);
    }

    private void addButtons() {
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            ".", "0", "=", "+",
            "C", "(", ")", "SCI"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(digitalFont.deriveFont(18f));
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            if (text.matches("[0-9]|[.]")) {
                button.addActionListener(new AddListenersToButton(display, text));
            } else if (text.equals("C")) {
                button.addActionListener(new CButton(display));
            } else if (text.equals("=")) {
                button.addActionListener(new EnterButton(display));
            } else if (text.equals("SCI")) {
                button.addActionListener(e -> sciPanel.setVisible(!sciPanel.isVisible()));
            } else {
                button.addActionListener(new ButtonsOperations(display, text));
            }
            panel.add(button);
        }
    }

    private void addScientificButtons() {
        String[] scientificButtons = {
            "log", "exp", "sin", "cos",
            "tan", "sqrt", "x^2", "x^3"
        };

        for (String text : scientificButtons) {
            JButton button = new JButton(text);
            button.setFont(digitalFont.deriveFont(18f));
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(100, 50)); // Agrandir les boutons scientifiques
            button.addActionListener(new SciButton(display, text));
            sciPanel.add(button);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UI calculette = new UI();
            calculette.setVisible(true);
        });
    }
}