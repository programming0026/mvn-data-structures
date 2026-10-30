package com.juniverse.datastructures.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Árbol general (cada nodo puede tener cualquier cantidad de hijos): la estructura de las carpetas, los menús o un organigrama.
 *
 * @param <T> tipo del valor de los nodos
 */
public class GeneralTree<T> {

    private final T value;
    private final List<GeneralTree<T>> children = new ArrayList<>();

    /**
     * Crea un nodo.
     *
     * @param value valor
     */
    public GeneralTree(T value) {
        this.value = value;
    }

    /**
     * Agrega un hijo.
     *
     * @param child nodo hijo
     * @return este nodo (para encadenar llamadas)
     */
    public GeneralTree<T> add(GeneralTree<T> child) {
        children.add(child);
        return this;
    }

    /**
     * Cuenta todos los nodos del árbol.
     *
     * @return cantidad de nodos
     */
    public int count() {
        int total = 1;
        for (GeneralTree<T> child : children) {
            total += child.count();
        }
        return total;
    }

    /**
     * Altura (niveles) del árbol.
     *
     * @return altura
     */
    public int height() {
        int max = 0;
        for (GeneralTree<T> child : children) {
            max = Math.max(max, child.height());
        }
        return 1 + max;
    }

    /**
     * Imprime el árbol con sangría, como un explorador de archivos.
     *
     * @param prefix sangría acumulada
     * @param sb destino
     */
    public void print(String prefix, StringBuilder sb) {
        sb.append(prefix).append(value).append('\n');
        for (GeneralTree<T> child : children) {
            child.print(prefix + "   ", sb);
        }
    }
}
