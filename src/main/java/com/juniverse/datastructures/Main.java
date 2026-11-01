package com.juniverse.datastructures;

import com.juniverse.datastructures.linkedlist.LinkedListDemo;
import com.juniverse.datastructures.list.ListDemo;
import com.juniverse.datastructures.queue.QueueDemo;
import com.juniverse.datastructures.stack.StackDemo;
import com.juniverse.datastructures.tree.TreeDemo;

import java.util.logging.Logger;
/**
 * Estructuras de datos implementadas desde cero: listas, listas enlazadas, pilas, colas y árboles.
 */
public final class Main {
    private static final Logger LOGGER = AppLogger.get(Main.class);


    private Main() {
    }

    private static void title(String text) {
        LOGGER.info("\n=========== " + text + " ===========");
    }

    /**
     * Punto de entrada.
     *
     * @param args no se usan
     */
    public static void main(String[] args) {
        // Salida en UTF-8 para que se vean bien los acentos en cualquier consola
        System.setOut(new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8));
        title("1. LISTA (arreglo dinámico)");
        ListDemo.run();
        title("2. LISTAS ENLAZADAS (simple y doble)");
        LinkedListDemo.run();
        title("3. PILAS (LIFO)");
        StackDemo.run();
        title("4. COLAS (FIFO)");
        QueueDemo.run();
        title("5. ÁRBOLES");
        TreeDemo.run();
    }
}
