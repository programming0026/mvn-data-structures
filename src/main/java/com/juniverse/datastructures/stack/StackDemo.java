package com.juniverse.datastructures.stack;


import java.util.logging.Logger;
import com.juniverse.datastructures.AppLogger;
/**
 * Demostración de pilas con dos aplicaciones clásicas: balanceo de paréntesis y evaluación de expresiones en notación posfija.
 */
public final class StackDemo {
    private static final Logger LOGGER = AppLogger.get(StackDemo.class);


    private StackDemo() {
    }

    /**
     * Comprueba si los paréntesis, corchetes y llaves están bien balanceados.
     *
     * @param text texto a revisar
     * @return {@code true} si cada símbolo abierto se cierra en el orden correcto
     */
    public static boolean balanced(String text) {
        ArrayStack<Character> stack = new ArrayStack<>();
        for (char c : text.toCharArray()) {
            switch (c) {
                case '(', '[', '{' -> stack.push(c);
                case ')', ']', '}' -> {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char open = stack.pop();
                    if ((c == ')' && open != '(') || (c == ']' && open != '[') || (c == '}' && open != '{')) {
                        return false;
                    }
                }
                default -> { }
            }
        }
        return stack.isEmpty();
    }

    /**
     * Evalúa una expresión posfija ("3 4 + 2 *" = 14).
     *
     * @param expression números y operadores separados por espacios
     * @return resultado
     */
    public static int evalPostfix(String expression) {
        LinkedStack<Integer> stack = new LinkedStack<>();
        for (String token : expression.trim().split("\\s+")) {
            switch (token) {
                case "+" -> stack.push(stack.pop() + stack.pop());
                case "*" -> stack.push(stack.pop() * stack.pop());
                case "-" -> {
                    int b = stack.pop();
                    stack.push(stack.pop() - b);
                }
                case "/" -> {
                    int b = stack.pop();
                    stack.push(stack.pop() / b);
                }
                default -> stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    /** Ejecuta la demostración. */
    public static void run() {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("uno");
        stack.push("dos");
        stack.push("tres");
        LOGGER.info("Pila (LIFO): peek = " + stack.peek() + ", pop = " + stack.pop() + ", pop = " + stack.pop() + ", tamaño = " + stack.size());
        for (String s : new String[]{"{[()]}", "([)]", "((", "f(x) = [a{b}c]"}) {
            LOGGER.info("  ¿\"" + s + "\" está balanceado? " + balanced(s));
        }
        LOGGER.info("Posfija \"3 4 + 2 *\" = " + evalPostfix("3 4 + 2 *") + ", \"10 2 8 * + 3 -\" = " + evalPostfix("10 2 8 * + 3 -"));
    }
}
