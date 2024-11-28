package main.java.fr.tse.calculatrice.app;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private JTextField display;
    private JPanel panel;

    public UI() {
        setTitle("Calculatrice TD7");
        setSize(250,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        display = new JTextField(0);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5,4));
        addButtons();
        add(panel, BorderLayout.CENTER);
    }

    private void addButtons() {
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+",
            "%", "(" , ")", "SCI"
        };
    

    for (String text : buttons) {
        JButton button = new JButton();
        panel.add(button);    
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UI calculette = new UI();
            calculette.setVisible(true);
        });
    }

}