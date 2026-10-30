package com.juniverse.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista enlazada simple: cada nodo guarda su valor y una referencia al siguiente.
 *
 * <p>Insertar o borrar al inicio O(1); agregar al final O(n) (no se guarda la cola); buscar O(n); no hay acceso directo por índice.</p>
 *
 * @param <T> tipo de los elementos
 */
public class SinglyLinkedList<T> implements Iterable<T> {

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private int size;

    /**
     * Inserta al inicio. O(1).
     *
     * @param value elemento
     */
    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        node.next = head;
        head = node;
        size++;
    }

    /**
     * Inserta al final recorriendo la lista. O(n).
     *
     * @param value elemento
     */
    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = node;
        }
        size++;
    }

    /**
     * Elimina y devuelve el primero. O(1).
     *
     * @return primer elemento
     */
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Lista vacía");
        }
        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    /**
     * Elimina la primera aparición de un valor. O(n).
     *
     * @param value elemento a eliminar
     * @return {@code true} si estaba
     */
    public boolean remove(T value) {
        Node<T> previous = null;
        for (Node<T> current = head; current != null; previous = current, current = current.next) {
            if (java.util.Objects.equals(current.value, value)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si contiene el valor. O(n).
     *
     * @param value elemento
     * @return {@code true} si existe
     */
    public boolean contains(T value) {
        for (T item : this) {
            if (java.util.Objects.equals(item, value)) {
                return true;
            }
        }
        return false;
    }

    /** Invierte la lista cambiando las referencias (sin crear nodos nuevos). O(n). */
    public void reverse() {
        Node<T> previous = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    /**
     * Cantidad de elementos.
     *
     * @return tamaño
     */
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T item : this) {
            sb.append(item).append(" -> ");
        }
        return sb.append("null").toString();
    }
}
