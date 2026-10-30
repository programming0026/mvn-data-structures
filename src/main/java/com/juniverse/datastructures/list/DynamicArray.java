package com.juniverse.datastructures.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista basada en un arreglo que crece automáticamente (la idea de {@code ArrayList}).
 *
 * <p>Acceso por índice O(1); agregar al final O(1) amortizado; insertar o borrar en medio O(n) porque hay que desplazar elementos.</p>
 *
 * @param <T> tipo de los elementos
 */
public class DynamicArray<T> implements Iterable<T> {

    private static final int INITIAL_CAPACITY = 4;

    private Object[] data = new Object[INITIAL_CAPACITY];
    private int size;

    /**
     * Agrega al final. Si el arreglo está lleno, duplica su capacidad.
     *
     * @param value elemento
     */
    public void add(T value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = value;
    }

    /**
     * Inserta en una posición, desplazando a la derecha los elementos siguientes. O(n).
     *
     * @param index posición (de 0 a size)
     * @param value elemento
     */
    public void add(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice " + index + ", tamaño " + size);
        }
        add(value); // asegura capacidad
        System.arraycopy(data, index, data, index + 1, size - 1 - index);
        data[index] = value;
    }

    /**
     * Elemento de una posición. O(1).
     *
     * @param index posición
     * @return elemento
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        check(index);
        return (T) data[index];
    }

    /**
     * Elimina una posición, desplazando a la izquierda los elementos siguientes. O(n).
     *
     * @param index posición
     * @return elemento eliminado
     */
    public T remove(int index) {
        T removed = get(index);
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null; // evita retener el objeto en memoria
        return removed;
    }

    /**
     * Posición de un elemento. O(n).
     *
     * @param value elemento a buscar
     * @return índice o -1 si no está
     */
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (java.util.Objects.equals(data[i], value)) {
                return i;
            }
        }
        return -1;
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
     * Capacidad actual del arreglo interno.
     *
     * @return capacidad
     */
    public int capacity() {
        return data.length;
    }

    private void check(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice " + index + ", tamaño " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(cursor++);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(i > 0 ? ", " : "").append(data[i]);
        }
        return sb.append(']').toString();
    }
}
