package com.juniverse.datastructures.linkedlist;

/** Demostración de las listas enlazadas simple y doble. */
public final class LinkedListDemo {

    private LinkedListDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        SinglyLinkedList<Integer> simple = new SinglyLinkedList<>();
        simple.addLast(2);
        simple.addLast(3);
        simple.addFirst(1);
        System.out.println("Lista simple:   " + simple + "  (tamaño " + simple.size() + ")");
        simple.reverse();
        System.out.println("reverse():      " + simple);
        System.out.println("remove(2):      " + simple.remove(2) + " -> " + simple + ", contains(3): " + simple.contains(3));

        DoublyLinkedList<String> doble = new DoublyLinkedList<>();
        doble.addLast("B");
        doble.addLast("C");
        doble.addFirst("A");
        System.out.println("Lista doble:    " + doble);
        System.out.println("Hacia atrás:    " + doble.backwards());
        System.out.println("removeLast():   " + doble.removeLast() + ", removeFirst(): " + doble.removeFirst() + " -> " + doble);
    }
}
