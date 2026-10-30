package com.juniverse.datastructures.queue;

import java.util.NoSuchElementException;

/**
 * Cola (FIFO: el primero en entrar es el primero en salir) con un arreglo circular de capacidad fija.
 * Los índices "dan la vuelta" con el operador módulo, así enqueue y dequeue son O(1) sin desplazar elementos.
 *
 * @param <T> tipo de los elementos
 */
public class CircularQueue<T> {

    private final Object[] data;
    private int head;
    private int size;

    /**
     * Crea la cola.
     *
     * @param capacity capacidad máxima
     */
    public CircularQueue(int capacity) {
        this.data = new Object[capacity];
    }

    /**
     * Encola al final.
     *
     * @param value elemento
     * @return {@code false} si la cola está llena
     */
    public boolean enqueue(T value) {
        if (size == data.length) {
            return false;
        }
        data[(head + size) % data.length] = value;
        size++;
        return true;
    }

    /**
     * Desencola el primero.
     *
     * @return primer elemento
     */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Cola vacía");
        }
        T value = (T) data[head];
        data[head] = null;
        head = (head + 1) % data.length;
        size--;
        return value;
    }

    /**
     * Cantidad de elementos.
     *
     * @return tamaño
     */
    public int size() {
        return size;
    }

    /**
     * Indica si está llena.
     *
     * @return {@code true} si no cabe otro elemento
     */
    public boolean isFull() {
        return size == data.length;
    }
}
