package com.juniverse.datastructures.tree;

/** Demostración de árboles: BST (inserción, búsqueda, borrado y recorridos) y árbol general. */
public final class TreeDemo {

    private TreeDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        for (int n : new int[]{50, 30, 70, 20, 40, 60, 80, 35}) {
            bst.insert(n);
        }
        System.out.println("Árbol binario de búsqueda (la raíz está a la izquierda):");
        System.out.print(bst.draw());
        System.out.println("Tamaño: " + bst.size() + ", altura: " + bst.height() + ", mín: " + bst.min() + ", máx: " + bst.max());
        System.out.println("En orden (ordenado): " + bst.inOrder());
        System.out.println("Preorden:            " + bst.preOrder());
        System.out.println("Postorden:           " + bst.postOrder());
        System.out.println("Por niveles:         " + bst.levelOrder());
        System.out.println("contains(60) = " + bst.contains(60) + ", contains(65) = " + bst.contains(65));
        bst.remove(30); // nodo con dos hijos: lo reemplaza su sucesor (35)
        bst.remove(20); // hoja
        System.out.println("Tras borrar 30 y 20 -> en orden: " + bst.inOrder() + ", tamaño: " + bst.size());

        BinarySearchTree<Integer> degenerado = new BinarySearchTree<>();
        for (int i = 1; i <= 8; i++) {
            degenerado.insert(i);
        }
        System.out.println("Insertar datos ya ordenados 1..8 -> altura " + degenerado.height()
                + " (se vuelve una lista: O(n)); con el árbol anterior de 8 nodos la altura era 4");

        GeneralTree<String> carpetas = new GeneralTree<>("proyecto/")
                .add(new GeneralTree<>("src/").add(new GeneralTree<>("Main.java")).add(new GeneralTree<>("App.java")))
                .add(new GeneralTree<>("pom.xml"))
                .add(new GeneralTree<>("docs/").add(new GeneralTree<>("README.md")));
        StringBuilder sb = new StringBuilder();
        carpetas.print("", sb);
        System.out.println("Árbol general (carpetas):");
        System.out.print(sb);
        System.out.println("Nodos: " + carpetas.count() + ", altura: " + carpetas.height());
    }
}
