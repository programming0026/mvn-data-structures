package com.juniverse.datastructures.stack;

import java.util.EmptyStackException;

/**
 * Pila con nodos enlazados: la cima es la cabeza de la lista. Sin límite de capacidad ni copias de arreglo.
 *
 * @param <T> tipo de los elementos
 */
public class LinkedStack<T> {

    private static final class Node<T> {
        private final T value;
        private final Node<T> below;

        private Node(T value, Node<T> below) {
            this.value = value;
            this.below = below;
        }
    }

    private Node<T> top;
    private int size;

    /**
     * Apila un elemento.
     *
     * @param value elemento
     */
    public void push(T value) {
        top = new Node<>(value, top);
        size++;
    }

    /**
     * Desapila y devuelve la cima.
     *
     * @return elemento de la cima
     */
    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        T value = top.value;
        top = top.below;
        size--;
        return value;
    }

    /**
     * Indica si está vacía.
     *
     * @return {@code true} si no tiene elementos
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Cantidad de elementos.
     *
     * @return tamaño
     */
    public int size() {
        return size;
    }
}
