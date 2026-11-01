package com.juniverse.datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.juniverse.datastructures.linkedlist.DoublyLinkedList;
import com.juniverse.datastructures.linkedlist.LinkedSequence;
import com.juniverse.datastructures.linkedlist.SinglyLinkedList;
import com.juniverse.datastructures.queue.CircularQueue;
import com.juniverse.datastructures.queue.LinkedQueue;
import com.juniverse.datastructures.queue.Queue;
import com.juniverse.datastructures.stack.ArrayStack;
import com.juniverse.datastructures.stack.LinkedStack;
import com.juniverse.datastructures.stack.Stack;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Pruebas por contrato: la misma prueba se ejecuta con cada implementación de la interfaz, porque todas deben
 * comportarse igual (principio de sustitución de Liskov).
 */
class ContractsTest {

    static Stream<Supplier<Stack<Integer>>> stacks() {
        return Stream.of(ArrayStack::new, LinkedStack::new);
    }

    static Stream<Supplier<Queue<Integer>>> queues() {
        return Stream.of(LinkedQueue::new, () -> new CircularQueue<>(10));
    }

    static Stream<Supplier<LinkedSequence<Integer>>> sequences() {
        return Stream.of(SinglyLinkedList::new, DoublyLinkedList::new);
    }

    @ParameterizedTest
    @MethodSource("stacks")
    void laPilaSaleEnOrdenInverso(Supplier<Stack<Integer>> factory) {
        Stack<Integer> stack = factory.get();
        assertTrue(stack.isEmpty());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.size());
        assertEquals(3, stack.peek());
        assertEquals(3, stack.size());
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("stacks")
    void unaPilaVaciaLanzaEmptyStackException(Supplier<Stack<Integer>> factory) {
        Stack<Integer> stack = factory.get();
        assertThrows(EmptyStackException.class, stack::pop);
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @ParameterizedTest
    @MethodSource("queues")
    void laColaSaleEnElOrdenDeEntrada(Supplier<Queue<Integer>> factory) {
        Queue<Integer> queue = factory.get();
        assertTrue(queue.isEmpty());
        assertTrue(queue.enqueue(1));
        assertTrue(queue.enqueue(2));
        assertTrue(queue.enqueue(3));
        assertEquals(3, queue.size());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("queues")
    void unaColaVaciaLanzaNoSuchElementException(Supplier<Queue<Integer>> factory) {
        assertThrows(NoSuchElementException.class, () -> factory.get().dequeue());
    }

    @org.junit.jupiter.api.Test
    void laColaCircularRechazaElementosCuandoEstaLlena() {
        CircularQueue<Integer> queue = new CircularQueue<>(2);
        assertTrue(queue.enqueue(1));
        assertTrue(queue.enqueue(2));
        assertFalse(queue.enqueue(3));
        assertTrue(queue.isFull());
        queue.dequeue();
        assertTrue(queue.enqueue(3)); // el espacio liberado se reutiliza (circular)
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @ParameterizedTest
    @MethodSource("sequences")
    void laSecuenciaEnlazadaAgregaYQuitaEnLosExtremos(Supplier<LinkedSequence<Integer>> factory) {
        LinkedSequence<Integer> list = factory.get();
        list.addLast(2);
        list.addLast(3);
        list.addFirst(1);
        List<Integer> seen = new ArrayList<>();
        list.forEach(seen::add);
        assertEquals(List.of(1, 2, 3), seen);
        assertEquals(3, list.size());
        assertEquals(1, list.removeFirst());
        assertEquals(2, list.size());
    }

    @ParameterizedTest
    @MethodSource("sequences")
    void quitarDeUnaSecuenciaVaciaLanzaNoSuchElementException(Supplier<LinkedSequence<Integer>> factory) {
        assertThrows(NoSuchElementException.class, () -> factory.get().removeFirst());
    }
}
