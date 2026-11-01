package com.juniverse.datastructures.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Árbol binario de búsqueda (BST): para cada nodo, todo lo que está a su izquierda es menor y todo lo que está a su derecha es mayor.
 *
 * <p>Buscar, insertar y borrar cuestan O(h), con h la altura: O(log n) si el árbol está equilibrado, pero O(n) en el peor caso
 * (por ejemplo, al insertar datos ya ordenados). Los árboles AVL y rojinegros se auto-equilibran para evitarlo.</p>
 *
 * @param <T> tipo de los elementos (comparables)
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private static final class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;

    /**
     * Inserta un valor (los repetidos se ignoran).
     *
     * @param value valor
     */
    public void insert(T value) {
        root = insert(root, value);
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            size++;
            return new Node<>(value);
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else if (cmp > 0) {
            node.right = insert(node.right, value);
        }
        return node;
    }

    /**
     * Busca un valor.
     *
     * @param value valor
     * @return {@code true} si está en el árbol
     */
    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            int cmp = value.compareTo(current.value);
            if (cmp == 0) {
                return true;
            }
            current = cmp < 0 ? current.left : current.right;
        }
        return false;
    }

    /**
     * Elimina un valor. Con dos hijos, se reemplaza por el sucesor (el menor del subárbol derecho).
     *
     * @param value valor
     */
    public void remove(T value) {
        root = remove(root, value);
    }

    private Node<T> remove(Node<T> node, T value) {
        if (node == null) {
            return null;
        }
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = remove(node.left, value);
        } else if (cmp > 0) {
            node.right = remove(node.right, value);
        } else {
            size--;
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node<T> successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            node.value = successor.value;
            size++; // la llamada recursiva descuenta el nodo sucesor
            node.right = remove(node.right, successor.value);
        }
        return node;
    }

    /**
     * Menor valor del árbol.
     *
     * @return el mínimo
     */
    public T min() {
        Node<T> n = root;
        while (n.left != null) {
            n = n.left;
        }
        return n.value;
    }

    /**
     * Mayor valor del árbol.
     *
     * @return el máximo
     */
    public T max() {
        Node<T> n = root;
        while (n.right != null) {
            n = n.right;
        }
        return n.value;
    }

    /**
     * Altura del árbol (número de niveles; un árbol vacío tiene 0).
     *
     * @return altura
     */
    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * Cantidad de nodos.
     *
     * @return tamaño
     */
    public int size() {
        return size;
    }

    /**
     * Recorrido en orden (izquierda, raíz, derecha): en un BST devuelve los valores ORDENADOS.
     *
     * @return valores
     */
    public List<T> inOrder() {
        List<T> out = new ArrayList<>();
        inOrder(root, out);
        return out;
    }

    private void inOrder(Node<T> n, List<T> out) {
        if (n != null) {
            inOrder(n.left, out);
            out.add(n.value);
            inOrder(n.right, out);
        }
    }

    /**
     * Recorrido en preorden (raíz, izquierda, derecha): sirve para copiar o serializar el árbol.
     *
     * @return valores
     */
    public List<T> preOrder() {
        List<T> out = new ArrayList<>();
        preOrder(root, out);
        return out;
    }

    private void preOrder(Node<T> n, List<T> out) {
        if (n != null) {
            out.add(n.value);
            preOrder(n.left, out);
            preOrder(n.right, out);
        }
    }

    /**
     * Recorrido en postorden (izquierda, derecha, raíz): sirve para borrar el árbol, hijos antes que padres.
     *
     * @return valores
     */
    public List<T> postOrder() {
        List<T> out = new ArrayList<>();
        postOrder(root, out);
        return out;
    }

    private void postOrder(Node<T> n, List<T> out) {
        if (n != null) {
            postOrder(n.left, out);
            postOrder(n.right, out);
            out.add(n.value);
        }
    }

    /**
     * Recorrido por niveles (en anchura) con una cola.
     *
     * @return valores nivel por nivel
     */
    public List<T> levelOrder() {
        List<T> out = new ArrayList<>();
        if (root == null) {
            return out;
        }
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> n = queue.remove();
            out.add(n.value);
            if (n.left != null) {
                queue.add(n.left);
            }
            if (n.right != null) {
                queue.add(n.right);
            }
        }
        return out;
    }

    /**
     * Dibuja el árbol de lado (la raíz a la izquierda, el subárbol derecho arriba).
     *
     * @return dibujo en texto
     */
    public String draw() {
        StringBuilder sb = new StringBuilder();
        draw(root, 0, sb);
        return sb.toString();
    }

    private void draw(Node<T> n, int depth, StringBuilder sb) {
        if (n != null) {
            draw(n.right, depth + 1, sb);
            sb.append("    ".repeat(depth)).append(n.value).append('\n');
            draw(n.left, depth + 1, sb);
        }
    }
}
