package com.juniverse.datastructures.list;

/** Demostración de la lista con arreglo dinámico. */
public final class ListDemo {

    private ListDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        DynamicArray<String> lista = new DynamicArray<>();
        for (String s : new String[]{"a", "b", "c", "d", "e"}) {
            lista.add(s);
        }
        System.out.println("Lista: " + lista + " (tamaño " + lista.size() + ", capacidad " + lista.capacity() + " -> creció al llenarse)");
        lista.add(2, "X");
        System.out.println("add(2, \"X\"): " + lista + "  (se desplazaron los elementos a la derecha)");
        System.out.println("get(3) = " + lista.get(3) + ", indexOf(\"d\") = " + lista.indexOf("d"));
        System.out.println("remove(0) = " + lista.remove(0) + " -> " + lista);
        StringBuilder sb = new StringBuilder();
        for (String s : lista) {
            sb.append(s.toUpperCase());
        }
        System.out.println("Recorrido con for-each (Iterable): " + sb);
        try {
            lista.get(99);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Fuera de rango: " + e.getMessage());
        }
    }
}
