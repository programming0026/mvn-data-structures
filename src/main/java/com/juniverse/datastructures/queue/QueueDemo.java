package com.juniverse.datastructures.queue;


import java.util.logging.Logger;
import com.juniverse.datastructures.AppLogger;
/** Demostración de colas: circular de capacidad fija y enlazada, con un recorrido por niveles de ejemplo. */
public final class QueueDemo {
    private static final Logger LOGGER = AppLogger.get(QueueDemo.class);


    private QueueDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        CircularQueue<String> circular = new CircularQueue<>(3);
        LOGGER.info("Cola circular (capacidad 3): enqueue A,B,C,D -> "
                + circular.enqueue("A") + ", " + circular.enqueue("B") + ", " + circular.enqueue("C") + ", " + circular.enqueue("D") + " (la cuarta no cabe)");
        LOGGER.info("dequeue -> " + circular.dequeue() + "; ahora cabe otro: enqueue E -> " + circular.enqueue("E")
                + " (reutiliza el hueco dando la vuelta)");
        StringBuilder sb = new StringBuilder();
        while (circular.size() > 0) {
            sb.append(circular.dequeue()).append(' ');
        }
        LOGGER.info("Orden de salida (FIFO): " + sb.toString().trim());

        LinkedQueue<Integer> linked = new LinkedQueue<>();
        for (int i = 1; i <= 5; i++) {
            linked.enqueue(i * 10);
        }
        LOGGER.info("Cola enlazada: tamaño " + linked.size() + ", dequeue = " + linked.dequeue() + ", " + linked.dequeue());

        // Ejemplo de uso real: turnos de atención
        LinkedQueue<String> shifts = new LinkedQueue<>();
        shifts.enqueue("Ana");
        shifts.enqueue("Beto");
        shifts.enqueue("Carla");
        while (!shifts.isEmpty()) {
            LOGGER.info("  Atendiendo a " + shifts.dequeue());
        }
    }
}
