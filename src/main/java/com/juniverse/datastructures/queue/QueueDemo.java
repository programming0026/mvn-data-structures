package com.juniverse.datastructures.queue;

/** Demostración de colas: circular de capacidad fija y enlazada, con un recorrido por niveles de ejemplo. */
public final class QueueDemo {

    private QueueDemo() {
    }

    /** Ejecuta la demostración. */
    public static void run() {
        CircularQueue<String> circular = new CircularQueue<>(3);
        System.out.println("Cola circular (capacidad 3): enqueue A,B,C,D -> "
                + circular.enqueue("A") + ", " + circular.enqueue("B") + ", " + circular.enqueue("C") + ", " + circular.enqueue("D") + " (la cuarta no cabe)");
        System.out.println("dequeue -> " + circular.dequeue() + "; ahora cabe otro: enqueue E -> " + circular.enqueue("E")
                + " (reutiliza el hueco dando la vuelta)");
        StringBuilder sb = new StringBuilder();
        while (circular.size() > 0) {
            sb.append(circular.dequeue()).append(' ');
        }
        System.out.println("Orden de salida (FIFO): " + sb.toString().trim());

        LinkedQueue<Integer> enlazada = new LinkedQueue<>();
        for (int i = 1; i <= 5; i++) {
            enlazada.enqueue(i * 10);
        }
        System.out.println("Cola enlazada: tamaño " + enlazada.size() + ", dequeue = " + enlazada.dequeue() + ", " + enlazada.dequeue());

        // Ejemplo de uso real: turnos de atención
        LinkedQueue<String> turnos = new LinkedQueue<>();
        turnos.enqueue("Ana");
        turnos.enqueue("Beto");
        turnos.enqueue("Carla");
        while (!turnos.isEmpty()) {
            System.out.println("  Atendiendo a " + turnos.dequeue());
        }
    }
}
