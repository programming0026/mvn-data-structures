package com.juniverse.datastructures.tree;


import java.util.logging.Logger;
import com.juniverse.datastructures.AppLogger;
/** Demostración de árboles: BST (inserción, búsqueda, borrado y recorridos) y árbol general. */
public final class TreeDemo {
    private static final Logger LOGGER = AppLogger.get(TreeDemo.class);


    private TreeDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int n : new int[]{50, 30, 70, 20, 40, 60, 80, 35}) {
            bst.insert(n);
        }
        LOGGER.info("Árbol binario de búsqueda (la raíz está a la izquierda):");
        LOGGER.info(bst.draw());
        LOGGER.info("Tamaño: " + bst.size() + ", altura: " + bst.height() + ", mín: " + bst.min() + ", máx: " + bst.max());
        LOGGER.info("En orden (ordenado): " + bst.inOrder());
        LOGGER.info("Preorden:            " + bst.preOrder());
        LOGGER.info("Postorden:           " + bst.postOrder());
        LOGGER.info("Por niveles:         " + bst.levelOrder());
        LOGGER.info("contains(60) = " + bst.contains(60) + ", contains(65) = " + bst.contains(65));
        bst.remove(30); // nodo con dos hijos: lo reemplaza su sucesor (35)
        bst.remove(20); // hoja
        LOGGER.info("Tras borrar 30 y 20 -> en orden: " + bst.inOrder() + ", tamaño: " + bst.size());

        BinarySearchTree<Integer> degenerate = new BinarySearchTree<>();
        for (int i = 1; i <= 8; i++) {
            degenerate.insert(i);
        }
        LOGGER.info("Insertar datos ya ordenados 1..8 -> altura " + degenerate.height()
                + " (se vuelve una lista: O(n)); con el árbol anterior de 8 nodos la altura era 4");

        GeneralTree<String> folders = new GeneralTree<>("proyecto/")
                .add(new GeneralTree<>("src/").add(new GeneralTree<>("Main.java")).add(new GeneralTree<>("App.java")))
                .add(new GeneralTree<>("pom.xml"))
                .add(new GeneralTree<>("docs/").add(new GeneralTree<>("README.md")));
        StringBuilder sb = new StringBuilder();
        folders.print("", sb);
        LOGGER.info("Árbol general (carpetas):");
        LOGGER.info(String.valueOf(sb));
        LOGGER.info("Nodos: " + folders.count() + ", altura: " + folders.height());
    }
}
