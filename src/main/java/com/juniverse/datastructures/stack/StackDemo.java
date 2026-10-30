package com.juniverse.datastructures.stack;

/**
 * Demostración de pilas con dos aplicaciones clásicas: balanceo de paréntesis y evaluación de expresiones en notación posfija.
 */
public final class StackDemo {

    private StackDemo() {
    }

    /**
     * Comprueba si los paréntesis, corchetes y llaves están bien balanceados.
     *
     * @param text texto a revisar
     * @return {@code true} si cada símbolo abierto se cierra en el orden correcto
     */
    public static boolean balanced(String text) {
        ArrayStack<Character> pila = new ArrayStack<>();
        for (char c : text.toCharArray()) {
            switch (c) {
                case '(', '[', '{' -> pila.push(c);
                case ')', ']', '}' -> {
                    if (pila.isEmpty()) {
                        return false;
                    }
                    char open = pila.pop();
                    if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{')) {
                        return false;
                    }
                }
                default -> { }
            }
        }
        return pila.isEmpty();
    }

    /**
     * Evalúa una expresión posfija ("3 4 + 2 *" = 14).
     *
     * @param expression números y operadores separados por espacios
     * @return resultado
     */
    public static int evalPostfix(String expression) {
        LinkedStack<Integer> pila = new LinkedStack<>();
        for (String token : expression.trim().split("\\s+")) {
            switch (token) {
                case "+" -> pila.push(pila.pop() + pila.pop());
                case "*" -> pila.push(pila.pop() * pila.pop());
                case "-" -> {
                    int b = pila.pop();
                    pila.push(pila.pop() - b);
                }
                case "/" -> {
                    int b = pila.pop();
                    pila.push(pila.pop() / b);
                }
                default -> pila.push(Integer.parseInt(token));
            }
        }
        return pila.pop();
    }

    /** Ejecuta la demostración. */
    public static void run() {
        ArrayStack<String> pila = new ArrayStack<>();
        pila.push("uno");
        pila.push("dos");
        pila.push("tres");
        System.out.println("Pila (LIFO): peek = " + pila.peek() + ", pop = " + pila.pop() + ", pop = " + pila.pop() + ", tamaño = " + pila.size());
        for (String s : new String[]{"{[()]}", "([)]", "((", "f(x) = [a{b}c]"}) {
            System.out.println("  ¿\"" + s + "\" está balanceado? " + balanced(s));
        }
        System.out.println("Posfija \"3 4 + 2 *\" = " + evalPostfix("3 4 + 2 *") + ", \"10 2 8 * + 3 -\" = " + evalPostfix("10 2 8 * + 3 -"));
    }
}
