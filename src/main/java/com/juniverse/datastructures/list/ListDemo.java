package com.juniverse.datastructures.list;


import java.util.logging.Logger;
import com.juniverse.datastructures.AppLogger;
/** Demostración de la lista con arreglo dinámico. */
public final class ListDemo {
    private static final Logger LOGGER = AppLogger.get(ListDemo.class);


    private ListDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        DynamicArray<String> list = new DynamicArray<>();
        for (String s : new String[]{"a", "b", "c", "d", "e"}) {
            list.add(s);
        }
        LOGGER.info("Lista: " + list + " (tamaño " + list.size() + ", capacidad " + list.capacity() + " -> creció al llenarse)");
        list.add(2, "X");
        LOGGER.info("add(2, \"X\"): " + list + "  (se desplazaron los elementos a la derecha)");
        LOGGER.info("get(3) = " + list.get(3) + ", indexOf(\"d\") = " + list.indexOf("d"));
        LOGGER.info("remove(0) = " + list.remove(0) + " -> " + list);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s.toUpperCase());
        }
        LOGGER.info("Recorrido con for-each (Iterable): " + sb);
        try {
            list.get(99);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Fuera de rango: " + e.getMessage());
        }
    }
}
