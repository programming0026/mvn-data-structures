package com.juniverse.datastructures.queue;

import java.util.NoSuchElementException;

/**
 * Cola con nodos enlazados: guarda la cabeza (por donde sale) y la cola (por donde entra). Sin límite de capacidad.
 *
 * @param <T> tipo de los elementos
 */
public class LinkedQueue<T> implements Queue<T> {

    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Encola al final. O(1).
     *
     * @param value elemento
     */
    @Override
    public boolean enqueue(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    /**
     * Desencola el primero. O(1).
     *
     * @return primer elemento
     */
    @Override
    public T dequeue() {
        if (head == null) {
            throw new NoSuchElementException("Cola vacía");
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Cantidad de elementos.
     *
     * @return tamaño
     */
    @Override
    public int size() {
        return size;
    }
}
