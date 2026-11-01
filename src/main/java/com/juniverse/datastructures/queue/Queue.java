package com.juniverse.datastructures.queue;

/**
 * Cola (FIFO): el primer elemento que entra es el primero que sale. Tiene varias implementaciones
 * ({@link LinkedQueue} con nodos y {@link CircularQueue} con un arreglo de capacidad fija).
 *
 * @param <T> tipo de los elementos
 */
public interface Queue<T> {

    /**
     * Encola al final.
     *
     * @param value elemento
     * @return {@code false} si la cola está llena y no se pudo agregar; las colas sin límite siempre devuelven
     *     {@code true}
     */
    boolean enqueue(T value);

    /**
     * Desencola el primero.
     *
     * @return primer elemento
     * @throws java.util.NoSuchElementException si la cola está vacía
     */
    T dequeue();

    /** Indica si la cola no tiene elementos. */
    boolean isEmpty();

    /** Cantidad de elementos. */
    int size();
}
