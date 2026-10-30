package com.juniverse.datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Pila (LIFO: el último en entrar es el primero en salir) sobre un arreglo. push, pop y peek son O(1).
 *
 * @param <T> tipo de los elementos
 */
public class ArrayStack<T> {

    private Object[] data = new Object[4];
    private int size;

    /**
     * Apila un elemento.
     *
     * @param value elemento
     */
    public void push(T value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, size * 2);
        }
        data[size++] = value;
    }

    /**
     * Desapila y devuelve el elemento de la cima.
     *
     * @return elemento de la cima
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        T value = (T) data[--size];
        data[size] = null;
        return value;
    }

    /**
     * Mira la cima sin quitarla.
     *
     * @return elemento de la cima
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return (T) data[size - 1];
    }

    /**
     * Indica si está vacía.
     *
     * @return {@code true} si no tiene elementos
     */
    public boolean isEmpty() {
        return size == 0;
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
