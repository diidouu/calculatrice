package main.java.fr.tse.calculatrice.app;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import main.java.fr.tse.calculatrice.app.listeners.*;

public class UI extends JFrame {
    private JTextField display;
    private JPanel panel;
    private JPanel sciPanel;
    private Font digitalFont;

    public UI() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Calculatrice TD7");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Ajouter des marges
        getContentPane().setBackground(new Color(240, 240, 240)); // Fond gris clair

        // Charger la police Digital-7 à partir des ressources
        try (InputStream is = getClass().getResourceAsStream("/digital-7.ttf")) {
            if (is != null) {
                digitalFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(digitalFont);
            } else {
                throw new IOException("Police non trouvée");
            }
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
        display.setPreferredSize(new Dimension(350, 60));
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        displayPanel.add(display);
        add(displayPanel, BorderLayout.NORTH);

        // Configuration du panneau principal
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 8, 8));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addButtons();
        add(panel, BorderLayout.CENTER);

        // Configuration du panneau scientifique
        sciPanel = new JPanel();
        sciPanel.setLayout(new GridLayout(2, 4, 8, 8));
        sciPanel.setBackground(new Color(240, 240, 240));
        sciPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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
            button.setOpaque(true);
            button.setBackground(new Color(128, 128, 128)); // Gris plus foncé
            button.setForeground(Color.BLACK);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            button.setContentAreaFilled(true);
            button.setBorderPainted(false);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UI calculator = new UI();
            calculator.setVisible(true);
        });
    }
}