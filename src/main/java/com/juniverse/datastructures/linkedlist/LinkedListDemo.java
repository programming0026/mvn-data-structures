package com.juniverse.datastructures.linkedlist;


import java.util.logging.Logger;
import com.juniverse.datastructures.AppLogger;
/** Demostración de las listas enlazadas simple y doble. */
public final class LinkedListDemo {
    private static final Logger LOGGER = AppLogger.get(LinkedListDemo.class);


    private LinkedListDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        SinglyLinkedList<Integer> simple = new SinglyLinkedList<>();
        simple.addLast(2);
        simple.addLast(3);
        simple.addFirst(1);
        LOGGER.info("Lista simple:   " + simple + "  (tamaño " + simple.size() + ")");
        simple.reverse();
        LOGGER.info("reverse():      " + simple);
        LOGGER.info("remove(2):      " + simple.remove(2) + " -> " + simple + ", contains(3): " + simple.contains(3));

        DoublyLinkedList<String> doubly = new DoublyLinkedList<>();
        doubly.addLast("B");
        doubly.addLast("C");
        doubly.addFirst("A");
        LOGGER.info("Lista doble:    " + doubly);
        LOGGER.info("Hacia atrás:    " + doubly.backwards());
        LOGGER.info("removeLast():   " + doubly.removeLast() + ", removeFirst(): " + doubly.removeFirst() + " -> " + doubly);
    }
}
