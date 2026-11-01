package com.juniverse.datastructures.stack;

/**
 * Pila (LIFO): el último elemento que entra es el primero que sale. Tiene varias implementaciones
 * ({@link ArrayStack} con arreglo y {@link LinkedStack} con nodos) y el código que la usa no necesita saber cuál.
 *
 * @param <T> tipo de los elementos
 */
public interface Stack<T> {

    /**
     * Apila un elemento.
     *
     * @param value elemento
     */
    void push(T value);

    /**
     * Desapila y devuelve la cima.
     *
     * @return elemento de la cima
     * @throws java.util.EmptyStackException si la pila está vacía
     */
    T pop();

    /**
     * Devuelve la cima sin quitarla.
     *
     * @return elemento de la cima
     * @throws java.util.EmptyStackException si la pila está vacía
     */
    T peek();

    /** Indica si la pila no tiene elementos. */
    boolean isEmpty();

    /** Cantidad de elementos. */
    int size();
}
