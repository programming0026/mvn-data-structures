package com.juniverse.datastructures.linkedlist;

/**
 * Secuencia enlazada: se puede agregar y quitar en los extremos y recorrer. Tiene dos implementaciones,
 * {@link SinglyLinkedList} (enlace simple) y {@link DoublyLinkedList} (enlace doble).
 *
 * @param <T> tipo de los elementos
 */
public interface LinkedSequence<T> extends Iterable<T> {

    /**
     * Agrega al inicio.
     *
     * @param value elemento
     */
    void addFirst(T value);

    /**
     * Agrega al final.
     *
     * @param value elemento
     */
    void addLast(T value);

    /**
     * Quita y devuelve el primer elemento.
     *
     * @return primer elemento
     * @throws java.util.NoSuchElementException si la secuencia está vacía
     */
    T removeFirst();

    /** Cantidad de elementos. */
    int size();
}
