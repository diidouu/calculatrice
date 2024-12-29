package main.java.fr.tse.calculatrice.app;

import java.util.Stack;

public class Operation {
    /**
     * Évalue une expression mathématique sous forme de chaîne de caractères
     * @param expression L'expression mathématique à évaluer (ex: "2 + 3 * 4")
     * @return Le résultat de l'évaluation de l'expression
     */
    public static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        // Pile pour stocker les nombres
        Stack<Double> values = new Stack<>();

        // Pile pour stocker les opérateurs
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            // Ignore les espaces
            if (tokens[i] == ' ')
                continue;

            // Si le caractère est un chiffre ou un point décimal
            if (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.') {
                StringBuilder sbuf = new StringBuilder();
                // Il peut y avoir plusieurs chiffres dans le nombre
                while (i < tokens.length && (tokens[i] >= '0' && tokens[i] <= '9' || tokens[i] == '.'))
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
                i--;
            }

            // Si c'est une parenthèse ouvrante, on l'ajoute aux opérateurs
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            // Si c'est une parenthèse fermante, on résout toute la parenthèse
            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Si c'est un opérateur
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                    tokens[i] == '*' || tokens[i] == '/') {
                // Tant que le sommet de 'ops' a une précédence supérieure ou égale
                // à l'opérateur courant, on applique l'opérateur du sommet
                // aux deux premiers éléments de la pile des valeurs
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // On ajoute l'opérateur courant à la pile des opérateurs
                ops.push(tokens[i]);
            }
        }

        // L'expression a été entièrement analysée, on applique les opérateurs restants
        // aux valeurs restantes
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Le sommet de la pile 'values' contient le résultat
        return values.pop();
    }

    /**
     * Vérifie la précédence entre deux opérateurs
     * @param op1 Premier opérateur
     * @param op2 Deuxième opérateur
     * @return true si op2 a une précédence supérieure ou égale à op1
     */
    public static boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    /**
     * Applique un opérateur sur deux opérandes
     * @param op L'opérateur à appliquer (+, -, *, /)
     * @param b Le deuxième opérande
     * @param a Le premier opérande
     * @return Le résultat de l'opération
     * @throws UnsupportedOperationException Si division par zéro
     */
    public static double applyOp(char op, double b, double a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new UnsupportedOperationException("Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    // Méthodes pour les opérations scientifiques
    /**
     * Calcule le logarithme en base 10 d'un nombre
     * @param a Le nombre
     * @return Le logarithme de a
     */
    public static double log(double a) {
        return Math.log10(a);
    }

    /**
     * Calcule l'exponentielle d'un nombre
     * @param a L'exposant
     * @return e^a
     */
    public static double exp(double a) {
        return Math.exp(a);
    }

    /**
     * Calcule le sinus d'un nombre
     * @param a L'angle en radians
     * @return Le sinus de a
     */
    public static double sin(double a) {
        return Math.sin(a);
    }

    /**
     * Calcule le cosinus d'un nombre
     * @param a L'angle en radians
     * @return Le cosinus de a
     */
    public static double cos(double a) {
        return Math.cos(a);
    }

    /**
     * Calcule la tangente d'un nombre
     * @param a L'angle en radians
     * @return La tangente de a
     */
    public static double tan(double a) {
        return Math.tan(a);
    }

    /**
     * Calcule la racine carrée d'un nombre
     * @param a Le nombre
     * @return La racine carrée de a
     */
    public static double sqrt(double a) {
        return Math.sqrt(a);
    }

    /**
     * Calcule la puissance d'un nombre
     * @param a La base
     * @param b L'exposant
     * @return a^b
     */
    public static double pow(double a, double b) {
        return Math.pow(a, b);
    }
}
