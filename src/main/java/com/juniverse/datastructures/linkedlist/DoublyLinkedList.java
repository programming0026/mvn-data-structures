package com.juniverse.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista doblemente enlazada: cada nodo conoce al anterior y al siguiente, y la lista guarda la cabeza y la cola.
 *
 * <p>Insertar o borrar en CUALQUIER extremo es O(1) y se puede recorrer en ambos sentidos. Cuesta más memoria por nodo.</p>
 *
 * @param <T> tipo de los elementos
 */
public class DoublyLinkedList<T> implements LinkedSequence<T> {

    private static final class Node<T> {
        private final T value;
        private Node<T> prev;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Inserta al inicio. O(1).
     *
     * @param value elemento
     */
    @Override
    public void addFirst(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    /**
     * Inserta al final. O(1) gracias a la referencia a la cola.
     *
     * @param value elemento
     */
    @Override
    public void addLast(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Elimina y devuelve el primero. O(1).
     *
     * @return primer elemento
     */
    @Override
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("Lista vacía");
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return value;
    }

    /**
     * Elimina y devuelve el último. O(1).
     *
     * @return último elemento
     */
    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("Lista vacía");
        }
        T value = tail.value;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return value;
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

    /**
     * Recorre desde la cola hacia la cabeza.
     *
     * @return texto con los elementos en orden inverso
     */
    public String backwards() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> n = tail; n != null; n = n.prev) {
            sb.append(n.value).append(" <- ");
        }
        return sb.append("null").toString();
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
        StringBuilder sb = new StringBuilder("null <- ");
        for (T item : this) {
            sb.append(item).append(" <-> ");
        }
        return sb.append("null").toString();
    }
}
